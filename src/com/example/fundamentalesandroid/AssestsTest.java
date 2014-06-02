package com.example.fundamentalesandroid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class AssestsTest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		setContentView(textView);
		AssetManager assetManager= getAssets();
		InputStream inputStream= null;//lee los datos como bits
		try {
			inputStream= assetManager.open("canciones/sinti.txt");
			String text=cargargarArchivoText(inputStream);
			textView.setText(text);
		} catch (IOException e) {
			// TODO: handle excep tion
			textView.setText("No se puede Cargar Archivo");
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e2) {
					// TODO: handle exception
					textView.setText("No se puede cargar el Archivo");
				}
			}
		}
		
	}
	
	public String cargargarArchivoText(InputStream inStrem) throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes=new byte[4096];
		int len=0;
		while ((len = inStrem.read(bytes))>0) {
			byteStream.write(bytes, 0, len);			
		}
		return new String(byteStream.toByteArray(),"UTF8");
		
	}

}
