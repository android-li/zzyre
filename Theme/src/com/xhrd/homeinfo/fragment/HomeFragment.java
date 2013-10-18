package com.xhrd.homeinfo.fragment;

import com.example.theme.R;
import com.xhrd.customview.FragmentIndicator;
import com.xhrd.customview.FragmentIndicator.OnIndicateListener;
import com.xhrd.view.BaseFragment;
import com.xhrd.view.MainActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


/**
 * 主界面
 * @author Administrator
 *
 */

@SuppressLint("NewApi")
public class HomeFragment extends BaseFragment implements OnClickListener {
	private Activity activity;
	private Button bt_showMenu;
	// 二级导航
	private FragmentIndicator indicator;

	
	protected void loadView() {
		setContentView(R.layout.fragment_home);
		//初使值的设置
		setFragmentIndicator(0);

	}

	// 初始化fragment内容
	private void setFragmentIndicator(int whichIsDefault) {
		Fragment fragment = null;
		switch (whichIsDefault) {
		case 0:
			fragment = new ThemeFragment();
			break;
		case 1:
			fragment = new Lock_ScreenFragment();
			break;
		case 2:
			fragment = new WallpaperFragment();
			break;
		case 3:
			fragment = new RingingFragment();
			break;
		case 4:
			fragment = new IconFragment();
			break;
		}
		if (fragment != null) {
			switchFragment(fragment);
		}
	}

	// 切换Fragment
	public void switchFragment(Fragment fragment) {
		getFragmentManager().beginTransaction()
				.replace(R.id.content_home, fragment).commit();
	}

	@Override
	protected boolean initData(View view, Bundle savedInstanceState) {
		activity = getActivity();
		bt_showMenu = (Button) findViewById(R.id.bt_showMenu);
		bt_showMenu.setOnClickListener(this);

		indicator = (FragmentIndicator) findViewById(R.id.indicator);
		indicator.setOnIndicateListener(new OnIndicateListener() {

			@Override
			public void onIndicate(View view, int swicth) {
				setFragmentIndicator(swicth);
			}
		});
		return false;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		// 显示Menu ，即左边导航栏
		case R.id.bt_showMenu:
			if (getActivity() == null)
				return;
			if (getActivity() instanceof MainActivity) {
				MainActivity activity = (MainActivity) getActivity();
				activity.showMenu();
			}
			break;
		}
	}
}
