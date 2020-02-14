package com.example.katalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void  Click_add(View view)
    {

        String name;
        String address;
        Double price;
        String data;
        Integer quantity;
        Boolean prepayment;
        DatePicker datePicker;
        EditText editText;


        editText=findViewById(R.id.editText1);
        name=editText.getText().toString();
        editText=findViewById(R.id.editText2);
        address=editText.getText().toString();
        editText=findViewById(R.id.editText3);
        price= Double.parseDouble(editText.getText().toString());
        datePicker=findViewById(R.id.datePicker);
        data= (datePicker.getYear()+"."+(datePicker.getMonth()+1)+"."+datePicker.getDayOfMonth());
        editText =findViewById(R.id.editText);
        quantity = Integer.parseInt(editText.getText().toString());


        Switch sw=findViewById(R.id.switch1);
        prepayment=sw.isChecked();


        if(name.length()==0||address.length()== 0||price==0)
        {
            Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show();
        }
        else{
        House w= House.builder().name(name).address(address).price(price).data(data).quantity(quantity).prepayment(prepayment).build();
        MainActivity.list.add(w);


            ArrayList<House> list1=MainActivity.list;

            FileOutputStream fos = null;
            try {
                fos = getBaseContext().openFileOutput("Katalog", Context.MODE_PRIVATE);
                ObjectOutputStream os = null;
                try {
                    os = new ObjectOutputStream(fos);
                    os.writeObject(list1);
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Запис додано", Toast.LENGTH_SHORT).show();}

    }
