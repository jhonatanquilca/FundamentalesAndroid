package com.example.fundamentalesandroid;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FundamentalesAndroid_MainActivity extends ListActivity {

	// String pruebas[]= new String[]{"LifeCycleTest"};
	String pruebas[] = { "PruebaDeCicloDeVida", "PuebaDeTouchSimple",
			"PuebaDeMultitouch", "PrubaDeTeclado", "PruebaDeAcelerometro",
			"AssestsTest", "PruebaDeAlmacenamientoExterno",
			"EfectoDeAudioSonido", "AudioPlayerTest", "MediaPalyerTest",
			"PruebaPantallaCompleta", "RenderViewTest","PruebaFiguras", 
			"EscojerImagenDibujar","PruebadeTexto", "SurfaceWiewTest" };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, pruebas));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String nombrePruebas = pruebas[position];

		try {
			Class<?> clazz = Class.forName("com.example.fundamentalesandroid."
					+ nombrePruebas);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
