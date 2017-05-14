package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ChartController {

	@FXML
	private NumberAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	private ScatterChart<Double, Double> chart;

	@FXML
	private Pane panel;

	//テーブル
	@FXML
	private TableView<SakeData> tableView;
	@FXML
	private TableColumn<SakeData, String> nameCol;
	@FXML
	private TableColumn<SakeData, Double> SMVCol;
	@FXML
	private TableColumn<SakeData, Double> acidityCol;
	@FXML
	private TableColumn<SakeData, String> tastyCol;

	private ObservableList<SakeData> tableRecord = FXCollections.observableArrayList();

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

		nameCol.setCellValueFactory(new PropertyValueFactory<SakeData, String>("name"));
		SMVCol.setCellValueFactory(new PropertyValueFactory<SakeData, Double>("SMV"));
		acidityCol.setCellValueFactory(new PropertyValueFactory<SakeData, Double>("acidity"));
		tastyCol.setCellValueFactory(new PropertyValueFactory<SakeData, String>("tasty"));

		//csvからデータ取り込み
		Series<Double, Double> series = new Series<>();
		ArrayList<String> nameList = new ArrayList<>();
		try {
			//ファイルを読み込む
			FileReader fr = new FileReader("data/data.csv");
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
					String name = token.nextToken();
					double nihonshudo = Double.parseDouble(token.nextToken()) * (-1);
					double sando = Double.parseDouble(token.nextToken());
					String tasty = token.nextToken();
					nameList.add(name);
					series.getData().add(new XYChart.Data<Double, Double>(nihonshudo, sando));
					tableRecord.add(new SakeData(name, -nihonshudo, sando, tasty));
				}
			}

			//終了処理
			br.close();

		} catch (IOException ex) {
			//例外発生時処理
			ex.printStackTrace();
		}

		tableView.setItems(tableRecord);
		ObservableList<XYChart.Series<Double, Double>> seriesList = FXCollections.observableArrayList();
		seriesList.addAll(series);
		chart.getData().addAll(seriesList);

		// ToolTipを表示
		// データをchartにセットした後でないと正しく表示されなかったため，この場所での処理
		for (Series<Double, Double> s : chart.getData()) {
			int index = 0;
			for (Data<Double, Double> point : s.getData()) {
				double x = point.getXValue().doubleValue();
				double y = point.getYValue().doubleValue();
				String name = nameList.get(index);
				index++;
				Tooltip tooltip = new Tooltip(String.format("%s \n 日本酒度：%.2f,酸度：%.2f", name, -x, y));
				tooltip.setFont(new Font(15));
				Tooltip.install(point.getNode(), tooltip);
			}
		}
	}
}
