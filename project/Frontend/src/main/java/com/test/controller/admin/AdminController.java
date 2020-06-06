package com.test.controller.admin;


import com.test.transition.Fade;
import com.test.transition.Move;
import com.test.transition.Scale;
import com.test.transition.Transition;
import com.test.view.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;

/**
 * 管理员界面控制类
 */

public class AdminController {
    @FXML
    private ImageView userImage;
    @FXML
    private ImageView workloadImage;

    @FXML
    private Polygon userPolygon;
    @FXML
    private Polygon workloadPolygon;

    @FXML
    private Label userLabel;
    @FXML
    private Label workloadLabel;

    public void toUserManagement()
    {
        GUI.switchToUserManagement();
    }

    public void toWorkloadEntry()
    {
        GUI.switchToSalaryEntry();
    }

    public void userEnter()
    {
        new Transition()
        .add(new Move(userImage).x(-70))
        .add(new Fade(userLabel).fromTo(0,1)).add(new Move(userLabel).x(95))
        .add(new Scale(userPolygon).ratio(1.8)).add(new Move(userPolygon).x(180))
        .add(new Scale(workloadPolygon).ratio(1.8)).add(new Move(workloadPolygon).x(180))
        .play();
    }

    public void userExited()
    {
        new Transition()
        .add(new Move(userImage).x(0))
        .add(new Fade(userLabel).fromTo(1,0)).add(new Move(userLabel).x(0))
        .add(new Scale(userPolygon).ratio(1)).add(new Move(userPolygon).x(0))
        .add(new Scale(workloadPolygon).ratio(1)).add(new Move(workloadPolygon).x(0))
        .play();
    }

    public void workloadEnter()
    {
        new Transition()
        .add(new Move(workloadImage).x(50))
        .add(new Move(workloadLabel).x(-130)).add(new Fade(workloadLabel).fromTo(0,1))
        .add(new Scale(userPolygon).ratio(1.8)).add(new Move(userPolygon).x(-140))
        .add(new Scale(workloadPolygon).ratio(1.8)).add(new Move(workloadPolygon).x(-140))
        .play();
    }

    public void workloadExited()
    {
        new Transition()
        .add(new Move(workloadImage).x(0))
        .add(new Move(workloadLabel).x(0)).add(new Fade(workloadLabel).fromTo(1,0))
        .add(new Scale(userPolygon).ratio(1)).add(new Move(userPolygon).x(0))
        .add(new Scale(workloadPolygon).ratio(1)).add(new Move(workloadPolygon).x(0))
        .play();
    }
}
