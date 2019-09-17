package JuomaAutomaatti;

public class JuomaAutomaatti {
	private int teetä, kahvia, kaakaota;

	public int getTeetä() {
		return teetä;
	}

	public void setTeetä(int teetä) {
		this.teetä = teetä;
	}

	public int getKahvia() {
		return kahvia;
	}

	public void setKahvia(int kahvia) {
		this.kahvia = kahvia;
	}

	public int getKaakaota() {
		return kaakaota;
	}

	public void setKaakaota(int kaakaota) {
		this.kaakaota = kaakaota;
	}

	public boolean onnistuuko() {
		boolean check;
		int luku = (int) (Math.random() * 100 + 1);

		if (luku >= 1 && luku <= 25) {
			check = false;
		} else {
			check = true;
		}
		return check;
	}

	public JuomaAutomaatti() {
		teetä = 50;
		kahvia = 50;
		kaakaota = 50;
	}

	public JuomaAutomaatti(int tee, int kahvi, int kaakao) {
		teetä = tee;
		kahvia = kahvi;
		kaakaota = kaakao;
	}

	public void valmistaKahvi() {
		if (onnistuuko()) {
			if (kahvia > 0) {
				System.out.println("Odota hetki, kahvisi valmistuu");
				this.kahvia -= 10;
				System.out.println(toString());
				System.out.println();
			} else {
				System.out.println("Kahvia ei ole enää jäljellä! Täytä säiliö");
				System.out.println();
			}
		} else {
			System.out.println("Ei onnistu, kiitos kuitenkin maksustasi");
			System.out.println();
			Kone.Automaatti();
		}
	}

	public void valmistaTee() {
		if (onnistuuko()) {
			if (teetä > 0) {
				System.out.println("Odota hetki, teesi valmistuu");
				this.teetä -= 10;
				System.out.println(toString());
				System.out.println();
			} else {
				System.out.println("Teetä ei ole enää jäljellä! Täytä säiliö");
				System.out.println();
			}
		} else {
			System.out.println("Ei onnistu, kiitos kuitenkin maksustasi");
			System.out.println();
			Kone.Automaatti();
		}
	}

	public void valmistaKaakao() {
		if (onnistuuko()) {
			if (kaakaota > 0) {
				System.out.println("Odota hetki, kaakaosi valmistuu");
				this.kaakaota -= 10;
				System.out.println(toString());
				System.out.println();
			} else {
				System.out.println("Kaakaota ei ole enää jäljellä! Täytä säiliö");
				System.out.println();
			}
		} else {
			System.out.println("Ei onnistu, kiitos kuitenkin maksustasi");
			System.out.println();
			Kone.Automaatti();
		}
	}

	@Override
	public String toString() {
		return "Teetä jäljellä=" + teetä + ", kahvia jäljellä=" + kahvia + ", kaakaota jäljellä=" + kaakaota;
	}
	

}
