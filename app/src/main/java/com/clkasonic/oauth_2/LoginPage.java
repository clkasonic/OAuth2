package com.clkasonic.oauth_2;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import android.net.Uri;

import com.uwanttolearn.easysocial.EasySocialAuthActivity;
import com.uwanttolearn.easysocial.EasySocialCredential;

public class LoginPage extends AppCompatActivity {

    private int REQUEST_CODE = 0x1;
    private TextView _textView;

    private static String CLIENT_KEY = "key";
    private static String CLIENT_SECRET = "secret";
    private static String REDIRECT_URI = "https://api.misfitwearables.com/auth/login";
    private static String SCOPES = "public,birthday,email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        _textView = (TextView)findViewById(R.id.textView);
    }

    public void StartOauth(View v) {

        Intent intent = new Intent(this, EasySocialAuthActivity.class);
        // TO DO: For loginURL order: client_id, scopes, response_type, redirect_uri
        String loginURL = "https://www.api.misfitwearable.com/auth/dialog/authorize?" + "client_id=" + CLIENT_KEY  + "&scopes=" + SCOPES + "&response_type=code" + "&redirect_uri=" + REDIRECT_URI;
        intent.putExtra(EasySocialAuthActivity.URL, loginURL);
        intent.putExtra(EasySocialAuthActivity.REDIRECT_URL, REDIRECT_URI);

        // TO DO; For aTokenURL order: grant_type, code, redirect_uri, client_id, client_secret
        String accessTokenURL = "https://www.api.misfitwearables.com/auth/tokens/exchange?" + "grant_type=code" + "&code=code" + "&redirect_uri=" + REDIRECT_URI + "&client_id=" + CLIENT_KEY + "&client_secret=" + CLIENT_SECRET;
        intent.putExtra(EasySocialAuthActivity.ACCESS_TOKEN, accessTokenURL);
        startActivityForResult(intent, REQUEST_CODE);
    }

    protected void OnActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == requestCode) {
                String accessToken = data.getStringExtra("data");
                _textView.setText(accessToken);
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            if (resultCode == requestCode) {
                Toast.makeText(this, data.getIntExtra(EasySocialAuthActivity.ERROR_CODE, 0) + "", Toast.LENGTH_LONG).show();
            }
        }
    }
}
