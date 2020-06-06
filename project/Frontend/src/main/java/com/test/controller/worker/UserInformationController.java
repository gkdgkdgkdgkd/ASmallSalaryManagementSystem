package com.test.controller.worker;

import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.avatar.DownloadOneAvatarRequestBuilder;
import com.test.network.requestBuilder.avatar.UploadOneAvatarRequestBuilder;
import com.test.network.requestBuilder.crud.SaveOneRequestBuilder;
import com.test.transition.Rotate;
import com.test.transition.Scale;
import com.test.utils.AvatarUtils;
import com.test.utils.Check;
import com.test.view.GUI;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * 用户信息界面控制类
 */

public class UserInformationController {
    @FXML
    private ImageView cellphoneImage;
    @FXML
    private ImageView positionImage;
    @FXML
    private ImageView nameImage;
    @FXML
    private ImageView departmentImage;
    @FXML
    private ImageView userAvatar;

    @FXML
    private Label cellphoneLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label departmentLabel;

    @FXML
    private TextField nameText;
    @FXML
    private TextField positionText;
    @FXML
    private TextField departmentText;

    @FXML
    private Button modify;

    private Worker worker;
    private boolean workerChanged = false;
    private boolean avatarChanged = false;
    private String tempImagePath;
    private File avatar;

    private boolean modifying = false;

    public void init(Worker worker)
    {
        this.worker = worker;
        tempImagePath = AvatarUtils.getImageViewPath(worker.getCellphone());
        if(AvatarUtils.exists(worker.getCellphone()))
            setRoundImage(tempImagePath);
        else
        {
            Object result = OKHTTP.send(new DownloadOneAvatarRequestBuilder().cellphone(worker.getCellphone()).build());
            AvatarUtils.createPath(worker.getCellphone());
            setRoundImage((Check.isEmpty(result) || !((boolean)result)) ? AvatarUtils.DEFAULT_AVATAR_PATH : tempImagePath);
        }

        initAvatarEvent();
        initImageEvent(0.6,0.82,cellphoneImage, positionImage,nameImage,departmentImage);

        cellphoneLabel.setText(worker.getCellphone());
        nameLabel.setText(worker.getName());
        positionLabel.setText(worker.getPosition());
        departmentLabel.setText(worker.getDepartment());
    }

    private void initAvatarEvent()
    {
        userAvatar.setOnMouseEntered(t->new Rotate(userAvatar).seconds(4).to(720).play());
        userAvatar.setOnMouseExited(t->new Rotate(userAvatar).seconds(4).to(0).play());
    }

    private void initImageEvent(double originalRatio,double scaleRatio,ImageView ... images)
    {
        for(ImageView image:images)
        {
            image.setOnMouseEntered(t-> new Scale(image).ratio(scaleRatio).play());
            image.setOnMouseExited(t-> new Scale(image).ratio(originalRatio).play());
        }
    }

    public void modifyInformation()
    {
        setTransparentAndDisable(nameLabel,positionLabel,departmentLabel);
        setNotTransparentAndEnable(nameText,positionText,departmentText);
        nameText.setText(worker.getName());
        positionText.setText(worker.getPosition());
        departmentText.setText(worker.getDepartment());

        modifying = true;
        modify.setText("保存信息");
        modify.setOnMouseClicked(v->saveInformation());
    }

    public void saveInformation()
    {
        setNotTransparentAndEnable(nameLabel,positionLabel,departmentLabel);
        setTransparentAndDisable(nameText,positionText,departmentText);
        checkNotEmptyText();
        if(labelNotSame())
        {
            refreshLabel();
            refreshWorker();
            resetText();

            modifying = false;
            workerChanged = true;
        }
        modify.setText("修改信息");
        modify.setOnMouseClicked(v->modifyInformation());
    }

    public void chooseFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择图片");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("All Images","*.jpg","*.png","*.wbep","*.jpeg","*.bmp")
        );
        avatar = fileChooser.showOpenDialog(GUI.getStage());
        if(!Check.isEmpty(avatar))
        {
            AvatarUtils.writeTempImage(avatar,worker.getCellphone());
            setRoundImage(tempImagePath);
            avatarChanged  = true;
        }
    }

    private void setRoundImage(String imagePath)
    {
        userAvatar.setImage(new Image(imagePath));
        Rectangle clip = new Rectangle(userAvatar.getFitWidth(),userAvatar.getFitHeight());
        clip.setArcWidth(300);
        clip.setArcHeight(300);
        userAvatar.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = userAvatar.snapshot(parameters,null);
        userAvatar.setClip(null);
        userAvatar.setImage(image);
    }

    public boolean isModifying()
    {
        return modifying;
    }

    private void setTransparentAndDisable(Node... nodes)
    {
        for (Node node : nodes) {
            node.setOpacity(0);
            node.setDisable(true);
        }
    }

    private void setNotTransparentAndEnable(Node ... nodes)
    {
        for (Node node : nodes) {
            node.setOpacity(1);
            node.setDisable(false);
        }
    }

    private void checkNotEmptyText()
    {
        if(Check.isEmpty(nameText.getText()))
            nameText.setText("无姓名");
        if(Check.isEmpty(positionText.getText()))
            positionText.setText("无职位");
        if(Check.isEmpty(departmentText.getText()))
            departmentText.setText("无部门");
    }

    private boolean labelNotSame()
    {
        return !nameLabel.getText().equals(nameText.getText())
                ||
               !positionLabel.getText().equals(positionText.getText())
                ||
               !departmentLabel.getText().equals(departmentText.getText());
    }

    private void refreshWorker()
    {
        worker.setName(nameText.getText());
        worker.setPosition(positionText.getText());
        worker.setDepartment(departmentText.getText());
    }

    private void refreshLabel()
    {
        nameLabel.setText(nameText.getText());
        positionLabel.setText(positionText.getText());
        departmentLabel.setText(departmentText.getText());
    }

    private void resetText()
    {
        nameText.setText("");
        positionText.setText("");
        departmentText.setText("");
    }

    public void getModifyingFocus()
    {
        nameText.requestFocus();
    }

    public void syncDatabaseIfChanged()
    {
        if(workerChanged)
        {
            OKHTTP.send(new SaveOneRequestBuilder().worker(worker).build());
            workerChanged = false;
        }
        if(avatarChanged)
        {
            OKHTTP.send(new UploadOneAvatarRequestBuilder().cellphone(worker.getCellphone()).file(avatar).build());
            avatarChanged = false;
        }
    }

    public void reset()
    {
        AvatarUtils.deletePathIfExists();
    }
}
