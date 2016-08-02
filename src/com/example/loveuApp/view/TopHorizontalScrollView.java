package com.example.loveuApp.view;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TopHorizontalScrollView extends HorizontalScrollView {

	private Context mContext;
	private static final int COLOR_TEXT_NORMAL = 0X77000000;
	private static final int COLOR_TEXT_HIGHLIGHT = 0Xff0099ff;
	private ViewPager mPager;
	private int nowposition;
	private LinearLayout layout;
	private int cCount;
	private int screemWidth;
	private int touched;
	
	private int mTabVisibleCount = 3;

	public TopHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public TopHorizontalScrollView(Context context) {
		this(context, null);
	}

	public void setViewPager(ViewPager viewPager, int pos, Context context) {
		mPager = viewPager;
		mContext = context;
		screemWidth = getScreemWidth();
		layout = (LinearLayout) getChildAt(0);
		cCount = layout.getChildCount();
		setItemOnClickEvent();
		higthLightTextView(0);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				resetTextViewColor();
				higthLightTextView(position);
				nowposition = position;
			}

			public void onPageScrolled(int nowposition, float offset, int arg2) {
				// TODO Auto-generated method stub
				// 该方法用于设置其他滚动效果
				scroll(nowposition, offset);
			}

			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		mPager.setCurrentItem(pos);

	}

	private void scroll(int position, float offset) {
		// TODO Auto-generated method stub

		//Log.i("position",position+"");
		//Log.i("nowposition",nowposition+"");
		if (position == 0 && touched == mTabVisibleCount-1){
			this.scrollTo(screemWidth ,0);
			return ;
		}
		 if (position >= (mTabVisibleCount - 2) && offset > 0
	                && cCount > mTabVisibleCount) {

			this.scrollTo((position - (mTabVisibleCount - 2)) * screemWidth / 3
					+ (int) (screemWidth / 3 * offset), 0);

		}

		invalidate();
	}

	private void resetTextViewColor() {
		for (int i = 0; i < cCount; i++) {
			View view = layout.getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	private void higthLightTextView(int pos) {
		resetTextViewColor();
		View view = layout.getChildAt(pos);
		if (view instanceof TextView) {
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
	}

	private void setItemOnClickEvent() {

		// int cCount = getChildCount();
		for (int i = 0; i < cCount; i++) {

			final int j = i;

			View view = layout.getChildAt(i);
			view.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (Math.abs(j-nowposition)==1) {

						mPager.setCurrentItem(j);
					}else {
						if (nowposition == 0 && j ==2)
							TopHorizontalScrollView.this.scrollTo(screemWidth/3,0);
						if (nowposition == cCount-1 && j ==cCount-3)
							TopHorizontalScrollView.this.scrollTo(screemWidth/3*2,0);
						mPager.setCurrentItem(j,false);
					}
					
				}
			});
		}
	}

	private int getScreemWidth() {

		WindowManager wmManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wmManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
}
