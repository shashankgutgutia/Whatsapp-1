package com.example.adity.whatsup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ChatScreen extends AppCompatActivity {

    private static final int SELECTED_PICTURE=1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView= (ImageView) findViewById(R.id.imageView);

    }
    public void cPhoto(View v){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECTED_PICTURE);
        }
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,SELECTED_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SELECTED_PICTURE:
                if(resultCode==RESULT_OK){
                    Uri uri=data.getData();
                    String[] projection={MediaStore.Images.Media.DATA};
                    Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filepath=cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap yImage= BitmapFactory.decodeFile(filepath);
                    Drawable d= new BitmapDrawable(yImage);
                    imageView.setBackground(d);

                }
                break;
        }
    }

}
