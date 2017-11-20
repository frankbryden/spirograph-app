package com.example.frankie.spirograph;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SpiroView spiroView;
    private Hypercycloid hp;
    private Random random;
    private SeekBar innerSeek, outerSeek, penPosSeek, dotCountSeek, animateSpeedSeek;
    CheckBox animateCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.random = new Random(System.currentTimeMillis());

        /*Spiro view - draws hypercycloid */
        this.spiroView = (SpiroView) findViewById(R.id.myHP);
        this.update(getRandomHypercycloid());
        this.spiroView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MyAPp", "Touch");
                update(getRandomHypercycloid());
                return false;
            }
        });

        /* Inner Seek Bar */
        this.innerSeek = (SeekBar) findViewById(R.id.inner_radius_seek);
        this.innerSeek.setMax(290);
        this.innerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hp.setInnerRadius(progress + 10);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /* Outer seek bar */
        this.outerSeek = (SeekBar) findViewById(R.id.outer_radius_seek);
        this.outerSeek.setMax(290);
        this.outerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hp.setOuterRadius(progress + 10);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /* Pen pos seek */
        this.penPosSeek = (SeekBar) findViewById(R.id.pen_pos_radius_seek);
        this.penPosSeek.setMax(290);
        this.penPosSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hp.setPenPos(progress + 10.0);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /* Dot count seek */
        this.dotCountSeek = (SeekBar) findViewById(R.id.dot_count_seek);
        this.dotCountSeek.setMax(900);
        this.dotCountSeek.setProgress(spiroView.getDotCount());
        this.dotCountSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                spiroView.setDotCount(progress + 100);
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /* Animation Speed Seek Bar */
        this.animateSpeedSeek = (SeekBar) findViewById(R.id.animateSpeed);
        this.animateSpeedSeek.setMax(950);
        this.animateSpeedSeek.setProgress(spiroView.getAnimationSpeed());
        this.animateSpeedSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                spiroView.setAnimationSpeed(progress + 50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /* Animate check box */
        this.animateCheckbox = (CheckBox) findViewById(R.id.animateCheckbox);
        this.animateCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    spiroView.startAnimation();
                } else {
                    spiroView.stopAnimation();
                }
            }
        });


    }

    public void update(Hypercycloid hypercycloid){
        this.hp = hypercycloid;
        spiroView.update(hypercycloid);
        spiroView.invalidate();
    }

    public void update(){
        spiroView.invalidate();
    }

    public Hypercycloid getRandomHypercycloid(){
        Hypercycloid hypercycloid = new Hypercycloid(getRandomCircle(), getRandomCircle(), getRandomPosition());
        return hypercycloid;
    }

    public Position getRandomPosition(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return new Position((random.nextDouble() + 2) * 4, (random.nextDouble() + 2) * 4);
    }

    public Circle getRandomCircle(){
        return new Circle((random.nextInt(2) + 4) * 10, getRandomPosition());
    }

}
