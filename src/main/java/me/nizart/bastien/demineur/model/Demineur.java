package me.nizart.bastien.demineur.model;

/**
 * Cette classe représente un objet de type Demineur (facade du model)
 * Il s'agit en fait de la représentation d'une partie de jeu.
 * Partie propre à une grille et un nombre de drapeau.
 * @author bastien
 * @version 1.0
 */
public class Demineur {
	/**
	 * Grille de jeu.
	 */
	private Grille grille;

	/**
	 * Controlleur principal de l'application.
	 */
	private IController controller;

	/**
	 * Nombre de drapeaux posés durant la partie.
	 */
	private int nbDrapeaux;

	/**
	 * Méthode permettant de démarrer une partie.
	 */
	public void startPartie() {
		this.nbDrapeaux = 0;
		this.grille = new Grille();
		controller.initGrille();
	}

	public boolean estVisible(int ligne, int colonne) {
		return grille.getCase(ligne, colonne).estVisible();
	}

	public boolean possedeDrapeau(int ligne, int colonne) {
		return grille.getCase(ligne, colonne).possedeDrapeau();
	}

	public boolean estUneMine(int ligne, int colonne) {
		return grille.getCase(ligne, colonne).estUneMine();
	}

	/**
	 * Renvoie la valeur affichable de la case.
	 * @param ligne ligne de la case à récupérer.
	 * @param colonne colonne de la case à récupérer.
	 * @return valeur de la case.
	 */
	public String getValeurCase(int ligne, int colonne) {
		if ((grille.getCase(ligne, colonne).estVisible()) || (grille.getCase(ligne, colonne).possedeDrapeau())) {
			return grille.getCase(ligne, colonne).getValeur();
		}

		return "";
	}

	/**
	 * Méthode mettant fin à la partie.
	 */
	public void endPartie() {
		this.grille.retirerTousDrapeaux();
		this.grille.tousAfficher();
	}

	/**
	 * Méthode vérifiant si la partie est gagnée.
	 * La partie est gagnée si tous les drapeaux sont placés sur toutes les mines.
	 * @return si la partie est gagnée.
	 */
	public boolean partieGagnee() {
		if (nbDrapeaux < Grille.NB_MINES) {
			return false;
		}

		boolean test;

		for (Case mine : grille.getMines()) {
			test = false;
			for (Case drapeau : grille.getDrapeaux()) {
				if (mine.equals(drapeau)) {
					test = true;
				}
			}

			if (!test) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Cette méthode va révéler la case mise en paramètre.
	 * Ainsi que les cases voisines si elles sont vides.
	 * @param ligne ligne de la case à révéler.
	 * @param colonne colonne de la case à révéler.
	 */
	public void revelerCases(int ligne, int colonne) {
		Case caseActuelle = grille.getCase(ligne, colonne);
		caseActuelle.setVisible(true);

		if (caseActuelle.getValeur().equals("0")) {
			for (Case voisine : grille.getCasesVoisines(ligne, colonne)) {
				if (voisine.getValeur().equals("0")) {
					voisine.setVisible(true);
				}
			}
		}
	}

	/**
	 * Modifier la valeur du drapeau d'une case.
	 */
	public void setDrapeau(int ligne, int colonne, boolean drapeau) {
		this.grille.getCase(ligne, colonne).setDrapeau(drapeau);
	}

	public void ajouterDrapeau() {
		this.nbDrapeaux++;
	}

	public void retirerDrapeau() {
		this.nbDrapeaux--;
	}

	/**
	 * Vérifie qu'il reste des drapeaux à placer.
	 * @return si il reste des drapeaux à placer.
	 */
	public boolean drapeauDispo() {
		return (Grille.NB_MINES - nbDrapeaux > 0);
	}

	/**
	 * Cette méthode retourne le nombre de drapeaux restant à placer.
	 * @return nombre de drapeaux à placer.
	 */
	public int getNbDrapeaux() {
		return Grille.NB_MINES - nbDrapeaux;
	}

	/**
	 * Setteur du controller principal du projet.
	 * @param controller à set.
	 */
	public void setController(IController controller) {
		this.controller = controller;
	}
}
