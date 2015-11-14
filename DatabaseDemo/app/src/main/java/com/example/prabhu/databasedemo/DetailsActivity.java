package com.example.prabhu.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView tvname,tvemail,tvroll,tvaddress,tvbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
         position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.name);
        tvemail =(TextView)findViewById(R.id.email);
        tvroll =(TextView)findViewById(R.id.roll);
        tvaddress =(TextView)findViewById(R.id.address);
        tvbranch =(TextView)findViewById(R.id.branch);
        helpher = new DatabaseHelpher(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            String email=dbList.get(position).getEmail();
            String roll=dbList.get(position).getRoll();
            String address=dbList.get(position).getAddress();
            String branch=dbList.get(position).getBranch();
            tvname.setText(name);
            tvemail.setText(email);
            tvroll.setText(roll);
            tvaddress.setText(address);
            tvbranch.setText(branch);
        }

        Toast.makeText(DetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
