package com.example.fundamentalesandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceWiewTest extends Activity {
	FastRenderView renderView;// clase personalizada que maneja el hilo

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		renderView = new FastRenderView(this);
		setContentView(renderView);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		renderView.renderThread.destroy();
	}

	// METODOS PERSONALES res escritos------------------
	protected void onResume() {
		super.onResume();
		renderView.resume();
	}

	protected void onPause() {
		super.onPause();
		renderView.pause();
	}

	// CLASE PRESONALIZADA ----------------------

	class FastRenderView extends SurfaceView implements Runnable {

		Thread renderThread = null;
		SurfaceHolder holder;// envoltorio entorno a la suface entorno a las
								// acciones

		volatile/*comprueva el orden de procedimientos*/ boolean running = false;

		public FastRenderView(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			holder = getHolder();
		}

		public void resume() {
			running = true;
			renderThread = new Thread(this);
			renderThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (running) {
				if (!holder.getSurface().isValid()) {
					continue;
				}

				Canvas canvas = holder.lockCanvas();// blokea la suface del
													// canvas
				canvas.drawRGB(255, 0, 0);//acciones que quiera
				holder.unlockCanvasAndPost(canvas);// desblokear la surface y
													// hace que se muestre lo ke
													// hemos digujado

			}

		}

		public void pause() {
			running = false;
			while (true) {
				try {
					renderThread.join();
				} catch (InterruptedException e) {
					// retry
				}
			}
		}

	}
}
