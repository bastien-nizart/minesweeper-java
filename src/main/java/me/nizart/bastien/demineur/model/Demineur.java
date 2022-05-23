package me.nizart.bastien.demineur.model;

import me.nizart.bastien.demineur.controller.DemineurController;

public class Demineur {
	private Grille grille;
	private DemineurController controller;

	private int nbDrapeaux;

	public void startPartie() {
		this.nbDrapeaux= 0;
		this.grille = new Grille();
		controller.initGrille(grille);
	}

	public void endPartie() {
		this.grille.tousAfficher();
	}

	public void ajouterDrapeau() {
		this.nbDrapeaux++;
	}

	public boolean drapeauDispo() {
		return (Grille.NB_MINES - nbDrapeaux > 0);
	}

	public int getNbDrapeaux() {
		return (Grille.NB_MINES - nbDrapeaux);
	}

	public void retirerDrapeau() {
		this.nbDrapeaux--;
	}

	public void setController(DemineurController controller) {
		this.controller = controller;
	}
}
