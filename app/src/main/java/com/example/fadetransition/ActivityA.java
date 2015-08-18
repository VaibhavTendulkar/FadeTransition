package com.example.fadetransition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ActivityA extends AppCompatActivity {

    public ViewGroup mRoot;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_a);
            transition.excludeTarget(android.R.id.navigationBarBackground, true);
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setExitTransition(transition);
            Slide slide = new Slide();
            slide.setDuration(5000);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setReenterTransition(slide);
            getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        }

        setContentView(R.layout.activity_a);

        mRoot = (ViewGroup) findViewById(R.id.container_a);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickButton_1(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
            Fade fade = new Fade();
            fade.setDuration(5000);
            TransitionManager.beginDelayedTransition(mRoot, fade);
            toggleVisibility(mButton1, mButton2, mButton3, mButton4);
        }
        else {

        }
    }

    public void onClickButton_2(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
            Explode explode = new Explode();
            explode.setDuration(5000);
            TransitionManager.beginDelayedTransition(mRoot, explode);
            toggleVisibility(mButton1, mButton2, mButton3, mButton4);
        }
        else {

        }
    }

    public void onClickButton_3(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
            startActivity(new Intent(this, ActivityB.class), compat.toBundle());
        }
        else {
            startActivity(new Intent(this, ActivityB.class));
        }
    }

    public void onClickButton_4(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
        v.setTransitionName("selectedButton");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, v.getTransitionName());
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent, optionsCompat.toBundle());
        }
        else{
            startActivity(new Intent(this, ActivityB.class));
        }
    }

    public void toggleVisibility(View... views) {
        for (View current : views) {
            if (current.getVisibility() == View.VISIBLE) {
                current.setVisibility(View.INVISIBLE);
            }
        }
    }
}
