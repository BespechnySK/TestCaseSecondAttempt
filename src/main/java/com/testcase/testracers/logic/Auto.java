package com.testcase.testracers.logic;

abstract public class Auto implements Racer {
    protected double speed;
    protected byte blowChance;
    protected double distance;
    protected int place;
    protected String name;
    protected boolean blow;
    protected int repairTime;
    protected int repairProgress;
    protected RacerInfo racerInfo;


    public Auto() {
    }

    public Auto(double speed, byte blowChance, String name) {
        this.speed = speed;
        this.blowChance = blowChance;
        this.name = name;
        this.repairTime = 15;
        this.repairProgress=0;
        this.blow=false;
        this.racerInfo = new RacerInfo(this);
    }

    public void setBlow(boolean blow) {
        this.blow = blow;
    }

    public int getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(int repairTime) {
        this.repairTime = repairTime;
    }

    public boolean isBlow() {
        return blow;
    }

    public void blow() {
        this.blow = true;
    }

    public void repair(){
        this.repairProgress--;
        if (this.repairProgress<=0){
            this.blow=false;
        }
    }
    @Override
    public RacerInfo getRaceInfo() {
        return this.racerInfo;
    }

    public void move(){
        if(this.chekBlow()){
            this.setRepairProgress(this.getRepairTime());
            this.blow=true;
        } else {
            this.setDistance(this.getDistance()+this.getSpeed()/10);
        }
    }
    public void step() {
        System.out.println("----");
        if (this.isBlow()){
            System.out.println("repair");
            this.repair();
        } else {
            System.out.println("move");
            this.move();
        };
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getBlowChance() {
        return blowChance;
    }

    public void setBlowChance(byte blowChance) {
        this.blowChance = blowChance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepairProgress() {
        return repairProgress;
    }

    public void setRepairProgress(int repairProgress) {
        this.repairProgress = repairProgress;
    }

    protected boolean chekBlow(){
        return Math.random()*100 < this.blowChance;
    }

    protected String getBaseInfo(){
        return this.getName() +
                " Скорость:" + this.getSpeed() +
                " Шанс прокола:" + this.getBlowChance();
    }

}
