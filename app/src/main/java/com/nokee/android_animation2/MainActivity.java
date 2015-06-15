package com.nokee.android_animation2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private int[] res = {R.id.imageViewa, R.id.imageViewb, R.id.imageViewc, R.id.imageViewd,
            R.id.imageViewe, R.id.imageViewf, R.id.imageViewg, R.id.imageViewh};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void click(View view) {
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    public void move(View view) {
        // Old usage
//        TranslateAnimation animation = new TranslateAnimation(0, 200, 0, 0);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.startAnimation(animation);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        // The 1st way
//        ObjectAnimator.ofFloat(imageView, "rotation", 0F, 360F).setDuration(1000).start();
//        ObjectAnimator.ofFloat(imageView, "translationX", 0F, 200F).setDuration(1000).start();
//        ObjectAnimator.ofFloat(imageView, "translationY", 0F, 200F).setDuration(1000).start();

        // The 2nd way
//        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0, 360F);
//        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0, 200F);
//        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0, 200F);
//        ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3).setDuration(1000).start();

        // The 3rd way
//        ObjectAnimator object1 = ObjectAnimator.ofFloat(imageView, "rotation", 0F, 360F);
//        ObjectAnimator object2 = ObjectAnimator.ofFloat(imageView, "translationX", 0F, 200F);
//        ObjectAnimator object3 = ObjectAnimator.ofFloat(imageView, "translationY", 0F, 200F);
//        AnimatorSet set = new AnimatorSet();
////        set.playTogether(object1, object2, object3);
////        set.playSequentially(object1, object2, object3);
//        set.play(object2).with(object3);
//        set.play(object1).after(object2);
//        set.setDuration(1000);
//        set.start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0F, 1F);
        animator.setDuration(1000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e("Animation study", "onAnimationEnd");
                Toast.makeText(MainActivity.this, "anmi end", Toast.LENGTH_LONG).show();
            }
        });
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e("Animation study", "onAnimationEnd");
//                Toast.makeText(MainActivity.this, "anmi end", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewa:
                if (flag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            default:
                break;
        }
    }

    private void closeAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", i*50, 0);
            animator.setDuration(500);
            animator.setStartDelay(i * 300);
            animator.start();
        }
        flag = true;
    }

    private void startAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0F, i*50);
            animator.setDuration(500);
            animator.setStartDelay(i * 300);
            animator.start();
        }
        flag = false;
    }
}
