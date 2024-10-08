import java.io.IOException;

import alex.Alex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alex using FXML.
 */
public class Main extends Application {

    private Alex alex = new Alex("./data/alex.txt");

    /**
     * starts the GUI for Alex
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlex(alex);  // inject the Alex instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
