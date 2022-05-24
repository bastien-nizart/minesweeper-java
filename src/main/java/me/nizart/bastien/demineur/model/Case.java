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

	/**
	 * Constructeur principal d'une case.
	 * @param ligne ligne où se trouve la case.
	 * @param colonne colonne où se trouve la case.
	 */
	Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.visible = false;
		this.mine = false;
		this.drapeau = false;
	}

	/**
	 * Vérifie que deux cases soient égales.
	 * @param tuile case à comparer.
	 * @return si les cases sont égales.
	 */
	public boolean equals(Case tuile) {
		return (this.colonne == tuile.getColonne()) && (this.ligne == tuile.getLigne());
	}

	/**
	 * Retourne la colonne de la case.
	 * @return colonne de la case.
	 */
	public int getColonne() {
		return this.colonne;
	}

	/**
	 * Retourne la ligne de la case.
	 * @return ligne de la case.
	 */
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

	/**
	 * Instancie le statut du drapeau.
	 * @param drapeau statut du drapeau.
	 */
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

	/**
	 * Vérifie que la case soit visible.
	 * @return si la case est visible.
	 */
	public boolean estVisible() {
		return this.visible;
	}

	/**
	 * Vérifie si la case possède un drapeau.
	 * @return si la case possède un drapeau.
	 */
	public boolean possedeDrapeau() {
		return this.drapeau;
	}

	/**
	 * Instancie la visibilité de la case.
	 * @param visible visibilité de la case.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Retourne la valeur de la case.
	 * @return valeur de la case.
	 */
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
