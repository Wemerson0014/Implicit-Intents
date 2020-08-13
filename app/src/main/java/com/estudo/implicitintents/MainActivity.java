package com.estudo.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openWebSite();
        openLocation();
        shareText();
    }

    public void openWebSite() {
        Button openWebSite = findViewById(R.id.open_website_button);
        EditText webSiteEditText = findViewById(R.id.website_edittext);
        final String url = webSiteEditText.getText().toString();
        final Uri webPage = Uri.parse(url);
        openWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", getString(R.string.log_error));
                }
            }
        });
    }

    private void openLocation() {
        Button openLocation = findViewById(R.id.open_location_button);
        EditText location = findViewById(R.id.location_edit_text);
        final String loc = location.getText().toString();
        final Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        openLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", getString(R.string.log_error));
                }
            }
        });
    }

    private void shareText() {
        Button shareText = findViewById(R.id.share_text_button);
        final EditText shareEdit = findViewById(R.id.share_edit_text);
        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = shareEdit.getText().toString();
                shareCompat(text);
            }
        });
    }

    private void shareCompat(String text) {
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle(R.string.share_message)
                .setText(text)
                .startChooser();
    }
}