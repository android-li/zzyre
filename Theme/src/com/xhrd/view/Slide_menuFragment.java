package com.xhrd.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.theme.R;
import com.xhrd.fragment.BaskFragment;
import com.xhrd.fragment.InivteFragment;
import com.xhrd.fragment.AboutFragment;
import com.xhrd.fragment.FeedbackFragment;
import com.xhrd.fragment.LiveFragment;
import com.xhrd.fragment.MapFragment;
import com.xhrd.fragment.NoticeFragment;
import com.xhrd.fragment.ResultFragment;
import com.xhrd.fragment.SelectFragment;
import com.xhrd.fragment.ShouzhuFragment;
import com.xhrd.fragment.SoccerFragment;
import com.xhrd.homeinfo.fragment.HomeFragment;
import com.xhrd.utils.DesktopAdapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

/**
 * 左边导航栏菜单界面
 * 
 * @author Administrator
 * 
 */

@SuppressLint("NewApi")
public class Slide_menuFragment extends BaseFragment {

	private ExpandableListView mDisplay;

	private DesktopAdapter mAdapter;
	public static int mChooesId = -1;
	public static int mChooes = -1;

	private List<Map<String, Object>> mGroup = new ArrayList<Map<String, Object>>();
	private List<List<Map<String, Object>>> mChild = new ArrayList<List<Map<String, Object>>>();
	private String[] mGroupName;
	private String[] mChildFavorite;
	private String[] mChildAction;
	private String[] mChildBisai;
	private String[] mChildGenduo;

	private int[] mChildFavoriteIcon;
	private int[] mChildActionIcon;
	private int[] mChildBisaiIcon;
	private int[] mChildGenduoIcon;

	@Override
	protected void loadView() {
		setContentView(R.layout.desktop);
	}

	@Override
	protected boolean initData(View view, Bundle savedInstanceState) {
		mDisplay = (ExpandableListView) findViewById(R.id.desktop_list);

		init();

		setListener();
		return false;
	}

	private void init() {
		init_Data();

		mAdapter = new DesktopAdapter(getActivity(), mGroup, mChild);
		mDisplay.setAdapter(mAdapter);
		for (int i = 0; i < mGroup.size(); i++) {
			mDisplay.expandGroup(i);
		}
	}

	private void init_Data() {
		//
		mGroupName = this.getResources().getStringArray(
				R.array.desktop_list_head_strings);
		mChildFavorite = this.getResources().getStringArray(
				R.array.desktop_list_item_info_strings);
		mChildAction = this.getResources().getStringArray(
				R.array.desktop_list_item_jishuiqi_strings);

		mChildBisai = this.getResources().getStringArray(
				R.array.desktop_list_item_bisai_info_strings);
		mChildGenduo = this.getResources().getStringArray(
				R.array.desktop_list_item_genduo_strings);

		mChildFavoriteIcon = new int[2];
		mChildActionIcon = new int[3];
		mChildBisaiIcon = new int[3];
		mChildGenduoIcon = new int[4];

		mChildFavoriteIcon[0] = R.drawable.v5_0_1_desktop_list_newsfeed;
		mChildFavoriteIcon[1] = R.drawable.v5_0_1_desktop_list_message;
		mChildActionIcon[0] = R.drawable.v5_0_1_desktop_list_settings;
		mChildActionIcon[1] = R.drawable.v5_0_1_desktop_list_log_out;
		mChildActionIcon[2] = R.drawable.v5_0_1_desktop_list_log_out;

		mChildBisaiIcon[0] = R.drawable.v5_0_1_desktop_list_chat;
		mChildBisaiIcon[1] = R.drawable.v5_0_1_desktop_list_friends;
		mChildBisaiIcon[2] = R.drawable.v5_0_1_desktop_list_page;

		mChildGenduoIcon[0] = R.drawable.v5_0_1_desktop_list_location;
		mChildGenduoIcon[1] = R.drawable.v5_0_1_desktop_list_search;
		mChildGenduoIcon[2] = R.drawable.v5_0_1_desktop_list_apps_center;
		mChildGenduoIcon[3] = R.drawable.v5_0_1_desktop_list_location;
		getGroupList();
		getChildList();
	}

	private void getGroupList() {
		for (int i = 0; i < mGroupName.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", mGroupName[i]);
			mGroup.add(map);
		}
	}

	private void getChildList() {
		for (int i = 0; i < mGroupName.length; i++) {
			if (i == 0) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < mChildFavorite.length; j++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", mChildFavoriteIcon[j]);
					map.put("name", mChildFavorite[j]);
					map.put("click", false);
					list.add(map);
				}
				mChild.add(list);
			} else if (i == 1) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < mChildAction.length; j++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", mChildActionIcon[j]);
					map.put("name", mChildAction[j]);
					map.put("click", false);
					list.add(map);
				}
				mChild.add(list);
			} else if (i == 2) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < mChildBisai.length; j++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", mChildBisaiIcon[j]);
					map.put("name", mChildBisai[j]);
					map.put("click", false);
					list.add(map);
				}
				mChild.add(list);
			} else if (i == 3) {

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < mChildGenduo.length; j++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", mChildGenduoIcon[j]);
					map.put("name", mChildGenduo[j]);
					map.put("click", false);
					list.add(map);
				}
				mChild.add(list);

			}
		}
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", mChildFavoriteIcon[0]);
		map.put("name", mChildFavorite[0]);
		map.put("click", true);
		mChild.get(0).set(0, map);
	}

	private void setListener() {
		mDisplay.setOnGroupClickListener(new OnGroupClickListener() {

			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				
				return true;
			}
		});

		mDisplay.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1,
					int arg2, int arg3, long arg4) {
				Fragment newContent = null;
				if (arg2 == 0) {
					mChooesId = arg3;
					mChooes=0;
					mAdapter.notifyDataSetChanged();
					switch (arg3) {
					case 0:
						//资讯中心主界面
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						newContent = new HomeFragment();
						break;
					case 1:
						//竞彩公告
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						newContent = new NoticeFragment();
						break;
					}
				} else if (arg2 == 1) {
					mChooesId = arg3;
					mChooes=1;
					mAdapter.notifyDataSetChanged();
					switch (arg3) {
					case 0:
						//计算器足球
						newContent = new SoccerFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 1:
						//计算器篮球
						newContent = new BaskFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 2:
						//选彩记录
						newContent = new SelectFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					}
				} else if (arg2 == 2) {
					mChooesId = arg3;
					mChooes=2;
					mAdapter.notifyDataSetChanged();
					switch (arg3) {
					case 0:
						//受注赛程
						newContent = new ShouzhuFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 1:
						//比分直播
						newContent = new LiveFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 2:
						//赛果开奖
						newContent = new ResultFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					}
				} else if (arg2 == 3) {
					mChooesId = arg3;
					mChooes=3;
					mAdapter.notifyDataSetChanged();
					switch (arg3) {
					case 0:
						//地图
						newContent = new MapFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 1:
						//反馈
						newContent = new FeedbackFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 2:
						//邀请
						newContent = new InivteFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					case 3:
						//关于
						newContent = new AboutFragment();
//						Toast.makeText(getActivity(), "第"+arg2+"组，"+"第"+arg3+"个成员", 0).show();
						break;
					}
				}
				if (newContent != null)
					switchFragment(newContent);
				return true;
			}
		});

	}
	

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof MainActivity) {
			MainActivity activity = (MainActivity) getActivity();
			activity.switchContent(fragment);
		}
	}

}
