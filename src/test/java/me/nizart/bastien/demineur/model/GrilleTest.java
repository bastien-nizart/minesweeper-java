package me.nizart.bastien.demineur.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {
	private Grille grilleTest;

	@BeforeEach
	void genererGrille() {
		this.grilleTest = new Grille();
	}

	@AfterEach
	void resetGrille() {
		this.grilleTest = null;
	}

	@Test
	@DisplayName("Vérifie le nombre de mines placées")
	void testNombreMinesPlacee() {
		int cpt = 0;

		for (Case[] cases : grilleTest.getCases()) {
			for (Case tuile : cases) {
				if (tuile.estUneMine()) {
					cpt++;
				}
			}
		}

		assertEquals(Grille.NB_MINES, cpt);
	}
}