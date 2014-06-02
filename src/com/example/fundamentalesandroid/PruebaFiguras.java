package com.example.fundamentalesandroid;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PruebaFiguras extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(new RenderView(this));
	
	}
	
	//-----SUBCLASE PARA REDIBIJAR----------
	class RenderView extends View{
		Paint paint;
		public RenderView(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			paint=new Paint();
		
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			canvas.drawRGB(255, 255, 255);
			paint.setColor(Color.RED);
			canvas.drawLine(0, 0, canvas.getWidth()-1, canvas.getHeight()-1, paint);//dibuja linea
			//--------DUBUJA UN CIRCULO
			paint.setStyle(Style.STROKE);//comtorno de la siguiente fugura
			paint.setColor(0xff00ff00);
			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 40, paint);
			//-------DUBUJA UB CUADRO-----
			paint.setStyle(Style.FILL);
			paint.setColor(0x770000ff);
			canvas.drawRect(100, 100,200, 200, paint);
			invalidate();
		}
	}
}
