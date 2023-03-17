package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import java.util.Random;
import java.lang.String;


/**
 *
 * @author Oumayma Gaiech
 */
public class UserCRUD implements InterfaceUser{
    Connection conx = MyDB.getInstance().getConnection();
    private Object connexion;

    @Override
    public void ajouterUser(User U) {
             String req ="INSERT INTO `user`(`fullName`,`genreUser`,`email`,`mdp`,`region`,`municipalite`,`telephone`,`role`,`adresseAgence`,`jourTravail`,`heureTravail`)VALUES ('"+U.getFullName()+"','" +U.getGenreUser()+"','"+U.getEmail()+"','"+U.getMdp()+"','"+U.getRegion()+"','"+U.getMunicipalite()+"','"+U.getTelephone()+"','"+U.getRole()+"','"+U.getAdresseAgence()+"','"+U.getJourTravail()+"','"+U.getHeureTravail()+"')";
              Statement st ;
              try {
              st = conx.createStatement() ;
              st.executeUpdate(req) ;
              System.out.println("L'utilisateur est ajouté");}
              
               catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        
    }
        @Override
    public void supprimerUser(int idUser) {

    try {
        String req=" DELETE FROM user WHERE idUser="+ idUser ;

        PreparedStatement St = conx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("L'utilisateur est supprimé");}
     catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    }
    @Override
    public void modifierUser(User U ,int idUser) {
       String req = "UPDATE `user` SET `fullName`='"+U.getFullName()+"',`genreUser`='"+U.getGenreUser()+"',`email`='"+U.getEmail()+"',`mdp`='"+U.getMdp()+"',`region`='"+U.getRegion()+"',`municipalite`='"+U.getMunicipalite()+"',`telephone`='"+U.getTelephone()+"',`role`='"+U.getRole()+"',`adresseAgence`='"+U.getAdresseAgence()+"',`jourTravail`='"+U.getJourTravail()+"',`heureTravail`='"+U.getHeureTravail()+"' WHERE idUser ="+U.getIdUser() ;
        Statement st  ;
        try {
        st = conx.createStatement() ;
        st.executeUpdate(req) ;
        System.out.println("L'utilisateur est modifié");}
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<User> Afficher() {

    List <User> list = new ArrayList<>();
        try {
    String req = "SELECT * FROM  User";        
    Statement st;
    st =conx.createStatement();
    ResultSet rs = st.executeQuery(req);
    System.out.println("L'utilisateur est affiché");
    while(rs.next()){
    User u = new User (rs.getInt(1), rs.getString("fullname"),rs.getString("genreUser"), rs.getString("email"),rs.getString("mdp"),rs.getString("region"),rs.getString("municipalite"),rs.getString("telephone"),rs.getString("role"),rs.getString("adresseAgence"),rs.getString("jourTravail"), rs.getString("heureTravail"));
    list.add(u);
              }
        }
        catch (SQLException ex){
    System.err.println(ex.getMessage());
}
    return list;
    }  

 public boolean verifierLogin(String email,String password)throws SQLException{
    String req="SELECT * FROM `user` WHERE email=? AND mdp=? ;";
            int id=-1;
            PreparedStatement pst =conx.prepareStatement(req);
            pst.setString(1,email);
            pst.setString(2,password);            
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                id=rs.getInt(1);
                System.out.println(id);
            }
            if(id!=-1)
                return true;
            
            return false;
    }

    public User getUser(int idUser)throws SQLException {
        User U=new User();
    
    String req="SELECT * FROM `user` WHERE idUser=?  ;";
           
            PreparedStatement pst =conx.prepareStatement(req);
            pst.setInt(1,idUser);

            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
               U.setIdUser(rs.getInt(1));
               U.setFullName(rs.getString(2));
               U.setGenreUser(rs.getString(3));
               U.setEmail(rs.getString(4));
               U.setMdp(rs.getString(5));
               U.setRegion(rs.getString(6));
               U.setMunicipalite(rs.getString(7));
               U.setTelephone(rs.getString(8));
               U.setRole(rs.getString(9));
               U.setAdresseAgence(rs.getString(10));
               U.setJourTravail(rs.getString(11));
               U.setHeureTravail(rs.getString(12));
            }
    return U;
    }
    
    public User getUser(String email)throws SQLException {
        User U=new User();
    
    String req="SELECT * FROM `user` WHERE email=?  ;";
           
            PreparedStatement pst =conx.prepareStatement(req);
            pst.setString(1,email);

            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
               U.setIdUser(rs.getInt(1));
               U.setFullName(rs.getString(2));
               U.setGenreUser(rs.getString(3));
               U.setEmail(rs.getString(4));
               U.setMdp(rs.getString(5));
               U.setRegion(rs.getString(6));
               U.setMunicipalite(rs.getString(7));
               U.setTelephone(rs.getString(8));
               U.setRole(rs.getString(9));
               U.setAdresseAgence(rs.getString(10));
               U.setJourTravail(rs.getString(11));
               U.setHeureTravail(rs.getString(12));
            }
    return U;
    }
    

    

    public  List<User> selectAll() throws SQLException {
        List<User> arrayList = new ArrayList<>();
        String req ="SELECT * FROM user ";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                arrayList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
                
            }
          
        return arrayList;

    }

    @Override
    public void supprimerUser_home(String fullName)  {
        try {
        String req=" DELETE FROM user WHERE fullName=?" ;

        PreparedStatement St = conx.prepareStatement(req);
        St.setString(1, fullName);
        St.executeUpdate();
        System.out.println("L'utilisateur est supprimé");}
        catch (SQLException ex) {
        System.err.println(ex.getMessage());}
    }

    public String generer()  {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
         // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
         // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
         Random random = new Random();
        // specify length of random string
         int length = 10;
        for(int i = 0; i < length; i++) {
         // generate random index number
        int index = random.nextInt(alphaNumeric.length());
        // get character specified by index
        // from the string
        char randomChar = alphaNumeric.charAt(index);
        // append the character to string builder
        sb.append(randomChar);}
        String newmdp = sb.toString();
        System.out.println(newmdp);
        
        /*String req="UPDATE `user` SET `newmdp`= ? WHERE idUser=?";
            
            PreparedStatement pst =conx.prepareStatement(req);
            pst.setText((String) (newmdp));
            pst.setInt(2,idUser);            
            pst.executeUpdate();*/
        return newmdp;
    }}
    
        
    

    




    

