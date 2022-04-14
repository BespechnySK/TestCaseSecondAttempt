package com.testcase.testracers.view;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

public class CarView extends AutoView{
    ComboBox<Integer> pass;

    public CarView() {
        super();
        this.pass = new ComboBox<>();
        pass.getItems().addAll(1,2,3,4,5);
        this.pass.setValue(1);
        draw();
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
        this.img.setImage(new Image(getClass().getResourceAsStream("car.jpg")));
    }
    public int getPeoples(){
        return this.pass.getValue();
    }
}
