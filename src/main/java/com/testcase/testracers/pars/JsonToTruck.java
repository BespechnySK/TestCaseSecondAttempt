package com.testcase.testracers.pars;

public class JsonToTruck extends JsonToAuto{
    double cargo;

    public JsonToTruck() {

    }

    public JsonToTruck(double speed, double blow, double cargo) {
        super(speed, blow);
        setCargo(cargo);
    }

    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        if (cargo>15000) {
            this.cargo=15000;
        }
        else if (cargo<0) {
            this.cargo=0;
        } else this.cargo = cargo;

    }
}
