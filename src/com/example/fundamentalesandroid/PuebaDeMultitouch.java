package com.example.fundamentalesandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class PuebaDeMultitouch extends Activity implements OnTouchListener {

	StringBuilder builder = new StringBuilder();
	TextView textView;
	float[] x = new float[10];
	float[] y = new float[10];
	boolean[] tocado = new boolean[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Toca y arrastra !(soporta a multi touch o carios dedos)!");
		textView.setOnTouchListener(this);
		setContentView(textView);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int accion = event.getAction() & MotionEvent.ACTION_MASK;

		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

		int pointerId = event.getPointerId(pointerIndex);
		switch (accion) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			tocado[pointerId] = true;
			x[pointerId] = (int) event.getX(pointerIndex);
			y[pointerId] = (int) event.getY(pointerIndex);

			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL:
			tocado[pointerId] = false;
			x[pointerId] = (int) event.getX(pointerIndex);
			y[pointerId] = (int) event.getY(pointerIndex);

			break;
		case MotionEvent.ACTION_MOVE:
			int punteroContar = event.getPointerCount();
			for (int i = 0; i < punteroContar; i++) {
				pointerIndex = i;
				pointerId = event.getPointerId(pointerIndex);
				x[pointerId] = (int) event.getX(pointerIndex);
				y[pointerId] = (int) event.getY(pointerIndex);
			}

			break;

		default:
			break;
		}
		modificarTextView(pointerId, pointerIndex);
		return true;
	}

	private void modificarTextView(int pointerId, int pointerIndex) {
		builder.setLength(0);// actualiza el texto buildier a vacio
		for (int i = 0; i < 10; i++) {
			if (i == pointerId) {
				builder.append(tocado[i]);
				builder.append(", ");
				builder.append(x[i]);
				builder.append(", ");
				builder.append(y[i]);
				builder.append(", ");
				builder.append(pointerId);
				builder.append(", ");
				builder.append(pointerIndex);
				builder.append("\n");

			}
			

		}

		textView.setText(builder.toString());
	}

}
