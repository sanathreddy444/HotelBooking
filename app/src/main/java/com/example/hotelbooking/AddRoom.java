package com.example.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRoom extends AppCompatActivity {

    private FirebaseFirestore db;

    EditText roomname, rent, description;

    RadioButton roomtype ,mbookingstatus;

    RadioGroup rgroomtype,bookingstatus;

    Button addroombtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);

        roomname=findViewById(R.id.roomname);
        rent=findViewById(R.id.dailyrent);
        description=findViewById(R.id.description);
        rgroomtype=findViewById(R.id.rg_roomtype);
        bookingstatus=findViewById(R.id.rg_bookstatus);
        addroombtn=findViewById(R.id.addroom);

        db = FirebaseFirestore.getInstance();

        addroombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rgroomtype.getCheckedRadioButtonId();
                int nselectedid=bookingstatus.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                roomtype = (RadioButton) findViewById(selectedId);
                mbookingstatus=(RadioButton)findViewById(nselectedid);
                Map<String, Object> product = new HashMap<>();
                product.put("roomname", roomname.getText().toString());
                product.put("rent", rent.getText().toString());
                product.put("description",description.getText().toString());
                product.put("roomtype",roomtype.getText().toString());
                product.put("bookingstatus",mbookingstatus.getText().toString());
                db.collection("roomlist")
                        .add(product)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(AddRoom.this, "Added", Toast.LENGTH_SHORT).show();
                                //finish();
                                //startActivity(new Intent(AddBookDetails.this, ViewDetails.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });


    }
}
