package com.example.shipmentracking;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    public ListView kargo_bilgileri;
    public FirebaseDatabase database;
    public DatabaseReference refDb;
    ArrayList <String> shipments_info = new ArrayList<>();
    public  ArrayAdapter<String> shipments_info_adapter;

    Shipment shp;
    public String gelen_shipment_num;
    public String gidecek_delivery_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        database =  FirebaseDatabase.getInstance();

        kargo_bilgileri = (ListView) findViewById(R.id.kargo_bilgileri); //listenin adı
        shp = new Shipment();

        Intent gelen_intent= getIntent();
        gelen_shipment_num = gelen_intent.getStringExtra("shipment_number");
        refDb=database.getReference("shipments").child(gelen_shipment_num);

        shipments_info_adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1,
                android.R.id.text1, shipments_info);
        Menu menu = bottom_navigation.getMenu();

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Intent home = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(home);
                        break;
                    case R.id.item3:
                        Intent delivery_status = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(delivery_status);
                        gidecek_delivery_num=gelen_shipment_num;
                        Intent gidecek_intent = new Intent(MainActivity.this,MainActivity3.class);
                        gidecek_intent.putExtra("delivery_number", gidecek_delivery_num);
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
       GetDb();

    }

    //veritabanından veri çekmek için yazılmış kod bloğu
    public void GetDb(){

        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                shipments_info.add(value);
                shipments_info_adapter.notifyDataSetChanged();
                //gidecek listenin adı
                kargo_bilgileri.setAdapter(shipments_info_adapter);
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

