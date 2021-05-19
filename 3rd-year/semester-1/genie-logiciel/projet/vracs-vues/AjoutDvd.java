package ia;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjoutDvd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ean;
	private JTextField titre;
	private JTextField realisateur;
	private JTextField langue;
	private JTextField duree;
	private JTextField nbExemp;
	private JTextField motCles;
	private JTextField etat;
	private JTextField acteurs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutDvd frame = new AjoutDvd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AjoutDvd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750,550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ajouter un DVD");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(225, 10, 178, 51);
		contentPane.add(lblNewLabel);
		
		ean = new JTextField();
		ean.setBounds(109, 81, 145, 31);
		contentPane.add(ean);
		ean.setColumns(10);
		
		titre = new JTextField();
		titre.setColumns(10);
		titre.setBounds(109, 133, 145, 31);
		contentPane.add(titre);
		
		realisateur = new JTextField();
		realisateur.setColumns(10);
		realisateur.setBounds(109, 196, 145, 31);
		contentPane.add(realisateur);
		
		langue = new JTextField();
		langue.setColumns(10);
		langue.setBounds(109, 258, 145, 31);
		contentPane.add(langue);
		
		JLabel eanLabel = new JLabel("EAN");
		eanLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		eanLabel.setBounds(10, 90, 86, 20);
		contentPane.add(eanLabel);
		
		JLabel titreLabel = new JLabel("Titre");
		titreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		titreLabel.setBounds(10, 142, 86, 20);
		contentPane.add(titreLabel);
		
		JLabel realisateurLabel = new JLabel("R\u00E9alisateur");
		realisateurLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		realisateurLabel.setBounds(10, 205, 86, 20);
		contentPane.add(realisateurLabel);
		
		JLabel langueLabel = new JLabel("Langue");
		langueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		langueLabel.setBounds(10, 267, 86, 20);
		contentPane.add(langueLabel);
		
		JTextArea resume = new JTextArea();
		resume.setBounds(109, 310, 145, 51);
		contentPane.add(resume);
		
		JLabel resumeLabel = new JLabel("Resume");
		resumeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		resumeLabel.setBounds(10, 326, 77, 20);
		contentPane.add(resumeLabel);
		
		JLabel datePubLabel = new JLabel("Date de Publication");
		datePubLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		datePubLabel.setHorizontalAlignment(SwingConstants.LEFT);
		datePubLabel.setBounds(359, 81, 145, 34);
		contentPane.add(datePubLabel);
		
		JLabel nbExempLabel = new JLabel("Nombre Exemplaire");
		nbExempLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nbExempLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		nbExempLabel.setBounds(359, 133, 145, 34);
		contentPane.add(nbExempLabel);
		
		JLabel dureeLabel = new JLabel("Dur\u00E9e");
		dureeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dureeLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		dureeLabel.setBounds(359, 196, 145, 34);
		contentPane.add(dureeLabel);
		
		JFormattedTextField datPub = new JFormattedTextField();
		datePubLabel.setLabelFor(datPub);
		datPub.setBounds(514, 81, 190, 31);
		contentPane.add(datPub);
		
		nbExemp = new JTextField();
		nbExemp.setBounds(514, 133, 190, 31);
		contentPane.add(nbExemp);
		nbExemp.setColumns(10);
		
		duree = new JTextField();
		duree.setColumns(10);
		duree.setBounds(514, 196, 190, 31);
		contentPane.add(duree);
		
		motCles = new JTextField();
		motCles.setBounds(511, 258, 193, 31);
		contentPane.add(motCles);
		motCles.setColumns(10);
		
		etat = new JTextField();
		etat.setColumns(10);
		etat.setBounds(511, 312, 193, 31);
		contentPane.add(etat);
		
		JLabel motClesLabel = new JLabel("Mot cl\u00E9s");
		motClesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		motClesLabel.setBounds(359, 258, 122, 31);
		contentPane.add(motClesLabel);
		
		JLabel lblEtat = new JLabel("Etat");
		lblEtat.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEtat.setBounds(359, 315, 122, 31);
		contentPane.add(lblEtat);
		
		acteurs = new JTextField();
		acteurs.setBounds(109, 392, 145, 44);
		contentPane.add(acteurs);
		acteurs.setColumns(10);
		
		JLabel acteurLabel = new JLabel("Acteurs");
		acteurLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		acteurLabel.setBounds(10, 407, 77, 31);
		contentPane.add(acteurLabel);
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String is = ean.getText();
				String title = titre.getText();
				String real = realisateur.getText();
				String act = acteurs.getText();
				String resu = resume.getText();
				String date = datPub.getText();
				int nbEx = Integer.parseInt(nbExemp.getText());
				String lang = langue.getText();
				String mot = motClesLabel.getText();
				String et = etat.getText();
				int dure = Integer.parseInt(duree.getText());
				
				AjoutMedia.ajouterDVD(is,title,resu,lang,date,mot,nbEx,(byte)Integer.parseInt(et),real,act,dure);
			}
		});
		ajouter.setFont(new Font("Arial", Font.PLAIN, 16));
		ajouter.setBounds(421, 429, 157, 44);
		contentPane.add(ajouter);
	}
}
