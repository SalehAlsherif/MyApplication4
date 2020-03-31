package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    Button Menu;
    RecyclerView recyclerMenu;
    public static ArrayList<Item> Items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        Menu=findViewById(R.id.menubutton);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuVisit();
            }
        });
    }
    private void menuVisit(){
        setContentView(R.layout.menu_layout);
        recyclerMenu = (RecyclerView) findViewById(R.id.menu_recycler_view);
        recyclerMenu.setHasFixedSize(true);
        RecyclerMenuAdapter myAdapter = new RecyclerMenuAdapter(this,Items);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));
        recyclerMenu.setAdapter(myAdapter);
    }
}
