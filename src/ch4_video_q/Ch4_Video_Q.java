package ch4_video_q;

import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ch4_Video_Q extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane paneTextArea = FXMLLoader.load(getClass().getResource("TextAreaPane.fxml"));
        Pane paneListView = FXMLLoader.load(getClass().getResource("ListViewPane.fxml"));
        Pane paneTableView = FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
        Map<String, Pane> mapPanes = new TreeMap<>();
        mapPanes.put("textArea", paneTextArea);
        mapPanes.put("listView", paneListView);
        mapPanes.put("TableView", paneTableView);
        Scene scene = new Scene(mapPanes.get("TableView"));
        primaryStage.setTitle("Chapter 4 video last question");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
