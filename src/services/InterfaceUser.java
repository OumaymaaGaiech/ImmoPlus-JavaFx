
package services;

import entities.User;
import java.util.List;

/**
 *
 * @author Oumayma Gaiech
 */
public interface InterfaceUser {
    public void ajouterUser (User U) ;
    public void supprimerUser(int id) ;
    public void modifierUser(User U, int idUser) ;
    public List<User> Afficher() ;
    public void supprimerUser_home(String fullName);
    
}
