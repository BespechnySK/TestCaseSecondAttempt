package com.testcase.testracers.circleView;

import com.testcase.testracers.logic.Race;
import com.testcase.testracers.logic.Racer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RacerDot extends Group {
    double x;
    double y;
    double k; // коэффициент для ползиции на круге;
    double angle;
    double r;
    Racer racer;
    Circle dot;
    Label label;

    public RacerDot(Racer racer, Race race) {
        dot= new Circle();
        label= new Label(racer.getRaceInfo().getName());
        dot.setRadius(5);
        if (racer.getClass().equals(com.testcase.testracers.logic.Car.class)){
            dot.setFill(Color.BLUE);
        }
        if (racer.getClass().equals(com.testcase.testracers.logic.Truck.class)){
            dot.setFill(Color.RED);
        }
        if (racer.getClass().equals(com.testcase.testracers.logic.Moto.class)){
            dot.setFill(Color.GREEN);
        }
        this.k=race.getRaceDistance()/(3.14*2);
        this.angle=0;
        this.racer=racer;
        this.getChildren().addAll(dot,label);
    }


    public void setRaceCenter(int x,int y, int r){
        this.x=x;
        this.y=y;
        this.r=r;


    }

    public void nextStep(){
        this.angle=racer.getRaceInfo().getDistance()/k;
        this.setLayoutX(x+r*Math.cos(angle));
        this.setLayoutY(y+r*Math.sin(angle));

    }

}
