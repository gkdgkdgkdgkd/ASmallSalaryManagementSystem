package com.test.transition;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

/**
 * 移动动画
 */

@NoArgsConstructor
public class Move implements CustomTransitionOperation{
    private final TranslateTransition transition = new TranslateTransition(Duration.seconds(defaultSeconds));

    public Move(Node node)
    {
        transition.setNode(node);
    }

    public Move x(double x)
    {
        transition.setToX(x);
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
