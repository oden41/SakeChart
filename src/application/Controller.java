package application;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class Controller {

	@FXML
	private ScatterChart<Double, Double> chart;

	@FXML
	private Pane panel;

	public Controller() {
	}

	@FXML
	void initialize() {
		ObjectProperty<Background> property = chart.backgroundProperty();
	}

	@FXML
	public void onEvent(ActionEvent e) {

	}
}
