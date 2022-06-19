package me.nizart.bastien.demineur.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import me.nizart.bastien.demineur.model.Demineur;
import me.nizart.bastien.demineur.model.Grille;
import me.nizart.bastien.demineur.model.IController;

/**
 * Controller principal de l'application.
 * Il permet de gérer les interactions entre la vue et le model.
 * @author bastien
 * @version 1.0
 */
public class DemineurController implements IController {
	/**
	 * Grille contenant les labels du jeu.
	 */
	@FXML
	private GridPane grilleJeu;

	/**
	 * Label contenant le message de fin de partie.
	 */
	@FXML
	private Label messageFin;

	/**
	 * Label contenant le nombre de drapeaux restant à poser.
	 */
	@FXML
	private Label nbDrapeauLabel;

	/**
	 * Bouton permettant de relancer une partie.
	 */
	@FXML
	private Button boutonRelancer;

	/**
	 * Listes des cases du jeu.
	 */
	private Label[][] cases = new Label[Grille.DIMENSION][Grille.DIMENSION];

	/**
	 * Facade du model.
	 */
	private Demineur model;

	/**
	 * Permet de lier le model au controller.
	 * @param facade model à lier.
	 */
	@Override
	public void setModel(Demineur facade) {
		this.model = facade;
	}

	/**
	 * Initialise les labels représentant la grille.
	 */
	@Override
	public void initGrille() {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j=0; j < Grille.DIMENSION; j++) {
				// Création
				cases[i][j] = new Label();
				cases[i][j].setText("");

				// Attribution de la valeur visible
				cases[i][j].setText(model.getValeurCase(i, j));

				// Propriété graphique
				cases[i][j].setAlignment(Pos.CENTER);
				cases[i][j].setFont(Font.font(18));
				cases[i][j].setPrefSize(100, 100);

				int finalI = i;
				int finalJ = j;
				cases[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
					// Placer drapeau
					if (e.getButton() == MouseButton.SECONDARY) {
						model.poserDrapeau(finalI, finalJ);
						nbDrapeauLabel.setText(model.getNbDrapeaux());
						updateGrille();
					}

					// Découvrir case
					if (e.getButton() == MouseButton.PRIMARY) {
						model.decouvrirCase(finalI, finalJ);
						nbDrapeauLabel.setText(model.getNbDrapeaux());
						updateGrille();
					}
				});

				grilleJeu.add(cases[i][j], j, i);
			}
		}
		grilleJeu.setGridLinesVisible(true);
		nbDrapeauLabel.setText(model.getNbDrapeaux());
	}

	/**
	 * Permet de commencer une partie.
	 */
	@Override
	public void commencerPartie() {
		messageFin.setText("");
		model.startPartie();
		boutonRelancer.setDisable(false);
	}

	/**
	 * Permet de relancer une partie.
	 */
	@Override
	public void relancer() {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j = 0; j < Grille.DIMENSION; j++) {
				cases[i][j].setText("");
			}
		}

		commencerPartie();
	}

	/**
	 * Permet de terminer une partie.
	 * Et de personnaliser le message de fin.
	 * @param gagne si le joueur a gagné.
	 */
	@Override
	public void finirPartie(boolean gagne) {
		boutonRelancer.setDisable(false);
		model.endPartie();
		updateGrille();

		if (gagne) {
			messageFin.setText("Bravo !");
		}

		else {
			messageFin.setText("Dommage ...");
		}

		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j = 0; j < Grille.DIMENSION; j++) {
				cases[i][j].setDisable(true);
			}
		}
	}

	/**
	 * Update la grille de jeu.
	 * À executer avant chaque action.
	 */
	@Override
	public void updateGrille() {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j = 0; j < Grille.DIMENSION; j++) {
				cases[i][j].setText(model.getValeurCase(i, j));
			}
		}

		if (model.partieGagnee()) {
			finirPartie(true);
		}
	}

}