package com.example.loveuApp.updata;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by zsj on 2015/12/5 0005.
 */
public class EyepetizerMenuAnimation implements View.OnClickListener{

    private View mEyepetizerMenuView;
    private ImageView mActionMenu;
    private Context mContext;

    private ObjectAnimator mMenuOpenAnimation;
    private ObjectAnimator mMenuCloseAnimation;
    private ObjectAnimator mActionMenuAnimation;

    private DecelerateInterpolator mInterpolator;

    private boolean mIsMenuClose = true;
    private static final String ROTATION = "rotation";
    private static final String TRANSLATION = "translationY";


    public EyepetizerMenuAnimation(EyepetizerMenuBuilder builder) {
        this.mContext = builder.context;
        this.mEyepetizerMenuView = builder.eyepetizerMenuView;
        this.mActionMenu = builder.actionMenu;
        this.mInterpolator = new DecelerateInterpolator();
        this.mMenuOpenAnimation = buildMenuOpenAnimation();
        this.mMenuCloseAnimation = buildMenuCloseAnimation();
        mActionMenu.setOnClickListener(this);
        if (mIsMenuClose) {
            mEyepetizerMenuView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        mActionMenuAnimation = ObjectAnimator.ofFloat(
                mActionMenu, ROTATION, mIsMenuClose ? 0 : 90, mIsMenuClose ? 90 : 0 );
        mActionMenuAnimation.setInterpolator(mInterpolator);
        mActionMenuAnimation.setDuration(350);
        if (mIsMenuClose) {
            open();
        }else {
            close();
        }
    }

    private void open() {
        mIsMenuClose = false;
        AnimatorSet set = new AnimatorSet();
        set.playTogether(mActionMenuAnimation, mMenuOpenAnimation);
        set.start();
    }

    private void close() {
        mIsMenuClose = true;
        AnimatorSet set = new AnimatorSet();
        set.playTogether(mActionMenuAnimation, mMenuCloseAnimation);
        set.start();
    }

    private ObjectAnimator buildMenuOpenAnimation() {
        ObjectAnimator menuOpenAnimation = ObjectAnimator.ofFloat(
                mEyepetizerMenuView, TRANSLATION, -ScreenUtils.getHeight(mContext), 0);
        menuOpenAnimation.setInterpolator(mInterpolator);
        menuOpenAnimation.setDuration(350);
        menuOpenAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mEyepetizerMenuView.setVisibility(View.VISIBLE);
            }
        });
        return menuOpenAnimation;
    }

    private ObjectAnimator buildMenuCloseAnimation() {
        ObjectAnimator menuCloseAnimation = ObjectAnimator.ofFloat(
                mEyepetizerMenuView, TRANSLATION, 0, -ScreenUtils.getHeight(mContext));
        menuCloseAnimation.setInterpolator(mInterpolator);
        menuCloseAnimation.setDuration(350);
        menuCloseAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mEyepetizerMenuView.setVisibility(View.GONE);
            }
        });
        return menuCloseAnimation;
    }

    public static class EyepetizerMenuBuilder {

        private View eyepetizerMenuView;
        private ImageView actionMenu;
        private Context context;

        public EyepetizerMenuBuilder(Context context, View eyepetizerMenuView, ImageView actionMenu) {
            this.eyepetizerMenuView = eyepetizerMenuView;
            this.actionMenu = actionMenu;
            this.context = context;
        }

        public EyepetizerMenuAnimation build() {
            return new EyepetizerMenuAnimation(this);
        }

    }

}
