package com.gadgetcreek.text_to_speech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    EditText edittext;
    ImageButton imgbutton;
    SeekBar seekPitch;
    double pitch=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext=(EditText)findViewById(R.id.editText);
        imgbutton=(ImageButton)findViewById(R.id.imgbutton);
        seekPitch = (SeekBar) findViewById(R.id.seekpitch);
        seekPitch.setThumbOffset(5);

        edittext.setText("Welcome to Gadget Creek");

        seekPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pitch = (float) progress / (seekBar.getMax() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                        tts.setLanguage(Locale.US);
                } else if (status == TextToSpeech.ERROR) {
                    Toast.makeText(getApplicationContext(), "Sorry! Text To Speech failed...",Toast.LENGTH_LONG).show();
                }
            }
        });

        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = edittext.getText().toString();
                tts.setPitch((float) pitch);
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}

