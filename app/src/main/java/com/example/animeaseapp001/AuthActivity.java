package com.example.animeaseapp001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class AuthActivity extends AppCompatActivity {
    static LottieAnimationView lavAuth;
    static LottieAnimationView lavWelcome;
    static Button btnAuthLogin;
    static Button btnAuthRegister;
    static TextView tvAuthAnimease,tvAuthToContinue;
    static ImageView grassImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        lavAuth = findViewById(R.id.lavAuth);
        btnAuthLogin = findViewById(R.id.btnAuthLogin);
        btnAuthRegister = findViewById(R.id.btnAuthRegister);
        lavWelcome = findViewById(R.id.lavWelcome);
        tvAuthAnimease = findViewById(R.id.tvAuthAnimease);
        tvAuthToContinue = findViewById(R.id.tvAuthToContinue);
        grassImageView = findViewById(R.id.grassImageView);

        startWelcomeAnimation();

        btnAuthLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this,LoginActivity.class);
            startActivity(intent);
        });
        btnAuthRegister.setOnClickListener(v -> {
            startWelcomeAnimation();
            //Intent intent = new Intent(AuthActivity.this,RegisterActivity.class);
            //startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        startResumeAnimation();
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_settings){
            Intent intent= new Intent(AuthActivity.this,SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void startWelcomeAnimation(){

        lavWelcome.setVisibility(View.VISIBLE);
        grassImageView.setVisibility(View.VISIBLE);
        lavAuth.setVisibility(View.GONE);
        tvAuthAnimease.setVisibility(View.GONE);
        tvAuthToContinue.setVisibility(View.GONE);
        btnAuthRegister.setVisibility(View.GONE);
        btnAuthLogin.setVisibility(View.GONE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AuthActivity.lavAuth.setVisibility(View.VISIBLE);
                        AuthActivity.tvAuthAnimease.setVisibility(View.VISIBLE);
                        AuthActivity.tvAuthToContinue.setVisibility(View.VISIBLE);
                        AuthActivity.btnAuthLogin.setVisibility(View.VISIBLE);
                        AuthActivity.btnAuthRegister.setVisibility(View.VISIBLE);
                        AuthActivity.lavWelcome.setVisibility(View.GONE);
                        AuthActivity.grassImageView.setVisibility(View.GONE);

                        AnimatorSet lavAuthAndBtnAnimatorSet = new AnimatorSet();
                        CustomAnimationHelper lavAuthAndBtnAnimator = new CustomAnimationHelper(new View[]{lavAuth,tvAuthAnimease,tvAuthToContinue,btnAuthLogin,btnAuthRegister},lavAuthAndBtnAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,false);
                        lavAuthAndBtnAnimator.slideIn(100,0f,-40f);
                    }
                });
            }
        },4200);

        AnimatorSet tvWelcomeSlideInAnimatorSet = new AnimatorSet();
        AnimatorSet tvWelcomeSlideOutAnimatorSet = new AnimatorSet();
        CustomAnimationHelper tvWelcomeSlideInAnimator = new CustomAnimationHelper(new View[]{lavWelcome},tvWelcomeSlideInAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,true);
        CustomAnimationHelper tvWelcomeSlideOutAnimator = new CustomAnimationHelper(new View[]{lavWelcome},tvWelcomeSlideOutAnimatorSet,CustomAnimationHelper.InterpolatorType.ACCELERATE,true);

        AnimatorSet grassImageViewScaleInAnimatorSet = new AnimatorSet();
        AnimatorSet grassImageViewScaleOutAnimatorSet = new AnimatorSet();
        CustomAnimationHelper grassImageViewScaleInAnimator = new CustomAnimationHelper(new View[]{grassImageView},grassImageViewScaleInAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,true);
        CustomAnimationHelper grassImageViewScaleOutAnimator = new CustomAnimationHelper(new View[]{grassImageView},grassImageViewScaleOutAnimatorSet, CustomAnimationHelper.InterpolatorType.ACCELERATE,true);
        grassImageViewScaleInAnimator.scaleIn(400,0f,-1f);
        grassImageViewScaleOutAnimatorSet.setStartDelay(3700);
        grassImageViewScaleOutAnimator.scaleOut(400,0f,-1f);

        tvWelcomeSlideOutAnimatorSet.setStartDelay(3500);
        tvWelcomeSlideInAnimator.slideIn(1000,-500f,0f);
        tvWelcomeSlideOutAnimator.slideOut(1000,500f,0f);
    }

    public static void startResumeAnimation(){
        AnimatorSet lavAuthAndBtnAnimatorSet = new AnimatorSet();
        CustomAnimationHelper lavAuthAndBtnAnimator = new CustomAnimationHelper(new View[]{lavAuth,tvAuthAnimease,tvAuthToContinue,btnAuthLogin,btnAuthRegister},lavAuthAndBtnAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,false);
        lavAuthAndBtnAnimator.slideIn(150,0f,-40f);
        lavAuthAndBtnAnimatorSet.setStartDelay(300);
    }
}