/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author Oumayma Gaiech
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Button id_authen;
    @FXML
    private Button id_creerCompte;
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField id_email;
    @FXML
    private PasswordField id_mdp;
    private Object email;
    private Object mdp;
    @FXML
    private Button id_mdp_oublier;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void authen(ActionEvent event)throws SQLException, IOException {
         UserCRUD uc =new UserCRUD(),USER=new UserCRUD();
        if(uc.verifierLogin(id_email.getText(),id_mdp.getText()))
        {
            User U=new User();
            U=uc.getUser(id_email.getText());
            System.out.println("Connected");
            if(U.getRole().equals("Simple Client") || U.getRole().equals("Agence"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                fxml=loader.load(); 
                HomeController homecontroller=loader.getController();
                U=USER.getUser(U.getIdUser());
                homecontroller.setUser(U);
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(fxml);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();}
            else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show();}
        }
        else{
            System.out.println("Not connected");}
    }
     
    @FXML
     private void creerCompte(ActionEvent event) throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreerCompte.fxml"));
        fxml=loader.load(); 
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene((Parent) fxml);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show(); 
    }

    @FXML
    private void mdp_oublier(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MotDePasseOublier.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene((Parent) fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show(); 
    }
}

    
    

