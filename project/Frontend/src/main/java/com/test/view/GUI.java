package com.test.view;

import com.test.constant.PaneName;
import com.test.constant.path.CSSPath;
import com.test.controller.admin.AdminController;
import com.test.controller.admin.salaryEntry.SalaryEntryController;
import com.test.controller.admin.userManagement.UserManagementController;
import com.test.controller.start.RetrievePasswordController;
import com.test.controller.start.SignInUpController;
import com.test.controller.worker.SalaryQueryController;
import com.test.controller.worker.UserInformationController;
import com.test.controller.worker.WorkerController;
import com.test.entity.Worker;
import com.test.utils.AvatarUtils;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * 界面整体控制类，包括初始化，加载，逻辑控制等
 */

public class GUI {
    @Setter
    @Getter
    private static Scene scene;
    @Getter
    @Setter
    private static Stage stage;
    @Setter
    @Getter
    private static AnchorPane mainPane;
    @Setter
    @Getter
    private static ObservableList<Node> children;

    //Loader
    @Setter
    @Getter
    private static FXMLLoader mainLoader;
    @Setter
    @Getter
    private static FXMLLoader signInUpLoader;
    @Setter
    @Getter
    private static FXMLLoader retrievePasswordLoader;

    @Setter
    @Getter
    private static FXMLLoader workerLoader;
    @Setter
    @Getter
    private static FXMLLoader userInforamtionLoader;
    @Setter
    @Getter
    private static FXMLLoader salaryQueryLoader;

    @Setter
    @Getter
    private static FXMLLoader adminLoader;
    @Setter
    @Getter
    private static FXMLLoader userManagementLoader;
    @Setter
    @Getter
    private static FXMLLoader salaryEntryLoader;


    //Controller
    @Setter
    @Getter
    private static SignInUpController signInUpController;
    @Setter
    @Getter
    private static RetrievePasswordController retrievePasswordController;

    @Setter
    @Getter
    private static WorkerController workerController;
    @Setter
    @Getter
    private static UserInformationController userInformationController;
    @Setter
    @Getter
    private static SalaryQueryController salaryQueryController;

    @Setter
    @Getter
    private static AdminController adminController;
    @Setter
    @Getter
    private static UserManagementController userManagementController;
    @Setter
    @Getter
    private static SalaryEntryController salaryEntryController;

    //Parent
    @Setter
    @Getter
    private static Parent mainParent;
    @Setter
    @Getter
    private static Parent signInUpParent;
    @Setter
    @Getter
    private static Parent retrievePasswordParent;

    @Setter
    @Getter
    private static Parent workerParent;
    @Setter
    @Getter
    private static Parent userInformationParent;
    @Setter
    @Getter
    private static Parent salaryQueryParent;

    @Setter
    @Getter
    private static Parent adminParent;
    @Setter
    @Getter
    private static Parent userManagmentParent;
    @Setter
    @Getter
    private static Parent salaryEntryParent;

    @Getter
    @Setter
    private static Worker worker;

    @Getter
    @Setter
    private static ObservableMap<KeyCombination,Runnable> keyEvent;

    public static void switchToSignInUp()
    {
        if(GUI.isUserInformation())
        {
            AvatarUtils.deletePathIfExists();
            GUI.getUserInformationController().reset();
        }
        mainParent.requestFocus();
        children.clear();
        children.add(signInUpParent.lookup(PaneName.SIGN_IN_UP));
        scene.getStylesheets().add(CSSPath.SIGN_IN_UP);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("-");
        minimize.setFont(new Font("System", 20));
        minimize.setOnMouseClicked(v->minimize());
    }

    public static void switchToRetrievePassword()
    {
        mainParent.requestFocus();
        children.clear();
        children.add(retrievePasswordParent.lookup(PaneName.RETRIEVE_PASSWORD));
        scene.getStylesheets().add(CSSPath.RETRIEVE_PASSWORD);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToSignInUp());
    }

    public static void switchToWorker()
    {
        if(GUI.isUserInformation())
        {
            UserInformationController controller = GUI.getUserInformationController();
            if(controller.isModifying())
                controller.saveInformation();
            controller.syncDatabaseIfChanged();
            controller.reset();
        }
        else if(GUI.isSalaryQuery())
            GUI.getSalaryQueryController().clear();
        mainParent.requestFocus();
        children.clear();
        children.add(workerParent.lookup(PaneName.WORKER));
        scene.getStylesheets().add(CSSPath.WORKER);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToSignInUp());
    }

    public static void switchToUserInformation()
    {
        mainParent.requestFocus();
        children.clear();
        children.add(userInformationParent.lookup(PaneName.USER_INFORMATION));
        scene.getStylesheets().add(CSSPath.USER_INFORMATION);
        userInformationController.init(worker);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToWorker());
    }

    public static void switchToSalaryQuery()
    {
        mainParent.requestFocus();
        children.clear();
        children.add(salaryQueryParent.lookup(PaneName.SALARY_QUERY));
        scene.getStylesheets().add(CSSPath.SALARY_QUERY);
        salaryQueryController.init(worker);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToWorker());
    }

    public static void switchToAdmin()
    {
        if(GUI.isUserManagement() || GUI.isSalaryEntry())
        {
            GUI.getUserManagementController().syncDatabaseIfChanged();
            GUI.getSalaryEntryController().syncDatabaseIfChanged();
        }
        mainParent.requestFocus();
        children.clear();
        children.add(adminParent.lookup(PaneName.ADMIN));
        scene.getStylesheets().add(CSSPath.ADMIN);
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToSignInUp());
    }

    public static void switchToUserManagement()
    {
        mainParent.requestFocus();
        children.clear();
        children.add(userManagmentParent.lookup(PaneName.USER_MANAGEMENT));
        scene.getStylesheets().add(CSSPath.USER_MANAGEMENT);
        if(userManagementController.isInit())
            userManagementController.refresh();
        else
            userManagementController.init();
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToAdmin());
    }

    public static void switchToSalaryEntry()
    {
        mainParent.requestFocus();
        children.clear();
        children.add(salaryEntryParent.lookup(PaneName.SALARY_ENTRY));
        scene.getStylesheets().add(CSSPath.SALARY_ENTRY);
        if(salaryEntryController.isInit())
            salaryEntryController.refresh();
        else
            salaryEntryController.init();
        Label minimize = (Label) (mainParent.lookup("#minimize"));
        minimize.setText("<");
        minimize.setFont(new Font("System", 15));
        minimize.setOnMouseClicked(v->switchToAdmin());
    }

    public static boolean isSignInUp()
    {
        return children.contains(signInUpParent.lookup(PaneName.SIGN_IN_UP));
    }

    public static boolean isRetrievePassword()
    {
        return children.contains(retrievePasswordParent.lookup(PaneName.RETRIEVE_PASSWORD));
    }

    public static boolean isWorker()
    {
        return children.contains(workerParent.lookup(PaneName.WORKER));
    }

    public static boolean isUserInformation()
    {
        return children.contains(userInformationParent.lookup(PaneName.USER_INFORMATION));
    }

    public static boolean isSalaryQuery()
    {
        return children.contains(salaryQueryParent.lookup(PaneName.SALARY_QUERY));
    }

    public static boolean isAdmin()
    {
        return children.contains(adminParent.lookup(PaneName.ADMIN));
    }

    public static boolean isUserManagement()
    {
        return children.contains(userManagmentParent.lookup(PaneName.USER_MANAGEMENT));
    }

    public static boolean isSalaryEntry()
    {
        return children.contains(salaryEntryParent.lookup(PaneName.SALARY_ENTRY));
    }

    public static void close()
    {
        AvatarUtils.deletePathIfExists();
        stage.close();
    }

    public static void minimize()
    {
        stage.setIconified(true);
    }

}
