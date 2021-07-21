package www.tangan.com.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import www.tangan.com.bean.Event;
import www.tangan.com.bean.Report;
import www.tangan.com.bean.User;
import www.tangan.com.service.factory.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static www.tangan.com.Controller.LoginViewController.account1;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.turnActivity;


/**
 * @author 谭淦
 */
public class UserViewController implements Initializable {
    @FXML
    private Button btnReport;
    @FXML
    private TableColumn<Report, Integer> tRid;
    @FXML
    private TableView<Report> tableReport;
    @FXML
    private TableColumn<Report, String> tReportTitle;
    @FXML
    private TableColumn<Report, String> tReportContent;
    @FXML
    private TableColumn<Report, String> tReportReason;
    @FXML
    private TableColumn<Report, String> tReporter;
    @FXML
    private TableColumn<Report, String> tReportWriter;
    @FXML
    private TableColumn<Report, Date> tReportTime;
    @FXML
    private TextField tfScan;
    @FXML
    private TableColumn<Event, Integer> tcId;
    @FXML
    private Label labelName;
    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> tTitle;
    @FXML
    private TableColumn<Event, String> tContent;
    @FXML
    private TableColumn<Event, Integer> tCollectionNum;
    @FXML
    private TableColumn<Event, Integer> tPrise;
    @FXML
    private TableColumn<Event, String> tWriter;
    @FXML
    private TableColumn<Event, Date> tReleaseTime;
    public static int eventId1;
    private User user;
    /**
     * userId1 是为了辨别谁登录的
     */
    public static int userid1;
    /**
     * userId2 是为了辨别点击进入的文章的那个作者
     */
    public static int userid2;
    /**
     * reportId1 静态变量是为了跳转之后给 report_view传递过去，便于report_view这个界面用reportId1找到对应的举报信息
     */
    public static int reportId1;

    /**
     * 把所有瓜都加载到菜单的方法
     */
    @FXML
    private void addDataToTable() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(USER_SERVICE).findAllMelon());
        putDataInTable(list);
    }


    /**
     * 显示用户页面的用户信息
     */
    private void showUserLabel() {
        user = (ServiceFactory.getService(USER_SERVICE).findUser(account1));
        userid1 = user.getUserId();
        labelName.setText(user.getName());
    }

    /**
     * 跳转到添加瓜瓜的页面
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnToAddMelon() throws IOException {
        turnActivity(ADD_MELON_VIEW, ADD_MELON_VIEW_WIDTH, ADD_MELON_VIEW_HEIGHT);
    }

    /**
     * 菜单点击事件的方法
     */
    @FXML
    private void clickTable() {
        table.getSelectionModel().selectedItemProperty().addListener(// 选中某一行
                (observableValue, oldItem, newItem) -> {
                    // userid2是静态变量，点击了谁的格子 userid2 就是谁的userid，便于跳转页面后用userid2 去找到现在点击的user
                    userid2 = newItem.getUserId();
                    //eventId1 是静态变量 ，把格子中的事件的eventId给了eventId1，那么跳转页面后可以用eventId1找到现在点击的event
                    eventId1 = newItem.getEventId();
                    try {
                        turnActivity(MELON_VIEW_FXML, MELON_VIEW_WIDTH, MELON_VIEW_HEIGHT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 点击举报列表的响应事件
     */
    @FXML
    private void clickReportTable() {
        tableReport.getSelectionModel().selectedItemProperty().addListener(// 选中某一行
                (observableValue, oldItem, newItem) -> {
                    userid2 = newItem.getUserId();
                    eventId1 = newItem.getEventId();
                    reportId1 = newItem.getId();

                    try {
                        turnActivity(REPORT_VIEW, REPORT_VIEW_WIDTH, REPORT_VIEW_HEIGHT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 返回登录页面的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void goBack() throws IOException {
        turnActivity(LOGIN_VIEW, LOGIN_VIEW_WIDTH, LOGIN_VIEW_HEIGHT);
    }

    /**
     * 找到所有的集合
     */
    @FXML
    private void findAllCollects() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(COLLECTION_SERVICE).selectCollectMelon(userid1));
        putDataInTable(list);
    }

    /**
     * 找到所有自己的瓜瓜
     */
    @FXML
    private void findAllMines() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(USER_SERVICE).selectAllMines(userid1));
        putDataInTable(list);
    }

    /**
     * 找到所有点赞的瓜瓜
     */
    @FXML
    private void findAllPraise() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(PRAISE_SERVICE).selectPraiseMelon(userid1));
        putDataInTable(list);
    }

    /**
     * 找到所有7天内点赞的瓜瓜
     */
    @FXML
    private void find7dayPraise() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(PRAISE_SERVICE).select7DayPraise(userid1));
        putDataInTable(list);
    }

    /**
     * 找到所有举报信息
     */
    @FXML
    private void findAllReport() {
        table.setVisible(false);
        tableReport.setVisible(true);
        LinkedList<Report> list = (ServiceFactory.getService(REPORT_SERVICE).findAllReport());
        putReportDataInTable(list);
    }

    /**
     * 显示瓜瓜表格
     */
    private void showTable() {
        table.setVisible(true);
        tableReport.setVisible(false);
    }

    /**
     * 把瓜瓜集合的数据加入表格
     *
     * @param list 瓜瓜
     */
    private void putDataInTable(LinkedList<Event> list) {
        tTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        tCollectionNum.setCellValueFactory(new PropertyValueFactory<>("collectionNum"));
        tPrise.setCellValueFactory(new PropertyValueFactory<>("praiseNum"));
        tWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
        tReleaseTime.setCellValueFactory(new PropertyValueFactory<>("releaseTime"));
        tcId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        table.setItems(FXCollections.observableArrayList(list));
        table.refresh();
    }

    /**
     * 把举报信息加入列表
     *
     * @param list report 的list
     */
    private void putReportDataInTable(LinkedList<Report> list) {

        tReportTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tReportContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        tReporter.setCellValueFactory(new PropertyValueFactory<>("reporter"));
        tReportReason.setCellValueFactory(new PropertyValueFactory<>("reportReason"));
        tReportWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
        tReportTime.setCellValueFactory(new PropertyValueFactory<>("reportTime"));
        tRid.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        tableReport.setItems(FXCollections.observableArrayList(list));
        tableReport.refresh();

    }

    /**
     * 跳转到更新个人信息的列表
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnToUpdateOwnView() throws IOException {
        userid2 = userid1;
        turnActivity(UPDATE_VIEW, UPDATE_VIEW_WIDTH, UPDATE_VIEW_HEIGHT);
    }

    /**
     * 模糊查询的找相应瓜瓜的方法
     */
    @FXML
    private void findEventLike() {
        showTable();
        LinkedList<Event> list = (ServiceFactory.getService(MELON_SERVICE).findEventLike(tfScan.getText()));
        putDataInTable(list);
    }

    /**
     * 管理者显示管理按钮的方法
     */
    private void showReportBtn() {
        user = (ServiceFactory.getService(USER_SERVICE).findUser(account1));
        if (user != null) {
            if (user.getPosition() == MANAGER_USER) {
                btnReport.setVisible(true);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showReportBtn();
        clickTable();
        showUserLabel();
        addDataToTable();
        tcId.setVisible(false);
        clickReportTable();
    }
}
