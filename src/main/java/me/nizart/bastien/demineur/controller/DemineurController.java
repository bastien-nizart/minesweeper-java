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

public class DemineurController implements IController {
	@FXML
	private GridPane grilleJeu;

	@FXML
	private Label messageFin;

	@FXML
	private Label nbDrapeauLabel;

	@FXML
	private Button boutonRelancer;

	private Label[][] cases = new Label[Grille.DIMENSION][Grille.DIMENSION];

	private Demineur model;

	public void setModel(Demineur facade) {
		this.model = facade;
	}

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
				cases[i][j].setPrefWidth(100);
				cases[i][j].setPrefHeight(100);

				int finalI = i;
				int finalJ = j;
				cases[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
					// Placer drapeau
					if (e.getButton() == MouseButton.SECONDARY) {
						if (!model.estVisible(finalI, finalJ) && model.possedeDrapeau(finalI, finalJ)) {
							model.setDrapeau(finalI, finalJ, false);
							model.retirerDrapeau();
						}

						if (!model.estVisible(finalI, finalJ) && !model.possedeDrapeau(finalI, finalJ) && model.drapeauDispo()) {
							model.setDrapeau(finalI, finalJ, true);
							model.ajouterDrapeau();
						}

						nbDrapeauLabel.setText("Drapeau(x) : "+model.getNbDrapeaux());
						updateGrille();
					}

					// Découvrir case
					if (e.getButton() == MouseButton.PRIMARY) {
						if (model.estUneMine(finalI, finalJ)) {
							finirPartie(false);
						}

						if (model.possedeDrapeau(finalI, finalJ) && !model.estUneMine(finalI, finalJ)) {
							model.setDrapeau(finalI, finalJ, false);
							model.retirerDrapeau();
							nbDrapeauLabel.setText("Drapeau(x) : "+model.getNbDrapeaux());
						}

						model.revelerCases(finalI, finalJ);
						updateGrille();
					}
				});

				grilleJeu.add(cases[i][j], j, i);
			}
		}
		grilleJeu.setGridLinesVisible(true);
		nbDrapeauLabel.setText("Drapeau(x) : "+model.getNbDrapeaux());
	}

	@Override
	public void commencerPartie() {
		messageFin.setText("");
		model.startPartie();
		boutonRelancer.setDisable(false);
	}

	@Override
	public void relancer() {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j = 0; j < Grille.DIMENSION; j++) {
				cases[i][j].setText("");
			}
		}

		commencerPartie();
	}

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