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

	public void initGrille(Grille grille) {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j=0; j < Grille.DIMENSION; j++) {
				cases[i][j] = new Label();
				cases[i][j].setText("");

				if (grille.getCase(i, j).estVisible()) {
					cases[i][j].setText(grille.getCase(i, j).getValeur());
				}

				cases[i][j].setAlignment(Pos.CENTER);
				cases[i][j].setFont(Font.font(18));
				cases[i][j].setPrefWidth(100);
				cases[i][j].setPrefHeight(100);

				int finalI = i;
				int finalJ = j;
				cases[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
					// Placer drapeau
					if (e.getButton() == MouseButton.SECONDARY) {
						if (!grille.getCase(finalI, finalJ).estVisible()) {
							if (grille.getCase(finalI, finalJ).possedeDrapeau()) {
								grille.getCase(finalI, finalJ).setDrapeau(false);
								model.retirerDrapeau();
							}

							else {
								if (model.drapeauDispo()) {
									grille.getCase(finalI, finalJ).setDrapeau(true);
									model.ajouterDrapeau();
								}
							}
						}

						nbDrapeauLabel.setText("Drapeau(x) : "+model.getNbDrapeaux());
						updateGrille(grille);
					}

					// Découvrir case
					if (e.getButton() == MouseButton.PRIMARY) {
						if (grille.getCase(finalI, finalJ).estUneMine()) {
							finirPartie(grille, false);
						}

						else {
							if ((grille.getCase(finalI, finalJ).possedeDrapeau())) {
								grille.getCase(finalI, finalJ).setDrapeau(false);
								model.ajouterDrapeau();
								nbDrapeauLabel.setText("Drapeau(x) : "+model.getNbDrapeaux());
								System.out.println(grille.getCase(finalI, finalJ).possedeDrapeau());
							}

							//grille.getCase(finalI, finalJ).setVisible(true);
							model.revelerCases(finalI, finalJ);
						}

						updateGrille(grille);
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
	public void finirPartie(Grille grille, boolean gagne) {
		boutonRelancer.setDisable(false);
		model.endPartie();
		updateGrille(grille);

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
	public void updateGrille(Grille grille) {
		for (int i=0; i < Grille.DIMENSION; i++) {
			for (int j = 0; j < Grille.DIMENSION; j++) {
				if ((grille.getCase(i, j).estVisible()) || (grille.getCase(i, j).possedeDrapeau())) {
					cases[i][j].setText(grille.getCase(i, j).getValeur());
				}
				else {
					cases[i][j].setText("");
				}
			}
		}

		if (model.partieGagnee()) {
			finirPartie(grille, true);
		}
	}

}