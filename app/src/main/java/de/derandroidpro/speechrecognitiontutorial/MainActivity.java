package de.derandroidpro.speechrecognitiontutorial;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageButton imbtn;
    EditText et;
    final int SPEECHINTENT_REQ_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        imbtn = (ImageButton) findViewById(R.id.imageButton);
        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechRecognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString());
                startActivityForResult(speechRecognitionIntent, SPEECHINTENT_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SPEECHINTENT_REQ_CODE && resultCode == RESULT_OK){
            ArrayList<String> speechResults = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String finaltext;
            if(et.getText().length() >0) {
                finaltext = et.getText().toString() + " " + speechResults.get(0);
            } else {
                finaltext = speechResults.get(0);
            }
            et.setText(finaltext);
        }
    }
}
