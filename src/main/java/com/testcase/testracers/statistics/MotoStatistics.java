package com.testcase.testracers.statistics;

public class MotoStatistics extends AutoStatistics {
   public String side;

    public MotoStatistics() {
    }

    public MotoStatistics(String name, int speed, int blow, int place, String side) {
        super(name, speed, blow, place);
        this.side = side;
    }

    public String isSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}
