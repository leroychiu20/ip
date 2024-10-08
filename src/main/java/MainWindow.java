import alex.Alex;
import alex.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the mainWindow.css GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/Alex.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Alex instance
     * */
    public void setAlex(Alex a) {
        alex = a;
        Ui ui = new Ui();
        String welcomeMessage = ui.welcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(welcomeMessage, alexImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alex's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String reply = alex.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(reply, alexImage)
        );
        userInput.clear();
    }
}
