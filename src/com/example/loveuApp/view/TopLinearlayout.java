package com.example.loveuApp.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.loveuApp.R;
import io.rong.imkit.RongIM;
import org.w3c.dom.Text;

public class TopLinearlayout extends LinearLayout {

    private ViewPager mViewPager;
    private LinearLayout root;
    private MenuView mMenuView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragments[];
    private int mImgsR[] = {R.drawable.tabbar_home, R.drawable.tabbar_discover, 0, R.drawable.tabbar_profile, R.drawable.tabbar_message_center};
    private int mImgsH[] = {R.drawable.tabbar_home_highlighted, R.drawable.tabbar_discover_highlighted, 0, R.drawable.tabbar_profile_highlighted,
            R.drawable.tabbar_message_center_highlighted};

    private static final int COLOR_TEXT_NORMAL = 0XFFB4B4B4;
    private static final int COLOR_TEXT_HIGHLIGHT = 0XFFFF8200;

    private int nowposition = 0;
    private int oldposition = 0;

    public TopLinearlayout(Context context) {
        this(context, null);

    }

    public TopLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // TODO Auto-generated constructor stub
    }

    /**
     * 指示器跟随手指移动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        // TODO Auto-generated method stub

    }

    public void setFragmentEvent(FragmentManager fragmentManager,
                                 FragmentTransaction fragmentTransaction, Fragment[] fragments) {
        mFragmentManager = fragmentManager;
        mFragmentTransaction = fragmentTransaction;
        mFragments = fragments;
        setItemClickEvent();
        initImageView();
        //higthLightTextView(0);
    }

    public void setViewPage(ViewPager viewPager, int pos) {
        mViewPager = viewPager;

        setItemClickEvent();

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //higthLightTextView(position);
                nowposition = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                // 该方法用于设置其他滚动效果
                scroll(arg0, arg1);
            }

            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        mViewPager.setCurrentItem(pos);

        //higthLightTextView(pos);
    }

    public void initImageView() {
        for (int i =0;i<=4;i++){
            if (i==2)
                continue;
            View view = getChildAt(i);
            LinearLayout layout = (LinearLayout) view;
            ImageView img = (ImageView)layout.getChildAt(0);
            if (i==0){
                img.setImageResource(mImgsH[i]);
                TextView text = (TextView) layout.getChildAt(1);
                text.setTextColor(COLOR_TEXT_HIGHLIGHT);
            }else
                 img.setImageResource(mImgsR[i]);
        }
    }


    private void setColor(int pos) {
        Log.i("nowposition",pos+"");
        Log.i("oldposition",oldposition+"");
        View view = getChildAt(pos);
        //当前位置的布局
        if (pos==2&&oldposition!=2){
            Log.i("1","1");
            //中间的
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
            //除去中间的
            View view1 = getChildAt(oldposition);
            LinearLayout layout = (LinearLayout)view1;
            View img = layout.getChildAt(0);
            ImageView imgView = (ImageView) img;
            imgView.setImageResource(mImgsR[oldposition]);
            imgView.invalidate();
            View tex = layout.getChildAt(1);
            TextView text = (TextView)tex;
            text.setTextColor(COLOR_TEXT_NORMAL);
        }/*else if (pos==2&&oldposition==2){
            Log.i("2","2");
            //中间的
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
            //除去中间的
            View
        }*/else if (pos!=2&&oldposition==2){
            Log.i("3","3");
            //中间的
            View view2 = getChildAt(oldposition);
            ((TextView) view2).setTextColor(COLOR_TEXT_NORMAL);
            //除去中间的
            View view1 = getChildAt(pos);
            LinearLayout layout = (LinearLayout)view1;
            View img = layout.getChildAt(0);
            ImageView imgView = (ImageView) img;
            imgView.setImageResource(mImgsH[pos]);
            View tex = layout.getChildAt(1);
            TextView text = (TextView)tex;
            text.setTextColor(COLOR_TEXT_HIGHLIGHT);
        }else if (pos!=2&&oldposition!=2){
            Log.i("4","4");
            View view1 = getChildAt(pos);
            LinearLayout layout = (LinearLayout)view1;
            View img = layout.getChildAt(0);
            ImageView imgView = (ImageView) img;
            imgView.setImageResource(mImgsH[pos]);
            View tex = layout.getChildAt(1);
            TextView text = (TextView)tex;
            text.setTextColor(COLOR_TEXT_HIGHLIGHT);

            View view2 = getChildAt(oldposition);
            LinearLayout layout2 = (LinearLayout)view2;
            View img2 = layout2.getChildAt(0);
            ImageView imgView2 = (ImageView) img2;
            imgView2.setImageResource(mImgsR[oldposition]);
            imgView2.invalidate();
            View tex2 = layout2.getChildAt(1);
            TextView text2 = (TextView)tex2;
            text2.setTextColor(COLOR_TEXT_NORMAL);
        }



        /*if (pos==2)
        {
            TextView textView = (TextView)view;
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
            return;
        }*/
       /* if (oldposition==2){
            TextView textView = (TextView)view;
            ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
        }*/
       /* LinearLayout layout = (LinearLayout)view;
        Log.i("nowposition",pos+"");
        Log.i("oldposition",oldposition+"");
        if (pos!=2) {

            View img =layout.getChildAt(0);
            ImageView imgView = (ImageView) img;
            imgView.setImageResource(mImgsH[pos]);
            View tex = layout.getChildAt(1);
            TextView text = (TextView)tex;
            text.setTextColor(COLOR_TEXT_HIGHLIGHT);
            Log.i("H","ok");
        }
        if (oldposition!=2){
            View img = layout.getChildAt(0);
            ImageView imgView = (ImageView) img;
            imgView.setImageDrawable(getResources().getDrawable(mImgsR[pos]));
            imgView.invalidate();
            View tex = layout.getChildAt(1);
            TextView text = (TextView)tex;
            text.setTextColor(COLOR_TEXT_NORMAL);
            Log.i("R","ok");
        }*/

    }


    /**
     * 设置点击事件
     */
    private void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {

            final int j = i;

            View view = getChildAt(i);

            view.setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    Log.i("点击", "点击");
                    if (j == nowposition) {
                        return;
                    }

                    nowposition = j;
                    setColor(j);
                    oldposition = j;
                    //resetTextViewColor();
                    //higthLightTextView(j);
                    switch (j) {

                        case 0:


                            mFragmentTransaction = mFragmentManager
                                    .beginTransaction().hide(mFragments[1])
                                    .hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]);
                            mFragmentTransaction.show(mFragments[0]).commit();
                            break;

                        case 1:

                            mFragmentTransaction = mFragmentManager
                                    .beginTransaction().hide(mFragments[0])
                                    .hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]);
                            mFragmentTransaction.show(mFragments[1]).commit();
                            break;
                        case 2:
                            mFragmentTransaction = mFragmentManager
                                    .beginTransaction().hide(mFragments[0])
                                    .hide(mFragments[1]).hide(mFragments[3]).hide(mFragments[4]);
                            mFragmentTransaction.show(mFragments[2]).commit();
                            break;
                        case 3:

                            mFragmentTransaction = mFragmentManager
                                    .beginTransaction().hide(mFragments[0])
                                    .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[4]);
                            mFragmentTransaction.show(mFragments[3]).commit();
                            break;
                        case 4:

                            mFragmentTransaction = mFragmentManager
                                    .beginTransaction().hide(mFragments[0])
                                    .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
                            mFragmentTransaction.show(mFragments[4]).commit();
                            break;
                    }

                }
            });

        }
    }
}
