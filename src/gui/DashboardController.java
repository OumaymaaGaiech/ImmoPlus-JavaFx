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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author Oumayma Gaiech
 */
public class DashboardController implements Initializable {

    @FXML
    private Button id_listeUSer;
    @FXML
    private Button id_deconn_admin;
    @FXML
    private TableView<User> id_liste;
    @FXML
    private TableColumn<User, Integer> id_idUser;
    @FXML
    private TableColumn<User, String> id_fullName;
    @FXML
    private TableColumn<User, String> id_genreUSer;
    @FXML
    private TableColumn<User, String> id_email;
    @FXML
    private TableColumn<User, String> id_mdp;
    @FXML
    private TableColumn<User, String> id_region;
    @FXML
    private TableColumn<User, String> id_municipalite;
    @FXML
    private TableColumn<User, String> id_telephone;
    @FXML
    private TableColumn<User, String> id_role;
    @FXML
    private TableColumn<User, String> id_adresseAgence;
    @FXML
    private TableColumn<User, String> id_jourTravail;
    @FXML
    private TableColumn<User, String> id_heureTravail;
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField id_recherche;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void listeUser(ActionEvent event) {
        UserCRUD uc =new UserCRUD();
        ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        id_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        id_genreUSer.setCellValueFactory(new PropertyValueFactory<>("genreUser"));
        id_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        id_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        id_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        id_municipalite.setCellValueFactory(new PropertyValueFactory<>("municipalite"));
        id_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        id_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        id_adresseAgence.setCellValueFactory(new PropertyValueFactory<>("adresseAgence"));
        id_jourTravail.setCellValueFactory(new PropertyValueFactory<>("jourTravail"));
        id_heureTravail.setCellValueFactory(new PropertyValueFactory<>("heureTravail"));
    }

    @FXML
    private void deconn_admin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
             fxml=loader.load(); 
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             scene=new Scene((Parent) fxml);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show(); 
        
    }

    @FXML
    private void chercheuser(ActionEvent event) {
         UserCRUD ac = new UserCRUD();
       
        ObservableList<User> list = FXCollections.observableArrayList(ac.Afficher());
        FilteredList<User> filteredData = new FilteredList<>(list, b -> true);
        id_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(User -> {
                if (newValue == null || newValue.isEmpty()) {
                  
                    System.out.println("bien");
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (User.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getGenreUser().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (User.getMunicipalite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getTelephone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getHeureTravail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (User.getJourTravail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (User.getAdresseAgence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                
                 else {
                    return false;
                }
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(id_liste.comparatorProperty());

        id_liste.setItems(sortedData);
    }

    }
    

