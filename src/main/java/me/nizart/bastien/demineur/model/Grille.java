package me.nizart.bastien.demineur.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille {
	/**
	 * Ensemble des cases
	 */
	private Case[][] cases = new Case[DIMENSION][DIMENSION];

	/**
	 * Dimension de la grille
	 */
	public static final int DIMENSION = 10;

	/**
	 * Nombre de mines à placer
	 */
	public static final int NB_MINES = (int) (DIMENSION*DIMENSION*0.2);

	/**
	 * Constructeur principal d'une grille.
	 */
	Grille() {
		this.initialiser();
	}

	/**
	 * Getteur de l'ensemble des cases du jeu.
	 * @return ensemble des cases du jeu.
	 */
	public Case[][] getCases() {
		return this.cases;
	}

	public Case getCase(int ligne, int colonne) {
		if ((ligne >= DIMENSION) || (colonne >= DIMENSION)) {
			return null;
		}

		return this.cases[ligne][colonne];
	}

	private void initialiser() {
		Random randomGenerateur = new Random();

		// Initialisation cases
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				this.cases[i][j] = new Case(i, j);
			}
		}

		// Initialisation mines
		int minesAPlacer = Grille.NB_MINES;

		while (minesAPlacer > 0) {
			int i = randomGenerateur.nextInt(0, Grille.DIMENSION-1);
			int j = randomGenerateur.nextInt(0, Grille.DIMENSION-1);

			if (!this.cases[i][j].estUneMine()) {
				this.cases[i][j].setMine(true);
				minesAPlacer--;
			}
		}

		// Initialisation des valeurs
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				if (!this.cases[i][j].estUneMine()) {
					this.cases[i][j].setValeur(this.calculerValeurCase(i, j));
				}
			}
		}
	}

	public List<Case> getCasesVoisines(int ligne, int colonne) {
		List<Case> voisines = new ArrayList<>();

		if (ligne > 0) {
			voisines.add(this.getCase(ligne-1, colonne));

			if (colonne > 0) {
				voisines.add(this.getCase(ligne-1, colonne-1));
			}

			if (colonne < DIMENSION-1) {
				voisines.add(this.getCase(ligne-1, colonne+1));
			}
		}

		if (colonne > 0) {
			voisines.add(this.getCase(ligne, colonne-1));
		}

		if (colonne < DIMENSION-1) {
			voisines.add(this.getCase(ligne, colonne+1));
		}

		if (ligne < DIMENSION-1) {
			voisines.add(this.getCase(ligne+1, colonne));

			if (colonne > 0) {
				voisines.add(this.getCase(ligne+1, colonne-1));
			}

			if (colonne < DIMENSION-1) {
				voisines.add(this.getCase(ligne+1, colonne+1));
			}
		}

		return voisines;
	}

	private int calculerValeurCase(int ligne, int colonne) {
		int valeur = 0;
		List<Case> voisines = getCasesVoisines(ligne, colonne);

		for (Case voisine : voisines) {
			if (voisine.estUneMine()) {
				valeur++;
			}
		}

		return valeur;
	}

	public void tousAfficher() {
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				this.cases[i][j].setVisible(true);
			}
		}
	}

	public void retirerTousDrapeaux() {
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				this.cases[i][j].setDrapeau(false);
			}
		}
	}

	public List<Case> getMines() {
		List<Case> mines = new ArrayList<>();
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				if (this.cases[i][j].estUneMine()) {
					mines.add(this.cases[i][j]);
				}
			}
		}

		return mines;
	}

	public List<Case> getDrapeaux() {
		List<Case> drapeaux = new ArrayList<>();
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				if (this.cases[i][j].possedeDrapeau()) {
					drapeaux.add(this.cases[i][j]);
				}
			}
		}

		return drapeaux;
	}
}
