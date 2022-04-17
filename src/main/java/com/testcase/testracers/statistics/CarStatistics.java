package com.testcase.testracers.statistics;

public class CarStatistics extends AutoStatistics{
   public int pas;

    public CarStatistics() {
    }

    public CarStatistics(String name, int speed, int blow, int place, int pas) {
        super(name, speed, blow, place);
        this.pas = pas;
    }

    public int getPas() {
        return pas;
    }

    public void setPas(int pas) {
        this.pas = pas;
    }


}
