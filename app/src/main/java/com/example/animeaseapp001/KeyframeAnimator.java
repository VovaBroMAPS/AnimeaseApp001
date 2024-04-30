package com.example.animeaseapp001;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

public class KeyframeAnimator {

    private final InterpolatorType interpolatorType;

    public KeyframeAnimator(InterpolatorType interpolatorType) {
        this.interpolatorType = interpolatorType;
    }

    public enum InterpolatorType {
        EASE_IN_OUT,
        ACCELERATE,
        DECELERATE,
        ANTICIPATE,
        OVERSHOOT,
        ANTICIPATE_OVERSHOOT,
        BOUNCE
    }

    public Interpolator getInterpolator() {
        switch (interpolatorType) {
            case EASE_IN_OUT:
                return new AccelerateDecelerateInterpolator();
            case ACCELERATE:
                return new AccelerateInterpolator();
            case DECELERATE:
                return new DecelerateInterpolator();
            case ANTICIPATE:
                return new AnticipateInterpolator();
            case OVERSHOOT:
                return new OvershootInterpolator();
            case ANTICIPATE_OVERSHOOT:
                return new AnticipateOvershootInterpolator();
            case BOUNCE:
                return new BounceInterpolator();
            default:
                return new AccelerateDecelerateInterpolator();
        }
    }
}
