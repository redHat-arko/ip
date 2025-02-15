package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private Ui ui;
    private Duke duke;

    /**
     * Default constructor to create an instance of Main.
     */
    public Main() {
        ui = new Ui();
        duke = new Duke(FILE_PATH, ui);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke, ui);
            stage.setTitle("Vincent");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
