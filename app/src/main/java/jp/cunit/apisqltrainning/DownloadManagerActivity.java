package jp.cunit.apisqltrainning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import jp.cunit.apisqltrainning.service.DownloadManager;
import jp.cunit.apisqltrainning.util.DirectoryHelper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DownloadManagerActivity extends AppCompatActivity {

    Button downloadImageButton, downloadSongButton;
    private static final String IMAGE_DOWNLOAD_PATH = "http://www.africau.edu/images/default/sample.pdf";
    private static final String SONG_DOWNLOAD_PATH = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4";
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);
        downloadImageButton = (Button) findViewById(R.id.downloadImageButton);
        downloadSongButton = (Button) findViewById(R.id.downloadSongButton);


        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(DownloadManager.getDownloadService(DownloadManagerActivity.this,
                        IMAGE_DOWNLOAD_PATH,
                        DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/manual/1"),
                        "manual",
                        "1",
                        "http://cus-ymctest.com/kariup/sondo/images/prd.png"));
            }
        });
        downloadSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(DownloadManager.getDownloadService(DownloadManagerActivity.this,
                        SONG_DOWNLOAD_PATH,
                        DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/movie/1"),
                        "movie",
                        "1",
                        "http://cus-ymctest.com/kariup/sondo/images/prd.png"));
            }
        });

        //check permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            return;
        }
        DirectoryHelper.createDirectory(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                DirectoryHelper.createDirectory(this);
        }
    }

}