package com.test.controller.worker;

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
 * 用户界面整体控制类
 */

public class WorkerController {
    @FXML
    private ImageView userImage;
    @FXML
    private ImageView queryImage;

    @FXML
    private Polygon userPolygon;
    @FXML
    private Polygon queryPolygon;

    @FXML
    private Label userLabel;
    @FXML
    private Label queryLabel;

    public void toUserInformation()
    {
        GUI.switchToUserInformation();
    }

    public void toSalaryQuery()
    {
        GUI.switchToSalaryQuery();
    }

    public void userEnter()
    {
        new Transition()
        .add(new Move(userImage).x(-70))
        .add(new Fade(userLabel).fromTo(0,1)).add(new Move(userLabel).x(95))
        .add(new Scale(userPolygon).ratio(1.8)).add(new Move(userPolygon).x(180))
        .add(new Scale(queryPolygon).ratio(1.8)).add(new Move(queryPolygon).x(180))
        .play();
    }

    public void userExited()
    {
        new Transition()
        .add(new Move(userImage).x(0))
        .add(new Fade(userLabel).fromTo(1,0)).add(new Move(userLabel).x(0))
        .add(new Scale(userPolygon).ratio(1)).add(new Move(userPolygon).x(0))
        .add(new Scale(queryPolygon).ratio(1)).add(new Move(queryPolygon).x(0))
        .play();
    }

    public void salaryEnter()
    {
        new Transition()
        .add(new Move(queryImage).x(50))
        .add(new Move(queryLabel).x(-130)).add(new Fade(queryLabel).fromTo(0,1))
        .add(new Scale(userPolygon).ratio(1.8)).add(new Move(userPolygon).x(-140))
        .add(new Scale(queryPolygon).ratio(1.8)).add(new Move(queryPolygon).x(-140))
        .play();
    }

    public void salaryExited()
    {
        new Transition()
        .add(new Move(queryImage).x(0))
        .add(new Move(queryLabel).x(0)).add(new Fade(queryLabel).fromTo(1,0))
        .add(new Scale(userPolygon).ratio(1)).add(new Move(userPolygon).x(0))
        .add(new Scale(queryPolygon).ratio(1)).add(new Move(queryPolygon).x(0))
        .play();
    }

}
