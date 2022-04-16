package com.testcase.testracers.pars;

public class JsonToCar extends JsonToAuto{
    int pass;

    public JsonToCar() {
    }

    public JsonToCar(double speed, double blow, byte pass) {
        super(speed, blow);
        setPass(pass);
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {

        if (pass>5) {
            this.pass=5;
        }
        else this.pass = Math.max(pass, 1);

    }
}
