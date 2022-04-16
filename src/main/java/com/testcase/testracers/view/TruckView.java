package com.testcase.testracers.view;

import com.testcase.testracers.logic.Racer;
import com.testcase.testracers.pars.JsonToTruck;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.Objects;
import java.util.regex.Pattern;

public class TruckView extends AutoView {
    TextField cargo;

    public TruckView() {
        super();
        draw();
    }

    public void setFromJsonToTruck(JsonToTruck truck){
        super.setInfo(truck.getSpeed(), truck.getBlow());
        this.cargo.setText(String.format("%.0f",truck.getCargo()));

    }
    @Override
    protected void draw() {
        super.draw();
        this.getChildren().add(cargo);
        this.cargo.setLayoutX(155);
        this.cargo.setLayoutY(10);
        this.cargo.setPrefWidth(60);
        this.img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("truck.jpg"))));
    }
    public int getCargo(){
        return Integer.parseInt(cargo.getText());
    }

    @Override
    public void beforeStart(Racer racer) {
        super.beforeStart(racer);
        this.getChildren().remove(cargo);
    }


    @Override
    public void regEx() {
        super.regEx();
        this.cargo = new TextField();
        cargo.textProperty().addListener((observableValue, s, t1) -> {
            if(!Pattern.matches("\\d{0,4}",t1)){
                cargo.setText(s);
            }
        });
    }
}
