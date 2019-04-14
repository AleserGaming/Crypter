import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main extends Application {

    public static Cipher ecipher;
    public static Cipher dcipher;
    private static SecretKeySpec key;

    public static String message;
    public static String enteredKey;
    public static final String DEFAULT_KEY = "STANDART";

    public static void main(String[] args) throws Exception {
        setKey(DEFAULT_KEY);
        initCipher();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Шифровальщик");
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpg")));
        primaryStage.show();
    }

    public static void setKey(String str) throws Exception {
        byte[] byteKey = str.getBytes();
        key = new SecretKeySpec(byteKey,"DES");
    }

    public static void initCipher() throws Exception{
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);

    }
}
