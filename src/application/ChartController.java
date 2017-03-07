package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;

public class ChartController {

	@FXML
	private NumberAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	private ScatterChart<Double, Double> chart;

	@FXML
	private Pane panel;

	public ChartController() {
	}

	@FXML
	void initialize() {
		chart.getXAxis().setTickLabelsVisible(false);
		chart.getXAxis().setOpacity(0);

		chart.getYAxis().setTickLabelsVisible(false);
		chart.getYAxis().setOpacity(0);

		chart.setLegendVisible(false);

		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(-30);
		xAxis.setUpperBound(30);
		xAxis.setTickUnit(5);

		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0.2);
		yAxis.setUpperBound(2.8);
		yAxis.setTickUnit(0.2);

		//csvからデータ取り込み
		Series<Double, Double> series = new Series<>();
		try {
			//ファイルを読み込む
			FileReader fr = new FileReader("data.csv");
			BufferedReader br = new BufferedReader(fr);
			//読み込んだファイルを１行ずつ処理する
			String line;
			StringTokenizer token;
			boolean isinit = true;
			while ((line = br.readLine()) != null) {
				if (isinit) {
					isinit = false;
					continue;
				}
				//区切り文字","で分割する
				token = new StringTokenizer(line, ",");

				//分割した文字を画面出力する
				while (token.hasMoreTokens()) {
					double nihonshudo = Double.parseDouble(token.nextToken());
					double sando = Double.parseDouble(token.nextToken());
					series.getData().add(new XYChart.Data<Double, Double>(nihonshudo, sando));
				}
			}

			//終了処理
			br.close();

		} catch (IOException ex) {
			//例外発生時処理
			ex.printStackTrace();
		}
		ObservableList<XYChart.Series<Double, Double>> seriesList = FXCollections.observableArrayList();
		seriesList.addAll(series);
		chart.getData().addAll(seriesList);
	}
}
