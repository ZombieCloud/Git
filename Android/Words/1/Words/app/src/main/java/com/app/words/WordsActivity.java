package com.app.words;

import android.os.Bundle;
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

//    public URL url;
//    public HttpURLConnection urlConnection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
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

    public void buttonOnClick(View v) throws InterruptedException {
        Button button = (Button) v;
        button.setText("Stop");

        TextView _textView = (TextView) findViewById(R.id.textView);
        EditText _firstWord = (EditText) findViewById(R.id.firstWord);
        EditText _lastWord = (EditText) findViewById(R.id.lastWord);
        EditText _timeInterval = (EditText) findViewById(R.id.timeInterval);

        int startNum = Integer.valueOf(_firstWord.getText().toString());
        int finNum = Integer.valueOf(_lastWord.getText().toString());

        for (int currentNum = startNum; currentNum <= finNum; currentNum++) {
            Word newWord = new Word(String.valueOf(currentNum));
            _textView.setText(newWord._en);
            _textView.refreshDrawableState();
            TimeUnit.SECONDS.sleep(Integer.valueOf(_timeInterval.getText().toString()));
            _textView.setText(newWord._ru);
            _textView.refreshDrawableState();
            TimeUnit.SECONDS.sleep(Integer.valueOf(_timeInterval.getText().toString()));
        }

    }
}
