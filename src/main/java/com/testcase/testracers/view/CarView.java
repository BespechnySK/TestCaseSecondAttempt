package com.testcase.testracers.view;

import com.testcase.testracers.logic.Race;
import com.testcase.testracers.logic.Racer;
import com.testcase.testracers.pars.JsonToCar;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class CarView extends AutoView{
    ComboBox<Integer> pass;

    public CarView() {
        super();
        this.pass = new ComboBox<>();
        pass.getItems().addAll(1,2,3,4,5);
        this.pass.setValue(1);
        draw();
    }

    public void setFromJsonToCar(JsonToCar car) {
        super.setInfo(car.getSpeed(), car.getBlow());
        this.pass.setValue(car.getPass());
    }

    @Override
    public void raceAgain() {
        super.raceAgain();
        this.getChildren().add(pass);
    }

    @Override
    protected void draw() {
        super.draw();
        this.getChildren().add(pass);
        this.pass.setLayoutX(155);
        this.pass.setLayoutY(10);
        this.pass.setMinWidth(40);
        this.pass.setPrefWidth(50);
        this.pass.setStyle("-fx-padding:0px");
        this.img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("car.jpg"))));
    }
    public int getPeoples(){
        return this.pass.getValue();
    }

    @Override
    public void beforeStart(Racer racer) {
        super.beforeStart(racer);
        this.getChildren().remove(pass);
    }
}
