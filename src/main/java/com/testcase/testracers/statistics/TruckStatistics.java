package com.testcase.testracers.statistics;

public class TruckStatistics extends AutoStatistics{
    public int cargo;

    public TruckStatistics() {
    }

    public TruckStatistics(String name, int speed, int blow, int place, int cargo) {
        super(name, speed, blow, place);
        this.cargo = cargo;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
}
