package com.testcase.testracers.pars;

public class JsonToRacers {
    JsonToCar[] cars;
    JsonToMoto[] motos;
    JsonToTruck[] trucks;

    public JsonToRacers() {
    }

    public JsonToRacers(JsonToCar[] cars, JsonToMoto[] motos, JsonToTruck[] trucks) {
        this.cars = cars;
        this.motos = motos;
        this.trucks = trucks;
    }

    public JsonToCar[] getCars() {
        return cars;
    }

    public void setCars(JsonToCar[] cars) {
        this.cars = cars;
    }

    public JsonToMoto[] getMotos() {
        return motos;
    }

    public void setMotos(JsonToMoto[] motos) {
        this.motos = motos;
    }

    public JsonToTruck[] getTrucks() {
        return trucks;
    }

    public void setTrucks(JsonToTruck[] trucks) {
        this.trucks = trucks;
    }
}
