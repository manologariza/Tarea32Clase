package com.example.dell.tarea32clase;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);

    }

    public void guardar(View v){
        String nomArchivo=et1.getText().toString();
        nomArchivo=nomArchivo.replace('/', '-');
        try{
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(nomArchivo, Activity.MODE_PRIVATE));
            archivo.write(et2.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
        Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_LONG).show();
        et1.setText("");
        et2.setText("");
    }

    public void recuperar(View v){
        String nomArchivo=et1.getText().toString();
        nomArchivo=nomArchivo.replace('/','-');
        boolean enco=false;
        String[] archivos=fileList();
        for(int f=0; f<archivos.length; f++)
            if(nomArchivo.equals(archivos[f]))
                enco=true;

        if(enco==true){
            try{
                InputStreamReader archivo=new InputStreamReader((openFileInput(nomArchivo)));
                BufferedReader br=new BufferedReader(archivo);
                String linea=br.readLine();
                String todo="";
                while(linea!=null){
                    todo+=linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                et2.setText(todo);

            }catch (IOException e){

            }
        }
        else{
            Toast.makeText(this, "No existe ese fichero", Toast.LENGTH_LONG).show();
            et2.setText("");
        }
    }

}
