package me.nizart.bastien.demineur.model;

public interface IController {
	void setModel(Demineur facade);

	void initGrille();

	void commencerPartie();

	void relancer();

	void finirPartie(boolean gagne);

	void updateGrille();
}
