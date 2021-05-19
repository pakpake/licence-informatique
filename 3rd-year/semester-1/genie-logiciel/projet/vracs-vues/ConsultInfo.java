package com.logiciel.maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette vue permet à l'utilisateur de consulter les informations de son profil et
 * d'appyer sur le bouton modifier afin de les modifier
 */
public class ConsultInfo extends BackgroundView {


    private JPanel panel1;
    private JLabel labelTitre;
    private JList listInfo;
    private DefaultListModel info = new DefaultListModel();
    private JButton buttonRetour;
    private JButton buttonDeco;
    private JButton buttonModifInfo;
    private JButton buttonSuprCompte;
    private Some control;
    /**
     * constructeur : créer une vue ConsultInfo
     */
    public ConsultInfo(){
        super();
        control = new Some();//initialisation du controleur
        //initialisations composants :
        panel1 = new JPanel();
        panel1.setLayout(null);

        setContentPane(panel1);
        labelTitre = new JLabel("Informations de votre profil :");
        labelTitre.setBounds(100,20,300,15);

        listInfo = new JList(info);
        listInfo.setBounds(238,106,300,200);

        buttonRetour = new JButton("< Retour ");
        buttonRetour.setBounds(0,500,150,15);

        buttonModifInfo = new JButton("Modifier");
        buttonModifInfo.setBounds(238,310,150,20);


        buttonDeco = new JButton("Déconnexion");
        buttonDeco.setBounds(600,500,150,20);

        buttonSuprCompte = new JButton("Supprimer votre compte");
        buttonSuprCompte.setBounds(300,500,200,20);

        //paramètrages composants :

        //

        info.addElement("Nom : ");
        info.addElement("Prenom :");
        info.addElement("Email : ");
        info.addElement("Numéro de sécurité social : ");
        info.addElement("Identifiant : ");
        info.addElement("Date de naissance : ");
        info.addElement("Date de création de compte : ");
        info.addElement("est sur liste noire : ");


        //listeners boutons
        buttonModifInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VueModifInfo v = new VueModifInfo();
                v.setVisible(true);
            }
        });
        buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AccueilBiblio();
            }
        });

        buttonModifInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO : redirection vers vue de modif des informations
                new VueModifInfo();
            }
        });
        buttonSuprCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.something();
            }
        });

        //ajout des composants aux conteneurs principales
        panel1.add(labelTitre);
        panel1.add(listInfo);
        panel1.add(buttonRetour);
        panel1.add(buttonModifInfo);
        panel1.add(buttonDeco);
        panel1.add(buttonSuprCompte);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    ConsultInfo frame = new ConsultInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
class Some {
    private boolean b = true;

    public Some() {

    }

    public void something(){
    }

    public boolean isB() {
        return b;
    }
}