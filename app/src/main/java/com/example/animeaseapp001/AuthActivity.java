package com.example.animeaseapp001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class AuthActivity extends AppCompatActivity {
    static LottieAnimationView lavAuth;
    static Button btnLogin;
    static Button btnRegister;
    static TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        lavAuth = findViewById(R.id.lavAuth);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvWelcome = findViewById(R.id.tvWelcome);
        lavAuth.setVisibility(View.INVISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);
        btnRegister.setVisibility(View.INVISIBLE);
        tvWelcome.setVisibility(View.INVISIBLE);

        startAnimation();

        btnLogin.setOnClickListener(v -> {
            startAnimation();
        });
        btnRegister.setOnClickListener(v -> {
        });
    }
    public static void startAnimation(){

        lavAuth.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.VISIBLE);
        tvWelcome.setVisibility(View.VISIBLE);

        // Animation for Welcome TextView
        // Keeping track of the initial scale of the TextView
        float tvWelcomeInitialScaleX = AuthActivity.tvWelcome.getScaleX();
        float tvWelcomeInitialScaleY = tvWelcome.getScaleY();
        //scaling the TextView down to its normal size
        ObjectAnimator tvWelcomeScaleXAnimator = ObjectAnimator.ofFloat(tvWelcome,"scaleX",tvWelcomeInitialScaleX+0.5f,tvWelcomeInitialScaleX);
        ObjectAnimator tvWelcomeScaleYAnimator = ObjectAnimator.ofFloat(tvWelcome,"scaleY",tvWelcomeInitialScaleY+0.5f,tvWelcomeInitialScaleY);
        tvWelcomeScaleXAnimator.setDuration(500);
        tvWelcomeScaleYAnimator.setDuration(500);
        ObjectAnimator tvWelcomeAlphaAnimator = ObjectAnimator.ofFloat(tvWelcome,"alpha",0f,1f);
        tvWelcomeAlphaAnimator.setDuration(600);
        //setting interpolators for TextView, decelerate means the animation will start fast and slowly slow down
        tvWelcomeScaleXAnimator.setInterpolator(new DecelerateInterpolator());
        tvWelcomeScaleYAnimator.setInterpolator(new DecelerateInterpolator());
        tvWelcomeAlphaAnimator.setInterpolator(new DecelerateInterpolator());
        AnimatorSet tvWelcomeAnimatorSet = new AnimatorSet();
        tvWelcomeAnimatorSet.playTogether(tvWelcomeScaleXAnimator, tvWelcomeScaleYAnimator, tvWelcomeAlphaAnimator);

        //Animation for Login Button
        ObjectAnimator btnLoginTranslationYAnimator = ObjectAnimator.ofFloat(btnLogin,"translationY",30f,0f);
        ObjectAnimator btnLoginAlphaAnimator = ObjectAnimator.ofFloat(btnLogin,"alpha",0f,1f);
        //setting interpolators for the Login Button
        btnLoginTranslationYAnimator.setInterpolator(new DecelerateInterpolator());
        btnLoginAlphaAnimator.setInterpolator(new DecelerateInterpolator());
        AnimatorSet btnLoginAnimatorSet = new AnimatorSet();
        btnLoginAnimatorSet.playTogether(btnLoginAlphaAnimator,btnLoginTranslationYAnimator);

        //Animation for Register Button
        ObjectAnimator btnRegisterTranslationYAnimator = ObjectAnimator.ofFloat(btnRegister,"translationY",30f,0f);
        ObjectAnimator btnRegisterAlphaAnimator = ObjectAnimator.ofFloat(btnRegister,"alpha",0f,1f);
        //setting interpolators for the Register Button
        btnRegisterTranslationYAnimator.setInterpolator(new DecelerateInterpolator());
        btnRegisterAlphaAnimator.setInterpolator(new DecelerateInterpolator());
        AnimatorSet btnRegisterAnimatorSet = new AnimatorSet();
        btnRegisterAnimatorSet.playTogether(btnRegisterAlphaAnimator,btnRegisterTranslationYAnimator);

        // Animation for LottieAnimationView
        //alpha animation, view fades in
        ObjectAnimator lavAlphaAnimator = ObjectAnimator.ofFloat(lavAuth,"alpha",0f,1f);
        //X position animation, the view slides in
        ObjectAnimator lavTranslationYAnimator = ObjectAnimator.ofFloat(lavAuth,"translationY",30f,0f);
        lavTranslationYAnimator.setInterpolator(new DecelerateInterpolator());
        //AnimatorSet allows for sequences of animation to play together or sequentially
        AnimatorSet lavAnimatorSet = new AnimatorSet();
        lavAnimatorSet.playTogether(lavAlphaAnimator,lavTranslationYAnimator);

        AnimatorSet mainAnimatorSet = new AnimatorSet();
        mainAnimatorSet.playSequentially(tvWelcomeAnimatorSet,btnLoginAnimatorSet,btnRegisterAnimatorSet,lavAnimatorSet);
        mainAnimatorSet.start();
    }
}