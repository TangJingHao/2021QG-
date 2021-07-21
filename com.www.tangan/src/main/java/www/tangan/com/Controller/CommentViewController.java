package www.tangan.com.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import www.tangan.com.bean.Comment;
import www.tangan.com.service.factory.ServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static www.tangan.com.Controller.MelonViewController.commentId;
import static www.tangan.com.util.Constant.COMMENT_SERVICE;


/**
 * @author 谭淦
 */
public class CommentViewController implements Initializable {
    @FXML
    private TextArea taContent;
    @FXML
    private Label labelWriter;
    @FXML
    private Label labelTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //commentId是上一界面点击后绑定的评论id ，是个静态变量，下面用它来找到相应的评论
        Comment comment = (ServiceFactory.getService(COMMENT_SERVICE)).findComment(commentId);
        labelWriter.setText(comment.getWriter());
        labelTime.setText(comment.getReleaseTime().toString());
        taContent.setText(comment.getContent());
        //把评论内容区设置为不可编写
        taContent.setEditable(false);

    }
}
