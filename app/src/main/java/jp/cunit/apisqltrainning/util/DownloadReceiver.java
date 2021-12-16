package jp.cunit.apisqltrainning.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import jp.cunit.apisqltrainning.service.DownloadManager;

public class DownloadReceiver extends BroadcastReceiver {

    private final String TAG = "DownloadReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        //check when download complete
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        String action = intent.getAction();
        String folderName = DownloadManager.downloadFolderName;
        String idFile = DownloadManager.downloadIdFile;
        String urlThumb = DownloadManager.downloadUrlThumb;

        if (android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {

            android.app.DownloadManager.Query query = new android.app.DownloadManager.Query();
            query.setFilterById(DownloadManager.downloadId);

            // Check if file has been successfully downloaded
            Cursor c = downloadManager.query(query);
            if (c.moveToFirst()) {

                int fileUriIdx = c.getColumnIndex(android.app.DownloadManager.COLUMN_LOCAL_URI);
                String fileUri = c.getString(fileUriIdx);
                String fileName = null;
                if (fileUri != null) {
                    File mFile = new File(Uri.parse(fileUri).getPath());
                    fileName = mFile.getAbsolutePath().split("/")[mFile.getAbsolutePath().split("/").length - 1];
                }

                int intSize = c.getColumnIndex(android.app.DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                String size = String.valueOf(c.getInt(intSize));


            }
            // Close any open resources
            c.close();

            //check urlThumb to download thumbnail
            if(!TextUtils.isEmpty(urlThumb)){
                String path_thumb = "/" + folderName + "/" + idFile + "/thumb";
                context.startService(DownloadManager.getDownloadService(
                        context,
                        urlThumb,
                        DirectoryHelper.ROOT_DIRECTORY_NAME.concat(path_thumb),
                        "",
                        "",
                        ""));

            }

        }


    }
}
