package com.zenteno.miwebview;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hideSystemUI();
        myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient(){

            //impide que el hacer click en los links, estos se abran en otro navegador
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //return super.shouldOverrideUrlLoading(view, request);
                return false;
            }

            //ignora errores de certificado ssl
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://xiaobaizhan.com");
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (myWebView.canGoBack())
            myWebView.goBack();
        else
            super.onBackPressed();
    }
}
