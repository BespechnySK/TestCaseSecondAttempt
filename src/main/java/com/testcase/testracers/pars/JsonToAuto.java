package com.testcase.testracers.pars;

public class JsonToAuto {
    double speed;
    double blow;


    public JsonToAuto() {
    }

    public JsonToAuto(double speed, double blow) {
        this.speed = speed;
        this.blow = blow;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getBlow() {
        return blow;
    }

    public void setBlow(double blow) {
        this.blow = blow;
    }
}
