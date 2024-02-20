package com.example.animeaseapp001;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomAnimationHelper {
    private final View[] views;
    private final InterpolatorType interpolatorType;
    private AnimatorSet overallAnimatorSet;

    public enum InterpolatorType {
        EASE_IN_OUT,
        ACCELERATE,
        DECELERATE,
        ANTICIPATE,
        OVERSHOOT,
        ANTICIPATE_OVERSHOOT,
        BOUNCE
    }

    public CustomAnimationHelper(View[] views, AnimatorSet overallAnimatorSet, InterpolatorType interpolatorType) {
        this.views = views;
        this.overallAnimatorSet = overallAnimatorSet;
        this.interpolatorType = interpolatorType;
    }

    public void fadeIn(int duration) {
        AnimatorSet viewAnimatorSet = new AnimatorSet();
        this.overallAnimatorSet = new AnimatorSet();
        for (int i=0; i<this.views.length; i++){
            ObjectAnimator alphaAnimator =ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
            alphaAnimator.setDuration(duration);
            alphaAnimator.setInterpolator(getInterpolator());
            viewAnimatorSet.playTogether(alphaAnimator);
        }
        this.overallAnimatorSet.playSequentially();
        this.overallAnimatorSet.start();
    }

    public void fadeOut(int duration) {
        AnimatorSet viewAnimatorSet = new AnimatorSet();
        this.overallAnimatorSet = new AnimatorSet();
        for (int i=0; i<this.views.length; i++){
            ObjectAnimator alphaAnimator =ObjectAnimator.ofFloat(this.views[i], "alpha", 1f, 0f);
            alphaAnimator.setDuration(duration);
            alphaAnimator.setInterpolator(getInterpolator());
            viewAnimatorSet.playTogether(alphaAnimator);
        }
        this.overallAnimatorSet.playSequentially();
        this.overallAnimatorSet.start();
    }

    public void slideIn(int duration, float translateXFrom, float translateYFrom) {
        AnimatorSet viewAnimatorSet = new AnimatorSet();
        this.overallAnimatorSet = new AnimatorSet();
        float viewTranslationX, viewTranslationY;

        for(int i=0; i<this.views.length; i++){
            viewTranslationX = this.views[i].getTranslationX();
            viewTranslationY = this.views[i].getTranslationY();
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
            ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(this.views[i],"translationX",viewTranslationX+translateXFrom,viewTranslationX);
            ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(this.views[i],"translationY",viewTranslationY+translateYFrom,viewTranslationY);

            viewAnimatorSet.playTogether(alphaAnimator,translationXAnimator,translationYAnimator);
            this.overallAnimatorSet.playSequentially(viewAnimatorSet);
        }

        viewAnimatorSet.setDuration(duration);
        viewAnimatorSet.setInterpolator(getInterpolator());
        this.overallAnimatorSet.start();
    }

    public void slideOut(int duration, float translateXTo, float translateYTo) {
        AnimatorSet viewAnimatorSet = new AnimatorSet();
        this.overallAnimatorSet = new AnimatorSet();

        for(int i=0; i<this.views.length; i++){
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 1f, 0f);
            ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(this.views[i],"translationX",0f,translateXTo);
            ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(this.views[i],"translationY",0f,translateYTo);

            viewAnimatorSet.playTogether(alphaAnimator,translationXAnimator,translationYAnimator);
            this.overallAnimatorSet.playSequentially(viewAnimatorSet);
        }

        viewAnimatorSet.setDuration(duration);
        viewAnimatorSet.setInterpolator(getInterpolator());
        if(!isPlaying())
            this.overallAnimatorSet.start();
    }

    public void scaleIn(int duration,float scaleXFrom, float scaleYFrom){
        if(!isPlaying()){
            AnimatorSet viewAnimatorSet = new AnimatorSet();
            this.overallAnimatorSet = new AnimatorSet();
            List<Animator> animators = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleX",1f+scaleXFrom,1f);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleY", 1f+scaleYFrom,1f);

                animators.add(alphaAnimator);
                animators.add(scaleXAnimator);
                animators.add(scaleYAnimator);

            }
            viewAnimatorSet.playTogether(animators);
            viewAnimatorSet.setDuration(duration);
            viewAnimatorSet.setInterpolator(getInterpolator());
            this.overallAnimatorSet.playSequentially(viewAnimatorSet);
            this.overallAnimatorSet.start();
        }
    }

    public void scaleOut(int duration,float scaleXTo, float scaleYTo){
        if(!isPlaying()){
            AnimatorSet viewAnimatorSet = new AnimatorSet();
            this.overallAnimatorSet = new AnimatorSet();
            List<Animator> animators = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 1f, 0f);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleX",0f,scaleXTo);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleY", 0f,scaleYTo);

                animators.add(alphaAnimator);
                animators.add(scaleXAnimator);
                animators.add(scaleYAnimator);

            }
            viewAnimatorSet.playTogether(animators);
            viewAnimatorSet.setDuration(duration);
            viewAnimatorSet.setInterpolator(getInterpolator());
            this.overallAnimatorSet.playSequentially(viewAnimatorSet);
            this.overallAnimatorSet.start();
        }
    }

    public boolean isPlaying(){
        return overallAnimatorSet.isRunning() || overallAnimatorSet.isStarted();
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
