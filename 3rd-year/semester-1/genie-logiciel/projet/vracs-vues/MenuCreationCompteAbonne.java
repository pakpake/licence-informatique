package com.logiciel.maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Vue de la création d'un compte pour un abonné :
 * Fait apparaître un formulaire d'inscription que
 * l'utilisateur remplit.
 *Le contrôleur instancié contrôle les actions effectuer.
 */
public class MenuCreationCompteAbonne extends BackgroundView {
    //Composants de la vue :

    private final JPanel panel = new JPanel();//conteneur dans la fenêtre
    //défintions label et champ de texte :
    private final JLabel labelNom = new JLabel("nom : ");
    private final JLabel labelFirstName = new JLabel("prénom : ");
    private final JLabel labelBirthDate = new JLabel("Date de naissance : ");
    private final JLabel labelid = new JLabel("Identifiant : ");
    private final JLabel labelPw = new JLabel("password : ");
    private final JPasswordField passwordField;
    protected JTextField name;
    protected JTextField birthDate;
    protected JTextField id;
    protected JTextField firstName;
    protected JTextField numSecu;
    protected JTextField email;
    private JLabel labelEmail = new JLabel("Adresse email : ");
    private JLabel labelNumSecu = new JLabel("numéro de sécurité social");
    //boutons de la vue :
    private final JButton buttonSeConnecter = new JButton("Se Connecter à un compte existant");
    private final JButton previous = new JButton("Précédent");
    private final JButton buttonSubmit = new JButton("Créer un compter");
    //le contrôleur :
    private Controller controller;

    /**
     * constructeurs : instancie un menu de création
     * de compte pour un abonné
     */
    public MenuCreationCompteAbonne(){
        super();
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());

        //déclaration :
        name = new JTextField("",15);
        firstName = new JTextField("",15);
        birthDate = new JTextField("",15);
        id = new JTextField("",15);
        numSecu = new JTextField("",15);
        email = new JTextField(15);
        passwordField = new JPasswordField("", 15);
        //police d'écriture :
        Font police = new Font("Arial", Font.ROMAN_BASELINE, 12);
        //réglages textField
        setText(name,police);
        setText(firstName,police);
        setText(birthDate,police);
        setText(id,police);
        setText(email,police);
        setText(numSecu,police);



        buttonSubmit.setBackground(Color.blue);
        //conteneurs secondaires et réglages sur conteneurs principales
        /*
        JPanel top = new JPanel();
        JPanel center = new JPanel();
         */
        panel.setLayout(null);
        this.setContentPane(panel);




        //création panel pour chaque champ et label pour la disposition :
        /*
        JPanel panelNom = new JPanel();
        JPanel panelPrenom = new JPanel();
        JPanel panelDateNaissance = new JPanel();
        JPanel panelNumSecu = new JPanel();
        JPanel panelId_Password = new JPanel();
        */
        labelid.setLabelFor(id);
        labelBirthDate.setLabelFor(birthDate);
        labelBirthDate.setDisplayedMnemonic('D');
        labelFirstName.setLabelFor(firstName);
        labelNom.setLabelFor(name);
        labelEmail.setLabelFor(email);
        labelFirstName.setLabelFor(firstName);
        labelNumSecu.setLabelFor(numSecu);
        //ajout dans top le second conteneur des composants et sous-sous conteneurs :
        JLabel titre = new JLabel("Créer un compte ");
        titre.setBounds(10,50,200,30);
        panel.add(titre);

        labelNom.setBounds(350,50,200,30);
        panel.add(labelNom);

        labelFirstName.setBounds(350,100,200,30);
        panel.add(labelFirstName);

        labelEmail.setBounds(350,150,200,30);
        panel.add(labelEmail);

        labelid.setBounds(350,200,200,30);
        panel.add(labelid);

        labelBirthDate.setBounds(350,250,200,30);
        panel.add(labelBirthDate);

        labelNumSecu.setBounds(350,300,200,30);
        panel.add(labelNumSecu);

        labelPw.setBounds(350,350,200,30);
        labelPw.getPreferredSize();
        panel.add(labelPw);

        name.setBounds(550,50,200,30);
        panel.add(name);

        firstName.setBounds(550,100,200,30);
        panel.add(firstName);

        email.setBounds(550,150,200,30);
        panel.add(email);

        id.setBounds(550,200,200,30);
        panel.add(id);

        birthDate.setBounds(550,250,200,30);
        panel.add(birthDate);

        numSecu.setBounds(550,300,200,30);
        panel.add(numSecu);

        passwordField.setBounds(550,350,200,30);
        panel.add(passwordField);

        previous.setBounds(150,550,200,30);
        panel.add(previous);

        buttonSeConnecter.setBounds(400,550,200,30);
        panel.add(buttonSeConnecter);

        buttonSubmit.setBounds(650,550,200,30);
        panel.add(buttonSubmit);


        /*
        panelPrenom.add(labelFirstName);
        panelPrenom.add(firstName);
        center.add(panelPrenom);
        panelNom.add(labelNom);
        panelNom.add(name);
        center.add(panelNom);
        center.add(labelEmail);
        center.add(email);
        panelDateNaissance.add(labelBirthDate);
        panelDateNaissance.add(birthDate);
        center.add(panelDateNaissance);
        panelNumSecu.add(labelNumSecu);
        panelNumSecu.add(numSecu);
        center.add(panelNumSecu);
        panelId_Password.add(labelid);
        panelId_Password.add(id);

        panelId_Password.add(labelPw);
        panelId_Password.add(passwordField);
        center.add(panelId_Password);


        down.add(buttonSeConnecter);
        down.add(previous);
        down.add(buttonSubmit);
        */
        //controleur
        controller = new Controller();

        //TODO listeneurs :

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                controller.getPreviousView();
            }
        });
        buttonSeConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login log = new Login();
                log.setVisible(true);
            }
        });

        //finitions :


        //this.pack();
        this.setVisible(true);

    }


}
class Controller {
    public void getPreviousView(){
        MenuCreationCompte mc=new MenuCreationCompte();

    }
}