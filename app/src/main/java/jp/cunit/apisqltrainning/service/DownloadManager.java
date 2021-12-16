package jp.cunit.apisqltrainning.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jp.cunit.apisqltrainning.model.FileDownload;

public class DownloadManager extends IntentService {
    private static final String DOWNLOAD_PATH = "DOWNLOAD_PATH";
    private static final String DESTINATION_PATH = "DESTINATION_PATH";
    private static final String FOLDER_NAME = "FOLDER_NAME";
    private static final String ID_FILE = "ID_FILE";
    private static final String URL_THUMBNAIL = "URL_THUMBNAIL";
    public static long downloadId;
    public static String downloadFolderName;
    public static String downloadIdFile;
    public static String downloadUrlThumb;

    public DownloadManager() {
        super("DownloadManagerService");
    }

    public static Intent getDownloadService(final @NonNull Context callingClassContext,
                                            final @NonNull String downloadPath,
                                            final @NonNull String destinationPath,
                                            final @NonNull String folderName,
                                            final @NonNull String idFile,
                                            final @NonNull String urlThumb) {
        return new Intent(callingClassContext, DownloadManager.class)
                .putExtra(DOWNLOAD_PATH, downloadPath)
                .putExtra(DESTINATION_PATH, destinationPath)
                .putExtra(FOLDER_NAME, folderName)
                .putExtra(ID_FILE, idFile)
                .putExtra(URL_THUMBNAIL, urlThumb);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String downloadPath = intent.getStringExtra(DOWNLOAD_PATH);
        String destinationPath = intent.getStringExtra(DESTINATION_PATH);
        String folderName = intent.getStringExtra(FOLDER_NAME);
        String idFile = intent.getStringExtra(ID_FILE);
        String urlThumb = intent.getStringExtra(URL_THUMBNAIL);
        startDownload(downloadPath, destinationPath, folderName, idFile, urlThumb);
    }

    private void startDownload(String downloadPath, String destinationPath,
                               String folderName, String idFile, String urlThumb) {

        Uri uri = Uri.parse(downloadPath);
        Log.i("DownloadManager", "startDownload: " + uri);
        //check file exit
        String fileName = uri.getLastPathSegment();
        if(isFileExists(getExternalFilesDir(null).getPath() + "/" + destinationPath, fileName)){
            deleteFile(getExternalFilesDir(null).getPath() + "/" + destinationPath, fileName);
        }


        android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(android.app.DownloadManager.Request.NETWORK_MOBILE | android.app.DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
        request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
        request.setTitle("Downloading..."); // Title for notification.
        //request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalFilesDir(getApplicationContext(),destinationPath, fileName);  // Storage directory path
        downloadId = ((android.app.DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request); // This will start downloading
        downloadFolderName = folderName;
        downloadIdFile = idFile;
        downloadUrlThumb = urlThumb;
    }

    private boolean isFileExists(String dir, String filename){
        File file = new File(dir, filename);
        return file.exists();
    }

    private void deleteFile(String dir,  String filename){
        File file = new File(dir, filename);
        file.delete();
    }

    //get info file download

    public ArrayList<FileDownload> getDownloadStatus(){
        ArrayList<FileDownload> arrayList = new ArrayList<FileDownload>();

        android.app.DownloadManager.Query query = new android.app.DownloadManager.Query();
        query.setFilterById(downloadId);

        android.app.DownloadManager downloadManager = ((android.app.DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE));
        Cursor cursor = downloadManager.query(query);
        if(cursor.moveToNext()){
            int status = cursor.getInt(cursor.getColumnIndex(android.app.DownloadManager.COLUMN_STATUS));
            if (status == android.app.DownloadManager.STATUS_SUCCESSFUL) {
                String name = cursor.getString(cursor.getColumnIndex(android.app.DownloadManager.COLUMN_LOCAL_FILENAME));
                int clTotalSize = cursor.getColumnIndex(android.app.DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                String size = String.valueOf(cursor.getInt(clTotalSize));
                FileDownload filedownload = new FileDownload(name, size);
                arrayList.clear();
                arrayList.add(filedownload);
                return arrayList;
            }

        }

        return arrayList;
    }
}
