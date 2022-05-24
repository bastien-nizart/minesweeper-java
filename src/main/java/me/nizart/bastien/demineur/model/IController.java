package me.nizart.bastien.demineur.model;

public interface IController {
	void setModel(Demineur facade);

	void initGrille(Grille grille);

	void commencerPartie();

	void relancer();

	void finirPartie(Grille grille, boolean gagne);

	void updateGrille(Grille grille);
}
