package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import www.tangan.com.bean.User;
import www.tangan.com.service.factory.ServiceFactory;
import www.tangan.com.util.Md5Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static www.tangan.com.Controller.UserViewController.userid1;
import static www.tangan.com.Controller.UserViewController.userid2;
import static www.tangan.com.util.CommonUtil.isNumeric;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.Constant.BLANK;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;

/**
 * @author 谭淦
 */
public class UpdateResumeController implements Initializable {
    public Button btnSure;
    @FXML
    private Button btnUpdatePassword;
    @FXML
    private Label labelPassword2;
    @FXML
    private Label labelPassword1;
    @FXML
    private Label labelTip;
    @FXML
    private Button btnSave;
    @FXML
    private Label labelErrorPassword;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSchool;
    @FXML
    private PasswordField tfPassword1;
    @FXML
    private PasswordField tfPassword2;
    @FXML
    private TextField tfName;
    @FXML
    private Label labelErrorPhone;
    @FXML
    private TextArea taSign;
    @FXML
    private RadioButton radioMan;
    @FXML
    private RadioButton radioGirl;
    @FXML
    private ToggleGroup group;
    private String sex = null;
    private String passwordShow1;
    private String passwordShow2;

    /**
     * 保存个人信息的方法
     */
    @FXML
    private void save() {
        labelErrorPassword.setVisible(false);
        labelErrorPhone.setVisible(false);
        if (!tfPassword2.getText().equals(BLANK)) {
            if (!(tfPassword1.getText().equals(BLANK) || tfPassword2.getText().equals(BLANK))) {
                if (!checkPassword()) {
                    return;
                }
            } else {
                alert(" 错误提醒", "请不要留空", Alert.AlertType.ERROR);
            }
        }
        if (!checkPhone()) {
            return;
        }
        if (!checkBlank()) {
            return;
        }
        chooseSex();
        if (!checkSexRight()) {
            return;
        }
        User user = new User(null, passwordShow1, tfName.getText(), sex, tfPhone.getText(), tfSchool.getText(), taSign.getText());
        if (ServiceFactory.getService(USER_SERVICE).updateUser(user, userid2)) {
            alert("淦淦吃瓜系统", "恭喜您，修改成功！", Alert.AlertType.INFORMATION);
        } else {
            alert("错误提醒", "很抱歉，修改失败！", Alert.AlertType.ERROR);
        }
        btnSure.setText("确定密码");
    }

    /**
     * 检查密码的方法
     *
     * @return 符合要求就返回true
     */
    private boolean checkPassword() {

        if (tfPassword2.getText().length() < PASSWORD_LENGTH) {
            alert("错误提醒", "密码长度需要大于等于3", Alert.AlertType.INFORMATION);
            return false;
        }
        if (!tfPassword2.getText().equals(tfPassword2.getText())) {
            labelErrorPassword.setVisible(true);
            alert("错误提醒", "前后两次密码不一致", Alert.AlertType.ERROR);
            return false;
        }
        if (passwordShow2 != null) {
            passwordShow1 = Md5Util.getMd5Str(passwordShow2);
        }
        return true;
    }

    /**
     * 检查电话的方法
     *
     * @return 符合要求就true
     */
    private boolean checkPhone() {
        String phone = tfPhone.getText();
        if (phone.length() != PHONE_LENGTH || !(phone.startsWith(START_PHONE_NUM)) || !(isNumeric(phone))) {
            labelErrorPhone.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * 查空
     *
     * @return 如果空了就false
     */
    private boolean checkBlank() {
        if (taSign.getText().equals(BLANK) || tfPhone.getText().equals(BLANK) || tfName.getText().equals(BLANK)
                || tfSchool.getText().equals(BLANK)) {
            alert("错误提醒", "请不要留空！", Alert.AlertType.ERROR);
            return false;
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

            }
        });
    }

    /**
     * 检查性别是否选择正确的方法
     *
     * @return 选择正确就返回true
     */
    private boolean checkSexRight() {
        if (!(sex.equals(MAN) || sex.equals(GIRL))) {
            alert("错误提醒", "请选择好性别", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void initData() {
        User user = (ServiceFactory.getService(USER_SERVICE)).findUser(userid2);
        tfName.setText(user.getName());
        tfPhone.setText(user.getPhone());
        tfSchool.setText(user.getSchool());
        taSign.setText(user.getSign());
        if (user.getSex().equals(MAN)) {
            radioMan.setSelected(true);
            radioMan.requestFocus();
        }
        if (user.getSex().equals(GIRL)) {
            radioGirl.setSelected(true);
            radioGirl.requestFocus();
        }
        if (userid1 == userid2) {
            passwordShow1 = user.getPassword();
            passwordShow2 = passwordShow1;
            btnUpdatePassword.setVisible(true);
            tfPassword1.setEditable(true);
            tfName.setEditable(true);
            tfPhone.setEditable(true);
            tfSchool.setEditable(true);
            taSign.setEditable(true);
            btnSave.setVisible(true);
        } else {
            tfPassword1.setVisible(false);
            tfPassword2.setVisible(false);
            tfName.setEditable(false);
            tfPhone.setEditable(false);
            tfSchool.setEditable(false);
            taSign.setEditable(false);
            radioGirl.setDisable(true);
            radioMan.setDisable(true);
            btnSave.setVisible(false);
            labelTip.setVisible(true);
            labelTip.setText("欢迎查看我");
            labelPassword1.setVisible(false);
            labelPassword2.setVisible(false);
        }
    }

    /**
     * 跳转到用户界面的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnToUserView() throws IOException {
        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
    }

    /**
     * 显示是否要更新密码的方法，
     */
    @FXML
    private void updatePassword() {
        tfPassword1.setVisible(true);
        tfPassword1.setEditable(true);
        tfPassword2.setVisible(true);
        tfPassword2.setEditable(true);
        labelPassword2.setVisible(true);
        labelPassword1.setVisible(true);
        btnSure.setVisible(true);
        btnUpdatePassword.setVisible(false);
    }

    /**
     * 确定更新密码的方法
     */
    @FXML
    private void updatePasswordToView() {
        btnSure.setTextFill(Color.YELLOW);
        btnSure.setText("已确定");
        passwordShow1 = tfPassword1.getText();
        passwordShow2 = tfPassword2.getText();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseSex();
        initData();
        labelErrorPassword.setVisible(false);
    }
}
