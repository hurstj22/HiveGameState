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
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTest.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        counter++;
        output.setText("", TextView.BufferType.EDITABLE);

        switch(counter) {
            case 1: //make the first instance, don't display
                setFirstInstance();
                //output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;
            case 2: //make the second instance, display the first
                HiveGameState secondInstance = new HiveGameState(firstInstance);
                output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;

            case 3: //move the tiles
                firstInstance.makeMove()
                output.setText("", TextView.BufferType.EDITABLE);
                //third instance goes where qutations go
                break;
        }

    }

    public void setFirstInstance(){

        //to help us adding some starting tiles... I defintely made that more complicated than it had to be
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                if (i == 2) {
                    Tile blackTile = new Tile(i, j, Tile.PlayerPiece.BLACK);
                    if (j == 3) {
                        blackTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 4) {
                        blackTile.setType(Tile.Bug.GRASSHOPPER);
                    } else {
                        blackTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.addTile(blackTile);
                } else {
                    Tile whiteTile = new Tile(i, j, Tile.PlayerPiece.WHITE);
                    if (j == 3) {
                        whiteTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 4) {
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