package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import www.tangan.com.bean.Report;
import www.tangan.com.service.factory.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static www.tangan.com.Controller.UserViewController.eventId1;
import static www.tangan.com.Controller.UserViewController.reportId1;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;

/**
 * @author 谭淦
 */
public class ReportViewController implements Initializable {
    @FXML
    private Label labelReporter;
    @FXML
    private Label labelReportTime;
    @FXML
    private TextField tfTitle;
    @FXML
    private Label labelWriter;
    @FXML
    private TextArea taContent;
    @FXML
    private TextArea taReason;

    /**
     * 把举报信息都展示到举报页面的方法
     */
    private void showReportData() {
        Report report = (ServiceFactory.getService(REPORT_SERVICE).findReport(reportId1));
        labelReportTime.setText(report.getReportTime().toString());
        labelReporter.setText(report.getReporter());
        tfTitle.setText(report.getTitle());
        labelWriter.setText(report.getWriter());
        taContent.setText(report.getContent());
        taReason.setText(report.getReportReason());
        taReason.setEditable(false);
        taContent.setEditable(false);
        tfTitle.setEditable(false);
    }

    /**
     * 撤销举报的方法，就是把举报表中的该举报信息给删除掉，和撤销按钮绑定
     *
     * @throws IOException io异常
     */
    @FXML
    private void recallBackReport() throws IOException {
        if (ServiceFactory.getService(REPORT_SERVICE).deleteReport(reportId1)) {
            alert("淦淦吃瓜系统", "该举报已经成功撤销！", Alert.AlertType.INFORMATION);
            turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
        }
    }

    /**
     * 跳转回上一个页面的函数，和返回键绑定
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnBack() throws IOException {
        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
    }

    /**
     * 删除瓜瓜的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void deleteMelon() throws IOException {
        if (ServiceFactory.getService(MELON_SERVICE).deleteEvent(eventId1)) {
            alert("淦淦吃瓜系统", "该瓜都成功制裁并删除", Alert.AlertType.INFORMATION);
            turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showReportData();
    }
}
