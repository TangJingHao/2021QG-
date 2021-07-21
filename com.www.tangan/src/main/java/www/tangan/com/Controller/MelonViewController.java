package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import www.tangan.com.bean.Comment;
import www.tangan.com.bean.Event;
import www.tangan.com.bean.Report;
import www.tangan.com.bean.User;
import www.tangan.com.service.factory.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.Integer.parseInt;
import static www.tangan.com.Controller.LoginViewController.account1;
import static www.tangan.com.Controller.UserViewController.*;
import static www.tangan.com.util.Constant.*;
import static www.tangan.com.util.ViewUtil.alert;
import static www.tangan.com.util.ViewUtil.turnActivity;


/**
 * @author 谭淦
 */
public class MelonViewController implements Initializable {
    @FXML
    private VBox vBoxComment;
    @FXML
    private TextArea taReleaseComment;
    @FXML
    private Button btnDelete;
    @FXML
    private TextArea taContent;
    @FXML
    private Button btnPraise;
    @FXML
    private Button btnCollection;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelWriter;
    public static int commentId;
    public static String account2;
    public static int userid3;

    /**
     * 初始化瓜瓜 把瓜瓜内容都添加到该有的控件中
     */
    private void initMelon() {
        //直接显示用静态变量传递,通过eventID1来找到相应的瓜瓜
        Event event = (ServiceFactory.getService(MELON_SERVICE)).showEvent(eventId1);
        User user = (ServiceFactory.getService(USER_SERVICE)).findUser(userid2);
        account2 = user.getAccount();
        if (event != null) {
            taContent.setText(event.getContent());
            Date date = event.getReleaseTime();
            labelTime.setText(String.valueOf(date));
            labelTitle.setText(event.getTitle());
            taContent.setEditable(false);
        }
        labelWriter.setText(user.getName());
    }

    /**
     * 跳转回去的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void goBack() throws IOException {
        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
    }


    /**
     * 如果自己的瓜，就可以显示出删除瓜的，或者用户是管理者就可以删除
     */
    @FXML
    private void showDeleteButton() {
        User user1 = (ServiceFactory.getService(USER_SERVICE)).findUser(userid1);
        if (account1.equals(account2) || user1.getPosition() == MANAGER_USER) {
            btnDelete.setVisible(true);
        }
    }

    /**
     * 与删除瓜瓜的按钮绑定，删除瓜瓜的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void deleteEvent() throws IOException {
        (ServiceFactory.getService(MELON_SERVICE)).deleteEvent(eventId1);

        turnActivity(USER_VIEW_FXML, USER_VIEW_WIDTH, USER_VIEW_HEIGHT);
    }

    /**
     * 点下收藏键加收藏或者取消收藏的方法
     */
    @FXML
    private void addToCollection() {
        if (!(ServiceFactory.getService(COLLECTION_SERVICE).initAddCollect(userid1, eventId1))) {
            if (ServiceFactory.getService(COLLECTION_SERVICE).addCollection(userid1, eventId1) && ServiceFactory.getService(MELON_SERVICE).addCollectNum(eventId1)) {
                btnAddNum(btnCollection, COLLECTION_LOGO);
            }
        } else {
            if (ServiceFactory.getService(COLLECTION_SERVICE).cancelCollect(userid1, eventId1) && ServiceFactory.getService(MELON_SERVICE).reduceCollectNum(eventId1)) {
                reduceBtnNum(btnCollection, COLLECTION_LOGO);
            }
        }
    }

    /**
     * 点下点赞键，加点赞或者取消点赞的方法
     */
    @FXML
    private void addToPraise() {
        if (!(ServiceFactory.getService(PRAISE_SERVICE).initAddPraise(userid1, eventId1))) {
            if (ServiceFactory.getService(PRAISE_SERVICE).addPraise(userid1, eventId1) && ServiceFactory.getService(MELON_SERVICE).addPraise(eventId1)) {
                btnAddNum(btnPraise, PRAISE_LOGO);
            }
        } else {
            if (ServiceFactory.getService(PRAISE_SERVICE).cancelPraise(userid1, eventId1) && ServiceFactory.getService(MELON_SERVICE).reducePraise(eventId1)) {
                reduceBtnNum(btnPraise, PRAISE_LOGO);
            }
        }
    }

    /**
     * 点赞使得按钮加1 的方法
     *
     * @param button 传进入的按钮
     * @param logo   传进来的按钮logo
     */
    private void btnAddNum(Button button, String logo) {
        String collectionStr = button.getText();
        if (collectionStr.length() == 1) {
            String newCollectionStr = logo + "1";
            button.setText(newCollectionStr);
            button.setTextFill(Color.RED);
        } else {
            String numStr = collectionStr.substring(1);
            int num = parseInt(numStr);
            num++;
            //把num再次转化回字符串
            numStr = "" + num;
            String newCollectionStr = logo + numStr;
            button.setText(newCollectionStr);
            button.setTextFill(Color.RED);
        }
    }

    /**
     * 减少按钮的num
     *
     * @param button 传入的按钮
     * @param logo   按钮图标的字符串
     */
    private void reduceBtnNum(Button button, String logo) {
        String collectionStr = button.getText();
        String numStr = collectionStr.substring(1);
        int num = parseInt(numStr);
        num--;
        //把num再次转化回字符串
        numStr = "" + num;
        String newCollectionStr = logo + numStr;
        button.setText(newCollectionStr);
        button.setTextFill(Color.CADETBLUE);
    }

    /**
     * 初始化收藏的那个按钮
     */
    private void initCollectionBtn() {
        if (ServiceFactory.getService(COLLECTION_SERVICE).initAddCollect(userid1, eventId1)) {
            Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId1));
            int collectNum = event.getCollectionNum();
            String numStr = "" + collectNum;
            btnCollection.setText(COLLECTION_LOGO + numStr);
            btnCollection.setTextFill(Color.RED);
        } else {
            Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId1));
            String numStr = "";
            int collectNum = event.getCollectionNum();
            if (collectNum != 0) {
                numStr = "" + collectNum;
            }

            btnCollection.setText(COLLECTION_LOGO + numStr);
            btnCollection.setTextFill(Color.CADETBLUE);
        }
    }

    /**
     * 初始化点赞按钮
     */
    private void initPraiseBtn() {
        if (ServiceFactory.getService(PRAISE_SERVICE).initAddPraise(userid1, eventId1)) {
            Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId1));
            int praiseNum = event.getPraiseNum();
            String numStr = "" + praiseNum;
            btnPraise.setText(PRAISE_LOGO + numStr);
            btnPraise.setTextFill(Color.RED);
        } else {
            Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId1));
            String numStr = "";
            int praiseNum = event.getPraiseNum();
            if (praiseNum != 0) {
                numStr = "" + praiseNum;
            }

            btnPraise.setText(PRAISE_LOGO + numStr);
            btnPraise.setTextFill(Color.CADETBLUE);
        }
    }

    /**
     * 跳转到个人信息页面的方法
     *
     * @throws IOException io异常
     */
    @FXML
    private void turnToResume() throws IOException {
        turnActivity(UPDATE_VIEW, UPDATE_VIEW_WIDTH, UPDATE_VIEW_HEIGHT);
    }

    /**
     * 发布评论的方法
     */
    private boolean releaseComment() {
        String content = taReleaseComment.getText();
        if (content.equals(BLANK)) {
            alert("错误提醒", "请写入评价再发布！", Alert.AlertType.INFORMATION);
            return false;
        }
        Date date = new Date();
        User user = (ServiceFactory.getService(USER_SERVICE).findUser(userid1));
        Comment comment = new Comment(eventId1, userid1, content, user.getName(), date);
        return (ServiceFactory.getService(COMMENT_SERVICE).releaseComment(comment));
    }

    /**
     * 直接关联发布按钮的方法
     */
    @FXML
    private void showReleaseComment() throws IOException {
        if (releaseComment()) {
            alert("淦淦吃瓜系统", "发布成功", Alert.AlertType.INFORMATION);
            turnActivity(MELON_VIEW_FXML, MELON_VIEW_WIDTH, MELON_VIEW_HEIGHT);
        } else {
            alert("错误提醒", "发布失败", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * 显示所有评论的方法
     *
     * @throws IOException io异常
     */
    private void showAllComment() throws IOException {
        LinkedList<Comment> list = (ServiceFactory.getService(COMMENT_SERVICE).showComment(eventId1));

        for (Comment comment : list) {
            commentId = comment.getId();
            userid3 = comment.getUserId();
            //把评论的子布局加载出来
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(COMMENT_VIEW)));
            vBoxComment.getChildren().addAll(root);
            User user1 = (ServiceFactory.getService(USER_SERVICE)).findUser(userid1);
            //是自己的瓜，或者自己的评论，或者管理者，就可以删除评论
            if (userid1 == userid3 || userid1 == userid2 || user1.getPosition() == MANAGER_USER) {
                Label label = new Label();
                label.setText("删除");
                label.setTextFill(Color.BLUEVIOLET);
                HBox hBox = new HBox();
                Label label1 = new Label("                                                                  ");
                hBox.getChildren().addAll(label1, label);
                vBoxComment.getChildren().addAll(hBox);
                label.setOnMouseClicked(event -> {
                    if (ServiceFactory.getService(COMMENT_SERVICE).deleteComment(commentId)) {
                        alert("淦淦吃瓜系统", "删除成功！", Alert.AlertType.INFORMATION);
                        try {
                            turnActivity(MELON_VIEW_FXML, MELON_VIEW_WIDTH, MELON_VIEW_HEIGHT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        alert("淦淦吃瓜系统", "删除失败!", Alert.AlertType.ERROR);
                    }
                });
            }
        }
    }

    /**
     * 与举报按钮绑定的方法 ，点击下会有弹窗给你输入举报信息的方法
     */
    @FXML
    private void reportThisMelon() {
        Report report;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("举报窗口");
        dialog.setHeaderText("淦淦瓜瓜举报中心————————");
        dialog.setContentText("请输入您要举报该瓜的原因");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(BLANK)) {
                alert("错误提醒", "请输入举报原因！", Alert.AlertType.INFORMATION);
                return;
            }
            report = new Report(eventId1, result.get(), new Date(), userid1);
            if (ServiceFactory.getService(REPORT_SERVICE).addToReport(report)) {
                alert("淦淦吃瓜系统", "该瓜已被您举报，管理者将处理该瓜，请等待，谢谢投诉！", Alert.AlertType.INFORMATION);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMelon();
        initCollectionBtn();
        showDeleteButton();
        initPraiseBtn();
        try {
            showAllComment();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
