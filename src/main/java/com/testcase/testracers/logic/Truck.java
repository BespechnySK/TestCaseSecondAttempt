package com.testcase.testracers.logic;

public class Truck extends Auto{

    double cargo;


    public Truck() {

    }

    public Truck(double speed, byte blowChance, String name, double cargo) {
        super(speed, blowChance, name);
        this.cargo = cargo;
    }


    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }


    @Override
    public String getStartInfo() {
        return getBaseInfo()
                +"Масса груза: "+ this.getCargo();
    }
}
