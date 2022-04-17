package com.testcase.testracers.statistics;

import com.testcase.testracers.logic.Car;
import com.testcase.testracers.logic.Moto;
import com.testcase.testracers.logic.Truck;

import java.util.ArrayList;

public class Statistics {
   public int autoCount;
    public int carsCount;
    public int trucksCount;
    public int motosCount;
    public int raceLength;
    public ArrayList<CarStatistics> cars;
    public ArrayList<TruckStatistics> trucks;
    public ArrayList<MotoStatistics> motos;

    public Statistics(int autoCount, int carsCount, int trucksCount, int motosCount, int raceLength) {
        this.autoCount = autoCount;
        this.carsCount = carsCount;
        this.trucksCount = trucksCount;
        this.motosCount = motosCount;
        this.raceLength = raceLength;
        this.cars = new ArrayList<>();
        this.trucks = new ArrayList<>();
        this.motos = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(new CarStatistics(car.getName(),(int)Math.round(car.getSpeed()),(int)Math.round(car.getBlowChance()),car.getPlace(),car.getPeoples()));
    }
    public void addTruck(Truck truc){
        trucks.add(new TruckStatistics(truc.getName(),(int)Math.round(truc.getSpeed()),(int)Math.round(truc.getBlowChance()),truc.getPlace(),(int)Math.round(truc.getCargo())));
    }
    public void addMoto(Moto moto){
        motos.add(new MotoStatistics(moto.getName(),(int)Math.round(moto.getSpeed()),(int)Math.round(moto.getBlowChance()),moto.getPlace(),moto.getSideCar()));
    }

    public int getAutoCount() {
        return autoCount;
    }

    public void setAutoCount(int autoCount) {
        this.autoCount = autoCount;
    }

    public int getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }

    public int getTrucksCount() {
        return trucksCount;
    }

    public void setTrucksCount(int trucksCount) {
        this.trucksCount = trucksCount;
    }

    public int getMotosCount() {
        return motosCount;
    }

    public void setMotosCount(int motosCount) {
        this.motosCount = motosCount;
    }

    public int getRaceLength() {
        return raceLength;
    }

    public void setRaceLength(int raceLength) {
        this.raceLength = raceLength;
    }

    public ArrayList<CarStatistics> getCars() {
        return cars;
    }

    public void setCars(ArrayList<CarStatistics> cars) {
        this.cars = cars;
    }

    public ArrayList<TruckStatistics> getTrucks() {
        return trucks;
    }

    public void setTrucks(ArrayList<TruckStatistics> trucks) {
        this.trucks = trucks;
    }

    public ArrayList<MotoStatistics> getMotos() {
        return motos;
    }

    public void setMotos(ArrayList<MotoStatistics> motos) {
        this.motos = motos;
    }
}
