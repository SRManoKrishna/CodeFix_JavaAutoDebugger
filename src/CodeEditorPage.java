import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CodeEditorPage extends Application {

    private TextArea codeArea;
    private Label statusBar;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        codeArea = new TextArea();
        codeArea.setFont(Font.font("Consolas", 16));
        codeArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: lime; -fx-highlight-fill: magenta; -fx-highlight-text-fill: black;");
        codeArea.setEffect(new DropShadow(5, Color.CYAN));

        // Menu Bar
        MenuBar menuBar = createMenuBar(stage);
        root.setTop(menuBar);

        // Status bar
        statusBar = new Label("Ready");
        statusBar.setFont(Font.font("Consolas", 12));
        statusBar.setTextFill(Color.LIGHTGRAY);
        statusBar.setPadding(new Insets(5));
        statusBar.setStyle("-fx-background-color: #111;");

        // Action buttons
        Button checkBtn = new Button("Check Syntax");
        checkBtn.setFont(Font.font("Consolas", 14));
        checkBtn.setStyle("-fx-background-color: black; -fx-text-fill: cyan;");
        checkBtn.setEffect(new DropShadow(5, Color.MAGENTA));

        checkBtn.setOnAction(e -> {
            String code = codeArea.getText();
            String result = SyntaxChecker.checkCode(code);
            statusBar.setText(result);
        });

        HBox buttonBox = new HBox(checkBtn);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(5));
        buttonBox.setStyle("-fx-background-color: #111;");

        root.setCenter(codeArea);

        Scene scene = new Scene(root, 1000, 600, Color.BLACK);
        stage.setTitle("CodeFix Editor");
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar(Stage stage) {
        Menu fileMenu = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem print = new MenuItem("Print");
        MenuItem exit = new MenuItem("Exit");

        newFile.setOnAction(e -> {
            codeArea.clear();
            statusBar.setText("New file created");
        });

        openFile.setOnAction(e -> FileManager.openFile(codeArea, stage));
        saveFile.setOnAction(e -> FileManager.saveFile(codeArea, stage));
        print.setOnAction(e -> FileManager.printFile(codeArea));
        exit.setOnAction(e -> stage.close());

        fileMenu.getItems().addAll(newFile, openFile, saveFile, print, new SeparatorMenuItem(), exit);

        Menu themeMenu = new Menu("Theme");
        MenuItem dark = new MenuItem("Dark Neon");
        MenuItem light = new MenuItem("Light");

        dark.setOnAction(e -> ThemeManager.applyDarkTheme(codeArea));
        light.setOnAction(e -> ThemeManager.applyLightTheme(codeArea));

        themeMenu.getItems().addAll(dark, light);

        MenuBar menuBar = new MenuBar(fileMenu, themeMenu);
        menuBar.setStyle("-fx-background-color: black; -fx-border-color: magenta;");
        return menuBar;
    }
}
