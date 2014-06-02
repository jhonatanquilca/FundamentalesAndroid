package com.example.fundamentalesandroid;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class RenderViewTest extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));
		
	}
	
	//------------SUBCLASE PARA DIBUJAR---------------------
	
	class RenderView extends View{
		
		
		Random rand= new Random();
		public RenderView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			//super.onDraw(canvas);
			canvas.drawRGB(rand.nextInt(256), 
					rand.nextInt(256), 
					rand.nextInt(256));//color que dibuja de fondo de pantalla aleatorio
			invalidate();//metodo que redibuja el context
		}
		
	}



}
