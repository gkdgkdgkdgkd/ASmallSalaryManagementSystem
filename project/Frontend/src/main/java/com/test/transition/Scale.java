package com.test.transition;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

/**
 * 缩放动画
 */

@NoArgsConstructor
public class Scale implements CustomTransitionOperation{
    private final ScaleTransition transition = new ScaleTransition(Duration.seconds(defaultSeconds));

    public Scale(Node node)
    {
        transition.setNode(node);
    }

    public Scale ratio(double ratio)
    {
        transition.setToX(ratio);
        transition.setToY(ratio);
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
