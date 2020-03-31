package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button itemize;
    ImageView image;
    String pathToFile;
    Bitmap sourceImage;
    ArrayList<Item> Items;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Items=new ArrayList<Item>();
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btnTakePic);
        image=findViewById(R.id.image);
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakeAction();
            }
        });

        itemize=findViewById(R.id.itemize);

        itemize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemizeIntoTwo();
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RESULT_OK){
        }
        sourceImage= BitmapFactory.decodeFile(pathToFile);
        image.setImageBitmap(sourceImage);
    }

    private void itemizeIntoTwo() {
        //This where all the logic of using CV should go and the result is definitely more than 2
        Bitmap a= Bitmap.createBitmap(sourceImage,0,0,sourceImage.getWidth()/2, sourceImage.getHeight());
        Bitmap b= Bitmap.createBitmap(sourceImage,sourceImage.getWidth()/2,0,sourceImage.getWidth()/2, sourceImage.getHeight());
        Items.add(new Item("ddttttttttttttttttd","d","",a,""));
        Items.add(new Item("drfrdyyyyyyyyyyyyyyyyd","d","",b,""));

        // use this setting to improve performance if you know that changes
        // in content do not change the item_layout size of the RecyclerView

        // use a linear item_layout manager
        setContentView(R.layout.item_layout);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,Items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        // specify an adapter (see also next example)

    }

    private void dispatchPictureTakeAction() {
        Intent takePic=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile=null;
            photoFile=createPhotoFile();
            if(photoFile!=null){
                pathToFile=photoFile.getAbsolutePath();
                Uri photoURI= FileProvider.getUriForFile(MainActivity.this,"com.example.myapplication.fileprovider",photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,1);

            }
        }
    }

    private File createPhotoFile() {
        String name= new SimpleDateFormat("yyyyMMMdd").format(new Date());
        File storageDir=getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
             image= File.createTempFile(name,".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
