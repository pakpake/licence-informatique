package com.logiciel.maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vue de modification des informations du compte
 * L'utilisqteur appuie sur sauvegarder quand il a finis
 */
public class VueModifInfo extends BackgroundView {
    //déclarations des attributs :
    private JPanel pan;
    private JTextField nom;
    private JTextField prenom;
    private JTextField email;
    private JTextField numSecu;
    private JTextField dateDeNaissance;
    private JTextField dateCreationCompte;
    private JButton buttonSauvegarde;
    private JButton buttonModifMdp;
    private JButton buttonRetour;
    //TODO : declarer controleur
    private Some c;

    /**
     * constructeur de VueModifInfo :
     * permet d'instancier la vue associer
     * TODO : prend le controleur en parametre
     * @param :
     */
    public VueModifInfo() {
        super();
        c=new Some();
        pan = new JPanel();
        pan.setLayout(null);
        setContentPane(pan);
        //initialisation et parametrages composants :

        JLabel titre = new JLabel("Modification du profil : ");
        titre.setBounds(350,50,250,30);
        pan.add(titre);

        nom = new JTextField("test");
        nom.setBounds(200,100,200,30);
        pan.add(nom);

        prenom = new JTextField("test");
        prenom.setBounds(200,150,200,30);
        pan.add(prenom);

        email = new JTextField("test");
        email.setBounds(200,200,200,30);
        pan.add(email);

        numSecu = new JTextField("test");
        numSecu.setBounds(200,250,200,30);
        pan.add(numSecu);

        dateDeNaissance = new JTextField("test");
        dateDeNaissance.setBounds(200,300,200,30);
        pan.add(dateDeNaissance);

        dateCreationCompte= new JTextField("test");
        dateCreationCompte.setBounds(200,350,200,30);
        pan.add(dateCreationCompte);

        buttonRetour = new JButton("< Retour ");
        buttonRetour.setBounds(200,400,100,30);
        pan.add(buttonRetour);

        buttonModifMdp = new JButton("Modifier le mot de passe ");
        buttonModifMdp.setBounds(350,400,200,30);
        pan.add(buttonModifMdp);

        buttonSauvegarde = new JButton("Sauvegarder");
        buttonSauvegarde.setBounds(600,400,200,30);
        pan.add(buttonSauvegarde);

        //TODO listeners :
        buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConsultInfo frame = new ConsultInfo();
                frame.setVisible(true);

            }
        });

        buttonModifMdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("Entrer votre ancien mot de passe :");
                //JLabel label2 =new JLabel("Entrer votre nouveau mot de passe : ");
                JPasswordField jpf = new JPasswordField();
                JOptionPane.showConfirmDialog(null,new Object[]{label,jpf},"changement de mot de passe",JOptionPane.OK_CANCEL_OPTION);
                if(!c.isB()){
                    JOptionPane.showMessageDialog(null, "Erreur, veuillez réessayer", "Error", JOptionPane.ERROR_MESSAGE);
                }
                label.setText("Entrer votre nouveau mot de passe : ");
                JPasswordField nouveauMdp = new JPasswordField();
                if(!c.isB()){
                    JOptionPane.showConfirmDialog(null,new Object[]{label,nouveauMdp},"changement de mot de passe",JOptionPane.OK_CANCEL_OPTION);
                }

                if(!c.isB()){
                    JOptionPane.showMessageDialog(null, "Erreur, veuillez réessayer", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VueModifInfo frame2 = new VueModifInfo();
                    frame2.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

