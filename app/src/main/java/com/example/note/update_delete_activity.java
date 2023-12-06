package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class update_delete_activity extends AppCompatActivity {
    private EditText utitle,udesc;
    private ImageView update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        utitle = findViewById(R.id.title_update_activity);
        udesc = findViewById(R.id.desc_update_activity);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        dbclass user = (dbclass) getIntent().getSerializableExtra("allDetail");
        if(user!=null){
            utitle.setText(user.getTitle());
            udesc.setText(user.getNote());
        }
        update.setOnClickListener(view -> {
            try(DataBase db = new DataBase(update_delete_activity.this)){
                int id = user.getId();
                String title1 = utitle.getText().toString();
                String desc1 = udesc.getText().toString();
                boolean b = db.update(id,title1,desc1);
                if(b) {
                    Toast.makeText(update_delete_activity.this, "Values Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(update_delete_activity.this, "Error Ocured While Updation", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e){
                Toast.makeText(update_delete_activity.this, "Error During Updation", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try(DataBase db = new DataBase(update_delete_activity.this)) {
                    boolean b = db.deleteNote(user.getId());
                    if(b)
                        Toast.makeText(update_delete_activity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(update_delete_activity.this, "Failed To Delete", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}