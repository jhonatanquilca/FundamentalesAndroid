package com.example.fundamentalesandroid;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AudioPlayerTest extends Activity implements OnClickListener{
	TextView titulo;
	Button playButton;
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.player);
		
		playButton=(Button)this.findViewById(R.id.Button01);
		playButton.setOnClickListener(this);
		titulo=(TextView)this.findViewById(R.id.TextView01);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(android.content.Intent.ACTION_VIEW);
		File sdcard=Environment.getExternalStorageDirectory();
		File audioFile=new File(sdcard.getPath()+"/music/AKWID - Como, Cuando y Donde.mp3");
		titulo.setText(audioFile.toString());
		intent.setDataAndType(Uri.fromFile(audioFile), "audio/mp3");
		startActivity(intent);
		
	}

}
