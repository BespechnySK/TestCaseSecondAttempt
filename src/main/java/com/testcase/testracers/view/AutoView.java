package com.testcase.testracers.view;

import com.testcase.testracers.logic.Auto;
import com.testcase.testracers.logic.Racer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

public class AutoView extends Pane {
TextField speed;
TextField blow;
ImageView img;
public Button cancel;
Label info;
Racer racer;

    public AutoView() {
     init();
    }

    public void init(){
        this.speed=new TextField();
        this.blow= new TextField();
        this.img = new ImageView();
        this.cancel=new Button("X");
        this.info=new Label();
        this.regEx();
    }

    protected void setInfo(double speed,double blow){
        this.speed.setText(String.format("%.0f",speed));
        this.blow.setText(String.format("%.0f",blow));
    }

    protected void draw(){
        this.getChildren().addAll(speed,blow,img,cancel);
        this.img.setLayoutX(5);
        this.img.setLayoutY(5);
        this.speed.setLayoutX(60);
        this.speed.setLayoutY(10);
        this.speed.setPrefWidth(40);
        this.blow.setLayoutX(110);
        this.blow.setLayoutY(10);
        this.blow.setPrefWidth(40);
        this.cancel.setLayoutY(10);
        this.cancel.setLayoutX(240);
        this.info.setLayoutX(60);
        this.info.setLayoutY(10);
    }
    public int getSpeed(){
        return Integer.parseInt(this.speed.getText());
    }
    public int getChansBlow(){
        return Integer.parseInt(this.blow.getText());
    }

    public void beforeStart(Racer racer){
        this.racer=racer;
        this.getChildren().remove(speed);
        this.getChildren().remove(blow);
        this.getChildren().remove(cancel);
        this.getChildren().add(info);
        this.updateInfo();
    }
    public void updateInfo(){
        this.info.setText(racer.getRaceInfo().toString());
    }

    public void regEx(){
        speed.textProperty().addListener((observableValue, s, t1) -> {
            if(!Pattern.matches("\\d{0,3}",t1)){
                speed.setText(s);
            }
        });
        blow.textProperty().addListener((observableValue, s, t1) -> {
            if(!Pattern.matches("100|\\d{0,2}",t1)){
                blow.setText(s);
            }
        });
    }

}
