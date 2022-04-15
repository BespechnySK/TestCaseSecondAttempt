package com.testcase.testracers.view;

import com.testcase.testracers.logic.Racer;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

import java.util.Objects;

public class MotoView extends AutoView{
    CheckBox side;

    public MotoView() {
        super();
        this.side=new CheckBox();
        draw();
    }

    @Override
    protected void draw() {
        super.draw();
        this.getChildren().add(side);
        this.side.setLayoutX(155);
        this.side.setLayoutY(10);
        this.img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("moto.jpg"))));
    }

    public boolean getSidecar(){
        return this.side.isSelected();
    }

    @Override
    public void beforeStart(Racer racer) {
        super.beforeStart(racer);
        this.getChildren().remove(side);
    }
}
