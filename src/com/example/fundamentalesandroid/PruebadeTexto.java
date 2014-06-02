package com.example.fundamentalesandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PruebadeTexto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(new RenderView(this));
	}

	// ----CLases Personalizadas----------
	class RenderView extends View {
		Paint paint;
		Typeface font1, font2;// encapsular una fuente ttf(truetypefont)
		// con un unico metodo estatico para cargar un archivo fuente
		// createfon..
		Rect bounds = new Rect();// donde almacenaremos los limites de string

		public RenderView(Context context) {// constructor
			super(context);
			// TODO Auto-generated constructor stub
			paint = new Paint();

			font1 = Typeface.createFromAsset(context.getAssets(),
					"fuentes/horrendo.ttf");
			font2 = Typeface.createFromAsset(context.getAssets(),
					"fuentes/valuoldcaps.ttf");

		}

		protected void onDraw(Canvas canvas) {
			
			paint.setColor(Color.BLUE);
		
			
			canvas.drawLine(1, 1, canvas.getWidth()-1, 1, paint);
			canvas.drawLine(canvas.getWidth()-1, 1, canvas.getWidth()-1, canvas.getHeight()-1, paint);
			canvas.drawLine(canvas.getWidth()-1, canvas.getHeight()-1,1, canvas.getHeight()-1,paint);
			canvas.drawLine(1, canvas.getHeight()-1,1, 1, paint);
			
			paint.setColor(Color.BLACK);				
			paint.setTextSize(28);// tamaño de la fuente tamaño en pixeles
			paint.setTypeface(font1);// Fuente de nuestra letra			
			paint.setTextAlign(Paint.Align.CENTER);// alinear el text
			canvas.drawText("Hola Mundo..............", canvas.getWidth()/2,canvas.getHeight()/2, paint);
			invalidate();

			String text = "Esto es una prueva :P";
			paint.setColor(Color.BLUE);
			paint.setTextSize(18);
			paint.setTypeface(font2);
			paint.setTextAlign(Paint.Align.LEFT);
			paint.getTextBounds(text, 0/* caracter de inicio */,
					text.length()/* caracter final */, bounds);
			// para obtener los limites del texto
			canvas.drawText(text, canvas.getWidth() - bounds.width(), 140,
					paint);
			invalidate();
		}

	}

}
