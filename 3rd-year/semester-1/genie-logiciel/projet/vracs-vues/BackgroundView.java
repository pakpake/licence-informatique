package com.logiciel.maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * BackgroundView : classe permettant de normaliser les fenetres JFrame
 * au sein du projet.
 */
public abstract class BackgroundView extends JFrame {
    /**
     * Constructeur BackgroundView :
     * permet de générer une fenêtre avec certain paramètre classique,
     * permettant de réduire la duplication de codes.
     */
    public BackgroundView(){
        super("Logiciel de gestion de bibliothèque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * @param : la police, la dimension, la couleur
     * methode permetant de paramétrer la police
     */
    public void setText(JTextField textField,Font police){
        setFont(police);
        setPreferredSize(new Dimension(50,30));
        setForeground(Color.BLACK);


    }

}
