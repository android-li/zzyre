package com.xhrd.customview;

import com.example.theme.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


//自定义底部导航
public class FragmentIndicator extends LinearLayout implements
		View.OnClickListener {
	// 默认当前被选中是第0 导航键
	private int mDefaultIndicator = 0;
	// 当前被选中的导航键
	private static int mCurrentIndicator;
	// 装导航键的数组
	private static View[] mIndicators;
	// 选中和未选中 字体的颜色
	private static final int COLOR_UNSELECT = Color.BLACK;
	private static final int COLOR_SELECT = Color.YELLOW;
	// 文字的tag表示
	private static final String TAG_TEXT_0 = "text_tag_0";
	private static final String TAG_TEXT_1 = "text_tag_1";
	private static final String TAG_TEXT_2 = "text_tag_2";
	private static final String TAG_TEXT_3 = "text_tag_3";
	private static final String TAG_TEXT_4 = "text_tag_4";

	// 点击导航的监听
	public OnIndicateListener mOnIndicateListener;

	public FragmentIndicator(Context context) {
		super(context);
	}

	public FragmentIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCurrentIndicator = mDefaultIndicator;
		setOrientation(LinearLayout.HORIZONTAL);
		init();
	}

	// 初始化 第一个导航键被选中 其他未选择
	private void init() {
		mIndicators = new View[5];

		mIndicators[0] = createIndicator(R.string.tab_view1, COLOR_SELECT,
				TAG_TEXT_0);
		// 设置被选中背景的颜色
		mIndicators[0].setTag(Integer.valueOf(0));
		mIndicators[0].setOnClickListener(this);
		addView(mIndicators[0]);

		mIndicators[1] = createIndicator(R.string.tab_view2, COLOR_UNSELECT,
				TAG_TEXT_1);
		mIndicators[1].setBackgroundColor(Color.alpha(0));
		mIndicators[1].setTag(Integer.valueOf(1));
		mIndicators[1].setOnClickListener(this);
		addView(mIndicators[1]);

		mIndicators[2] = createIndicator(R.string.tab_view3, COLOR_UNSELECT,
				TAG_TEXT_2);
		mIndicators[2].setBackgroundColor(Color.alpha(0));
		mIndicators[2].setTag(Integer.valueOf(2));
		mIndicators[2].setOnClickListener(this);
		addView(mIndicators[2]);

		mIndicators[3] = createIndicator(R.string.tab_view4, COLOR_UNSELECT,
				TAG_TEXT_3);
		mIndicators[3].setBackgroundColor(Color.alpha(0));
		mIndicators[3].setTag(Integer.valueOf(3));
		mIndicators[3].setOnClickListener(this);
		addView(mIndicators[3]);

		mIndicators[4] = createIndicator(R.string.tab_view5, COLOR_UNSELECT,
				TAG_TEXT_4);
		mIndicators[4].setBackgroundColor(Color.alpha(0));
		mIndicators[4].setTag(Integer.valueOf(4));
		mIndicators[4].setOnClickListener(this);

		addView(mIndicators[4]);

	};

	/**
	 * 
	 * @param stringResID
	 *            字符ID
	 * @param stringColor
	 *            字体颜色ID
	 * @param textTag
	 *            文字的唯一标识
	 * @return 导航键View
	 */
	private View createIndicator(int stringResID, int stringColor,
			String textTag) {
		LinearLayout layout = new LinearLayout(getContext());
		// 竖直方向
		layout.setOrientation(LinearLayout.VERTICAL);
		// 宽高包裹内容
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));
		// 水平方向居中
		layout.setGravity(Gravity.CENTER_HORIZONTAL);

		// 文字
		TextView textView = new TextView(getContext());
		textView.setTag(textTag);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1));
		textView.setTextColor(stringColor);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 56);
		textView.setText(stringResID);

		// 添加到viewgroup
		layout.addView(textView);

		return layout;

	}

	// 定义一个 当用户点击的接口
	public interface OnIndicateListener {
		/**
		 * 
		 * @param view
		 *            当前的View对象
		 * @param swicth
		 *            当前的索引
		 */
		void onIndicate(View view, int swicth);
	}

	// 设置监听
	public void setOnIndicateListener(OnIndicateListener _indicateListener) {
		mOnIndicateListener = _indicateListener;
	}

	@Override
	public void onClick(View v) {
		if (mOnIndicateListener != null) {
			// 通过tag找到对应的View导航键
			int tag_view = (Integer) v.getTag();
			switch (tag_view) {
			case 0:
				// 防止重复点击 当前选中是0导航键 再次点击就没效果
				if (mCurrentIndicator != 0) {
					mOnIndicateListener.onIndicate(v, 0);
					setIndicator(0);
				}
				break;

			case 1:
				if (mCurrentIndicator != 1) {
					mOnIndicateListener.onIndicate(v, 1);
					setIndicator(1);
				}
				break;

			case 2:
				if (mCurrentIndicator != 2) {
					mOnIndicateListener.onIndicate(v, 2);
					setIndicator(2);
				}

				break;

			case 3:
				if (mCurrentIndicator != 3) {
					mOnIndicateListener.onIndicate(v, 3);
					setIndicator(3);
				}
				break;
			case 4:
				if (mCurrentIndicator != 4) {
					mOnIndicateListener.onIndicate(v, 4);
					setIndicator(4);
				}
				break;
			}

		}
	}

	/**
	 * 设置当前某个导航键的点击效果
	 * 
	 * @param swich
	 *            当前的索引
	 */
	public static void setIndicator(int swich) {

		clearCurrStatus();
		changeSeleStatus(swich);
	}

	// 使当前 导航键变成透明 不选中
	private static void clearCurrStatus() {
		// 清除当前导航键的颜色状态
		mIndicators[mCurrentIndicator].setBackgroundColor(Color.alpha(0));
		// 导航键的文字
		TextView textView;
		switch (mCurrentIndicator) {
		case 0:
			textView = (TextView) mIndicators[mCurrentIndicator]
					.findViewWithTag(TAG_TEXT_0);
			textView.setTextColor(COLOR_UNSELECT);
			break;
		case 1:
			textView = (TextView) mIndicators[mCurrentIndicator]
					.findViewWithTag(TAG_TEXT_1);
			textView.setTextColor(COLOR_UNSELECT);
			break;
		case 2:
			textView = (TextView) mIndicators[mCurrentIndicator]
					.findViewWithTag(TAG_TEXT_2);
			textView.setTextColor(COLOR_UNSELECT);
			break;
		case 3:
			textView = (TextView) mIndicators[mCurrentIndicator]
					.findViewWithTag(TAG_TEXT_3);
			textView.setTextColor(COLOR_UNSELECT);
			break;
		case 4:
			textView = (TextView) mIndicators[mCurrentIndicator]
					.findViewWithTag(TAG_TEXT_4);
			textView.setTextColor(COLOR_UNSELECT);
			break;
		}
	}

	// 使点击导航键 被选中
	private static void changeSeleStatus(int swich) {
		TextView textView;
		switch (swich) {
		case 0:
			textView = (TextView) mIndicators[swich]
					.findViewWithTag(TAG_TEXT_0);
			textView.setTextColor(COLOR_SELECT);
			break;
		case 1:
			textView = (TextView) mIndicators[swich]
					.findViewWithTag(TAG_TEXT_1);
			textView.setTextColor(COLOR_SELECT);
			break;
		case 2:
			textView = (TextView) mIndicators[swich]
					.findViewWithTag(TAG_TEXT_2);
			textView.setTextColor(COLOR_SELECT);
			break;
		case 3:
			textView = (TextView) mIndicators[swich]
					.findViewWithTag(TAG_TEXT_3);
			textView.setTextColor(COLOR_SELECT);
			break;
		case 4:
			textView = (TextView) mIndicators[swich]
					.findViewWithTag(TAG_TEXT_4);
			textView.setTextColor(COLOR_SELECT);
			break;
		}
		mCurrentIndicator = swich;
	}

}
