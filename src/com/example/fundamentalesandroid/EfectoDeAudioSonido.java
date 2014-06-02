package com.example.fundamentalesandroid;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class EfectoDeAudioSonido extends Activity implements OnTouchListener {
	///soudPoll  ->> reproduce sonido
	SoundPool soundPool;
	int misonidoId=-1;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		textView= new TextView(this);
		textView.setOnTouchListener(this);
		setContentView(textView);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);// controles de sonido 
		soundPool=new SoundPool(20/*concurrencia de sonidos que puede cargar*/, AudioManager.STREAM_MUSIC, 0);
		 try {
			 AssetManager assetManager= getAssets();//acceso al directorio asset
			 AssetFileDescriptor descriptor= assetManager.openFd("sonidos/sonido.ogg");
			 misonidoId=soundPool.load(descriptor, 1);//lansa una ecepcion si esque no se carga
			 textView.setText("el audio se cargo correctamente");
		} catch (IOException e) {
			// TODO: handle exception
			textView.setText("no se ha podido cargar el sonido "+e.getMessage());
			
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction()==MotionEvent.ACTION_UP) {
			if (misonidoId!=-1) {
				soundPool.play(misonidoId/*ide de sonido*/, 1/*volumen izquiedo*/, 1/*volumen derecho*/, 0/*prioridad*/, 0/*loop*/, 1/*velocidad de reproduccion*/);
				textView.setText("Reproduciendo");
				
				
			}
			
		}
		return true;
	}

}
