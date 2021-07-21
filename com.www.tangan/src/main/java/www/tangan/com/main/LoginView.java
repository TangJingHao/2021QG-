package www.tangan.com.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 谭淦
 */
public class LoginView extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/login.fxml")));
        stage.setScene(new Scene(root, 600, 500));
        String title = "淦淦吃瓜系统";
        stage.setTitle(title);
        stage.show();
    }


}
