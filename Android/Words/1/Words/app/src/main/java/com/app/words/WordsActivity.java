package com.app.words;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;




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
    Button button;

    boolean weHaveWord;
    Word newWord;

    int startNum;
    int finNum;
    int currentNum;

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        _textView = (TextView) findViewById(R.id.textView);
        _firstWord = (EditText) findViewById(R.id.firstWord);
        _lastWord = (EditText) findViewById(R.id.lastWord);
        _timeInterval = (EditText) findViewById(R.id.timeInterval);

        weHaveWord = false;


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_words, menu);
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
*/



    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 10000ms the TimerTask will run every 20000ms
        timer.schedule(timerTask, 1000, 2000);
    }


    public void stoptimertask(View v) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
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
                        } else {
                            newWord = new Word(String.valueOf(currentNum));
                            _textView.setText(newWord._en);
                            weHaveWord = true;
                        }

                        if (currentNum == finNum) timer.cancel();

                        currentNum = currentNum + 1;
                        button.setText(String.valueOf(currentNum));
                    }
                });
            }
        };
    }



    public void buttonOnClick(View v) throws InterruptedException {

        button = (Button) v;
        button.setText("Stop");

        startNum = Integer.valueOf(_firstWord.getText().toString());
        finNum = Integer.valueOf(_lastWord.getText().toString());

        currentNum = startNum;

        startTimer();
    }

}
