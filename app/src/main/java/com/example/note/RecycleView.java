package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity {
    private ImageView refresh_btn;
    private RecyclerView recycleView;
    private CardView add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recycleView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        refresh_btn = findViewById(R.id.refresh_btn);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        //fatch data from database  to array list
        ArrayList<dbclass> data=null;
        try(DataBase mydb = new DataBase(this)){
             data = mydb.getAllNotes();
        }
        catch(Exception e){
            Toast.makeText(this, "Error During Fetching Data", Toast.LENGTH_SHORT).show();
        }
        //set the data to the recycle view
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(RecycleView.this,data);
        recycleView.setAdapter(recyclerViewAdapter);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecycleView.this, MainActivity.class));
            }
        });
        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent refresh = getIntent();
                finish();
                startActivity(refresh);
            }
        });
    }
}