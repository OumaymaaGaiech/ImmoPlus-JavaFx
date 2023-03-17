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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserCRUD;
import utils.Email;

/**
 * FXML Controller class
 *
 * @author Oumayma Gaiech
 */
public class MotDePasseOublierController implements Initializable {

    @FXML
    private Button id_butt_continuer;
    @FXML
    private Button id_butt_annuler;
    private Object fxml;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField id_email_mdp_oublier;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void continuer(ActionEvent event)  throws SQLException, Exception {
        if(id_email_mdp_oublier.getText()!="")
        {
        UserCRUD uc =new UserCRUD();        
        User U=new User(),u=new User();
        U=uc.getUser(id_email_mdp_oublier.getText().toString());
            System.out.println(U);
        if(U.getEmail()!="")
        {
            String newmdp = null;
           System.out.println(U.getEmail());
           uc.generer();
          
             Email.sendMail(U.getEmail(), newmdp);

           
                      
             /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Code.fxml"));
             fxml=loader.load(); 
             CodeController codecontroller=loader.getController();
             u=USER.getUtilisateur(U.getID_UTILISATEUR());
             codecontroller.setUtilisateur(u);

             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(fxml);
             stage.setScene(scene);
             stage.show();*/
           
       }
       else 
       {
           System.out.println("\n ----- Username n'existe pas ------\n");
       }
    }
        }
    

    @FXML
    private void annuler(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene((Parent) fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show(); 
    }
    
}
