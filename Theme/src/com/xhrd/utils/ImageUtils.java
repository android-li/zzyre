package com.xhrd.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.example.theme.R;
import com.xhrd.utils.imagecore.core.DisplayImageOptions;
import com.xhrd.utils.imagecore.core.ImageLoader;
import com.xhrd.utils.imagecore.core.assist.ImageLoadingListener;
import com.xhrd.utils.imagecore.core.assist.SimpleImageLoadingListener;
import com.xhrd.utils.imagecore.core.display.FadeInBitmapDisplayer;
import com.xhrd.utils.imagecore.core.display.RoundedBitmapDisplayer;

public class ImageUtils {
	private static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	// 默认图片的处理
	public static void displayImage(String uri, ImageView imageView) {

		DisplayImageOptions options = new DisplayImageOptions.Builder()
		// 设置正在加载时 默认图片
				.showStubImage(R.drawable.ic_stub)
				// 设置当图片不存在时 默认图片
				.showImageForEmptyUri(R.drawable.ic_empty)
				// 设置当链接失败时 默认图片
				.showImageOnFail(R.drawable.ic_error)
				// 内存缓存
				.cacheInMemory(true)
				// 硬盘缓存
				.cacheOnDisc(true)
				// 倒圆角
				.displayer(new RoundedBitmapDisplayer(20)).build();

		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(uri, imageView, options, animateFirstListener);
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {
		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				FadeInBitmapDisplayer.animate(imageView, 500);
			}
		}
	}
}
