package com.testcase.testracers.logic;

import java.util.ArrayList;
import java.util.List;

public class Race {
    ArrayList<Racer> racers;
    ArrayList<Racer> finished;
    ArrayList<Racer> inRace;
    int currentRaceTime;
    double raceDistance;
    int places;

    public Race() {
        this.currentRaceTime = 0;
        this.places=1;
        this.raceDistance=0;
        this.racers=new ArrayList<>();
        this.inRace=new ArrayList<>();
        this.finished= new ArrayList<>();
    }

    public void setRaceDistance(double raceDistance) {
        this.raceDistance = raceDistance;
    }

    public ArrayList<Racer> getRacers() {
        return racers;
    }

    public void setRacers(ArrayList<Racer> racers) {
        this.racers = racers;
    }

    public void newRace(){
        this.finished.clear();
        this.places=1;
        this.currentRaceTime=0;
    }


    public boolean raceStep(){

        if (inRace.isEmpty()){
            return false;
        }

        for (Racer racer: inRace) {
            racer.step();
            if (racer.getRaceInfo().getDistance()>raceDistance){
                racer.getRaceInfo().getAuto().place=this.places++;
                finished.add(racer);
                inRace.remove(racer);
            }
        }
        return true;
    }

    public void add(Racer racer){
        this.racers.add(racer);
        this.inRace.add(racer);
    }

    public void remove(Racer racer){
        this.racers.remove(racer);
    }

    public ArrayList<Racer> getFinished() {
        return finished;
    }
}
