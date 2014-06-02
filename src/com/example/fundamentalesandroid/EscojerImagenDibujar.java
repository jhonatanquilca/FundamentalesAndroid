package com.example.fundamentalesandroid;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class EscojerImagenDibujar extends Activity implements OnClickListener,
		OnTouchListener {

	ImageView eligeImageView;// bit map donde cargara nuestra imagen
	Button elijeFotoButton;// boton el cual nos premitira seleccionar nuestra
							// imagen
	Bitmap bmp;// Imagen prinsipal
	Bitmap bmpmodificada;// imagen modificada final
	Canvas canvas;// dibijador
	Paint paint;// pintador
	Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.imagen2);

		eligeImageView = (ImageView) this.findViewById(R.id.EligeImagenView);
		elijeFotoButton = (Button) this.findViewById(R.id.EligeFotoButton);

		elijeFotoButton.setOnClickListener(this);
		eligeImageView.setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent eligeFotoIntent = new Intent(Intent.ACTION_PICK/*
															 * variable
															 * constante que nso
															 * va a cevolber los
															 * datos
															 * seleccionados
															 */,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI/*
																		 */);
		 
		startActivityForResult(eligeFotoIntent, 0);//

	}

	float downx = 0;
	float downy = 0;
	float upx = 0;
	float upy = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int accion = event.getAction();
		switch (accion) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			break;

		case MotionEvent.ACTION_MOVE:
			upx = event.getX();
			upy = event.getY();

			canvas.drawLine(downx, downy, upx, upy, paint);
			eligeImageView.invalidate();
			downx = upx;
			downy = upy;
			break;
			
		case MotionEvent.ACTION_UP:
			upx=event.getX();
			upy=event.getY();
			canvas.drawLine(downx, downy, upx, upy, paint);
			eligeImageView.invalidate();
			break;
			
		case MotionEvent.ACTION_CANCEL:
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Metodo se utiliza cunado queremos un resultado al finalizar
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			Uri imagenFileUri = data.getData();
			Display currDisplay = getWindowManager().getDefaultDisplay();

			float dw = currDisplay.getWidth();
			float dh = currDisplay.getHeight();

			try {
				BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
				bmpFactoryOptions.inJustDecodeBounds = true;
				bmp = BitmapFactory.decodeStream/*
												 * decodifica un string o un
												 * archivo en bitmap
												 */(getContentResolver()
						.openInputStream(imagenFileUri), null,
						bmpFactoryOptions);
				int heigtRatio = (int) Math.ceil(bmpFactoryOptions.outHeight
						/ dh);
				int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth
						/ dw);

				if (heigtRatio > 1 && widthRatio > 1) {

					if (heigtRatio > widthRatio) {
						bmpFactoryOptions.inSampleSize = heigtRatio;
					} else {
						bmpFactoryOptions.inSampleSize = widthRatio;
						// si el ancho es mayor , escalar de acuerdo a el
					}

				}

				bmpFactoryOptions.inJustDecodeBounds = false;
				bmp = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(imagenFileUri), null,
						bmpFactoryOptions);

				bmpmodificada = Bitmap.createBitmap(bmp.getWidth(),
						bmp.getHeight(), bmp.getConfig());
				canvas = new Canvas(bmpmodificada);
				paint = new Paint();// dibujar encima del cambas
				paint.setStrokeWidth(5);
				matrix = new Matrix();// matris de 3X3
				canvas.drawBitmap(bmp, matrix, paint);

				eligeImageView.setImageBitmap(bmpmodificada);
				eligeImageView.setOnTouchListener(this);
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				Log.v("Error", e.getMessage());
			}
		}
	}
	// ----METODOS PERSONALES-------------

}
