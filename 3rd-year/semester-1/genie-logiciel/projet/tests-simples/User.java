import java.io.*;
import java.util.*;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    
    // variables
    boolean admin=false;
    boolean ListeNoire=false;
    String numSecu;        // numéro unique pour chaque usagé
    String nom;
    String prenom;
    //LocalDate anniv;    // date d'anniversaire
    String anniv;    // date d'anniversaire
    LocalDate dateCreationCompte = LocalDate.now(); // date de creation du compte
    String pseudo;      // pseudo choisit pour se connecter au logiciel
    String mdp;         // mot de passe pour se connecter au logiciel

    // getters & setters
    public boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin=admin;
    }

    public boolean getListeNoire() {
        return this.ListeNoire;
    }

    public void setListeNoire(boolean ListeNoire) {
        this.ListeNoire=ListeNoire;
    }
    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAnniv() {
        return this.anniv;
    }

    public void setAnniv(String anniv) {
        this.anniv = anniv;
    }

    public LocalDate getDateCreationCompte() {
        return this.dateCreationCompte;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return this.mdp;
    }
    
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

   public static void main(String[] args) {}    
}
