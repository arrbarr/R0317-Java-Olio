package JuomaAutomaatti;

import java.util.Scanner;

public class Kone {
	static JuomaAutomaatti omaKone = new JuomaAutomaatti();
	static Scanner lukija = new Scanner(System.in);

	public static void Automaatti() {

		int valinta = 0;

		if (valinta >= 0 && valinta <= 3) {
			System.out.println("*******Juoma-Automaatti*******");
			System.out.println();
			System.out.println("1. Kahvi" + '\n' + "2. Tee" + '\n' + "3. Kaakao" + '\n' + "4. Lopeta" + '\n');
			System.out.println("******************************");
			valinta = lukija.nextInt();
			if (valinta == 1) {
				omaKone.valmistaKahvi();
				Automaatti();
			}
			if (valinta == 2) {
				omaKone.valmistaTee();
				Automaatti();
			}
			if (valinta == 3) {
				omaKone.valmistaKaakao();
				Automaatti();
			}
		} else {
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		Automaatti();

	}
}
