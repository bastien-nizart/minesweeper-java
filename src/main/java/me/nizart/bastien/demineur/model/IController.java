package me.nizart.bastien.demineur.model;

/**
 * Interface du controller.
 * Permettant de vérifier que toutes les méthodes essentielles sont présentes.
 * @author bastien
 * @version 1.0
 */
public interface IController {
	/**
	 * Permet de lier le model au controller.
	 * @param facade model à lier.
	 */
	void setModel(Demineur facade);

	/**
	 * Initialise les labels représentant la grille.
	 */
	void initGrille();

	/**
	 * Permet de commencer une partie.
	 */
	void commencerPartie();

	/**
	 * Permet de relancer une partie.
	 */
	void relancer();

	/**
	 * Permet de terminer une partie.
	 * Et de personnaliser le message de fin.
	 * @param gagne si le joueur a gagné.
	 */
	void finirPartie(boolean gagne);

	/**
	 * Update la grille de jeu.
	 * À executer avant chaque action.
	 */
	void updateGrille();
}
