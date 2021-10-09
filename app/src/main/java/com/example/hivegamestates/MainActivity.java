package com.example.hivegamestates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int counter = 0;
    HiveGameState firstInstance;
    HiveGameState secondInstance;
    HiveGameState thirdInstance;
    TextView output;
    Button runTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.editText);
        runTest = (Button) findViewById(R.id.runTestButton);

        //making firstInstance of gameState
        firstInstance = new HiveGameState();

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
                firstInstance.setWhoseTurn(HiveGameState.Turn.PLAYER1);
                secondInstance = new HiveGameState(firstInstance);
                output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;

            case 3: //move the white beetle
                firstInstance.makeMove(firstInstance.getTile(2, 3), 2, 2);
                output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;
            case 4: //move the black grasshopper
                firstInstance.setWhoseTurn(HiveGameState.Turn.PLAYER2);
                firstInstance.makeMove(firstInstance.getTile(3, 4), 1, 4);
                output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;
            case 5: //move the white bee
                firstInstance.setWhoseTurn(HiveGameState.Turn.PLAYER1);
                firstInstance.makeMove(firstInstance.getTile(2, 4), 3, 4);
                output.setText(firstInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;
            case 6: //copy the second instance and print the second and third showing they are equal
                thirdInstance = new HiveGameState(secondInstance);
                output.setText("Second Instance: \n" + secondInstance.toString() +
                                    "Third Instance: \n" + thirdInstance.toString(), TextView.BufferType.EDITABLE);
                view.invalidate();
                break;
        }

    }

    /**
     * Creates a premade gameboard as if two people had been playing
     * the game already
     */
    public void setFirstInstance(){

        //to help us adding some starting tiles... I defintely made that more complicated than it had to be
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                if (i == 2) {
                    Tile blackTile = new Tile(i, j, Tile.PlayerPiece.B);
                    if (j == 3) {
                        blackTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 4) {
                        blackTile.setType(Tile.Bug.GRASSHOPPER);
                    } else {
                        blackTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.setWhoseTurn(HiveGameState.Turn.PLAYER1);
                    firstInstance.addTile(blackTile);
                    firstInstance.removePiecesRemain(blackTile.getType());
                } else {
                    Tile whiteTile = new Tile(i, j, Tile.PlayerPiece.W);
                    if (j == 3) {
                        whiteTile.setType(Tile.Bug.BEETLE);
                    } else if (j == 4) {
                        whiteTile.setType(Tile.Bug.GRASSHOPPER);
                    } else {
                        whiteTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.setWhoseTurn(HiveGameState.Turn.PLAYER2);
                    firstInstance.addTile(whiteTile);
                    firstInstance.removePiecesRemain(whiteTile.getType());
                }
            }
        }
    }

}