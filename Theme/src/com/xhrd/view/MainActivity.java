package com.xhrd.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import com.example.theme.R;
import com.xhrd.homeinfo.fragment.HomeFragment;
import com.xhrd.utils.slidingmenu.SlidingActivityHelper;
import com.xhrd.utils.slidingmenu.SlidingMenu;

public class MainActivity extends Activity {
	private SlidingActivityHelper helper;
	private SlidingMenu menu;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new SlidingActivityHelper(this);
		// 设置内容
		helper.setContentView(this.getLayoutInflater().inflate(
				R.layout.content_frame, null));
		// 跳到主界面
		getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new HomeFragment()).commit();

		// 跳到左边侧滑导航界面
	 getFragmentManager().beginTransaction().replace(R.id.menu_frame,
				new Slide_menuFragment()).commit();
//	setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
		

		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu_frame);

	}

	@SuppressLint("NewApi")
	public void switchContent(final Fragment fragment) {
		getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				menu.showContent();
			}
		}, 50);
	}

	// 显示Menu
	public void showMenu() {
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				menu.showMenu();
			}
		}, 50);
	}

	@Override
	public void onBackPressed() {
		if (menu != null && menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

}
