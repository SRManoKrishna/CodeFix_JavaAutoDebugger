import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePage extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("CodeFix: Java Debugger & Auto-Correct");
        title.setFont(Font.font("Consolas", 30));
        title.setFill(Color.CYAN);
        title.setEffect(new DropShadow(20, Color.MAGENTA));

        Button enterBtn = new Button("Enter");
        enterBtn.setFont(Font.font("Consolas", 16));
        enterBtn.setStyle("-fx-background-color: black; -fx-text-fill: cyan;");
        enterBtn.setEffect(new DropShadow(10, Color.LIME));

        ProgressIndicator loader = new ProgressIndicator();
        loader.setVisible(false);
        loader.setStyle("-fx-progress-color: magenta;");

        enterBtn.setOnAction(e -> {
            enterBtn.setVisible(false);
            loader.setVisible(true);

            // Wait for 2.5 seconds, then switch to editor
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(ev -> {
                try {
                    new CodeEditorPage().start(stage); // Move to editor
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();
        });

        StackPane root = new StackPane(title, enterBtn, loader);
        StackPane.setMargin(enterBtn, new javafx.geometry.Insets(100, 0, 0, 0));
        StackPane.setMargin(loader, new javafx.geometry.Insets(100, 0, 0, 0));

        root.setStyle("-fx-background-color: black;");

        FadeTransition fade = new FadeTransition(Duration.seconds(2), title);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Welcome to CodeFix");
        stage.setScene(scene);
        stage.show();
    }
}
