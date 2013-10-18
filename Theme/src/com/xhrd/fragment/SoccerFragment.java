package com.xhrd.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.theme.R;
import com.xhrd.view.BaseFragment;
import com.xhrd.view.MainActivity;


@SuppressLint("NewApi")
public class SoccerFragment extends BaseFragment implements OnClickListener{
	private Activity activity;
	private Button bt_showMenu;
	@Override
	protected void loadView() {
		setContentView(R.layout.fragment_soccer);

	}

	@Override
	protected boolean initData(View view, Bundle savedInstanceState) {
		activity = getActivity();
		bt_showMenu = (Button) findViewById(R.id.bt_showMenu);
		bt_showMenu.setOnClickListener(this);
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
