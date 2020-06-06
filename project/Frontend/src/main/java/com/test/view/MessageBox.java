package com.test.view;

import com.test.constant.ViewSize;
import com.test.constant.path.CSSPath;
import com.test.constant.path.FXMLPath;
import com.test.log.L;
import com.test.utils.Utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 提示信息框
 */

public class MessageBox {
    public static void show(String message)
    {
        new MessageBox().showBox(message);
    }

    private void showBox(String message)
    {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MESSAGE_BOX));
            Scene scene = new Scene(root, ViewSize.MESSAGE_BOX_WIDTH,ViewSize.MESSAGE_BOX_HEIGHT);
            scene.getStylesheets().add(CSSPath.MESSAGE_BOX);
            Button button = (Button)root.lookup("#button");
            button.setOnMouseClicked(v->stage.hide());
            Label label = (Label)root.lookup("#label");
            label.setText(message);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            Utils.centerMessgeBoxStage(stage);
            stage.show();

            root.requestFocus();
            scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), stage::close);
            scene.getAccelerators().put(new KeyCodeCombination(KeyCode.BACK_SPACE), stage::close);
        } catch (IOException e) {
            L.error("Can not create message box view!");
            L.error(e);
        }
    }

    public static void emptyCellphone()
    {
        show("电话号为空");
    }

    public static void emptyFile()
    {
        show("文件为空");
    }

    public static void emptyPassword()
    {
        show("密码为空");
    }

    public static void emptyWorker()
    {
        show("工人类为空");
    }

    public static void emptyDatabase()
    {
        show("数据库为空");
    }

    public static void invalidCellphone()
    {
        show("非法电话号码");
    }

    public static void unknownError(String errorCode)
    {
        show("未知错误,错误码:"+errorCode);
    }

    public static void avatarToStringFailed()
    {
        show("图片转换字符串失败");
    }

    public static void stringToAvatarFailed()
    {
        show("字符串转换图片失败");
    }

    public static void writeTempImageFailed()
    {
        show("临时图片写入失败");
    }

    public static void createTempFileFailed()
    {
        show("临时图片创建失败");
    }

    public static void createTempFolderFailed()
    {
        show("临时文件夹创建失败");
    }

    public static void deleteTempFileOrFolderFailed()
    {
        show("删除临时文件或文件夹失败");
    }
}
