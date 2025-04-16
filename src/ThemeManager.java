import javafx.scene.control.TextArea;

public class ThemeManager {

    public static void applyDarkTheme(TextArea editor) {
        editor.setStyle(
                "-fx-control-inner-background: black;" +
                        "-fx-text-fill: lime;" +
                        "-fx-highlight-fill: magenta;" +
                        "-fx-highlight-text-fill: black;" +
                        "-fx-border-color: cyan;" +
                        "-fx-border-width: 2px;" +
                        "-fx-font-family: 'Consolas';" +
                        "-fx-font-size: 16;"
        );
    }

    public static void applyLightTheme(TextArea editor) {
        editor.setStyle(
                "-fx-control-inner-background: #eeeeee;" +
                        "-fx-text-fill: #222222;" +
                        "-fx-highlight-fill: #cccccc;" +
                        "-fx-highlight-text-fill: black;" +
                        "-fx-border-color: gray;" +
                        "-fx-border-width: 1px;" +
                        "-fx-font-family: 'Consolas';" +
                        "-fx-font-size: 16;"
        );
    }
}
