package com.testcase.testracers.statistics;

public class AutoStatistics {
    public String name;
    public int speed;
    public int blow;
    public int place;

    public AutoStatistics() {
    }

    public AutoStatistics(String name, int speed, int blow, int place) {
        this.name = name;
        this.speed = speed;
        this.blow = blow;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBlow() {
        return blow;
    }

    public void setBlow(int blow) {
        this.blow = blow;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
