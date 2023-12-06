package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText title,desc;
    private ImageView save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase db = new DataBase(MainActivity.this);
                String Title = title.getText().toString();
                String Note = desc.getText().toString();
                long r = db.addNotes(Title,Note);
                if(r==-1)
                {
                    Toast.makeText(MainActivity.this, "Error Occurred During Adding Data", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Adding Successful", Toast.LENGTH_SHORT).show();
                }
                title.setText("");
                desc.setText("");
            }
        });
    }
}