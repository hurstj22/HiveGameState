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

        setFirstInstance(); //creates the first test case
    }

    @Override
    public void onClick(View view) {

        output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);

        output.setText("", TextView.BufferType.EDITABLE);
        //first instance goes where qutations go

        output.setText("", TextView.BufferType.EDITABLE);
        //second instance goes where qutations go

    }

    public void setFirstInstance(){

        //to help us adding some starting tiles... I defintely made that more complicated than it had to be
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                if (i == 2) {
                    Tile blackTile = new Tile(i, j, Tile.PlayerPiece.BLACK);
                    if (j == 0) {
                        blackTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 1) {
                        blackTile.setType(Tile.Bug.GRASSHOPPER);
                    } else {
                        blackTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.addTile(blackTile);
                } else {
                    Tile whiteTile = new Tile(i, j, Tile.PlayerPiece.WHITE);
                    if (j == 0) {
                        whiteTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 1) {
                        whiteTile.setType(Tile.Bug.GRASSHOPPER);
                    } else {
                        whiteTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.addTile(whiteTile);
                }
            }
        }
    }

}