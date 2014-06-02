package com.example.fundamentalesandroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class PruebaDeAlmacenamientoExterno extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		String estado = Environment.getExternalStorageState();
		if (!estado.equals(Environment.MEDIA_MOUNTED)) {
			textView.setText("No hay Almacenamiento externo montado");
		} else {
			File extetenalDir = Environment.getExternalStorageDirectory();
			File texFile = new File(extetenalDir.getAbsolutePath()
					+ File.separator + "texto.txt");
			try {
				writeTextFile(texFile,
						"Esto es una prueba de funcionamiento de almacenamineto externo");
				String texto = readTextFile(texFile);
				textView.setText(texto);
				if (!texFile.delete()) {
					textView.setText("No se ha podifo eliminar el archivo");
				}
			} catch (IOException e) {
				// TODO: handle exception
				textView.setText("!se ha producido en error!" + e.getMessage());
			}

		}
	}

	private String readTextFile(File file) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader reader= new  BufferedReader(new FileReader(file));
		StringBuilder texto=new StringBuilder();
		String line;
		while ((line=reader.readLine())!=null) {
			texto.append(line);
			texto.append("\n");			
		}
		reader.close();
		return texto.toString();
	}

	private void writeTextFile(File file, String texto) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer=new BufferedWriter(new FileWriter(file));
		writer.write(texto);
		writer.close();
		

	}

}
