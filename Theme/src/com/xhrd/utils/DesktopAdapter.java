package com.xhrd.utils;

import java.util.List;
import java.util.Map;

import com.example.theme.R;
import com.xhrd.view.Slide_menuFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DesktopAdapter extends BaseExpandableListAdapter {

	private Context mContext = null;
	private List<Map<String, Object>> mGroup = null;
	private List<List<Map<String, Object>>> mChild = null;
	private LayoutInflater mInflater = null;

	public DesktopAdapter(Context context, List<Map<String, Object>> group,
			List<List<Map<String, Object>>> child) {
		mContext = context;
		mGroup = group;
		mChild = child;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		return mChild.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		ViewHolder holder = null;
		if (arg3 == null) {
			arg3 = mInflater.inflate(R.layout.desktop_list_child, null);
			holder = new ViewHolder();
			holder.mChildIcon = (ImageView) arg3
					.findViewById(R.id.desktop_list_child_icon);
			holder.mChildName = (TextView) arg3
					.findViewById(R.id.desktop_list_child_name);
			arg3.setTag(holder);
		} else {
			holder = (ViewHolder) arg3.getTag();
		}
		holder.mChildIcon.setImageDrawable(mContext.getResources().getDrawable(
				Integer.parseInt(mChild.get(arg0).get(arg1).get("icon")
						.toString())));
		holder.mChildName.setText(mChild.get(arg0).get(arg1).get("name")
				.toString());
		
		
		if (arg1 == Slide_menuFragment.mChooesId && arg0 == Slide_menuFragment.mChooes) {
			Toast.makeText(
					mContext,
					"被选中的为：" + Slide_menuFragment.mChooesId + ",,第" + arg0
							+ "组", 0).show();
			arg3.setBackgroundResource(R.drawable.desktop_list_item_pressed);
		}else {
			arg3.setBackgroundResource(R.drawable.desktop_list_item);
		}
		// else {
		// //颜色选择器
		// arg3.setBackgroundResource(R.drawable.desktop_list_item_bg);
		// }
		return arg3;
	}

	@Override
	public int getChildrenCount(int arg0) {
		return mChild.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		return mGroup.get(arg0);
	}

	@Override
	public int getGroupCount() {
		return mGroup.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {

		ViewHolder holder = null;
		if (arg2 == null) {
			arg2 = mInflater.inflate(R.layout.desktop_list_group, null);
			holder = new ViewHolder();
			holder.mGroupName = (TextView) arg2
					.findViewById(R.id.desktop_list_group_name);
			arg2.setTag(holder);
		} else {
			holder = (ViewHolder) arg2.getTag();
		}
		holder.mGroupName.setText(mGroup.get(arg0).get("name").toString());
		return arg2;

	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	private class ViewHolder {
		private TextView mGroupName;
		private ImageView mChildIcon;
		private TextView mChildName;
	}

}
