package com.xhrd.sport.application;

import android.app.Application;
import android.content.Context;

import com.xhrd.utils.imagecore.cache.disc.naming.Md5FileNameGenerator;
import com.xhrd.utils.imagecore.core.ImageLoader;
import com.xhrd.utils.imagecore.core.ImageLoaderConfiguration;
import com.xhrd.utils.imagecore.core.assist.QueueProcessingType;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
	}

	// Initialize ImageLoader with configuration.
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}
}
