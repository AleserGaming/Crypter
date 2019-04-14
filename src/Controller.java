import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.crypto.BadPaddingException;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea area;

    @FXML
    private PasswordField key;

    @FXML
    private Button dec;

    @FXML
    private Button enc;

    @FXML
    private Button copy;

    @FXML
    private Button paste;

    @FXML
    private Button clear;

    private StringSelection copying;
    private StringSelection pasting;
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private DataFlavor flavor = DataFlavor.stringFlavor;


    @FXML
    void version() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(false);
        alert.setTitle("Версия");
        alert.setHeaderText("Текущая версия программы: 1.0");
        alert.setContentText("Версия: 1.0\nБилд: 1");
        alert.show();
    }

    @FXML
    void update(){

    }

    @FXML
    void initialize() {
        enc.setOnAction(event -> {
            if (verify()) {
                try {
                    Main.setKey(key.getText());
                    Main.initCipher();
                    area.setText(Encrypt.encrypt(area.getText(), Main.ecipher));
                } catch (Exception ex) {

                }
            }
        });


        dec.setOnAction(event -> {
            if (verify()) {
                try {
                    Main.setKey(key.getText());
                    Main.initCipher();
                    area.setText(Decrypt.decrypt(area.getText(), Main.dcipher));
                } catch (BadPaddingException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Exception");
                    alert.setContentText(ex.getMessage());
                    alert.show();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Exception");
                    alert.setContentText(ex.getMessage());
                    alert.show();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Exception");
                    alert.setContentText(ex.getClass().getName());
                    alert.show();
                }
            }
        });
        copy.setOnAction(event -> {
            copying = new StringSelection(area.getText());
            clipboard.setContents(copying, null);
        });
        paste.setOnAction(event -> {
            DataFlavor flavor = DataFlavor.stringFlavor;
            if (clipboard.isDataFlavorAvailable(flavor)) {
                try {
                    area.setText((String) clipboard.getData(flavor));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        clear.setOnAction(event -> {
            area.setText("");
        });
    }

    private boolean verify() {
        if ((key.getText().length() != 8) && (key.getText().length() >= 0)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setResizable(false);
            alert.setTitle("Проверка ключа имеет отрицательный результат.");
            alert.setHeaderText("Ключ не соответствует требованиям!");
            alert.setContentText("Ключ должен содержать 8 символов!");
            alert.show();
            return false;
        } else {
            return true;
        }
    }
}
