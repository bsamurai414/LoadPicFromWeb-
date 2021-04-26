package com.example.loadpicfromweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends Activity {

    public String urls="";
    EditText editText;
    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.edittxt);

        image = (ImageView) findViewById(R.id.image);

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(arg0 -> {

            urls = editText.getText().toString();

            Log.i("Log1", urls.toString());

            new DownloadImage().execute(urls);
        });
    }



   // DownloadImage AsyncTask
   @SuppressLint("StaticFieldLeak")
   private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

       @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Download Image Using AsyncTask Tutorial");
            mProgressDialog.setMessage("Downloading...please wait...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String imageURL = urls[0];

            Bitmap bitmap = null;
            try {
// Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                Log.i("Log1", input.toString());
// Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
// Set the bitmap into ImageView
            image.setImageBitmap(result);
// Close progressdialog
            mProgressDialog.dismiss();
        }
    }
}