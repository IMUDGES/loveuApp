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
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopLinearlayout extends LinearLayout {

	private ViewPager mViewPager;

	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;
	private Fragment mFragments[];

	private static final int COLOR_TEXT_NORMAL = 0X77FFFFFF;
	private static final int COLOR_TEXT_HIGHLIGHT = 0XFFFFFFFF;

	private int nowposition = 0;

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
		higthLightTextView(0);
	}

	public void setViewPage(ViewPager viewPager, int pos) {
		mViewPager = viewPager;

		setItemClickEvent();

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				higthLightTextView(position);
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

		higthLightTextView(pos);
	}

	private void resetTextViewColor() {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	private void higthLightTextView(int pos) {
		resetTextViewColor();
		View view = getChildAt(pos);
		if (view instanceof TextView) {
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
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
					Log.i("点击","点击");
					if (j == nowposition) {
						return;
					}
					nowposition = j;
					resetTextViewColor();
					higthLightTextView(j);
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
