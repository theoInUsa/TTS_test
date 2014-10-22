package com.qolsys.tts_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;

import java.util.HashMap;
import java.util.Locale;
import android.widget.Toast;
 
public class MainActivity extends Activity implements OnClickListener, OnInitListener {
     
        //TTS object
    private TextToSpeech myTTS;
        //status check code
    private int MY_DATA_CHECK_CODE = 0;
    Button button1_1;
    Button button1_2;
    Button button1_3;
    TextView btnText1;
	private TextToSpeech mTTS;
     
        //create the Activity
    public void onCreate(Bundle savedInstanceState) {
     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
             
                //get a reference to the button element listed in the XML layout
            Button speakButton = (Button)findViewById(R.id.speak);
            
                //listen for clicks
         
            speakButton.setOnClickListener(this);
 
            //check for TTS data
            Intent checkTTSIntent = new Intent();
            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
            
            addListenerOnButton();
            addListenerOnButton2();
            addListenerOnButton3();
    }
    
    public void addListenerOnButton() {
    	 
		button1_1 = (Button) findViewById(R.id.button1);
		button1_2 = (Button) findViewById(R.id.button2);
		button1_3 = (Button) findViewById(R.id.button3);
 
		button1_1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				 TextView text_frase = (TextView)findViewById(R.id.btn1);
				 String frase = (String) text_frase.getText().toString();
				 Log.d("Tag",frase);
				 Intent checkTTSIntent = new Intent();
		            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
		        	speakWords(frase);
 
			}
 
		});
    }
    
    public void addListenerOnButton2() {
		
		button1_2.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				 TextView text_frase = (TextView)findViewById(R.id.btn2);
				 String frase2
				 = (String) text_frase.getText().toString();
				 Log.d("Tag",frase2);
				 Intent checkTTSIntent = new Intent();
		            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
		        	speakWords(frase2);
 
			}
 
		});
    }
    
    public void addListenerOnButton3() {
		
		button1_3.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				 TextView text_frase = (TextView)findViewById(R.id.btn3);
				 String frase3 = (String) text_frase.getText().toString();
				 Log.d("Tag",frase3);
				 Intent checkTTSIntent = new Intent();
		            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
		        	speakWords(frase3);
 
			}
 
		});
    }
     
        //respond to button clicks
    public void onClick(View v) {
 
            //get the text entered
            EditText enteredText = (EditText)findViewById(R.id.enter);
            String words = enteredText.getText().toString();
            speakWords(words);
    }
     
        //speak the user text
    private void speakWords(String speech) {
 
            //speak straight away
            myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }
     
        //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
            myTTS = new TextToSpeech(this, this);
            }
            else {
                    //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
 
        //setup TTS
    public void onInit(int initStatus) {
     
            //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
}