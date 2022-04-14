package com.testcase.testracers.logic;

public class Car extends Auto{

    private int peoples;


    public Car(double speed, byte blowChance, String name, int peoples) {
        super(speed, blowChance, name);
        this.peoples = peoples;
    }

    public int getPeoples() {
        return peoples;
    }

    public void setPeoples(int peoples) {
        this.peoples = peoples;
    }


    @Override
    public String getStartInfo() {
        return  this.getBaseInfo() +
                " В машине " + this.getPeoples();
    }
}
