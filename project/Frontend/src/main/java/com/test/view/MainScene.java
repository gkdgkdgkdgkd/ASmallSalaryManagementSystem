package com.test.view;

import com.test.constant.PaneName;
import com.test.constant.ViewSize;
import com.test.constant.path.CSSPath;
import com.test.constant.path.FXMLPath;
import com.test.controller.worker.UserInformationController;
import com.test.log.L;
import com.test.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * Scene控制类
 */

public class MainScene extends Application {

    private URL mainURL;
    private URL signInUpURL;
    private URL retrievePasswordURL;

    private URL workerURL;
    private URL userInformationURL;
    private URL salaryQueryURL;

    private URL adminURL;
    private URL userManagmentURL;
    private URL salaryEntryURL;
    private boolean createEnvSuccess = true;

    @Override
    public void start(Stage stage) {
        initStage(stage);
        initFXML();
        initParent();
        initController();
        initScene();
        initPane();
        setEnvironment();
        bindKeyEvent();
        show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initStage(Stage stage)
    {
        GUI.setStage(stage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
    }

    private void initFXML()
    {
        FXMLLoader mainLoader = new FXMLLoader();
        FXMLLoader signInUpLoader = new FXMLLoader();
        FXMLLoader retrievePasswordLoader = new FXMLLoader();

        FXMLLoader workerLoader = new FXMLLoader();
        FXMLLoader userInformationLoader = new FXMLLoader();
        FXMLLoader salaryQueryLoader = new FXMLLoader();

        FXMLLoader adminLoader = new FXMLLoader();
        FXMLLoader userManagementLoader = new FXMLLoader();
        FXMLLoader salaryEntryLoader = new FXMLLoader();

        GUI.setMainLoader(mainLoader);
        GUI.setSignInUpLoader(signInUpLoader);
        GUI.setRetrievePasswordLoader(retrievePasswordLoader);

        GUI.setWorkerLoader(workerLoader);
        GUI.setUserInforamtionLoader(userInformationLoader);
        GUI.setSalaryQueryLoader(salaryQueryLoader);

        GUI.setAdminLoader(adminLoader);
        GUI.setUserManagementLoader(userManagementLoader);
        GUI.setSalaryEntryLoader(salaryEntryLoader);

        mainURL = getClass().getResource(FXMLPath.MAIN);
        mainLoader.setLocation(mainURL);
        signInUpURL = getClass().getResource(FXMLPath.SIGN_IN_UP);
        signInUpLoader.setLocation(signInUpURL);
        retrievePasswordURL = getClass().getResource(FXMLPath.RETRIEVE_PASSWORD);
        retrievePasswordLoader.setLocation(retrievePasswordURL);

        workerURL = getClass().getResource(FXMLPath.WORKER);
        workerLoader.setLocation(workerURL);
        userInformationURL = getClass().getResource(FXMLPath.USER_INFORMATION);
        userInformationLoader.setLocation(userInformationURL);
        salaryQueryURL = getClass().getResource(FXMLPath.SALARY_QUERY);
        salaryQueryLoader.setLocation(salaryQueryURL);

        adminURL = getClass().getResource(FXMLPath.ADMIN);
        adminLoader.setLocation(adminURL);
        userManagmentURL = getClass().getResource(FXMLPath.USER_MANAGEMENT);
        userManagementLoader.setLocation(userInformationURL);
        salaryEntryURL = getClass().getResource(FXMLPath.SALARY_ENTRY);
        salaryEntryLoader.setLocation(salaryEntryURL);
    }

    private void initParent()
    {
        try
        {
            GUI.setMainParent(GUI.getMainLoader().load(mainURL.openStream()));
            GUI.setSignInUpParent(GUI.getSignInUpLoader().load(signInUpURL.openStream()));
            GUI.setRetrievePasswordParent(GUI.getRetrievePasswordLoader().load(retrievePasswordURL.openStream()));

            GUI.setWorkerParent(GUI.getWorkerLoader().load(workerURL.openStream()));
            GUI.setUserInformationParent(GUI.getUserInforamtionLoader().load(userInformationURL.openStream()));
            GUI.setSalaryQueryParent(GUI.getSalaryQueryLoader().load(salaryQueryURL.openStream()));

            GUI.setAdminParent(GUI.getAdminLoader().load(adminURL.openStream()));
            GUI.setUserManagmentParent(GUI.getUserManagementLoader().load(userManagmentURL.openStream()));
            GUI.setSalaryEntryParent(GUI.getSalaryEntryLoader().load(salaryEntryURL.openStream()));
        }
        catch (IOException e)
        {
            MessageBox.show("无法创建主界面，请联系管理员");
            L.error(e);
        }
    }

    private void initController()
    {
        GUI.setSignInUpController(GUI.getSignInUpLoader().getController());
        GUI.setRetrievePasswordController(GUI.getRetrievePasswordLoader().getController());

        GUI.setWorkerController(GUI.getWorkerLoader().getController());
        GUI.setUserInformationController(GUI.getUserInforamtionLoader().getController());
        GUI.setSalaryQueryController(GUI.getSalaryQueryLoader().getController());

        GUI.setAdminController(GUI.getAdminLoader().getController());
        GUI.setUserManagementController(GUI.getUserManagementLoader().getController());
        GUI.setSalaryEntryController(GUI.getSalaryEntryLoader().getController());
    }

    private void initScene()
    {
        Scene scene = new Scene(GUI.getMainParent(), ViewSize.MAIN_WIDTH,ViewSize.MAIN_HEIGHT);
        scene.getStylesheets().add(CSSPath.MAIN);
        GUI.setScene(scene);
    }

    private void initPane()
    {
        AnchorPane pane = (AnchorPane) (GUI.getMainParent().lookup(PaneName.MAIN));
        GUI.setMainPane(pane);
        GUI.setChildren(pane.getChildren());
        GUI.switchToSignInUp();
    }

    private void show()
    {
        if(createEnvSuccess)
        {
            GUI.getStage().setScene(GUI.getScene());
            Utils.centerMainStage();
            GUI.getStage().show();
        }
    }

    private void setEnvironment()
    {
//        if(!Utils.goWithTestEnvironment())


        if(!Utils.goWithDevEnvironment())
//        if(!Utils.goWithProdEnvironment())
        {
            MessageBox.show("环境创建失败");
            createEnvSuccess = false;
        }
    }

    private void bindKeyEvent()
    {
        GUI.setKeyEvent(GUI.getScene().getAccelerators());

        addKeyEvent(KeyCode.ENTER,()->
        {
            if (GUI.isSignInUp())
                GUI.getSignInUpController().signInUp();
            else if (GUI.isRetrievePassword())
                GUI.getRetrievePasswordController().reset();
            else if(GUI.isWorker())
                GUI.switchToUserInformation();
            else if(GUI.isAdmin())
                GUI.switchToUserManagement();
            else if(GUI.isUserInformation())
            {
                UserInformationController controller = GUI.getUserInformationController();
                if(controller.isModifying())
                    controller.saveInformation();
                else
                    controller.modifyInformation();
            }
            else if(GUI.isSalaryEntry())
            {
                GUI.getSalaryEntryController().save();
            }
        });
        addKeyEvent(KeyCode.BACK_SPACE,()->
        {
            if(GUI.isRetrievePassword())
                GUI.switchToSignInUp();
            else if(GUI.isWorker() || GUI.isAdmin())
                GUI.switchToSignInUp();
            else if(GUI.isUserInformation() || GUI.isSalaryQuery())
                GUI.switchToWorker();
            else if(GUI.isUserManagement() || GUI.isSalaryEntry())
                GUI.switchToAdmin();
        });
        addKeyEvent(KeyCode.ESCAPE,()->
        {
            if(GUI.isRetrievePassword())
                GUI.switchToSignInUp();
            else if(GUI.isWorker() || GUI.isAdmin())
                GUI.switchToSignInUp();
            else if(GUI.isUserInformation() || GUI.isSalaryQuery())
                GUI.switchToWorker();
            else if(GUI.isUserManagement() || GUI.isSalaryEntry())
                GUI.switchToAdmin();
        });

        addKeyEvent(KeyCode.LEFT,()->
        {
            if(GUI.isWorker())
                GUI.switchToUserInformation();
            else if(GUI.isAdmin())
                GUI.switchToUserManagement();
            else if(GUI.isUserInformation())
                GUI.getUserInformationController().chooseFile();
        });

        addKeyEvent(KeyCode.DOWN,()->
        {
            if(GUI.isWorker())
                GUI.switchToUserInformation();
            else if(GUI.isAdmin())
                GUI.switchToUserManagement();
        });

        addKeyEvent(KeyCode.RIGHT,()->
        {
            if(GUI.isWorker())
                GUI.switchToSalaryQuery();
            else if(GUI.isAdmin())
                GUI.switchToSalaryEntry();
            else if(GUI.isUserInformation())
                GUI.getUserInformationController().modifyInformation();
            else if(GUI.isUserInformation())
            {
                UserInformationController controller = GUI.getUserInformationController();
                if(controller.isModifying())
                    controller.saveInformation();
                else
                    controller.modifyInformation();
            }
        });

        addKeyEvent(KeyCode.UP,()->
        {
            if(GUI.isWorker())
                GUI.switchToSalaryQuery();
            else if(GUI.isAdmin())
                GUI.switchToSalaryEntry();
        });

        addKeyEvent(KeyCode.TAB,()->
        {
            if(GUI.isSignInUp())
                GUI.getSignInUpController().focusOnCellphoneField();
            else if(GUI.isUserInformation())
                GUI.getUserInformationController().getModifyingFocus();
        });

    }

    private void addKeyEvent(KeyCode code,Runnable runnable)
    {
        GUI.getKeyEvent().put(new KeyCodeCombination(code),runnable);
    }
}
