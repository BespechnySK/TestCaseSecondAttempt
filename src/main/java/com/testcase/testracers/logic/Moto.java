package com.testcase.testracers.logic;

public class Moto extends Auto{

    String sideCar;

    public Moto(double speed, byte blowChance, String name, boolean sideCar) {
        super(speed, blowChance, name);
        if (sideCar){
            this.sideCar=" С коляской";
        } else {
            this.sideCar = " без коляски";
        }
    }

    public Moto() {
    }

    public String getSideCar() {
        return sideCar;
    }

    public void setSideCar(String sideCar) {
        this.sideCar = sideCar;
    }


    @Override
    public String getStartInfo() {

        return getBaseInfo()
                +this.sideCar;
    }
}
