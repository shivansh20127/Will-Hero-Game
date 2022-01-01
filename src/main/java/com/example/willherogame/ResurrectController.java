package com.example.willherogame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.events.Event;

import java.net.URL;
import java.util.ResourceBundle;

public class ResurrectController implements Initializable
{
    private static boolean status;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status = false;
    }
    
    @FXML
    public void clickYes(ActionEvent event) {
        status = true;
    }
//
    public void clickNo(ActionEvent event) {
        status = false;
    }
    
    public static boolean getStatus() { return status; }
}
