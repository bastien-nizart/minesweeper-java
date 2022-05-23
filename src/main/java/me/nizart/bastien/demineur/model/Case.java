package me.nizart.bastien.demineur.model;

public class Case {
	/**
	 * Case visible ou non.
	 */
	private boolean visible = false;

	/**
	 * Case contient une mine ou non.
	 */
	private boolean mine = false;

	/**
	 * Case contient un drapeau ou non.
	 */
	private boolean drapeau = false;

	/**
	 * Valeur contenue dans la case.
	 */
	private int valeur = -1;

	/**
	 * Ajoute une valeur à la case.
	 * @param valeur valeur à ajouter.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Transforme la case en mine et inversement.
	 * @param statut mine ou case.
	 */
	public void setMine(boolean statut) {
		this.mine = statut;
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
