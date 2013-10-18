package com.xhrd.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.theme.R;
import com.xhrd.constant.Constant;
import com.xhrd.utils.CommonUtil;
import com.xhrd.utils.NetUtil;
import com.xhrd.utils.ThreadPoolManager;
import com.xhrd.utils.imagecore.core.ImageLoader;
import com.xhrd.vo.RequestVo;

@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ThreadPoolManager threadPoolManager;
	private int layoutResId;
	private ProgressDialog progressDialog;
	private View mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		threadPoolManager = ThreadPoolManager.getInstance();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		loadView();
		if (layoutResId <= 0) {
			throw new NullPointerException("please set layout first");
		}
		return inflater.inflate(layoutResId, container, false);
	}

	protected abstract void loadView();

	protected abstract boolean initData(View view, Bundle savedInstanceState);

	public void setContentView(int _layoutResId) {
		this.layoutResId = _layoutResId;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mView = view;
		boolean flag = initData(view, savedInstanceState);
		progressDate(flag);

		super.onViewCreated(view, savedInstanceState);
	}

	public void progressDate(boolean flag) {
		if (flag)
			showProgressDialog();
	}

	protected void showProgressDialog() {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this.getActivity());
		} else {
			if (this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
		}
		this.progressDialog.setTitle(getString(R.string.loadTitle));
		this.progressDialog.setMessage(getString(R.string.LoadContent));
		this.progressDialog.setCanceledOnTouchOutside(false);
		this.progressDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		this.progressDialog.show();
	}

	protected void closeProgressDialog() {
		if (this.progressDialog != null && this.progressDialog.isShowing())
			this.progressDialog.dismiss();

	}

	// 设置网络窗口
	public void showSetNetWorkDialog() {
		AlertDialog.Builder builder = new Builder(this.getActivity());
		builder.setTitle("设置网络");
		builder.setMessage("网络连接错误,请检查网络设置");
		builder.setNegativeButton("确定", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				getActivity().finish();
			}
		});
		builder.create().show();
	}

	class BaseHandler extends Handler {
		private Context context;
		private DataCallback callBack;

		public BaseHandler(Context context, DataCallback callBack) {
			this.context = context;
			this.callBack = callBack;
		}

		public void handleMessage(Message msg) {
			try {
				closeProgressDialog();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			if (msg.what == Constant.SUCCESS) {
				if (msg.obj == null) {
					CommonUtil.showInfoDialog(context,
							getString(R.string.net_error));
				} else {
					if (callBack != null) {
						callBack.processData(msg.obj);
					}
				}
			} else if (msg.what == Constant.NO_NET) {
				showSetNetWorkDialog();
			}
		}
	}

	class BaseTask implements Runnable {
		private Context context;
		private RequestVo reqVo;
		private Handler handler;

		public BaseTask(Context context, RequestVo reqVo, Handler handler) {
			this.context = context;
			this.reqVo = reqVo;
			this.handler = handler;
		}

		@Override
		public void run() {
			Looper.prepare();
			Object obj = null;
			Message msg = new Message();
			if (NetUtil.hasNetwork(context)) {
				obj = NetUtil.post(reqVo);
				msg.what = Constant.SUCCESS;
				msg.obj = obj;
				handler.sendMessage(msg);
			} else {
				msg.what = Constant.NO_NET;
				msg.obj = obj;
				handler.sendMessage(msg);
			}
			closeProgressDialog();
			Looper.loop();
		}

	}

	/**
	 * @param reqVo
	 * @param callBack
	 */
	protected void getDataFromServer(RequestVo reqVo, DataCallback callBack) {
		// showProgressDialog();
		BaseHandler handler = new BaseHandler(this.getActivity(), callBack);
		BaseTask taskThread = new BaseTask(this.getActivity(), reqVo, handler);
		this.threadPoolManager.addTask(taskThread);
	}

	public abstract interface DataCallback<T> {
		public abstract void processData(T responseObj);
	}

	@Override
	public void onDestroy() {
		closeProgressDialog();
		super.onDestroy();
	}

	// 清除图片缓存 内存和硬盘
	protected void clearCache() {
		imageLoader.clearMemoryCache();
		imageLoader.clearDiscCache();
	}

	/**
	 * look for a child view by id
	 * 
	 * @param id
	 *            the id search for
	 * @return
	 */
	protected View findViewById(int id) {
		return mView.findViewById(id);
	}

}
