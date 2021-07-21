package www.tangan.com.util;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import www.tangan.com.main.LoginView;

import java.io.IOException;
import java.util.Objects;

import static www.tangan.com.main.LoginView.stage;


/**
 * @author 谭淦
 */
public class ViewUtil {
    /**
     * 实现文本框多行显示的函数
     *
     * @param textArea javafx的文本区
     * @param rowCount 行数
     */
    public static void makeLongTextView(TextArea textArea, int rowCount) {

        textArea.setFont(Font.font(16));
        //允许自动换行
        textArea.setWrapText(true);
        //初始化设置行数
        textArea.setPrefRowCount(rowCount);


    }

    public static void alert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void turnActivity(String filename, double width, double height) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginView.class.getClassLoader().getResource(filename)));
        stage.setTitle("淦淦吃瓜系统");
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
}
