package com.example.katalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;

    ArrayList<House> objects;

    ProductAdapter(Context context, ArrayList<House> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        House p = getProduct(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "add is succesfull", Toast.LENGTH_SHORT).show();
            }
        });
        ((TextView) view.findViewById(R.id.adress)).setText("Ім'я: " + p.name);
        ((TextView) view.findViewById(R.id.kilk_kimn)).setText("Адреса: " + p.address);
        ((TextView) view.findViewById(R.id.vartist)).setText("Вартість: " + p.price + "грн.");
        ((TextView) view.findViewById(R.id.data1)).setText("Дата: " + p.data);
        ((TextView) view.findViewById(R.id.data2)).setText("Кількість: " + p.quantity);
        String ooo;
        if (p.prepayment.toString() == "false")
            ooo = "Не оплочено";
        else
            ooo = "Оплочено";
        ((TextView) view.findViewById(R.id.orenda)).setText(ooo);
        return view;
    }

    House getProduct(int position) {
        return ((House) getItem(position));
    }

}

