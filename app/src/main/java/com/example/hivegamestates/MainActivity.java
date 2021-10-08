package com.example.hivegamestates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //making firstInstance of gameState
        HiveGameState firstInstance = new HiveGameState();
        //to help us adding some starting tiles... I defintely made that more complicated than it had to be
        for(int i = 2; i < 4; i++){
            for(int j = 3; j < 6; j++){
                if(i == 2){
                    Tile blackTile = new Tile(i, j, Tile.PlayerPiece.BLACK);
                    if (j == 0){
                        blackTile.setType(Tile.Bug.BEETLE);
                    }
                    else if (j == 1){
                        blackTile.setType(Tile.Bug.GRASSHOPPER);
                    }
                    else{
                        blackTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.addTile(blackTile);
                }
                else{
                    Tile whiteTile = new Tile(i, j, Tile.PlayerPiece.WHITE);
                    if (j == 0){
                        whiteTile.setType(Tile.Bug.BEETLE);
                    }
                    else if (j == 1){
                        whiteTile.setType(Tile.Bug.GRASSHOPPER);
                    }
                    else{
                        whiteTile.setType(Tile.Bug.QUEEN_BEE);
                    }
                    firstInstance.addTile(whiteTile);
                }
            }
        }

    }
}