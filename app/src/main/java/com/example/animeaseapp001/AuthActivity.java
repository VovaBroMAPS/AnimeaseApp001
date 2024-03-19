package com.example.animeaseapp001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        startWelcomeAnimation();

        btnAuthLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this,LoginActivity.class);
            startActivity(intent);
        });
        btnAuthRegister.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this,RegisterActivity.class);
            startActivity(intent);
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
        btnAuthRegister.setVisibility(View.GONE);
        btnAuthLogin.setVisibility(View.GONE);
        lavAuth.setVisibility(View.GONE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AuthActivity.btnAuthLogin.setVisibility(View.VISIBLE);
                        AuthActivity.btnAuthRegister.setVisibility(View.VISIBLE);
                        AuthActivity.lavAuth.setVisibility(View.VISIBLE);
                        AuthActivity.lavWelcome.setVisibility(View.GONE);

                        AnimatorSet lavAuthAndBtnAnimatorSet = new AnimatorSet();
                        CustomAnimationHelper lavAuthAndBtnAnimator = new CustomAnimationHelper(new View[]{btnAuthLogin,btnAuthRegister,lavAuth},lavAuthAndBtnAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,false);
                        lavAuthAndBtnAnimator.slideIn(100,0f,-30f);
                    }
                });
            }
        },4200);

        AnimatorSet tvWelcomeScaleInAnimatorSet = new AnimatorSet();
        AnimatorSet tvWelcomeScaleOutAnimatorSet = new AnimatorSet();
        CustomAnimationHelper tvWelcomeScaleInAnimator = new CustomAnimationHelper(new View[]{lavWelcome},tvWelcomeScaleInAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,true);
        CustomAnimationHelper tvWelcomeScaleOutAnimator = new CustomAnimationHelper(new View[]{lavWelcome},tvWelcomeScaleOutAnimatorSet,CustomAnimationHelper.InterpolatorType.ANTICIPATE,true);

        tvWelcomeScaleOutAnimatorSet.setStartDelay(3500);
        tvWelcomeScaleInAnimator.scaleIn(1000,1f,1f);
        tvWelcomeScaleOutAnimator.scaleOut(600,-1f,-1f);
    }

    public static void startResumeAnimation(){
        AnimatorSet lavAuthAndBtnAnimatorSet = new AnimatorSet();
        CustomAnimationHelper lavAuthAndBtnAnimator = new CustomAnimationHelper(new View[]{btnAuthLogin,btnAuthRegister,lavAuth},lavAuthAndBtnAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,false);
        lavAuthAndBtnAnimator.slideIn(150,0f,-30f);
        lavAuthAndBtnAnimatorSet.setStartDelay(300);
    }
}