package com.logiciel.maven;

import javax.swing.*;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccueilBiblio extends BackgroundView {
    private JPanel panel1;

    private JLabel titre;
    private JLabel labelEmprunt;
    private JLabel labelRetourEmprunt;
    private JLabel labelAjouterMedias;
    private JLabel labelSupMedias;
    private JLabel labelAjoutUser;
    private JLabel labelModifUser;
    //déclarations des boutons :
    private JButton buttonDeco;
    private JButton buttonModifInfo;
    private JButton buttonEmprunt;
    private JButton buttonRetourEmprunt;
    private JButton buttonAjoutMedias;
    private JButton buttonSupMedias;
    private JButton buttonAjoutUser;
    private JButton buttonModifUser;


    public AccueilBiblio() {
        super();
        //initialisation compsoants

        //déclarations des différents coneteneurs
        panel1 = new JPanel();//conteneurs principal
        panel1.setLayout(null);


        //déclarations des différents boutons :
        buttonDeco = new JButton("Déconnexion");
        buttonDeco.setBounds(0,550,150,20);

        buttonModifInfo = new JButton("Votre profil");
        buttonModifInfo.setBounds(700,550,150,15);

        buttonAjoutMedias = new JButton("Ajouter un média \n");
        buttonAjoutMedias.setBounds(450,250,150,15);

        buttonAjoutUser = new JButton("Ajouter un abonné");
        buttonAjoutUser.setBounds(450,300,150,15);

        buttonEmprunt = new JButton("Emprunt");
        buttonEmprunt.setBounds(450,350,150,15);

        buttonRetourEmprunt = new JButton("Retour Emprunt");
        buttonRetourEmprunt.setBounds(450,400,150,15);

        buttonSupMedias = new JButton("Supprimer un média");
        buttonSupMedias.setBounds(450,450,150,15);

        buttonModifUser = new JButton("Modifier abonné");
        buttonModifUser.setBounds(450,500,150,15);
        buttonDeco.setPreferredSize(new Dimension());
        //déclarations des différents label :
        titre = new JLabel("Bienvenue dans votre espace Bibliothécaire, vous avez accès à toutes ces fonctionnalitées : ");
        titre.setBounds(250,20,600,15);

        labelAjouterMedias = new JLabel("Cliquez ici pour ajouter des médias : ");
        labelAjouterMedias.setBounds(200,200,150,15);
        labelAjouterMedias.setLabelFor(buttonAjoutMedias);

        labelAjoutUser = new JLabel("Cliquez ici pour ajouter un abonné");
        labelAjoutUser.setBounds(200,250,300,15);
        labelAjoutUser.setLabelFor(buttonAjoutUser);

        labelEmprunt = new JLabel("Cliquez ici pour enregistrter un emprunt : ");
        labelEmprunt.setBounds(200,350,300,15);
        labelEmprunt.setLabelFor(buttonEmprunt);

        labelRetourEmprunt = new JLabel("Cliquez ici pour enregistrer un retour d'emprunts : ");
        labelRetourEmprunt.setBounds(200,400,300,15);
        labelRetourEmprunt.setLabelFor(buttonRetourEmprunt);

        labelSupMedias = new JLabel("Cliquez ici pour supprimer des médias : ");
        labelSupMedias.setBounds(200,450,300,15);
        labelSupMedias.setLabelFor(buttonSupMedias);


        labelModifUser = new JLabel("Modifier les abonnés à votre bibliothèque : ");
        labelModifUser.setBounds(200,500,200,15);
        labelModifUser.setLabelFor(buttonModifUser);


        //TODO listeners :
        buttonModifInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //TODO abonne
                Abonne a = new Abonne();
                ConsultInfo c = new ConsultInfo();
                c.setVisible(true);
            }
        });




        //ajout des composants dans le conteneur :

        panel1.add(titre);
        panel1.add(labelEmprunt);
        panel1.add(buttonEmprunt);
        panel1.add(labelAjouterMedias);
        panel1.add(buttonAjoutMedias);
        panel1.add(labelSupMedias);
        panel1.add(buttonSupMedias);
        panel1.add(labelAjoutUser);
        panel1.add(buttonAjoutUser);
        panel1.add(labelModifUser);
        panel1.add(buttonModifUser);
        panel1.add(buttonModifInfo);
        panel1.add(buttonRetourEmprunt);
        panel1.add(buttonDeco);

        setContentPane(panel1);
        setVisible(true);//rend la fenetre visible
    }

    public static void main(String[] args) {
        AccueilBiblio accueilBiblio = new AccueilBiblio();
    }
}

/**
 * Contrôleur de test :
 */
class ControlAccueil {
    public static Abonne abonne;

    public ControlAccueil(Abonne a) {
        abonne = a;

    }

}
