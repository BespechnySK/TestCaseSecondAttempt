package com.testcase.testracers;

import com.testcase.testracers.logic.*;
import com.testcase.testracers.view.AutoView;
import com.testcase.testracers.view.CarView;
import com.testcase.testracers.view.MotoView;
import com.testcase.testracers.view.TruckView;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RaceController {

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

    @FXML
    void fromFile(ActionEvent event) {

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
        race.raceStep();
        updateViews();
        System.out.println(race.getRacers().get(0).getRaceInfo().getDistance());

    }

    public void initialize(){

        cars=new ArrayList<>();
        trucks=new ArrayList<>();
        motos=new ArrayList<>();
        race=new Race();
        timeline= new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

}
