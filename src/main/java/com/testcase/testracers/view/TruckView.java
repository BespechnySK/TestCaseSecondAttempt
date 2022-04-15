package com.testcase.testracers.view;

import com.testcase.testracers.logic.Racer;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.Objects;

public class TruckView extends AutoView {
    TextField cargo;

    public TruckView() {
        super();
        this.cargo = new TextField();
        draw();
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
}
