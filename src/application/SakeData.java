package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SakeData {
	private StringProperty name;
	private DoubleProperty SMV;
	private DoubleProperty acidity;
	private StringProperty tasty;

	SakeData(String name, double SMV, double acidity, String tasty) {
		this.name = new SimpleStringProperty(name);
		this.SMV = new SimpleDoubleProperty(SMV);
		this.acidity = new SimpleDoubleProperty(acidity);
		this.tasty = new SimpleStringProperty(tasty);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public DoubleProperty SMVProperty() {
		return SMV;
	}

	public DoubleProperty acidityProperty() {
		return acidity;
	}

	public StringProperty tastyProperty() {
		return tasty;
	}
}
