package com.app.words;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Timer;
import java.util.TimerTask;



class Word {
    public String _en = "";
    public String _ru = "";
    private String _num = "";


    //Конструктор
    public Word(String num) {
        _num = num;

        URL url;
        HttpURLConnection urlConnection = null;
        InputStream in;
        int data;
        InputStreamReader isw;

        try {
            //Слово EN (_en)
            url = new URL("http://tests.progmans.net/index.php?NUM_EN=" + _num);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            isw = new InputStreamReader(in);
            data = isw.read();
            while (data != -1) {
                char currentSymbol = (char) data;
                data = isw.read();
                _en = _en + currentSymbol;
            }

            //Слово RU (_ru)
            url = new URL("http://tests.progmans.net/index.php?NUM_RU=" + _num);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            isw = new InputStreamReader(in);
            data = isw.read();
            while (data != -1) {
                char currentSymbol = (char) data;
                data = isw.read();
                _ru = _ru + currentSymbol;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
};




public class WordsActivity extends AppCompatActivity {

    TextView _textView;
    EditText _firstWord;
    EditText _lastWord;
    EditText _timeInterval;

    boolean weHaveWord;
    Word newWord;

    int startNum;
    int lastNum;
    int currentNum;

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
//    View v;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        _textView = (TextView) findViewById(R.id.textView);
        _firstWord = (EditText) findViewById(R.id.firstWord);
        _lastWord = (EditText) findViewById(R.id.lastWord);
        _timeInterval = (EditText) findViewById(R.id.timeInterval);

        weHaveWord = false;
    }



    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //starts the timer
        timer.schedule(timerTask, 4000, Integer.valueOf(_timeInterval.getText().toString()) * 1000);
    }


    public void stopTimerTask() {
        //stop the timer
        if (timer != null) {
            timer.cancel();
            timer = null;
            button.setText("Go !!!");
        }
    }



    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run
                handler.post(new Runnable() {
                    public void run() {
                        if (weHaveWord) {
                            _textView.setText(newWord._ru);
                            weHaveWord = false;
                            currentNum = currentNum + 1;
                        } else {
                            newWord = new Word(String.valueOf(currentNum));
                            _textView.setText(newWord._en);
                            weHaveWord = true;
                        }

                        if (currentNum > lastNum) stopTimerTask();
                    }
                });
            }
        };
    }



    public void buttonOnClick(View v) throws InterruptedException {
        button = (Button) v;

        if (button.getText() == "Stop") {
            stopTimerTask();
            button.setText("Go !!!");
        } else {
            button.setText("Stop");

            startNum = Integer.valueOf(_firstWord.getText().toString());
            lastNum = Integer.valueOf(_lastWord.getText().toString());
            currentNum = startNum;

            startTimer();
        }
    }

}
