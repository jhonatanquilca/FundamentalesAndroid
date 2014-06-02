package com.example.fundamentalesandroid;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class PruebaDeAcelerometro extends Activity implements SensorEventListener {
	StringBuilder builder= new StringBuilder();
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		textView=new TextView(this);
		setContentView(textView);
		SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()==0){
			textView.setText("No hya ningun acelerometro instalado");
		}else {
			Sensor acelerometro=manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			if(!manager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_GAME)){
				textView.setText("No se a podido registrar en sensor ñistemer");
			}	
		}
		
		
	} 

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nada que hacer
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		builder.setLength(0);
		builder.append("x: ");
		builder.append(event.values[0]);
		builder.append(", y: ");
		builder.append(event.values[1]);
		builder.append(", z: ");
		builder.append(event.values[2]);
		String text= builder.toString();
		textView.setText(text);
		
	}

}
