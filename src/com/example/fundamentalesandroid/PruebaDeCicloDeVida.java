package com.example.fundamentalesandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PruebaDeCicloDeVida extends Activity {
	StringBuilder builder=new StringBuilder();
	TextView TextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView=new TextView(this);
		TextView.setText(builder.toString());
		setContentView(TextView);
		log("created");
	} 
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		log("Reanudar"); 
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		log("Pausado");
		if(isFinishing()){
			log("Finalisando"); 
		}
	}
	
	private void log(String text){
		Log.d("PruevaDeCicloDeVida", text);
		builder.append(text);
		builder.append("\n");
		TextView.setText(builder.toString());
		
		
	}

}
