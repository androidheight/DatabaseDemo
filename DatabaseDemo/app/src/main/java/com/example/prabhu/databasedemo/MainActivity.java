package com.example.prabhu.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName,etRoll,etAddress,etBranch,etEmail;
    Button btnSubmit,btngetdata;
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbList= new ArrayList<DatabaseModel>();
        etName = (EditText)findViewById(R.id.etName);
        etRoll = (EditText)findViewById(R.id.etRoll);
        etAddress =(EditText)findViewById(R.id.etAddress);
        etBranch = (EditText)findViewById(R.id.etBranch);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));

               // startActivity(new Intent(MainActivity.this, DetailsActivity.class));

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=etName.getText().toString();
                String email=etEmail.getText().toString();
                String roll=etRoll.getText().toString();
                String address=etAddress.getText().toString();
                String branch=etBranch.getText().toString();

            if(name.equals("") || email.equals("") || roll.equals("") ||address.equals("")||branch.equals("")){
                Toast.makeText(MainActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
            }else {
                helpher = new DatabaseHelpher(MainActivity.this);
                helpher.insertIntoDB(name, email, roll, address, branch);
            }
                etName.setText("");
                etRoll.setText("");
                etAddress.setText("");
                etBranch.setText("");
                etEmail.setText("");

                Toast.makeText(MainActivity.this, "insert value", Toast.LENGTH_LONG);

            }
        });

    }


}
