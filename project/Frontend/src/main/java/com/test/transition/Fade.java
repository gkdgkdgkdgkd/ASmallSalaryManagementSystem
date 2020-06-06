package com.test.transition;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

/**
 * 渐隐动画
 */

@NoArgsConstructor
public class Fade implements CustomTransitionOperation {

    private final FadeTransition transition = new FadeTransition(Duration.seconds(defaultSeconds));

    public Fade(Node node)
    {
        transition.setNode(node);
    }

    public Fade fromTo(double from, double to)
    {
        transition.setFromValue(from);
        transition.setToValue(to);
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
