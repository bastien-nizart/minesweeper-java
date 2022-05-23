package me.nizart.bastien.demineur.model;

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
				this.cases[i][j] = new Case();
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

	private int calculerValeurCase(int ligne, int colonne) {
		int valeur = 0;

		// Rangée du dessus
		if (ligne > 0) {
			// i-1, j-1
			if (colonne > 0) {
				if (this.cases[ligne-1][colonne-1].estUneMine()) {
					valeur++;
				}
			}

			// i-1, j
			if (this.cases[ligne-1][colonne].estUneMine()) {
				valeur++;
			}

			// i-1, j+1
			if (colonne < DIMENSION-1) {
				if (this.cases[ligne-1][colonne+1].estUneMine()) {
					valeur++;
				}
			}
		}

		// Rangée actuelle
		// i-1, j-1
		if (colonne > 0) {
			if (this.cases[ligne][colonne-1].estUneMine()) {
				valeur++;
			}
		}

		// i-1, j+1
		if (colonne < DIMENSION-1) {
			if (this.cases[ligne][colonne+1].estUneMine()) {
				valeur++;
			}
		}

		// Rangée du dessous
		if (ligne < DIMENSION-1) {
			// i+1, j-1
			if (colonne > 0) {
				if (this.cases[ligne+1][colonne-1].estUneMine()) {
					valeur++;
				}
			}

			// i+1, j
			if (this.cases[ligne+1][colonne].estUneMine()) {
				valeur++;
			}

			// i+1, j+1
			if (colonne < DIMENSION-1) {
				if (this.cases[ligne+1][colonne+1].estUneMine()) {
					valeur++;
				}
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
}
