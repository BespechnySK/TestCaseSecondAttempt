package com.testcase.testracers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.testracers.circleView.RacerDot;
import com.testcase.testracers.logic.*;
import com.testcase.testracers.pars.JsonToCar;
import com.testcase.testracers.pars.JsonToMoto;
import com.testcase.testracers.pars.JsonToRacers;
import com.testcase.testracers.pars.JsonToTruck;
import com.testcase.testracers.statistics.Statistics;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
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
    ArrayList<AutoView> autos;
    ArrayList<RacerDot> dots;
    Race race;
    Timeline timeline;
    KeyFrame keyFrame;
    int carsCount=1;
    int x;
    int y;
    int r;

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
            carView.cancel.setOnAction(e->{
                cars.remove(carView);
                racersPane.getChildren().remove(carView);
            });
            cars.add(carView);
            racersPane.getChildren().add(carView);
        }
        for (JsonToTruck truck: Objects.requireNonNull(racersFromJson).getTrucks()
        ) {
            TruckView truckView = new TruckView();
            truckView.setFromJsonToTruck(truck);
            truckView.cancel.setOnAction(e->{
                cars.remove(truckView);
                racersPane.getChildren().remove(truckView);
            });
            trucks.add(truckView);
            racersPane.getChildren().add(truckView);
        }
        for (JsonToMoto moto: Objects.requireNonNull(racersFromJson).getMotos()
        ) {
            MotoView motoView = new MotoView();
            motoView.setFromJsonToMoto(moto);
            motoView.cancel.setOnAction(e->{
                cars.remove(motoView);
                racersPane.getChildren().remove(motoView);
            });
            motos.add(motoView);
            racersPane.getChildren().add(motoView);
        }
        raceLenght.setText(String.valueOf(racersFromJson.getLenght()));

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
        autos.addAll(cars);
        autos.addAll(trucks);
        autos.addAll(motos);
        boolean start = true;
        for (AutoView av : autos
        ) {
            if (!av.ckeckFool()) {
                av.setStyle("-fx-background-color:RED");
                start = false;
            } else {
                av.setStyle("-fx-background-color:GREEN");
            }
        }

        if (raceLenght.getText().isEmpty()){
            start=false;
            raceLenght.setStyle("-fx-background-color:red");
        } else {
            raceLenght.setStyle("-fx-background-color:green");
        }

        if (start) {
            race.setRaceDistance(Double.parseDouble(raceLenght.getText()));

            for (CarView carv : cars
            ) {

                race.add(new Car(carv.getSpeed(), (byte) carv.getChansBlow(), "Машина" + carsCount++, carv.getPeoples()));
                carv.beforeStart(race.getRacers().get(race.getRacers().size() - 1));
                dots.add(new RacerDot(race.getRacers().get(race.getRacers().size() - 1),race));
                dots.get(dots.size()-1).setRaceCenter(x,y,r);
                circlePane.getChildren().add(dots.get(dots.size()-1));
            }
            for (TruckView truckv : trucks
            ) {
                race.add(new Truck(truckv.getSpeed(), (byte) truckv.getChansBlow(), "Грузовик" + carsCount++, truckv.getCargo()));
                truckv.beforeStart(race.getRacers().get(race.getRacers().size() - 1));
                dots.add(new RacerDot(race.getRacers().get(race.getRacers().size() - 1),race));
                dots.get(dots.size()-1).setRaceCenter(x,y,r);
                circlePane.getChildren().add(dots.get(dots.size()-1));
            }
            for (MotoView motov : motos
            ) {
                race.add(new Moto(motov.getSpeed(), (byte) motov.getChansBlow(), "Мотоцикл" + carsCount++, motov.getSidecar()));
                motov.beforeStart(race.getRacers().get(race.getRacers().size() - 1));
                dots.add(new RacerDot(race.getRacers().get(race.getRacers().size() - 1),race));
                dots.get(dots.size()-1).setRaceCenter(x,y,r);
                circlePane.getChildren().add(dots.get(dots.size()-1));
            }
            labelSpeed.setText("Имя гонщика");
            labelBlow.setText("Расстояние");
            labelBlow.setLayoutY(labelSpeed.getLayoutY());
            labelBlow.setLayoutX(labelBlow.getLayoutX() + 20);
            labelInfo.setText("");
            timeline.play();

        } else {
            System.out.println("----------");
        }
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
        for (RacerDot dott:dots
             ) {
            dott.nextStep();
        }
    }
    @FXML
    void newRace(ActionEvent event) {

        race.newRace();
        placeTable.getItems().clear();
        for (AutoView aut: autos
             ) {
            aut.raceAgain();
        }

    }

    public void endRace(){

        placeTable.setItems(race.getFinishers());
        timeline.stop();
        Statistics statistics = new Statistics(carsCount-1,cars.size(),trucks.size(),motos.size(),(int)Math.round(race.getRaceDistance()));

        for (Auto auto: race.getFinishers()
             ) {
           if (auto.getClass().equals(com.testcase.testracers.logic.Car.class)){
               Car car = (Car) auto;
               statistics.addCar(car);

           }
            if (auto.getClass().equals(com.testcase.testracers.logic.Truck.class)){
                Truck truck = (Truck) auto;
                statistics.addTruck(truck);
            }
            if (auto.getClass().equals(com.testcase.testracers.logic.Moto.class)){
                Moto moto = (Moto) auto;
                statistics.addMoto(moto);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        Date currentDate = new Date();
        try {
            mapper.writeValue(new File("race_"+System.currentTimeMillis()/1000+".json"),statistics);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void drawRace(){
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(r+10);
        circle.setFill(Color.BLACK);
        Circle circle1 = new Circle();
        circle1.setCenterX(x);
        circle1.setCenterY(y);
        circle1.setRadius(r-10);
        circle1.setFill(Color.GRAY);
        circlePane.getChildren().add(circle);
        circlePane.getChildren().add(circle1);
    }

    public void initialize(){
        this.x=150;//(int)Math.round(circlePane.getPrefWidth()/2);
        this.y=130;//(int)Math.round(circlePane.getPrefHeight()/2);
        this.r=80;
        drawRace();
        dots = new ArrayList<>();
        cars=new ArrayList<>();
        trucks=new ArrayList<>();
        motos=new ArrayList<>();
        race=new Race();

        keyFrame=new KeyFrame(Duration.millis(100), e->{
           if (race.raceStep()){
            updateViews();
        } else {
               endRace();
               updateViews();

           }
        }
           );

        timeline= new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);

        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        racerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        autos = new ArrayList<>();
        raceLenght.textProperty().addListener((observableValue, s, t1) -> {
            if (!Pattern.matches("\\d{0,6}",t1)){
                raceLenght.setText(s);
            }
        });

    }

}
