package com.example.hotelbooking;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class RoomDetails extends AppCompatActivity {

    FirebaseFirestore db;

    RoomAdapter productsAdapter;
    ArrayList<Product_Room> arrayList = new ArrayList<>();
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        productsAdapter = new RoomAdapter(this, arrayList);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        list.setAdapter(productsAdapter);


        db.collection("roomlist")

                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {

                        arrayList.clear();
                        for (QueryDocumentSnapshot document :value) {
                            Map<String, Object> data = document.getData();
                            Product_Room u = new Product_Room();
                            u.setId(document.getId());
                            u.setName(String.valueOf(data.get("roomname")));
                            u.setRent(String.valueOf(data.get("rent")));
                            u.setDescription(String.valueOf(data.get("description")));
                            u.setRoomtype(String.valueOf(data.get("roomtype")));
                            u.setBookingstatus(String.valueOf(data.get("bookingstatus")));
                            arrayList.add(u);
                        }
                        productsAdapter.notifyDataSetChanged();
                    }
                });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.add) {
//            startActivity(new Intent(getApplicationContext(), com.example.firestoredemo.AddActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
