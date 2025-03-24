import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

@SuppressWarnings("unchecked")
public class Monde {
	private ArrayList<Human>[][] Matrix;
	private int nbrI;
	private int nbrE;
	private int nbrS;
	private int nbrR;

	public Monde(MTRandom random) {

		this.Matrix = new ArrayList[300][300];
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 300; j++) {
				this.Matrix[i][j] = new ArrayList<Human>();
			}
		}

		for (long j = 0; j < 20000; j++) {

			int X = Math.abs(random.genRandInt32()) % 300;
			int Y = Math.abs(random.genRandInt32()) % 300;

			Human new_Human = new Human('S', X, Y, random);
			new_Human.setDCurrent(0);

			if (j > 19979) {
				new_Human.setState('I');
				new_Human.setDCurrent(new_Human.getDI());

			}

			this.Matrix[X][Y].add(new_Human);
			// int nbr_H = this.Matrix[X][Y].size();

		}
		// System.out.println("INIT " + this.Matrix[0][0] + " ET "+ this.Matrix[0][1]);

	}

	// GETTER
	public ArrayList<Human>[][] getMatrix() {
		return this.Matrix;
	}
	// pas besoin si déja initialisé au moment de la création de l'humain

	public void updateState(Human humain, MTRandom random) {

		if (humain.getDCurrent() > 0) {

			humain.setDCurrent(humain.getDCurrent() - 1);
			return;
		} else {
			if (humain.getState() == 'S') {
				int size_current, i_actual, j_actual;
				int near_infected = 0;
				for (int i = -1; i < 2; i++) {
					i_actual = (humain.getX() + i + 300) % 300;
					for (int j = -1; j < 2; j++) {
						j_actual = (humain.getY() + j + 300) % 300;
						size_current = this.Matrix[i_actual][j_actual].size();
						for (int k = 0; k < size_current; k++) {

							if (this.Matrix[i_actual][j_actual].get(k).getState() == 'I') {
								near_infected++;
							}
						}
					}
					if (near_infected != 0) {
						double proba = 1 - Math.exp(-0.5 * near_infected);
						if (random.genRand1() < proba) {
							humain.setState('E');
							humain.setDCurrent(humain.getDE());

							// humain.setDCurrent(stateLife('I',random));
						}
					}
				}
			} else if (humain.getState() == 'E') {
				humain.setState('I');
				humain.setDCurrent(humain.getDI());

				// humain.setDCurrent(stateLife('I',random));
			} else if (humain.getState() == 'I') {
				humain.setState('R');
				humain.setDCurrent(humain.getDR());
				// humain.setDCurrent(stateLife('R',random));
			} else {
				humain.setState('S');

			}

		}
	}

	public void Deplacer(MTRandom random) {
		int X = -1, Y = -1;
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 300; j++) {

				while (X < 0 || Y < 0) {
					X = random.genRandInt32() % 300;
					Y = random.genRandInt32() % 300;
				}
				int nbr_H = this.Matrix[i][j].size();

				for (int k = nbr_H - 1; k >= 0; k--) {

					Human current_H = this.Matrix[i][j].get(k);

					if (!current_H.getTag()) {
						// Alors on change les coordonnées
						this.Matrix[i][j].remove(k);

						current_H.setX(X);
						current_H.setY(Y);
						current_H.setTag(true);
						this.Matrix[X][Y].add(current_H);

					}
					X = random.genRandInt32() % 300;
					Y = random.genRandInt32() % 300;

				}

			}
		}
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 300; j++) {

				for (int k = 0; k < this.Matrix[i][j].size(); k++) {

					Human current_H = this.Matrix[i][j].get(k);

					if (current_H.getTag()) {

						current_H.setTag(false);
					}
				}

			}
		}

		// System.out.println(this.Matrix[0][0] + " et " + this.Matrix[0][1]);

	}

	public void nextday(MTRandom random) {
		Deplacer(random);
		setAllZero();
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 300; j++) {

				for (int k = 0; k < this.Matrix[i][j].size(); k++) {

					Human current_H = this.Matrix[i][j].get(k);
					updateState(current_H, random);
					if (current_H.getState() == 'I') {
						this.nbrI++;
					} else if (current_H.getState() == 'E') {
						this.nbrE++;
					} else if (current_H.getState() == 'R') {
						this.nbrR++;
					} else {
						this.nbrS++;
					}
				}

			}
		}
		// return "S:"+S+","+"E:"+E+","+"I:"+I+","+"R:"+R;

	}

	public void setAllZero() {
		this.nbrI = 0;
		this.nbrS = 0;
		this.nbrE = 0;
		this.nbrR = 0;
	}

	public int getNbrI() {
		return this.nbrI;
	}

	public int getNbrS() {
		return this.nbrS;
	}

	public int getNbrE() {
		return this.nbrE;
	}

	public int getNbrR() {
		return this.nbrR;
	}

	public static void main(String[] args) {
		/*
		 * MTRandom rand = new MTRandom(50);
		 * Monde hello = new Monde(rand);
		 * hello.nextday(rand);
		 * //System.out.print("");
		 * hello.nextday(rand);
		 * 
		 * hello.nextday(rand);
		 */
	}

}
