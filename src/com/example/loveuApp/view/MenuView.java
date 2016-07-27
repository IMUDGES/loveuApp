package com.example.loveuApp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;


/**
 * Created by jacob-wj on 2015/3/29.
 */
public class MenuView extends ViewGroup {
    public static final String TAG = "MenuView";

    //菜单的icon
    private int[] mDrawables;

    //菜单的文案
    private String[] mTitles;

    //子菜单的个数
    private int mChildCount;

    //屏幕横向的菜单个数，默认一排3个
    private final static int COLUME_NUM= 3;

    //菜单上下左右的间距
    private int mPadding = dpToPx(20);

    //菜单是否已经显示
    private boolean isMenuShowing = false;

    //每个icon的delay延迟时间
    private int[] mAnimDelay = new int[]{75,5,90,135,105,160};

    //动画持续时间
    public static final int DURATION = 350;

    //对外暴露的使用接口
    private onMenuClickListener onMenuListener;

    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.parseColor("#79000000"));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
        //由于这个view默认要求是全屏，所以这里就默认用全屏的尺寸
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width,height);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG,"onLayout");
        if (changed){
            int count = getChildCount();
            int index = -1;
            for (int i = 0; i < count; i++) {
                View view  = getChildAt(i);
                int cWidth = view.getMeasuredWidth();
                int cHeight = view.getMeasuredHeight();

                //start
                int startLeft = (getMeasuredWidth()-cWidth*COLUME_NUM)/2;
                int startTop = (getMeasuredHeight()-cHeight*(count/COLUME_NUM))/2;

                if (i%COLUME_NUM == 0){
                    index++;
                }
                int left = (i%COLUME_NUM)*cWidth+startLeft;
                int top = startTop+index*cHeight;
                view.layout(left,top,left+cWidth,top+cHeight);
            }
        }
    }

    /**
     * 设置菜单按钮的图片和文案
     */
    public void setMenuResource(int[] drawables, String[] mTitles) {
        if (drawables == null || mTitles == null)
            return;

        this.mDrawables = drawables;
        this.mTitles = mTitles;

        mChildCount = drawables.length;

        //根据子菜单的个数添加menu
        addItemView();
    }

    /**
     * 添加子菜单，每个子菜单也是通过映射布局文件得到childView
     */
    private void addItemView() {
        Log.e(TAG,"addItemView");
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < mChildCount; i++) {
            final int index = i;
            View view = inflater.inflate(R.layout.layout_menu_item,this,false);
            ImageView imageView = (ImageView) view.findViewById(R.id.test_imageView);
            TextView textView = (TextView) view.findViewById(R.id.test_textView);

            imageView.setImageResource(mDrawables[i]);
            textView.setText(mTitles[i]);

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMenuListener != null) {
                        onMenuListener.onMenuClick(index);
                    }
                }
            });
            view.setVisibility(INVISIBLE);
            addView(view);
        }
    }

    /**
     * 显示菜单
     */
    public void showMenu(){
        int count = getChildCount();
        int screenHeight = getMeasuredHeight();
        for (int i = 0; i < count; i++) {
            final  View child = getChildAt(i);
            final float  startY= child.getY();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                    child,View.Y,
                    screenHeight,
                    startY-25,
                    startY+10,
                    startY)
                    .setDuration(DURATION);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    child.setVisibility(VISIBLE);
                    child.setY(startY);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    child.setVisibility(VISIBLE);
                }
            });
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            AnimatorSet animatorSet =new AnimatorSet();
            animatorSet.setDuration(DURATION);
            animatorSet.setStartDelay(mAnimDelay[i]);
            animatorSet.play(objectAnimator);
            animatorSet.start();
        }
        isMenuShowing = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu(){
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final  View child = getChildAt(i);
            child.setVisibility(VISIBLE);
            final float  startY= child.getY();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                    child,View.Y,
                    child.getY(),
                    -child.getMeasuredWidth())
                    .setDuration(DURATION);

            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    child.setVisibility(INVISIBLE);
                    child.setY(startY);
                }
            });
            AnimatorSet animatorSet =new AnimatorSet();
            animatorSet.setDuration(DURATION);
            animatorSet.setStartDelay(mAnimDelay[i]);
            animatorSet.play(objectAnimator);
            animatorSet.start();
        }
        isMenuShowing = false;
    }

    public void toggleMenu(){
        if (isMenuShowing){
            closeMenu();
        }else{
            showMenu();
        }
    }

    public interface  onMenuClickListener{
        void onMenuClick(int position);
    }

    public void setOnMenuClickListener(onMenuClickListener listener){
        onMenuListener = listener;
    }

    private int dpToPx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
