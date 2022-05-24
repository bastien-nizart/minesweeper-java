package me.nizart.bastien.demineur.model;

import me.nizart.bastien.demineur.controller.DemineurController;

public class Demineur {
	private Grille grille;
	private IController controller;

	private int nbDrapeaux;

	public void startPartie() {
		this.nbDrapeaux= 0;
		this.grille = new Grille();
		controller.initGrille(grille);
	}

	public void endPartie() {
		this.grille.retirerTousDrapeaux();
		this.grille.tousAfficher();
	}

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

	public void ajouterDrapeau() {
		this.nbDrapeaux++;
	}

	public boolean drapeauDispo() {
		return (Grille.NB_MINES - nbDrapeaux > 0);
	}

	public int getNbDrapeaux() {
		return Grille.NB_MINES - nbDrapeaux;
	}

	public void retirerDrapeau() {
		this.nbDrapeaux--;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}
}
