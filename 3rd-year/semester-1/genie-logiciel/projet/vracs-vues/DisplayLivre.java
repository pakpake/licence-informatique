/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package displaylivre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayLivre extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static String numSecu;
	private JPanel contentPane;
	private JPanel contentPane2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    	new DisplayLivre(numSecu);
				    	//frame.setVisible(true);
				    } catch(Exception e) {
				      e.printStackTrace();
				    }
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DisplayLivre(String num) {
		numSecu = num;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane2 = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);

		try {
                    
                      // changer url complete de la bdd
                      String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
		      String user = "root";
		      String password = "";

		      Connection con = DriverManager.getConnection(url);

		      String query = "SELECT * FROM livres";

		      java.sql.Statement stm = con.createStatement();
		      ResultSet res = stm.executeQuery(query);

		      String columns[] = { "EAN", "Titre", "Auteur", "Date de publication", "Resume", "langue",
		    		  "mot clés", "nombre", "editeur", "etat"};

		      String data[][] = new String[20][10];

		      int i = 0;
		      while (res.next()) {

		        String ean = res.getString("EAN");
		        String titre = res.getString("titre");
		        String auteur = res.getString("auteur");
		        String datePublication = res.getString("datePublication");
		        String resume = res.getString("resume");
		        String langue = res.getString("langue");
		        String motCles = res.getString("motCles");
		        Integer nombre = res.getInt("nombre");
		        String editeur = res.getString("editeur");
		        //Integer etat = res.getInt("etat");

		        data[i][0] = ean + "";
		        data[i][1] = titre;
		        data[i][2] = auteur;
		        data[i][3] = datePublication;
		        data[i][4] = resume;
		        data[i][5] = langue;
		        data[i][6] = motCles;
		        data[i][7] = nombre.toString();
		        data[i][8] = editeur;
		        //data[i][9] = etat.toString();
		        i++;
		      }

		      DefaultTableModel model = new DefaultTableModel(data, columns);
		      JScrollPane pane = new JScrollPane();
		      pane.setFont(new Font("Arial", Font.PLAIN, 14));
		      pane.setBackground(new Color(230, 230, 250));
		      JFrame f = new JFrame("Livre disponible dans la base de données");

		        JLabel labelHead = new JLabel("Livre disponible");
		        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
		        getContentPane().add(labelHead,BorderLayout.PAGE_START);

		      contentPane.add(pane, BorderLayout.CENTER);
		      contentPane.add(contentPane2, BorderLayout.PAGE_END);
		      JTable table = new JTable(model);
		      pane.setViewportView(table);
		      table.setFont(new Font("Arial", Font.PLAIN, 14));
		      table.setShowGrid(true);
		      table.setShowVerticalLines(true);
		      table.setRowHeight(30);

		      JButton emprunter = new JButton("Emprunter");
		      emprunter.setFont(new Font("Arial", Font.PLAIN, 16));
		      emprunter.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		int row = table.getSelectedRow();
		      		String value = table.getModel().getValueAt(row,0).toString();
		      		Emprunt emprunt = new Emprunt();
                                
                                if(emprunt.peutEmprunter(numSecu, value, con)){
                                    
                                    if(!emprunt.emprunter(numSecu,value,con)) {
		      			JOptionPane.showMessageDialog(null, "Echec emprunt");
                                    }else {
		      			JOptionPane.showMessageDialog(null, "Emprunt réussi") ;
                                    }
                                    
                                }else{
                                    JOptionPane.showMessageDialog(null, "Ne peux pas emprunter");
                                }
                                 
		      		                           
        
                                
		      	}
		      });

		      JButton close = new JButton("Close");
		      close.setFont(new Font("Arial", Font.PLAIN, 16));
		      close.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		AcceuilAbonneView acv = new AcceuilAbonneView(numSecu);
		      		acv.setVisible(true);
		      		dispose();
		      	}
		      });

		      close.setBackground(new Color(230, 230, 250));
		      contentPane2.add(close, BorderLayout.LINE_START);

		      emprunter.setBackground(new Color(230, 230, 250));
		      contentPane2.add(emprunter, BorderLayout.LINE_END);

		      f.getContentPane().add(contentPane);
		      f.setSize(900,600);
		      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      f.setVisible(true);

		    } catch(SQLException e) {
		      e.printStackTrace();
		    }
	}

}
