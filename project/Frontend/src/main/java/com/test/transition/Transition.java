package com.test.transition;

import javafx.animation.Animation;

import java.util.ArrayList;

/**
 * 变换整体控制类
 */

public class Transition {
    private final ArrayList<Animation> animations = new ArrayList<>();

    public Transition add(CustomTransitionOperation animation)
    {
        animations.add(animation.build());
        return this;
    }

    public void play()
    {
        animations.forEach(Animation::play);
    }

}
