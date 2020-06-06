package com.test.controller.admin.userManagement.ui.button;

import com.test.controller.admin.userManagement.ShareData;
import com.test.controller.admin.userManagement.ui.Tree;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.avatar.UploadAllAvatarsRequestBuilder;
import com.test.utils.AvatarUtils;
import com.test.utils.Check;
import com.test.view.GUI;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;

/**
 * 修改头像按钮处理类
 */

public class ModifyAvatar {

    public static HashMap<String,String> changedAvatar = new HashMap<>();
    private static ImageView userAvatar;

    public static void init(ImageView userAvatar)
    {
        ModifyAvatar.userAvatar = userAvatar;
    }

    public static void modify()
    {
        if(Tree.isSelected())
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("请选择图片");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png", "*.wbep", "*.jpeg", "*.bmp")
            );
            File file = fileChooser.showOpenDialog(GUI.getStage());
            if (!Check.isEmpty(file)) {
                String cellphone = ShareData.worker.getCellphone();
                changedAvatar.put(cellphone, AvatarUtils.avatarToString(file.toPath()));
                AvatarUtils.writeAvatar(file,cellphone);
                setRoundAvatar(AvatarUtils.getImageViewPath(cellphone));
            }
        }
    }

    private static void setRoundAvatar(String path)
    {
        userAvatar.setImage(new Image(path));
        Rectangle clip = new Rectangle(userAvatar.getFitWidth(), userAvatar.getFitHeight());
        clip.setArcWidth(300);
        clip.setArcHeight(300);
        userAvatar.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = userAvatar.snapshot(parameters, null);
        userAvatar.setClip(null);
        userAvatar.setImage(image);
    }

    public static void setUserAvatar(String base64code)
    {
        String cellphone = ShareData.worker.getCellphone();
        if(!AvatarUtils.exists(cellphone))
            AvatarUtils.createPath(cellphone);
        AvatarUtils.stringToAvatar(base64code, cellphone);
        setRoundAvatar(AvatarUtils.getImageViewPath(cellphone));
    }

    public static void setDefaultAvatar()
    {
        userAvatar.setImage(new Image(AvatarUtils.DEFAULT_AVATAR_PATH));
    }

    public static HashMap<String,String> getData()
    {
        return changedAvatar;
    }

    public static void syncDatabaseIfChanged()
    {
        if(!Check.isEmpty(changedAvatar))
        {
            Tree.setUsersChanged();
            OKHTTP.send(new UploadAllAvatarsRequestBuilder().images(ModifyAvatar.getData()).build());
            changedAvatar = new HashMap<>();
        }
    }

    public static void clearAvatar()
    {
        userAvatar.setImage(null);
    }
}
