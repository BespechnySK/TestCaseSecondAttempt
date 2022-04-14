package com.testcase.testracers.view;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

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
        this.img.setImage(new Image(getClass().getResourceAsStream("moto.jpg")));
    }

    public boolean getSidecar(){
        return this.side.isSelected();
    }
}
