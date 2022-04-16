package com.testcase.testracers.pars;

public class JsonToMoto extends JsonToAuto{
    boolean side;

    public JsonToMoto() {
    }

    public JsonToMoto(double speed, double blow, boolean side) {
        super(speed, blow);
        this.side = side;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }
}
