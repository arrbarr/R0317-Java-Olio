import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Point;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class tietoKanta {

	public class elokuva {

		private int elokuvaID;
		private String elokuvaNimi;
		private int vuosi;
		
		//konstruktori elokuvalle
		public elokuva(int elokuvaID, String elokuvaNimi, int vuosi) {
			super();
			this.elokuvaID = elokuvaID;
			this.elokuvaNimi = elokuvaNimi;
			this.vuosi = vuosi;
		}
		//getterit ja setterit elokuvan tiedoille
		public int getElokuvaID() {
			return elokuvaID;
		}

		public void setElokuvaID(int elokuvaID) {
			this.elokuvaID = elokuvaID;
		}

		public String getElokuvaNimi() {
			return elokuvaNimi;
		}

		public void setElokuvaNimi(String elokuvaNimi) {
			this.elokuvaNimi = elokuvaNimi;
		}

		public int getVuosi() {
			return vuosi;
		}

		public void setVuosi(int vuosi) {
			this.vuosi = vuosi;
		}

	}


	public static void GUI() {
		// GUIn rakentaminen
		//Luodaan array johon tiedot laitetaan
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		JFrame ikkuna = new JFrame();
		ikkuna.setLocation(new Point(1000, 500));
		JScrollPane scrollPane = new JScrollPane();

		try {
			// Luodaan tietokantayhteys ja m‰‰ritet‰‰n k‰ytt‰j‰nimi ja salasana yhteytt‰ varten
			String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7313383";
			String USERID = "sql7313383";
			String PASSWORD = "6YQyEZnylq";

			// Luodaan yhteys k‰ytt‰en edell‰nmainittuja tietoja
			Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM elokuvat");

			while (rs.next()) {
				data.add(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3) });
			}
			con.close();
			stmt.close();
			rs.close();

			// Varaudutaan virheisiin
		} catch (Exception e) {
			System.out.println("Yhteyden luonti ei onnistunut " + e);
		}

		ikkuna.setTitle("Elokuvataulukko");
		ikkuna.getContentPane().setLayout(new BoxLayout(ikkuna.getContentPane(), BoxLayout.X_AXIS));
		ikkuna.getContentPane().add(scrollPane);

		// Lis‰t‰‰n sarakkeet elokuvaa varten
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Elokuvan ID");
		model.addColumn("Elokuvan nimi");
		model.addColumn("Julkaisuvuosi");

		//luodaan taulukko johon syˆtet‰‰n tiedot tietokannasta for- silmukan avulla
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(true);
		
		for (int i = 0; i < data.size(); i++) {
			model.addRow(data.get(i));
		}

		ikkuna.pack();

		JMenuBar menuBar = new JMenuBar();
		ikkuna.setJMenuBar(menuBar);

		JMenu tiedostoMenu = new JMenu("Tiedosto");
		menuBar.add(tiedostoMenu);

		//menu item joka lis‰‰ metodin uusiElokuva mukaisesti syˆtetyt tiedot taulukkoon
		JMenuItem lis‰‰Menu = new JMenuItem("Lis\u00E4\u00E4 rivi");
		lis‰‰Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uusiElokuva();
			}
		});
		tiedostoMenu.add(lis‰‰Menu);

		//poistetaan k‰ytt‰j‰n valitsema rivi taulukosta
		JMenuItem poistaMenu = new JMenuItem("Poista rivi");
		poistaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//selvitet‰‰n k‰ytt‰j‰n valitsema rivi ja syˆtet‰‰n se muuttujaan
			int rivi = table.getSelectedRow();	
			String id = table.getModel().getValueAt(rivi, 0).toString();
			//m‰‰ritet‰‰n sql lauseke joka poistaa valitun rivin
			String poista = "DELETE FROM elokuvat WHERE id = " + id;
			try {
				//muodostetaan yhteys tietokantaan
				String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7313383";
				String USERID = "sql7313383";
				String PASSWORD = "6YQyEZnylq";

				Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
				Statement stmt = con.createStatement();
				
				//suoritetaan p‰ivitys tietokantaan joka suorittaa SQL lauseen ja poistaa valitun rivin
				stmt.executeUpdate(poista);	
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Elokuva poistettu tietokannasta");
			//napataan mahdolliset virheet	
			} catch (Exception h) {

				System.out.println("Elokuvan poistaminen ep‰onnistui" + h);
			}
			}
		});
		tiedostoMenu.add(poistaMenu);

		//Suljetaan ohjelma kun k‰ytt‰j‰ painaa Sulje menu itemia
		JMenuItem suljeMenu = new JMenuItem("Sulje");
		suljeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//P‰ivitet‰‰n taulukolle mahdolliset muutokset kuten lis‰ykset ja poistot
		JMenuItem p‰ivit‰Menu = new JMenuItem("P\u00E4ivit\u00E4 taulukko");
		p‰ivit‰Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//muodostetaan yhteys
					String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7313383";
					String USERID = "sql7313383";
					String PASSWORD = "6YQyEZnylq";
					Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM elokuvat");
					
					//luodaan array johon p‰ivitetyt tiedot laitetaan
					ArrayList<Object[]> data = new ArrayList<Object[]>();

				while (rs.next()) {
							data.add(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3) });
					}
				//poistetaan taulukossa jo olevat rivit n‰kyvist‰ kaksikkaisuuksien v‰ltt‰miseksi
					model.setRowCount(0);
				//Lis‰t‰‰n tiedot for- silmukalla
				for (int i = 0; i < data.size(); i++) {
					model.addRow(data.get(i));
					}
					con.close();
					stmt.close();
					rs.close();
				
				//Napataan mahdolliset virheet
				} catch (Exception f) {
					System.out.println("P‰ivitt‰minen ep‰onnistui " + f);
				}
			}

	});
		tiedostoMenu.add(p‰ivit‰Menu);
		tiedostoMenu.add(suljeMenu);
		//tietoja ohjelmasta 
		JMenu tietoMenu = new JMenu("Tietoja ohjelmasta");
		
		menuBar.add(tietoMenu);
	
		JMenuItem menuItem = new JMenuItem("");
		menuItem.setIcon(new ImageIcon(tietoKanta.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Versiotiedot frame = new Versiotiedot();
				frame.setVisible(true);
			}
		});
		tietoMenu.add(menuItem);
		ikkuna.setVisible(true);
	}
		//metodi joka kysyy k‰ytt‰j‰lt‰ elokuvan tiedot ja lis‰‰ ne taulukkoon
	public static void uusiElokuva() {
		//luodaan uusi ikkuna jossa kyselyt
		JFrame ikkuna = new JFrame("Lis‰‰ elokuva");

		GridLayout layout = new GridLayout(5, 2);
		ikkuna.getContentPane().setLayout(layout);
		ikkuna.setBounds(0, 0, 350, 175);
		ikkuna.setLocationRelativeTo(null);
		ikkuna.setVisible(true);
		ikkuna.setTitle("Syˆt‰ elokuvan tiedot");
		ikkuna.setResizable(false);
		ikkuna.setAlwaysOnTop(true);

		JLabel id = new JLabel("ID");
		ikkuna.getContentPane().add(id);

		JTextField elokuvaID = new JTextField();
		ikkuna.getContentPane().add(elokuvaID);

		JLabel elokuva = new JLabel("Elokuvan nimi");
		ikkuna.getContentPane().add(elokuva);

		JTextField elokuvaNimi = new JTextField();
		ikkuna.getContentPane().add(elokuvaNimi);

		JLabel vuosi = new JLabel("Elokuvan julkaisuvuosi");
		ikkuna.getContentPane().add(vuosi);

		JTextField elokuvaVuosi = new JTextField();
		ikkuna.getContentPane().add(elokuvaVuosi);

		JButton nappi = new JButton("Lis‰‰");
		nappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					//muodostetaan yhteys tietokantaan
					String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7313383";
					String USERID = "sql7313383";
					String PASSWORD = "6YQyEZnylq";
					Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);

					String elokuvaID1 = elokuvaID.getText();
					int eID = Integer.parseInt(elokuvaID1);
					String eNimi = elokuvaNimi.getText();
					String eVuosi = elokuvaVuosi.getText();
					
					//Syˆtet‰‰n k‰ytt‰j‰n syˆtt‰m‰t tiedot tietokantaan SQL- lauseen avulla
					java.sql.PreparedStatement Syˆte = con.prepareStatement("INSERT INTO elokuvat (ID, Nimi, Vuosi) VALUES (?, ?, ?)");
					Syˆte.setInt(1, eID);
					Syˆte.setString(2, eNimi);
					Syˆte.setString(3, eVuosi);
					//p‰ivitet‰‰n tietokanta
					Syˆte.executeUpdate();
					//suljetaan ikkuna kun lis‰‰ nappia on painettu
					ikkuna.dispose();
					JOptionPane.showMessageDialog(null, "Elokuva lis‰tty tietokantaan!");
				//napataan mahdolliset virheet
				} catch (Exception e) {
					System.out.println("Elokuvan tietojen lis‰‰minen ep‰onnistui " + e);
				}
			}
		});
		
		ikkuna.getContentPane().add(nappi);
		//lis‰t‰‰n peruuta nappi joka sulkee tietojenlis‰ysikkunan
		JButton peruuta = new JButton("Peruuta");
		peruuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ikkuna.dispose();
			}
		});
		ikkuna.getContentPane().add(peruuta);

	}
		//pyˆritet‰‰n ohjelma
		public static void main(String[] args) {
			GUI();
	}
}