package com.example.hp.firebaseautoupdate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int versionCode = BuildConfig.VERSION_CODE;
        final String versionName = BuildConfig.VERSION_NAME;
        Toast.makeText(MainActivity.this,versionName,Toast.LENGTH_LONG).show();


        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("reg dev id",token);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference version  = db.getReference("Version");
        version.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             String ver = dataSnapshot.getValue().toString();
                Toast.makeText(MainActivity.this,ver+ "",Toast.LENGTH_LONG).show();

                if(!versionName.equals(ver)){
                    Toast.makeText(MainActivity.this,"hi it is successful",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-autoupdate.appspot.com/o/app-debug.apk?alt=media&token=2862ed82-ea4d-4816-82ed-e0a7de02324c"));
                    startActivity(i);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
