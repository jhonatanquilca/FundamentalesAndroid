package com.example.fundamentalesandroid;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PruebaPantallaCompleta extends PuebaDeTouchSimple {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//quita el titulo de pantalla
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//elimina la barra de esado
		super.onCreate(savedInstanceState);
	}
}
