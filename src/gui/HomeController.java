/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Oumayma Gaiech
 */
public class HomeController implements Initializable {

    @FXML
    private Button id_parametres;
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField id_chercher_home;
    @FXML
    private Button id_deconn_home;
    @FXML
    private Label label_userName;
    private User u;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
        void setUser(User U) {
        this.u=U;
        label_userName.setText(u.getFullName());

     
        
    }

    @FXML
    private void parametres(ActionEvent event) throws IOException{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Parametres.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene((Parent) fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show(); 
    }

    @FXML
    private void deconn_home(ActionEvent event)throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene((Parent) fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show(); 
        
    }
}

   
    

