package com.example.d064989.move;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager m;
    private TextView score;
    private TextView level;
    private TextView speed;
    private TextView rAxis;
    private int scr;
    private char[] axis = {'x', 'y', 'z'};
    private char randomAx;
    private int lvl = 0;
    private float newspeedlvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        level = findViewById(R.id.level);
        rAxis = findViewById(R.id.axis);
        speed = findViewById(R.id.speed);

        m = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m.registerListener(this,
                m.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_NORMAL);

        onSuccess(0);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        switch (randomAx) {
            case 'x':
                if (0 < newspeedlvl && x > newspeedlvl || newspeedlvl < 0 && x < newspeedlvl) {
                    onSuccess(lvl+1);
                }
                score.setText("Score: " + String.valueOf(scr));
                break;
            case 'y':
                if (0 < newspeedlvl && y > newspeedlvl || newspeedlvl < 0 && y < newspeedlvl) {
                    onSuccess(lvl+1);
                }
                score.setText("Score: " + String.valueOf(scr));
                break;
            case 'z':
                if (0 < newspeedlvl && z > newspeedlvl || newspeedlvl < 0 && z < newspeedlvl) {
                    onSuccess(lvl+1);
                    score.setText("Score: " + String.valueOf(scr));
                    break;
                }
        }

    }

    public void onSuccess(int currentlvl) {
        Random random = new Random();

        if (currentlvl == 0) {
            score.setText(String.valueOf(0));
        } else {
            scr += 10;
            lvl += 1;
        }

        randomAx = axis[random.nextInt(axis.length)];
        newspeedlvl = (lvl+0.1f) * 1.1257f;

        rAxis.setText("Axe: " + String.valueOf(randomAx));
        speed.setText("Speed: " + String.valueOf(newspeedlvl));

        level.setText("Level " + lvl);
    }
}
