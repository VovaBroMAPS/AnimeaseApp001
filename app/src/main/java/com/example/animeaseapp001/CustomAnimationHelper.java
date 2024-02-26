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

import java.util.ArrayList;
import java.util.List;

public class CustomAnimationHelper {
    private final View[] views;
    private final InterpolatorType interpolatorType;
    private AnimatorSet overallAnimatorSet;
    private boolean playTogether;

    public enum InterpolatorType {
        EASE_IN_OUT,
        ACCELERATE,
        DECELERATE,
        ANTICIPATE,
        OVERSHOOT,
        ANTICIPATE_OVERSHOOT,
        BOUNCE
    }

    public CustomAnimationHelper(View[] views, AnimatorSet overallAnimatorSet, InterpolatorType interpolatorType,boolean playTogether) {
        this.views = views;
        this.overallAnimatorSet = overallAnimatorSet;
        this.interpolatorType = interpolatorType;
        this.playTogether = playTogether;
    }

    public void fadeIn(int duration) {
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for (int i=0; i<this.views.length; i++){
                ObjectAnimator alphaAnimator =ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
                AnimatorSet viewAnimatorSet = new AnimatorSet();

                alphaAnimator.setDuration(duration);
                alphaAnimator.setInterpolator(getInterpolator());
                viewAnimatorSet.playTogether(alphaAnimator);
                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
    }

    public void fadeOut(int duration) {
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for (int i=0; i<this.views.length; i++){
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                ObjectAnimator alphaAnimator =ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);

                alphaAnimator.setDuration(duration);
                alphaAnimator.setInterpolator(getInterpolator());
                viewAnimatorSet.playTogether(alphaAnimator);
                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
    }

    public void slideIn(int duration, float translateXFrom, float translateYFrom) {
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();

                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
                ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(this.views[i],"translationX",translateXFrom,0f);
                ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(this.views[i],"translationY",translateYFrom,0f);

                animators.add(alphaAnimator);
                animators.add(translationXAnimator);
                animators.add(translationYAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());

                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
    }

    public void slideOut(int duration, float translateXTo, float translateYTo) {
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();

                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 1f, 0f);
                ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(this.views[i],"translationX",0f,translateXTo);
                ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(this.views[i],"translationY",0f,translateYTo);

                animators.add(alphaAnimator);
                animators.add(translationXAnimator);
                animators.add(translationYAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());

                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
    }

    public void rotateIn(int duration, float rotateToDegree) {
        //if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++) {
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i],"alpha",0,1);
                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(this.views[i],"rotation",0,rotateToDegree);

                animators.add(alphaAnimator);
                animators.add(rotationAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());
                animatorSets.add(viewAnimatorSet);
            }

            if(this.playTogether){
                this.overallAnimatorSet.playTogether(animatorSets);
            }
            else{
                this.overallAnimatorSet.playSequentially(animatorSets);
            }
            this.overallAnimatorSet.start();
        //}
    }

    public void rotateOut(int duration, float rotateToDegree) {
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++) {
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i],"alpha",1,0);
                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(this.views[i],"rotation",rotateToDegree,0);

                animators.add(alphaAnimator);
                animators.add(rotationAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());
                animatorSets.add(viewAnimatorSet);
            }

            if(this.playTogether){
                this.overallAnimatorSet.playTogether(animatorSets);
            }
            else{
                this.overallAnimatorSet.playSequentially(animatorSets);
            }
            this.overallAnimatorSet.start();
        }
    }

    public void scaleIn(int duration,float scaleXFrom, float scaleYFrom){
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 0f, 1f);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleX",1f+scaleXFrom,1f);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleY", 1f+scaleYFrom,1f);

                animators.add(alphaAnimator);
                animators.add(scaleXAnimator);
                animators.add(scaleYAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());
                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
    }

    public void scaleOut(int duration,float scaleXTo, float scaleYTo){
        if(!isPlaying()){
            List<Animator> animatorSets = new ArrayList<>();

            for(int i=0; i<this.views.length; i++){
                AnimatorSet viewAnimatorSet = new AnimatorSet();
                List<Animator> animators = new ArrayList<>();
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this.views[i], "alpha", 1f, 0f);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleX",1f,1f+scaleXTo);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this.views[i],"scaleY", 1f,1f+scaleYTo);

                animators.add(alphaAnimator);
                animators.add(scaleXAnimator);
                animators.add(scaleYAnimator);

                viewAnimatorSet.playTogether(animators);
                viewAnimatorSet.setDuration(duration);
                viewAnimatorSet.setInterpolator(getInterpolator());
                animatorSets.add(viewAnimatorSet);
            }

            if(playTogether)
                this.overallAnimatorSet.playTogether(animatorSets);
            else
                this.overallAnimatorSet.playSequentially(animatorSets);

            this.overallAnimatorSet.start();
        }
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

    public boolean isPlaying(){
        return overallAnimatorSet.isRunning() || overallAnimatorSet.isStarted();
    }
}
