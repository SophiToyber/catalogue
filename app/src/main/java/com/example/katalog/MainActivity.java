package com.example.katalog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<House> list = new ArrayList<>();
    public static ArrayList<House> list2 = new ArrayList<>();
    public static ArrayList<House> list3 = new ArrayList<>();
    public static ArrayList<House> list4 = new ArrayList<>();
    public static ArrayList<House> clearlist = new ArrayList<>();
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FileInputStream fis = null;
        try {
            fis = getBaseContext().openFileInput("Katalog");

            ObjectInputStream is = null;
            try {
                is = new ObjectInputStream(fis);

                list = (ArrayList<House>) is.readObject();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productAdapter = new ProductAdapter(getBaseContext(), list);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter = new ProductAdapter(getBaseContext(), list);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    public void vse(View view) {
        productAdapter = new ProductAdapter(getBaseContext(), list);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);
    }


    public void search(View view) {


        list2.clear();
        productAdapter = new ProductAdapter(getBaseContext(), clearlist);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);


        EditText textView = findViewById(R.id.search);

        for (int i = 0; i < list.size(); i++) {
            House k = list.get(i);
            if (k.getPrice() <= Double.parseDouble(textView.getText().toString())) {
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
                list2.add(k);
            }
        }
        productAdapter = new ProductAdapter(getBaseContext(), list2);
        lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

    }


    public void search1(View view) {
        list3.clear();
        productAdapter = new ProductAdapter(getBaseContext(), clearlist);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

        EditText textView = findViewById(R.id.search);

        for (int i = 0; i < list.size(); i++) {
            House k = list.get(i);
            if (k.prepayment.equals(true)) {
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
                list3.add(k);
            }
        }
        productAdapter = new ProductAdapter(getBaseContext(), list3);
        lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

    }


    public void search2(View view) {
        list4.clear();
        productAdapter = new ProductAdapter(getBaseContext(), clearlist);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

        EditText textView = findViewById(R.id.search);

        for (int i = 0; i < list.size(); i++) {
            House k = list.get(i);
            if (k.getPrepayment().equals(false)) {
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
                list4.add(k);
            }
        }
        productAdapter = new ProductAdapter(getBaseContext(), list4);
        lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);

    }


    public void del(View view) throws FileNotFoundException {
        EditText textView = findViewById(R.id.del2);

        ArrayList<House> lis = new ArrayList<>();
        ArrayList<House> listtmp = MainActivity.list;
        for (int i = 0; i < list.size(); i++) {

            House k = list.get(i);
            if (!textView.getText().toString().equals(k.getAddress())) {
                lis.add(k);
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
            }

        }

        FileOutputStream fos = null;
        try {
            fos = getBaseContext().openFileOutput("Katalog", Context.MODE_PRIVATE);
            ObjectOutputStream os = null;

            try {
                os = new ObjectOutputStream(fos);
                os.writeObject(lis);
                Toast.makeText(this, "Успішно", Toast.LENGTH_SHORT).show();
                os.close();
            } catch (IOException e) {

            }
            fos.close();


        } catch (IOException e) {


            productAdapter = new ProductAdapter(getBaseContext(), list);
            ListView lvMain = (ListView) findViewById(R.id.list);
            lvMain.setAdapter(productAdapter);

        }

        FileInputStream fis = null;
        try {
            fis = getBaseContext().openFileInput("Katalog");

            ObjectInputStream is = null;
            try {
                is = new ObjectInputStream(fis);

                list = (ArrayList<House>) is.readObject();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productAdapter = new ProductAdapter(getBaseContext(), list);
        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(productAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


