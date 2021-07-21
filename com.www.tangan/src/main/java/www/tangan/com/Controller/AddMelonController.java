package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;
import www.tangan.com.service.factory.ServiceFactory;
import www.tangan.com.util.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static www.tangan.com.Controller.LoginViewController.account1;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;

/**
 * @author 谭淦
 */
public class AddMelonController implements Initializable {
    @FXML
    private TextArea taMelon;
    @FXML
    private TextField tfTitle;

    /**
     * 保存瓜瓜的方法
     *
     * @throws IOException io流
     */
    @FXML
    private void saveMelon() throws IOException {
        Event event = new Event();
        event.setReleaseTime(new java.sql.Date((new java.util.Date()).getTime()));
        event.setTitle(tfTitle.getText());
        event.setContent(taMelon.getText());
        if (event.getTitle() == null || event.getContent() == null) {
            alert("温馨提示", "请不要留空您的标题或内容！", Alert.AlertType.INFORMATION);
            return;
        }
        event.setCollectionNum(0);
        event.setPraiseNum(0);

        //用登录时候的账号找到的user对象，account1是静态变量
        User user = (ServiceFactory.getService(USER_SERVICE).findUser(account1));
        if (user != null) {
            event.setUserId(user.getUserId());
            event.setWriter(user.getName());
        }
        if (!(ServiceFactory.getService(USER_SERVICE).saveMelon(event))) {
            alert("错误提醒", "很抱歉，添瓜失败", Alert.AlertType.INFORMATION);
        } else {
            alert("温馨提示", "恭喜您，添瓜成功！", Alert.AlertType.INFORMATION);
        }
        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);

    }

    /**
     * 与返回键绑定的跳转方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnBack() throws IOException {
        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewUtil.makeLongTextView(taMelon, 30);
    }
}
