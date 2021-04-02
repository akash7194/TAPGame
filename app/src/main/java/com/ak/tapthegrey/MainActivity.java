package com.ak.tapthegrey;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ImageView iv1, iv2, iv3, iv4;
    TextView score;
    boolean mStopHandler = false;
    Tile greyTile;//, selectedTile;
    int scorereal;
    private Timer mTimer1;
    private TimerTask mTt1;
    private Handler mTimerHandler;
    MediaPlayer ring1;
    AlertDialog dialog;
    private enum Tile {
            TILE1,
        TILE2,
        TILE3,
        TILE4
    }

    @Override
    protected void onStop() {
        super.onStop();
        ring1.pause();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ring1= MediaPlayer.create(MainActivity.this,R.raw.true11);
        ring1.start();
        ring1.setLooping(true);
        ring1.setVolume(0.39f , 0.39f);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
      //  ring1.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ring1.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.ivred);
        iv2 = findViewById(R.id.ivgreen);
        iv3 = findViewById(R.id.ivblue);
        iv4 = findViewById(R.id.ivblack);
        iv1.setOnTouchListener(this);
        iv2.setOnTouchListener(this);
        iv3.setOnTouchListener(this);
        iv4.setOnTouchListener(this);
        score = findViewById(R.id.score);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome");
        builder.setMessage("Lets Play");

        // add the buttons
        builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTimer();
                scorereal=0;
            }
        });
        //builder.setNeutralButton("Remind me later", null);


builder.setCancelable(false);
        // create and show the alert dialog
         dialog = builder.create();

        dialog.show();
        mTimerHandler=new Handler();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                changeRandomGrey();
//            }
//        }, 1000);


        }

    private void startTimer() {
        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run(){
                        changeRandomGrey();
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 0, 1000);
    }

    private void changeRandomGrey() {
        if (/*selectedTile==greyTile||*/greyTile==null) {
            final int min = 1;
            final int max = 4;
            final int random = new Random().nextInt(4);
            //selectedTile = random;
            switch (random) {
                case 0:
                    setallDefault();
                    iv1.setBackgroundColor(Color.GRAY);
                    greyTile = Tile.TILE1;
                    break;
                case 1:
                    setallDefault();
                    iv2.setBackgroundColor(Color.GRAY);
                    greyTile = Tile.TILE2;
                    break;
                case 2:
                    setallDefault();
                    iv3.setBackgroundColor(Color.GRAY);
                    greyTile = Tile.TILE3;
                    break;
                case 3:
                    setallDefault();
                    iv4.setBackgroundColor(Color.GRAY);
                    greyTile = Tile.TILE4;
                    break;
                default:
                    break;
            }
        }
        else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            gameOver();
        }
    }

    private void setallDefault() {
        iv1.setBackgroundColor(Color.RED);
        iv2.setBackgroundColor(Color.GREEN);
        iv3.setBackgroundColor(Color.BLUE);
        iv4.setBackgroundColor(Color.BLACK);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN && greyTile!=null ) {
            switch (v.getId()) {
                case R.id.ivred:
                    if (greyTile == Tile.TILE1) {
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        scorereal++;
                        greyTile = null;
                        setallDefault();

                        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.pup);
                        ring.start();
                        //selectedTile = Tile.TILE1;
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                        gameOver();

                    }
                    break;
                case R.id.ivgreen:
                    if (greyTile == Tile.TILE2) {
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        scorereal++;
                        setallDefault();

                        greyTile = null;
                        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.pup);
                        ring.start();
                        //selectedTile = Tile.TILE2;
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                        gameOver();

                    }
                    break;
                case R.id.ivblue:
                    if (greyTile == Tile.TILE3) {
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        scorereal++;
                        setallDefault();

                        greyTile = null;
                        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.pup);
                        ring.start();
                        //selectedTile = Tile.TILE3;
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                        gameOver();

                    }
                    break;

                case R.id.ivblack:
                    if (greyTile == Tile.TILE4) {
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        scorereal++;
                        setallDefault();

                        greyTile = null;
                        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.pup);
                        ring.start();
                        //selectedTile = Tile.TILE4;
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                        gameOver();

                    }
                    break;


                default:
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                    break;
            }
            updateScore(scorereal);
        }
        return true;
    }

    private void gameOver() {
stopTimer();

showDialognow();
    }

    private void showDialognow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GAME OVER");
        builder.setMessage("Your SCore is "+scorereal);

        // add the buttons
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTimer();
                greyTile=null;

                scorereal=0;
                updateScore(scorereal);
            }
        });
        //builder.setNeutralButton("Remind me later", null);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ring1.stop();
                finish();
            }
        });


        // create and show the alert dialog
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    private void stopTimer(){
        if(mTimer1 != null){
            mTimer1.cancel();
            mTimer1.purge();
        }
    }

    private void updateScore(int scorereal) {
        score.setText("Score :=: "+scorereal);
    }
}