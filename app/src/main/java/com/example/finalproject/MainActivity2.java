package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private EditText masukUrl;
    private Button downloadUrl;
    private TextView E;
    private TextView N;
    private static final int PERMISSION_STORAGE_CODE=1000;
    //pembuatan variabel data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //pembuatan method untuk menampilkan pemanggilan data
        Bundle bundle = getIntent().getExtras();//pembuatan obyek widget textview
        TextView E = (TextView) findViewById(R.id.E);
        TextView N = (TextView) findViewById(R.id.N);
        E.setText(bundle.getCharSequence("Nama"));//menset nilai dari widget textview
        N.setText(bundle.getCharSequence("Email"));

        //membuat method data variabel
        masukUrl = (EditText) findViewById(R.id.masukUrl);
        downloadUrl = (Button) findViewById(R.id.downloadUrl);

        downloadUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //mengaktifkkan tombol button
                //proses pembuatan share permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        startDownloading();//guna untuk melanjutkan proses masukkan
                    }
                } else {
                    startDownloading();
                }
            }
        });
    }
    private void startDownloading() {
        //pembuatan proses lanjutan
        String Url = masukUrl.getText().toString().trim();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file...");

        //pembuatan proses lanjutan ke penyimpanan
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
    @Override
    //proses memuat data masukkan dan juga notif jalannya proses lanjutan
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permission, grantResult);
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResult.length > 0 && grantResult[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    startDownloading();
                } else {
                    Toast.makeText(MainActivity2.this, "Downloading Mulai...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}


