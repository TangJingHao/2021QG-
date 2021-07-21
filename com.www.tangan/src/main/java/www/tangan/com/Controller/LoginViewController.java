package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import www.tangan.com.service.factory.ServiceFactory;
import www.tangan.com.util.Md5Util;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;

/**
 * @author 谭淦
 */
public class LoginViewController implements Initializable {
    @FXML
    public Label menuChooseId;
    @FXML
    public Label labelRegister;

    @FXML
    private Label labelReminderNull;
    @FXML
    private CheckBox chooseRemember;
    @FXML
    private TextField tfAccount;
    @FXML
    private PasswordField passwordField;
    public static String account1 = null;
    private final Properties properties = new Properties();

    /**
     * 与登录按钮绑定的方法
     *
     * @throws IOException io异常
     */
    public void login() throws IOException {
        //把上一次的错误提醒给清空
        labelReminderNull.setVisible(false);
        //获取输入账号
        String account = tfAccount.getText();
        account1 = account;
        //获取输入密码
        String password = passwordField.getText();
        //因为数据库里的密码已经加过密了，输入的密码也要进行一次加密才能对照起来
        password = Md5Util.getMd5Str(password);
        if (account.equals(BLANK) || password.equals(BLANK)) {
            errorTip();//错误提醒
            return;
        }
        if (password.equals(ServiceFactory.getService(LOGIN_SERVICE).getPassword(account))) {
            turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
        } else {
            String title = "淦淦吃瓜系统";
            alert(title, "您的账号或密码输入错误,请重新输入", Alert.AlertType.WARNING);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChooseRememberBox();
    }

    /**
     * 账号密码为空的提醒
     */
    private void errorTip() {
        labelReminderNull.setVisible(true);
    }

    /**
     * 跳转去注册的页面
     */
    @FXML
    private void register() {
        //跳转到注册页面
        try {
            turnActivity(REGISTER_VIEW, REGISTER_VIEW_WIDTH, REGISTER_VIEW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 记住密码的方法
     */
    @FXML
    private void rememberPassword() {
        String saveAccount = tfAccount.getText();
        String savePassword = passwordField.getText();
        int judgeRemember = chooseRemember.isSelected() ? 1 : 0;
        if (judgeRemember == REMEMBER_PASSWORD) {
            System.out.println("记住密码");
            if (!saveAccount.isEmpty() && !savePassword.isEmpty()) {
                try {
                    //把账号作为键 ，密码作为值 ，把它保存在properties中
                    FileOutputStream oFile = new FileOutputStream(USER_PROPERTIES, false);
                    properties.setProperty(saveAccount, savePassword);
                    properties.store(oFile, null);
                    oFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (judgeRemember == DO_NOT_REMEMBER_PASSWORD) {
            try {
                FileOutputStream oFile = new FileOutputStream(USER_PROPERTIES, false);
                properties.clear();
                properties.store(oFile, null);
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进入登录页面初始化记住密码的方法 如果选择了记住密码，就把上次记住密码的账号和密码给加载进页面
     */
    private void initChooseRememberBox() {
        try {
            if (new File(USER_PROPERTIES).exists()) {

                InputStream in = new BufferedInputStream(new FileInputStream(USER_PROPERTIES));
                properties.load(in);
                for (String key : properties.stringPropertyNames()) {
                    if (key != null) {
                        chooseRemember.setSelected(true);
                    }
                    tfAccount.setText(key);
                    passwordField.setText(properties.getProperty(key));
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

