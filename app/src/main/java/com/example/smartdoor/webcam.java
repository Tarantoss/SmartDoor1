package com.example.smartdoor;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartdoor.R;


public class webcam extends Activity {

    String url;
    Button btn1;
    EditText edit1;
    WebView ipcamView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webcam);

        edit1 = (EditText) findViewById(R.id.editText1);
//        btn1 = (Button) findViewById(R.id.button1); button1이 없어서 생기는 에러 주석처리


        ipcamView = (WebView) findViewById(R.id.webView1);
        ipcamView.getSettings().setJavaScriptEnabled(true);
        ipcamView.setWebViewClient(new WebViewClient());
        ipcamView.loadUrl("http://hbdoor.iptime.org:5555");


    }


    public void setListener() {

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    url = edit1.getText().toString();
                    ipcamView.loadUrl(url);

                }
                return false;
            }
        });

    }


    public class IpcamViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return false;
        }
    }

}

