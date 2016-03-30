package com.example.laurence.animatie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ImageView simpson1;
    ImageView simpson2;
    Boolean isluffy;
    Boolean fadeAni = false;
    Boolean translateAni = false;
    Boolean rotateAni = false;
    long tijd;
    SeekBar seekBar;
    RadioButton fadeButton;
    RadioButton translateButton;
    RadioButton rotateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpson1 = (ImageView) findViewById(R.id.simpson1);
        simpson2 = (ImageView) findViewById(R.id.simpson2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tijd = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        simpson2.animate().alpha(0f).setDuration(0l);

        fadeButton = (RadioButton) findViewById(R.id.fadeButton);
        fadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeAni = true;
                translateAni = false;
                rotateAni = false;

                if(isluffy){
                    simpson2.animate().alpha(0f);
                }
                else {
                    simpson1.animate().alpha(0f);
                }
                simpson2.animate().translationX(0f).setDuration(0l);
                simpson1.animate().translationX(0f).setDuration(0l);
                simpson2.animate().scaleX(1f).scaleY(1f).setDuration(1l);
                simpson1.animate().scaleX(1f).scaleY(1f).setDuration(1l);
            }
        });

        translateButton = (RadioButton) findViewById(R.id.translateButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateAni = true;
                fadeAni = false;
                rotateAni = false;
                simpson2.animate().alpha(1f);
                simpson1.animate().alpha(1f);
                if(isluffy){
                    simpson2.animate().translationX(-1000f).alpha(1f).setDuration(0l);
                    simpson2.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(1l);
                }
                else{
                    simpson1.animate().translationX(1000f).alpha(1f).setDuration(0l);
                    simpson1.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(1l);
                }
            }
        });

        rotateButton = (RadioButton) findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpson2.animate().alpha(1f);
                simpson1.animate().alpha(1f);
                rotateAni = true;
                fadeAni = false;
                translateAni = false;
                if(isluffy){
                    simpson2.animate().translationX(0f).setDuration(0l);
                    simpson2.animate().alpha(1f).scaleX(0f).scaleY(0f).setDuration(1l);
                }
                else{
                    simpson1.animate().translationX(0f).setDuration(0l);
                    simpson1.animate().alpha(1f).scaleX(0f).scaleY(0f).setDuration(1l);
                }
            }
        });
        isluffy = true;
        tijd = 2000l;
        seekBar.setProgress((int) tijd);

        fadeButton.setChecked(true);
        translateButton.setChecked(false);
        rotateButton.setChecked(false);
    }

    public void animate(View view){
        if(fadeAni == true){
            fade();
        }
        if(translateAni == true){
            rotate();
        }
        if(rotateAni == true){
            rotateAndScale();
        }
    }

    private void fade() {
        if (isluffy){
            simpson1.animate().alpha(0f).setDuration(tijd);
            simpson2.animate().alpha(1f).setDuration(tijd);
            isluffy = false;
        }
        else{
            simpson1.animate().alpha(1f).setDuration(tijd);
            simpson2.animate().alpha(0f).setDuration(tijd);
            isluffy = true;
        }
    }

    private void rotate(){
        if (isluffy){
            simpson1.animate()
                    .translationX(1000l)
                    .setDuration(tijd);
            simpson2.animate()
                    .translationX(0f)
                    .setDuration(tijd);
            isluffy = false;
        }
        else{
            simpson1.animate()
                    .translationX(0l)
                    .setDuration(tijd).alpha(1f);
            simpson2.animate()
                    .translationX(-1000f)
                    .setDuration(tijd);
            isluffy = true;
        }
    }

    private void rotateAndScale(){
        if(isluffy){
            simpson1.animate()
                    .rotation(1080f)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(tijd);
            simpson2.animate()
                    .rotation(-1080f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(tijd);
            isluffy = false;
        }
        else {
            simpson1.animate()
                    .rotation(-1080f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(tijd);
            simpson2.animate()
                    .rotation(1080f)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(tijd);
            isluffy = true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}