package com.example.animeaseapp001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class AuthActivity extends AppCompatActivity {
    LottieAnimationView lavAuth,lavWelcome;
    Button btnAuthLogin;
    Button btnAuthRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        lavAuth = findViewById(R.id.lavAuth);
        btnAuthLogin = findViewById(R.id.btnAuthLogin);
        btnAuthRegister = findViewById(R.id.btnAuthRegister);
        lavWelcome = findViewById(R.id.lavWelcome);

        startTransitionAnimation();

        btnAuthLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        });
        btnAuthRegister.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    protected void onResume() {
        startTransitionAnimation();
        super.onResume();
    }

    @Override
    protected void onStop() {
        lavAuth.setVisibility(View.GONE);
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

    public void startTransitionAnimation(){

        AnimatorSet tvWelcomeAnimatorSet = new AnimatorSet();
        AnimatorSet lavAuthAndBtnAnimatorSet = new AnimatorSet();
        CustomAnimationHelper tvWelcomeAnimator = new CustomAnimationHelper(new View[]{lavWelcome},tvWelcomeAnimatorSet, CustomAnimationHelper.InterpolatorType.OVERSHOOT,true);
        CustomAnimationHelper lavAuthAndBtnAnimator = new CustomAnimationHelper(new View[]{btnAuthLogin,btnAuthRegister,lavAuth},lavAuthAndBtnAnimatorSet, CustomAnimationHelper.InterpolatorType.DECELERATE,false);

        lavAuthAndBtnAnimatorSet.setStartDelay(300);
        tvWelcomeAnimator.scaleIn(300,0.5f,0.5f);
        lavAuthAndBtnAnimator.slideIn(150,0f,-30f);
    }

}