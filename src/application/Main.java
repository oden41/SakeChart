package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// (タブオーダーもFXMLの定義順になる.)
			// (FXML中に「@参照」による相対位置指定がある場合は、このURL相対位置となる.)
			URL fxml = getClass().getResource(getClass().getSimpleName() + ".fxml");

			// FXMLをロードする.
			// (ローカライズしない場合はリソースバンドルを指定しなくて良い)
			FXMLLoader ldr = new FXMLLoader(fxml, null);

			// このインスタンス自身をコントローラとする.
			// @FXMLアノテーションによりFXMLと結び付けられる.
			ldr.setController(this);

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Chart.fxml"));
			Scene scene = new Scene(root, 800, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
