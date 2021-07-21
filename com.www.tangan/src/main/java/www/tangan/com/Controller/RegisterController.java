package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import www.tangan.com.bean.User;
import www.tangan.com.service.factory.ServiceFactory;
import www.tangan.com.util.Md5Util;
import www.tangan.com.util.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static www.tangan.com.util.CommonUtil.isNumeric;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;

/**
 * @author 谭淦
 */
public class RegisterController implements Initializable {
    @FXML
    private TextArea taSign;
    @FXML
    private RadioButton radioMan;
    @FXML
    private RadioButton radioGirl;
    @FXML
    private Label labelErrorPhone;
    @FXML
    private TextField tfAccount;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSchool;
    @FXML
    private TextField tfPassword1;
    @FXML
    private TextField tfPassword2;
    @FXML
    private TextField tfName;
    private String sex;
    @FXML
    private ToggleGroup group;

    @FXML
    private void register() throws IOException {
        labelErrorPhone.setVisible(false);
        String account = tfAccount.getText();
        String sign = taSign.getText();
        String phone = tfPhone.getText();
        String password1 = tfPassword1.getText();
        String password2 = tfPassword2.getText();
        String school = tfSchool.getText();
        String name = tfName.getText();
        chooseSex();
        //判断是否重复
        if (!(judgeAccount(account))) {
            alert("温馨提示", "该账号已经被注册", Alert.AlertType.CONFIRMATION);
            return;
        }
        //判断是否含有空格
        if (account.contains(ONE_BLANK) || phone.contains(ONE_BLANK) || password1.contains(ONE_BLANK) ||
                password2.contains(ONE_BLANK) || name.contains(ONE_BLANK) || school.contains(ONE_BLANK)) {
            alert("错误提醒", "请不要在账号，密码，手机，名字，以及学校中填入空格字符", Alert.AlertType.WARNING);
            return;
        }
        if (account.equals(BLANK) || sign.equals(BLANK) || phone.equals(BLANK) || password1.equals(BLANK) || password2.equals(BLANK) || name.equals(BLANK)
                || school.equals(BLANK)) {
            alert("错误提醒", "请不要留空！", Alert.AlertType.ERROR);
            return;
        }
        if (!(password1.equals(password2))) {
            alert("错误提醒", "前后两次密码不一致", Alert.AlertType.WARNING);
            return;
        }
        //对手机进行过来
        if (phone.length() != PHONE_LENGTH || !(phone.startsWith(PHONE_FIRST_NUMBER)) || !(isNumeric(phone))) {
            labelErrorPhone.setVisible(true);
            return;
        }
        if (!(MAN.equals(sex) || GIRL.equals(sex))) {
            alert("错误提醒", "请选择好性别", Alert.AlertType.WARNING);
            return;
        }
        //账号，密码，名字，性别，手机，学校，地位，个性签名
        User user = new User(account, Md5Util.getMd5Str(password1), name, sex, phone, school, sign);
        if (ServiceFactory.getService(REGISTER_SERVICE).register(user)) {
            alert("淦淦吃瓜系统", "恭喜您成功注册", Alert.AlertType.INFORMATION);

        } else {
            alert("淦淦吃瓜系统", "很抱歉，注册失败", Alert.AlertType.WARNING);
            return;
        }
        turnActivity(LOGIN_VIEW, LOGIN_VIEW_WIDTH, LOGIN_VIEW_HEIGHT);
    }

    private boolean judgeAccount(String account) {
        LinkedList<String> list = (ServiceFactory.getService(REGISTER_SERVICE).checkAccount());
        for (String s : list) {
            if (s.equals(account)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 选择性别的方法
     */
    private void chooseSex() {

        group = new ToggleGroup();
        radioMan.setToggleGroup(group);
        radioGirl.setToggleGroup(group);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton radioButton = (RadioButton) newValue;
            if (radioButton != null) {
                sex = radioButton.getText();
//                sex = ((RadioButton) newValue).getText();

            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewUtil.makeLongTextView(taSign, 10);
        chooseSex();
    }

    /**
     * 返回登录界面的方法
     *
     * @throws IOException io流
     */
    @FXML
    private void backMainActivity() throws IOException {
        turnActivity(LOGIN_VIEW, LOGIN_VIEW_WIDTH, LOGIN_VIEW_HEIGHT);

    }

}
