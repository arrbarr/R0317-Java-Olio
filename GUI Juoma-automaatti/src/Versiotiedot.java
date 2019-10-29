import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class Versiotiedot extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Versiotiedot frame = new Versiotiedot();
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
	public Versiotiedot() {
		setTitle("Juoma-automaatti GUI v. 1.0");
		//DISPOSE_ON_CLOSE sulkee vain tämän ikkunan eikä koko ohjelmaa 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJuomaautomaattiV = new JLabel("Juoma-automaatti v. 1.0");
		lblJuomaautomaattiV.setFont(new Font("Lato", Font.PLAIN, 20));
		lblJuomaautomaattiV.setBounds(107, 93, 219, 41);
		contentPane.add(lblJuomaautomaattiV);
		
		JLabel lblTekijArttuPahkin = new JLabel("Tekij\u00E4: Arttu Pahkin");
		lblTekijArttuPahkin.setFont(new Font("Lato", Font.PLAIN, 13));
		lblTekijArttuPahkin.setBounds(153, 145, 128, 21);
		contentPane.add(lblTekijArttuPahkin);
	}
}
