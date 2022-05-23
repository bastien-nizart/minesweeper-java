module me.nizart.bastien.demineur {
	requires javafx.controls;
	requires javafx.fxml;


	opens me.nizart.bastien.demineur to javafx.fxml;
	exports me.nizart.bastien.demineur;
	exports me.nizart.bastien.demineur.controller;
	opens me.nizart.bastien.demineur.controller to javafx.fxml;
}