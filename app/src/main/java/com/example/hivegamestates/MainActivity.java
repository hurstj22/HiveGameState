package com.example.hivegamestates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText output = findViewById(R.id.editText);
    Button runTest = findViewById(R.id.runTestButton);
    //making firstInstance of gameState
    HiveGameState firstInstance = new HiveGameState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTest.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {

        output.setText("", TextView.BufferType.EDITABLE);


        output.setText("", TextView.BufferType.EDITABLE);
        //first instance goes where qutations go

        output.setText("", TextView.BufferType.EDITABLE);
        //second instance goes where qutations go

    }
}