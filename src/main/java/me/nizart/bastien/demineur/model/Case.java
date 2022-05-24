package me.nizart.bastien.demineur.model;

public class Case {
	/**
	 * Case visible ou non.
	 */
	private boolean visible;

	/**
	 * Case contient une mine ou non.
	 */
	private boolean mine;

	/**
	 * Case contient un drapeau ou non.
	 */
	private boolean drapeau;

	/**
	 * Valeur contenue dans la case.
	 */
	private int valeur;

	/**
	 * Coordonnée colonne
	 */
	private final int colonne;

	/**
	 * Coordonnée ligne
	 */
	private final int ligne;

	Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.visible = false;
		this.mine = false;
		this.drapeau = false;
	}

	public boolean equals(Case tuile) {
		return (this.colonne == tuile.getColonne()) && (this.ligne == tuile.getLigne());
	}

	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}

	/**
	 * Ajoute une valeur à la case.
	 * @param valeur valeur à ajouter.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Transforme la case en mine et inversement.
	 * @param mine mine ou case.
	 */
	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setDrapeau(boolean drapeau) {
		this.drapeau = drapeau;
	}

	/**
	 * Vérifie que la case est bien une mine.
	 * @return si la case est une mine.
	 */
	public boolean estUneMine() {
		return mine;
	}

	public boolean estVisible() {
		return this.visible;
	}

	public boolean possedeDrapeau() {
		return this.drapeau;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getValeur() {
		if (drapeau) {
			return "D";
		}

		if (mine) {
			return "M";
		}

		return Integer.toString(valeur);
	}
}
