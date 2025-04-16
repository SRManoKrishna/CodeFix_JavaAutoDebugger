import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileManager {

    public static void openFile(TextArea editor, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Java File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Java or Text Files", "*.java", "*.txt")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                editor.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(TextArea editor, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Java File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Java or Text Files", "*.java", "*.txt")
        );

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(editor.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printFile(TextArea editor) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(null)) {
            boolean success = job.printPage(editor);
            if (success) {
                job.endJob();
            }
        }
    }
}
