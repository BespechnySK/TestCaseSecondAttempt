package com.testcase.testracers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.testracers.logic.*;
import com.testcase.testracers.pars.JsonToCar;
import com.testcase.testracers.pars.JsonToMoto;
import com.testcase.testracers.pars.JsonToRacers;
import com.testcase.testracers.pars.JsonToTruck;
import com.testcase.testracers.view.AutoView;
import com.testcase.testracers.view.CarView;
import com.testcase.testracers.view.MotoView;
import com.testcase.testracers.view.TruckView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;



public class RaceController {

    @FXML
    private Label labelBlow;

    @FXML
    private Label labelInfo;

    @FXML
    private Label labelSpeed;

    @FXML
    private VBox racersPane;


    @FXML
    private Pane circlePane;

    @FXML
    private TableColumn<Auto, Integer> placeColumn;

    @FXML
    private TableView<Auto> placeTable;

    @FXML
    private TableColumn<Auto, String> racerColumn;
    @FXML
    private TextField raceLenght;

    ArrayList<CarView> cars;
    ArrayList<TruckView> trucks;
    ArrayList<MotoView> motos;
    Race race;
    Timeline timeline;
    KeyFrame keyFrame;

    @FXML
    void fromFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Выбрать файл");
        File file = chooser.showOpenDialog(new Stage());
        String fromFile;
        try {
            fromFile = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            jsonToViews(fromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void jsonToViews(String json){

        ObjectMapper mapper = new ObjectMapper();
        JsonToRacers racersFromJson=null;
        try {
           racersFromJson = mapper.readValue(json,JsonToRacers.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        for (JsonToCar car: Objects.requireNonNull(racersFromJson).getCars()
             ) {
            CarView carView = new CarView();
            carView.setFromJsonToCar(car);
            cars.add(carView);
            racersPane.getChildren().add(carView);
        }
        for (JsonToTruck truck: Objects.requireNonNull(racersFromJson).getTrucks()
        ) {
            TruckView truckView = new TruckView();
            truckView.setFromJsonToTruck(truck);
            trucks.add(truckView);
            racersPane.getChildren().add(truckView);
        }
        for (JsonToMoto moto: Objects.requireNonNull(racersFromJson).getMotos()
        ) {
            MotoView motoView = new MotoView();
            motoView.setFromJsonToMoto(moto);
            motos.add(motoView);
            racersPane.getChildren().add(motoView);
        }

    }

    @FXML
    void newCar(ActionEvent event) {
          CarView av = new CarView();
          racersPane.getChildren().add(av);
          cars.add(av);
          av.cancel.setOnAction(e->{
              cars.remove(av);
              racersPane.getChildren().remove(av);
          });
    }

    @FXML
    void newMoto(ActionEvent event) {
        MotoView mv = new MotoView();
        racersPane.getChildren().add(mv);
        motos.add(mv);
        mv.cancel.setOnAction(e->{
            motos.remove(mv);
            racersPane.getChildren().remove(mv);
        });
    }

    @FXML
    void newTruck(ActionEvent event) {
        TruckView tv = new TruckView();
        racersPane.getChildren().add(tv);
        trucks.add(tv);
        tv.cancel.setOnAction(e->{
            trucks.remove(tv);
            racersPane.getChildren().remove(tv);
        });
    }
    @FXML
    void startRace(ActionEvent event) {
    race.setRaceDistance(Double.parseDouble(raceLenght.getText()));
        int carsCount=1;
        for (CarView carv:cars
             ) {
            race.add(new Car(carv.getSpeed(),(byte)carv.getChansBlow(),"Машина"+carsCount++,carv.getPeoples()));
            carv.beforeStart(race.getRacers().get(race.getRacers().size()-1));
        }
        for (TruckView truckv:trucks
        ) {
            race.add(new Truck(truckv.getSpeed(),(byte)truckv.getChansBlow(),"Грузовик"+carsCount++,truckv.getCargo()));
            truckv.beforeStart(race.getRacers().get(race.getRacers().size()-1));
        }
        for (MotoView motov:motos
        ) {
            race.add(new Moto(motov.getSpeed(),(byte)motov.getChansBlow(),"Мотоцикл"+carsCount++,motov.getSidecar()));
            motov.beforeStart(race.getRacers().get(race.getRacers().size()-1));
        }
        labelSpeed.setText("Имя гонщика");
        labelBlow.setText("Расстояние");
        labelBlow.setLayoutY(labelSpeed.getLayoutY());
        labelBlow.setLayoutX(labelBlow.getLayoutX()+20);
        labelInfo.setText("");
        timeline.play();

    }


    void updateViews(){

        for (MotoView motov:motos
             ) {
            motov.updateInfo();
        }
        for (CarView carv:cars
        ) {
            carv.updateInfo();
        }
        for (TruckView truckv:trucks
        ) {
            truckv.updateInfo();
        }
    }
    @FXML
    void clearRace(ActionEvent event) {

        System.out.println(race.getRacers().get(0).getRaceInfo().getDistance());

    }

    public void endRace(){

        placeTable.setItems(race.getFinishers());
        timeline.stop();
    }

    public void initialize(){

        cars=new ArrayList<>();
        trucks=new ArrayList<>();
        motos=new ArrayList<>();
        race=new Race();

        keyFrame=new KeyFrame(Duration.millis(100), e->{
           if (race.raceStep()){
            updateViews();
        } else {
               endRace();


           }
        }
           );

        timeline= new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);

        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        racerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        raceLenght.textProperty().addListener((observableValue, s, t1) -> {
            if (!Pattern.matches("\\d{0,6}",t1)){
                raceLenght.setText(s);
            }
        });

    }

}
