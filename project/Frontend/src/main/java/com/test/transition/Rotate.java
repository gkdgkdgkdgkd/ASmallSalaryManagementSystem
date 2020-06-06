package com.test.transition;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * 旋转动画
 */

public class Rotate implements CustomTransitionOperation{
    private final RotateTransition transition = new RotateTransition(Duration.seconds(1));

    public Rotate(Node node)
    {
        transition.setNode(node);
    }

    public Rotate seconds(double seconds)
    {
        transition.setDuration(Duration.seconds(seconds));
        return this;
    }

    public Rotate to(double to)
    {
        transition.setToAngle(to);
        return this;
    }

    @Override
    public Animation build() {
        return transition;
    }

    @Override
    public void play() {
        transition.play();
    }
}
