package ia;

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

public class DisplayDVD extends JFrame {
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
				    	new DisplayDVD(numSecu);
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
	public DisplayDVD(String num) {
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
			  String url = "jdbc:mysql://127.0.0.1:3306/logiciel?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
		      String user = "root";
		      String password = "";
		    
		      Connection con = DriverManager.getConnection(url, user, password);
		    
		      String query = "SELECT * FROM dvd";
		    
		      java.sql.Statement stm = con.createStatement();
		      ResultSet res = stm.executeQuery(query);
		    
		      String columns[] = { "EAN", "Titre", "R�alisateur", "Resume", "langue", "principauxActeurs", "Date de publication",
		    		  "mot cl�s", "nombre", "Dur�e", "etat"};
		      
		      String data[][] = new String[20][11];
		    
		      int i = 0;
		      while (res.next()) {
		       
		        String ean = res.getString("EAN");
		        String titre = res.getString("titre");
		        String realisateur = res.getString("realisateur");
		        String resume = res.getString("resume");
		        String langue = res.getString("langue");
		        String ppxActeurs = res.getString("principauxActeurs");
		        String datePublication = res.getString("datePublication");
		        String motCles = res.getString("motCles");
		        Integer nombre = res.getInt("nombre");
		        String duree = res.getString("Dur�e");
		        Integer etat = res.getInt("etat");
		        
		        data[i][0] = ean + "";
		        data[i][1] = titre;
		        data[i][2] = realisateur;
		        data[i][3] = resume;
		        data[i][4] = langue;
		        data[i][5] = ppxActeurs;
		        data[i][6] = datePublication;
		        data[i][7] = motCles;
		        data[i][8] = nombre.toString();
		        data[i][9] = duree;
		        data[i][10] = etat.toString();
		        i++;
		      }
		    
		      DefaultTableModel model = new DefaultTableModel(data, columns);
		      JScrollPane pane = new JScrollPane();
		      pane.setFont(new Font("Arial", Font.PLAIN, 14));
		      pane.setBackground(new Color(230, 230, 250));
		      JFrame f = new JFrame("DVD disponible dans la base de donn�es");
		      
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
		      		Emprunt emprunt = new Emprunt(value);
		      		
		      		if(emprunt.peutEmprunter(numSecu, value, con)){
                        
                        if(!emprunt.emprunter(numSecu,value,con)) {
                        	JOptionPane.showMessageDialog(null, "Echec emprunt");
                        }else {
                        	JOptionPane.showMessageDialog(null, "Emprunt r�ussi") ;
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
		      		f.setVisible(false);
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
