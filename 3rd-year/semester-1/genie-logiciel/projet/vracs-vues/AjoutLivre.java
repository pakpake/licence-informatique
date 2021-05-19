package ia;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjoutLivre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField isbn;
	private JTextField titre;
	private JTextField auteur;
	private JTextField editeur;
	private JTextField nbExemp;
	private JTextField langue;
	private JTextField motCles;
	private JTextField etat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutLivre frame = new AjoutLivre();
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
	public AjoutLivre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel rootLabel = new JLabel("Ajouter un Livre");
		rootLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
		rootLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rootLabel.setBounds(225, 10, 178, 51);
		contentPane.add(rootLabel);
		
		isbn = new JTextField();
		isbn.setBounds(109, 81, 145, 31);
		contentPane.add(isbn);
		isbn.setColumns(10);
		
		titre = new JTextField();
		titre.setColumns(10);
		titre.setBounds(109, 133, 145, 31);
		contentPane.add(titre);
		
		auteur = new JTextField();
		auteur.setColumns(10);
		auteur.setBounds(109, 196, 145, 31);
		contentPane.add(auteur);
		
		editeur = new JTextField();
		editeur.setColumns(10);
		editeur.setBounds(109, 258, 145, 31);
		contentPane.add(editeur);
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setLabelFor(isbn);
		isbnLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		isbnLabel.setBounds(10, 90, 86, 20);
		contentPane.add(isbnLabel);
		
		JLabel titrelabel = new JLabel("Titre");
		titrelabel.setLabelFor(titre);
		titrelabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		titrelabel.setBounds(10, 142, 86, 20);
		contentPane.add(titrelabel);
		
		JLabel auteurLabel = new JLabel("Auteur");
		auteurLabel.setLabelFor(auteur);
		auteurLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		auteurLabel.setBounds(10, 205, 86, 20);
		contentPane.add(auteurLabel);
		
		JLabel editLabel = new JLabel("Editeur");
		editLabel.setLabelFor(editeur);
		editLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		editLabel.setBounds(10, 267, 86, 20);
		contentPane.add(editLabel);
		
		JTextArea resume = new JTextArea();
		resume.setBounds(109, 309, 145, 51);
		contentPane.add(resume);
		
		JLabel resumeLabel = new JLabel("Resume");
		resumeLabel.setLabelFor(resume);
		resumeLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		resumeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resumeLabel.setBounds(10, 327, 86, 34);
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
		
		JLabel langueLabel = new JLabel("Langue");
		langueLabel.setHorizontalAlignment(SwingConstants.LEFT);
		langueLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		langueLabel.setBounds(359, 196, 145, 34);
		contentPane.add(langueLabel);
		
		JFormattedTextField datPub = new JFormattedTextField();
		datePubLabel.setLabelFor(datPub);
		datPub.setBounds(514, 81, 190, 31);
		contentPane.add(datPub);
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO convertir en bytes
				String et=etat.getText();
				String edit = editeur.getText();
				int nombre = Integer.parseInt(nbExemp.getText());
                AjoutMedia.ajouterLivre(isbn.getText(),titre.getText(),auteur.getText(),
                datPub.getText(),resume.getText(),langue.getText(),motCles.getText(),nombre,edit,(byte) Integer.parseInt(et));
			}
		});
		ajouter.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
		ajouter.setBounds(359, 394, 178, 51);
		contentPane.add(ajouter);
		
		nbExemp = new JTextField();
		nbExemp.setBounds(514, 133, 190, 31);
		contentPane.add(nbExemp);
		nbExemp.setColumns(10);
		
		langue = new JTextField();
		langue.setColumns(10);
		langue.setBounds(514, 196, 190, 31);
		contentPane.add(langue);
		
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
	}
}
