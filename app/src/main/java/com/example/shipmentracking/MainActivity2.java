package com.example.shipmentracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class MainActivity2 extends AppCompatActivity {
    Button btn_sorgula;
    EditText edit_kargo_num;
    public String shipment_num;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference reference;
    public ArrayList<String> shipments_num = new ArrayList<>();
    public ArrayAdapter<String> shipments_num_adapter;
    Shipment shp;

        public void changeActivity () {
            Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent1);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        shp=new Shipment();

        btn_sorgula = (Button) findViewById(R.id.btn_sorgula);
        edit_kargo_num = (EditText) findViewById(R.id.edit_kargo_num);


        reference = firebaseDatabase.getReference("delivery");


       shipments_num_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, shipments_num);


        GetDb();
        btn_sorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shipment_num = edit_kargo_num.getText().toString();//delivery tablosundan çekilecek kargo numarası
                edit_kargo_num.setText("");
                if(shipments_num.contains(shipment_num))
                  {
                     changeActivity();
                     // kargo numarasını giden aktiviteye put extra ile yolladık
                     Intent shp_num = new Intent(MainActivity2.this,MainActivity.class);
                     shp_num.putExtra("shipment_number", shipment_num);
                     startActivity(shp_num);
                  }

                else if(((shipment_num.length()!=9) && (shipment_num.length()!=0)) ) {
                    Toast.makeText(getApplicationContext(), "Kargo takip numaranız 9 rakamdan oluşmalıdır ." , Toast.LENGTH_SHORT).show();
                }
                else if((shipment_num.length()==9) && (Pattern.matches("[a-zA-Z]+", shipment_num))){
                    Toast.makeText(getApplicationContext(), "Kargo takip numaranız sayısal olmalıdır." , Toast.LENGTH_SHORT).show();
                }

               else if((shipment_num.length()==9) && (Pattern.matches("[a-zA-Z]+", shipment_num) &&(Pattern.matches("[0-9]+",shipment_num)) )){
                    Toast.makeText(getApplicationContext(), "Kargo takip numaranız sayısal olmalıdır." , Toast.LENGTH_SHORT).show();
                }

                 else if(shipment_num.isEmpty()){
                Toast.makeText(getApplicationContext(), "Lütfen 9 haneli kargo takip numaranızı girin.", Toast.LENGTH_SHORT).show();
                 }
                else {
                Toast.makeText(getApplicationContext(), "Girdiğiniz bilgiler için bir sonuç bulunamamıştır. ", Toast.LENGTH_SHORT).show();
                    }


        }
        });


    }

 public void GetDb(){
     reference.addChildEventListener(new ChildEventListener() {
         @Override
         public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
             shipments_num.add(snapshot.getKey().toString());
             shipments_num_adapter.notifyDataSetChanged();

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