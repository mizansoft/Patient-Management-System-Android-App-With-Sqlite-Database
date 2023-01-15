package com.example.patientmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprice-03 1000IU Capsul", "", "", "", "50"},
                    {"HealthVit Chromiun Picelinate 200 mcg Capsul", "", "", "", "305"},
                    {"Vitamin B Complex Capsule", "", "", "", "448"},
                    {"Dolo 650 Tablet", "", "", "", "30"},
                    {"Strepsils Medicated Lozenges for Sore Throst", "", "", "", "40"},
                    {"Tata lmg Calcium + Vitamin D3", "", "", "", "30"},
                    {"Feronia -XT Tablet", "", "", "", "130"},
                    {"Feronia -XT Tablet", "", "", "", "150"},
                    {"Feronia -XT Tablet", "", "", "", "169"},
            };


    private String[] packages_details = {
            "Buliding and keeping the bones & teeth strong \n" +
                    "Reducing Fatigue/strees and Buscular pains\n" +
                    "Boostring immunity and increasing resistence against infection",

            "Boostring2 immunity and increasing resistence against infection",
            "Boostring3 immunity and increasing resistence against infection",
            "Boostring4 immunity and increasing resistence against infection",
            "Boostring5 immunity and increasing resistence against infection",
            "Boostring5 immunity and increasing resistence against infection",
            "Boostring6 immunity and increasing resistence against infection",
            "Boostring7 immunity and increasing resistence against infection",
            "Boostring9 immunity and increasing resistence against infection"
    };


    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);


        lst = findViewById(R.id.listViewBMCCart);
        btnBack = findViewById(R.id.buttonBMCCartBack);
        btnGoToCart = findViewById(R.id.buttonBMCCartCheckOut);


        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });


        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " + packages[i][4] + "/-");
            list.add(item);
        }


        sa = new SimpleAdapter(this, list,

                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5",},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,});
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("test1",packages[i][0]);
                it.putExtra("test2",packages_details[i]);
                it.putExtra("test3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}