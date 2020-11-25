package com.example.hotelbooking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateRoomDetails extends AppCompatActivity {
    private FirebaseFirestore db;

    EditText roomname, rent, description;

    RadioButton roomtype ,mbookingstatus,rb1,rb2,rb3,rb4;

    RadioGroup rgroomtype,bookingstatus;

    Button addroombtn;
    private Product_Room u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateroomdata);
        db = FirebaseFirestore.getInstance();
        roomname=findViewById(R.id.ur_roomname);
        rent=findViewById(R.id.ur_dailyrent);
        description=findViewById(R.id.ur_description);
        rgroomtype=findViewById(R.id.urrg_roomtype);
        bookingstatus=findViewById(R.id.urrg_bookstatus);
        addroombtn=findViewById(R.id.ur_addroom);
        rb1=findViewById(R.id.urrb_ac);
        rb2=findViewById(R.id.urrb_nonac);
        rb3=findViewById(R.id.urrb_booked);
        rb4=findViewById(R.id.urrb_vacant);

        u = getIntent().getParcelableExtra("roomlist");
        roomname.setText(u.getName());
        rent.setText(u.getRent());
        description.setText(u.getDescription());
        if (u.getRoomtype().equals("Ac")){
            rb1.setChecked(true);
        }else {
            rb2.setChecked(true);
        }

        if (u.getBookingstatus().equals("Booked")){
            rb3.setChecked(true);
        }else {
            rb4.setChecked(true);
        }
        findViewById(R.id.ur_addroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rgroomtype.getCheckedRadioButtonId();
                int nselectedid=bookingstatus.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                roomtype = (RadioButton) findViewById(selectedId);
                mbookingstatus=(RadioButton)findViewById(nselectedid);

                Map<String, Object> product = new HashMap<>();
                product.put("roomname", roomname.getText().toString());
                product.put("rent", rent.getText().toString());
                product.put("description", description.getText().toString());
                product.put("roomtype", roomtype.getText().toString());
                product.put("bookingstatus", mbookingstatus.getText().toString());
                db.collection("roomlist").document(u.getId()).update(product)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateRoomDetails.this, "Data Updates", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateRoomDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
            }
        });
    }
}
