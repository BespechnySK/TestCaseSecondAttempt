package com.testcase.testracers.logic;

public class RacerInfo {
    private Auto auto;

    public RacerInfo(Auto auto) {
        this.auto=auto;
    }

    public double getDistance() {
        return auto.getDistance();
    }

    public String getName() {
        return auto.getName();
    }

    public String isBlow() {
        if (auto.isBlow()){
            return "ПРОКОЛ";
        } else
        return "";
    }
    public Auto getAuto(){
        return this.auto;
    }

}
