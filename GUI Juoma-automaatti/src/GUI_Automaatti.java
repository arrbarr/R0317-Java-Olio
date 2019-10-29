import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

public class GUI_Automaatti extends JFrame {

	// Luokkamuuttujat
	// Esitell‰‰n t‰‰ll‰ jotta komponentteihin voidaan viitata mist‰ tahansa luokan
	// sis‰lt‰

	JPanel contentPane;
	private JMenuItem mntmTallennaAutomaatinTila;
	private JMenuItem mntmLataaAutomaatti;
	private JLabel KahviLabel;
	private JLabel TeeLabel;
	private JLabel KaakaoLabel;

	/**
	 * Main-metodi, joka k‰ynnist‰‰ sovelluksen
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Luodaan ensmin uusi JuomaAutomaatti-olio
					JuomaAutomaatti ja = new JuomaAutomaatti();

					// K‰yttˆliittym‰ saa parametrina olion, jonka tiedot se n‰ytt‰‰
					GUI_Automaatti frame = new GUI_Automaatti(ja);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * Konstruktorissa rakennetaan k‰yttˆliittym‰. Huomaa, ett‰ otetaan parametrina
	 * vastaan alussa luotu juoma-automaatti. T‰m‰ siksi, ett‰ voidaan n‰ytt‰‰ sen
	 * tiedot GUI:ssa
	 */
	public GUI_Automaatti(JuomaAutomaatti ja) {

		setTitle("Kahviautomaatti GUI v. 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 705);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu Yll‰pito = new JMenu("Yll\u00E4pito");
		Yll‰pito.setFont(new Font("Lato", Font.PLAIN, 12));
		menuBar.add(Yll‰pito);

		// Asettaa kahville syˆtetyn arvon ja v‰rin arvon mukaan
		JMenuItem asetaKahvi = new JMenuItem("Aseta kahvin m\u00E4\u00E4r\u00E4");
		asetaKahvi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String uusiKahviSyˆte = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiKahvi = Integer.parseInt(uusiKahviSyˆte);
				ja.setKahvi(uusiKahvi);
				if (uusiKahvi > 20) {
					KahviLabel.setForeground(Color.BLACK);
					KahviLabel.setText("Kahvia: " + uusiKahviSyˆte);
				} else {
					KahviLabel.setText("Kahvia: " + uusiKahviSyˆte);
				}
			}
		});
		Yll‰pito.add(asetaKahvi);

		// Asettaa teelle syˆtetyn arvon ja v‰rin arvon mukaan
		JMenuItem asetaTee = new JMenuItem("Aseta teen m\u00E4\u00E4r\u00E4");
		asetaTee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uusiTeeSyˆte = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiTee = Integer.parseInt(uusiTeeSyˆte);
				ja.setTee(uusiTee);
				if (uusiTee > 20) {
					TeeLabel.setForeground(Color.BLACK);
					TeeLabel.setText("Teet‰: " + uusiTeeSyˆte);
				} else {
					TeeLabel.setText("Teet‰: " + uusiTeeSyˆte);
				}
			}
		});
		Yll‰pito.add(asetaTee);

		// Asettaa kaakaolle syˆtetyn arvon ja v‰rin arvon mukaan
		JMenuItem asetaKaakao = new JMenuItem("Aseta kaakaon m\u00E4\u00E4r\u00E4");
		asetaKaakao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uusiKaakaoSyˆte = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiKaakao = Integer.parseInt(uusiKaakaoSyˆte);
				ja.setKaakao(uusiKaakao);
				if (uusiKaakao > 20) {
					KaakaoLabel.setForeground(Color.BLACK);
					KaakaoLabel.setText("Kaakaota: " + uusiKaakaoSyˆte);
				} else {
					KaakaoLabel.setText("Kaakaota: " + uusiKaakaoSyˆte);
				}
			}
		});
		Yll‰pito.add(asetaKaakao);

		// tallentaa automaatin .xml tiedostoon
		JMenuItem TallennaAutomaatti = new JMenuItem("Tallenna automaatin tila");
		TallennaAutomaatti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sarjallistamista.kirjoitaTiedostoon(ja);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Yll‰pito.add(TallennaAutomaatti);

		// lataa tallennetun automaatin ja asettaa sen arvot ja v‰rit arvojen mukaan
		JMenuItem LataaAutomaatti = new JMenuItem("Lataa automaatti");
		LataaAutomaatti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JuomaAutomaatti uusi = Sarjallistamista.lueTiedostosta();
					int ladattuKahvi = uusi.getKahvi();
					int ladattuTee = uusi.getTee();
					int ladattuKaakao = uusi.getKaakao();
					ja.setKahvi(ladattuKahvi);
					if (ladattuKahvi > 20) {
						KahviLabel.setForeground(Color.BLACK);
						KahviLabel.setText("Kahvia: " + ladattuKahvi);
					} else {
						KahviLabel.setText("Kahvia: " + ladattuKahvi);
						KahviLabel.setForeground(Color.RED);
					}
					ja.setTee(ladattuTee);
					if (ladattuTee > 20) {
						TeeLabel.setForeground(Color.BLACK);
						TeeLabel.setText("Teet‰: " + ladattuTee);
					} else {
						TeeLabel.setText("Teet‰: " + ladattuTee);
						TeeLabel.setForeground(Color.RED);
					}
					ja.setKaakao(ladattuKaakao);
					if (ladattuKaakao > 20) {
						KaakaoLabel.setForeground(Color.BLACK);
						KaakaoLabel.setText("Kaakaota: " + ladattuKaakao);
					} else {
						KaakaoLabel.setText("Kaakaota: " + ladattuKaakao);
						KaakaoLabel.setForeground(Color.RED);
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Yll‰pito.add(LataaAutomaatti);

		// lopettaa ohjelman
		JMenuItem lopeta = new JMenuItem("Lopeta");
		lopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lopetaOhjelma();
			}
		});
		Yll‰pito.add(lopeta);

		// avaa uuden ikkunan jossa tietoja ohjelmasta
		JMenu tietoja = new JMenu("Tietoja ohjelmasta");
		tietoja.setFont(new Font("Lato", Font.PLAIN, 12));
		menuBar.add(tietoja);

		JMenuItem mntmNewMenuItem = new JMenuItem("Versiotiedot");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Versiotiedot frame = new Versiotiedot();
				frame.setVisible(true);
			}
		});
		tietoja.add(mntmNewMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel kahvi = new JPanel();
		contentPane.add(kahvi);
		kahvi.setLayout(null);

		// m‰‰ritt‰‰ kahvinapin joka poistaa kahvi-arvosta 10 yksikkˆ‰ ja m‰‰ritt‰‰
		// v‰rin punaiseksi jos m‰‰r‰ on 20 tai alle
		JButton kahviNappi = new JButton("");
		kahviNappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ja.valmistaKahvi();
				String kahviTila = String.valueOf(ja.getKahvi());
				KahviLabel.setText("Kahvia: " + kahviTila);
				if (ja.getKahvi() <= 20) {
					KahviLabel.setForeground(Color.RED);
				}
			}
		});
		kahviNappi.setBounds(10, 40, 110, 110);
		kahviNappi.setIcon(new ImageIcon(
				"C:\\Users\\Arttu\\eclipse-workspace\\KT3-Juoma Automaatti-GUI-pohja-opiskelijat\\img\\coffee.jpg"));
		kahvi.add(kahviNappi);

		JLabel kahviNappiLabel = new JLabel("Kahvi");
		kahviNappiLabel.setFont(new Font("Lato Semibold", Font.PLAIN, 11));
		kahviNappiLabel.setBounds(40, 161, 46, 14);
		kahvi.add(kahviNappiLabel);

		KahviLabel = new JLabel("Kahvia: " + ja.getKahvi());
		KahviLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		KahviLabel.setBounds(185, 85, 103, 19);
		kahvi.add(KahviLabel);

		JPanel tee = new JPanel();
		contentPane.add(tee);
		tee.setLayout(null);

		// m‰‰ritt‰‰ teenapin joka poistaa tee-arvosta 10 yksikkˆ‰ ja m‰‰ritt‰‰ v‰rin
		// punaiseksi jos m‰‰r‰ on 20 tai alle
		JButton teeNappi = new JButton("");
		teeNappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaTee();
				String teeTila = String.valueOf(ja.getTee());
				TeeLabel.setText("Teet‰: " + teeTila);
				if (ja.getTee() <= 20) {
					TeeLabel.setForeground(Color.RED);
				}
			}
		});
		teeNappi.setBounds(10, 40, 110, 110);
		teeNappi.setIcon(new ImageIcon(
				"C:\\Users\\Arttu\\eclipse-workspace\\KT3-Juoma Automaatti-GUI-pohja-opiskelijat\\img\\tea.jpg"));
		tee.add(teeNappi);

		JLabel teeNappiLabel = new JLabel("Tee");
		teeNappiLabel.setFont(new Font("Lato Semibold", Font.PLAIN, 11));
		teeNappiLabel.setBounds(40, 161, 46, 14);
		tee.add(teeNappiLabel);

		TeeLabel = new JLabel("Teet\u00E4: " + ja.getTee());
		TeeLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		TeeLabel.setBounds(185, 85, 101, 19);
		tee.add(TeeLabel);

		JPanel kaakao = new JPanel();
		contentPane.add(kaakao);
		kaakao.setLayout(null);

		// m‰‰ritt‰‰ kaakaonapin joka poistaa kaakao-arvosta 10 yksikkˆ‰ ja m‰‰ritt‰‰
		// v‰rin punaiseksi jos m‰‰r‰ on 20 tai alle
		JButton kaakaoNappi = new JButton("");
		kaakaoNappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaKaakao();
				String kaakaoTila = String.valueOf(ja.getKaakao());
				KaakaoLabel.setText("Kaakaota: " + kaakaoTila);
				if (ja.getKaakao() <= 20) {
					KaakaoLabel.setForeground(Color.RED);
				}
			}
		});
		kaakaoNappi.setIcon(new ImageIcon(
				"C:\\Users\\Arttu\\eclipse-workspace\\KT3-Juoma Automaatti-GUI-pohja-opiskelijat\\img\\cocoa.jpg"));
		kaakaoNappi.setBounds(10, 40, 110, 110);
		kaakao.add(kaakaoNappi);

		JLabel kaakaoNappiLabel = new JLabel("Kaakao");
		kaakaoNappiLabel.setFont(new Font("Lato Semibold", Font.PLAIN, 11));
		kaakaoNappiLabel.setBounds(40, 161, 46, 14);
		kaakao.add(kaakaoNappiLabel);

		KaakaoLabel = new JLabel("Kaakaota: " + ja.getKaakao());
		KaakaoLabel.setFont(new Font("Lato", Font.PLAIN, 15));
		KaakaoLabel.setBounds(185, 85, 122, 19);
		kaakao.add(KaakaoLabel);

	}

	public JLabel getKahviLabel() {
		return KahviLabel;
	}

	public JLabel getTeeLabel() {
		return TeeLabel;
	}

	public JLabel getKaakaoLabel() {
		return KaakaoLabel;
	}

	void lopetaOhjelma() {
		System.exit(0);
	}
}
