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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author Oumayma Gaiech
 */
public class ParametresController implements Initializable {

    @FXML
    private Button id_modifier;
    @FXML
    private Button id_supprimer;
    @FXML
    private TextField id_email_delete;
    @FXML
    private TextField id_fullName_modif;
    @FXML
    private TextField id_email_modif;
    @FXML
    private TextField id_mdp_modif;
    @FXML
    private TextField id_region_modif;
    @FXML
    private TextField id_municipalite_modif;
    @FXML
    private TextField id_telephone_modif;
    @FXML
    private TextField id_adresseAgence_modif;
    @FXML
    private TextField id_jourTravail_modif;
    private TextField id_heureTravail;
    @FXML
    private ComboBox id_role_modif;
    @FXML
    private ComboBox id_genre_modif;
    @FXML
    private TextField id_heureTravail_modif;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Admin" , "Simple Client","Agence" ) ;
        id_role_modif.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Femme" , "Homme") ;
        id_genre_modif.setItems(liste);
        
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
    String fullName;
    String genreUser;
    String email;
    String mdp;
    String region;
     String municipalite;
    String telephone;
     String role;
     String adresseAgence;
    String jourTravail;
     String heureTravail;
     
     fullName=id_fullName_modif.getText();
     genreUser=id_genre_modif.getSelectionModel().getSelectedItem().toString(); 
     email=id_email_modif.getText();
     mdp=id_mdp_modif.getText();
     region=id_region_modif.getText();
     municipalite=id_municipalite_modif.getText();
     telephone=id_telephone_modif.getText();
     role=id_role_modif.getSelectionModel().getSelectedItem().toString(); 
     adresseAgence=id_adresseAgence_modif.getText();
     jourTravail=id_jourTravail_modif.getText();
     heureTravail=id_heureTravail_modif.getText();
     
     if(id_fullName_modif.getText().isEmpty() || id_email_modif.getText().isEmpty() ||id_mdp_modif.getText().isEmpty() || id_region_modif.getText().isEmpty() || id_municipalite_modif.getText().isEmpty() || id_telephone_modif.getText().isEmpty() || id_adresseAgence_modif.getText().isEmpty() || id_jourTravail_modif.getText().isEmpty() || id_heureTravail_modif.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
     else
        {
            User U2 = new User (fullName,genreUser,email,mdp,region,municipalite,telephone,role,adresseAgence,jourTravail,heureTravail);
            UserCRUD uc= new UserCRUD () ;
            uc.modifierUser(U2,1);
        
        JOptionPane.showMessageDialog(null,"Succés de modification ");
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String fullName;
        fullName=(String)id_email_delete.getText();
        
        UserCRUD uc= new UserCRUD ();
        uc.supprimerUser_home(fullName);
    }
    
}
