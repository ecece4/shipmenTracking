package com.example.shipmentracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    ListView teslimat_bilgileri;
    public FirebaseDatabase database;
    public DatabaseReference dBDdelivery;//delivery kısmı
    public ArrayList<String> delivery = new ArrayList<>();
    public ArrayAdapter<String> deliveryAdapter;
    Shipment shp;
    public  String gelen_delivery_num;
    public String gelen_shipment_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        teslimat_bilgileri = (ListView) findViewById(R.id.teslimat_bilgileri);
        shp=new Shipment();
        bottom_navigation= findViewById(R.id.bottom_navigation1);
        Menu menu = bottom_navigation.getMenu();
        database = FirebaseDatabase.getInstance();
        Intent gelen_intent = getIntent();
        gelen_delivery_num = gelen_intent.getStringExtra("delivery_number");
        dBDdelivery=database.getReference("delivery").child(gelen_delivery_num);
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item1:
                        Intent home = new Intent(MainActivity3.this, MainActivity2.class);
                        startActivity(home);
                        break;

                    case R.id.item2:

                        Intent delivery_waybill = new Intent(MainActivity3.this, MainActivity.class);
                        startActivity(delivery_waybill);

                        gelen_shipment_num = gelen_delivery_num;

                        Intent gidecek_intent = new Intent(MainActivity3.this,MainActivity.class);
                        gidecek_intent.putExtra("shipment_number", gelen_shipment_num);
                        startActivity(gidecek_intent);
                        break;

                    case R.id.item4:
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                        break;
                }
                return false;
            }
        });
        deliveryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, delivery);

        GetDb();


    }

    public void GetDb(){

        dBDdelivery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                delivery.add(value);
                deliveryAdapter.notifyDataSetChanged();
                teslimat_bilgileri.setAdapter(deliveryAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}