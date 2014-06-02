package com.example.fundamentalesandroid;



import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;


public class MediaPalyerTest extends Activity implements OnCompletionListener,
		OnTouchListener, OnClickListener {

	View theView;
	Button startButton, stopButton;
	MediaPlayer mediaPlayer;
	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player2);

		startButton = (Button) this.findViewById(R.id.startButton);

		stopButton = (Button) this.findViewById(R.id.stopButton);

		startButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);

		theView = (View) this.findViewById(R.id.View01);

		theView.setOnTouchListener(this);

		cargarMusica();
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		detener();
		mediaPlayer.release();
		
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		mediaPlayer.start();// inicia el reproductor
		mediaPlayer.seekTo(position);// posicion donde inicia la cancion
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		if (v == stopButton) {
			detener();
			startButton.setText("Reproducir");
		} else if (v == startButton) {

			if (startButton.getText().equals("Pausar")) {
				pausar();
				startButton.setText("Reproducir");
				
			} else if (startButton.getText().equals("Reproducir")) {
				reproducir();
				startButton.setText("Pausar");

			}

		}
			
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
	
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (mediaPlayer.isPlaying()) {
				position = (int) (event.getX() * mediaPlayer.getDuration() / theView
						.getWidth());
				Log.d("SEEK", "" + position);
				mediaPlayer.seekTo(position);// cambio de posicion en la
												// reproduccion del audio

			}
		}
		return true;
	}
	


	// ---------METODOS CREADOS---------------

	public void reproducir() {
		// TODO Auto-generated method stub
		super.onStart();
		
			
			mediaPlayer.start();// metodo que inicia la reproduccion	
		
	
	}

	public void detener() {
		// TODO Auto-generated method stub
super.onStop();
		mediaPlayer.stop();// metodo que detiene la preroduccion
		cargarMusica();
	}

	public void pausar() {
		super.onPause();
		mediaPlayer.pause();// pausa la musica		
	}
	public void cargarMusica(){
		mediaPlayer = MediaPlayer.create(this, R.raw.rattle);// direccio del recurso
		mediaPlayer.setOnCompletionListener(this);// escucha de nuestro reproductor
		mediaPlayer.setVolume(1, 1);
	}

	
	

}
