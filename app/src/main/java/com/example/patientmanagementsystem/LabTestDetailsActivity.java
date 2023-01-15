package com.example.patientmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;

    Button btnAddToCard, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewBMCCartTitle);
        tvTotalCost = findViewById(R.id.textViewBMDDTotalCost);
        edDetails = findViewById(R.id.listViewBMCCart);
        btnAddToCard = findViewById(R.id.buttonBMCCartCheckOut);
        btnBack = findViewById(R.id.buttonBMCCartBack);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("test1"));
        edDetails.setText(intent.getStringExtra("test2"));
        tvTotalCost.setText("Total Const : " +intent.getStringExtra("test3") + "/-");


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });


        btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefes", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("test3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Alreday added", Toast.LENGTH_SHORT).show();

                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Record Insert To the cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });


    }
}