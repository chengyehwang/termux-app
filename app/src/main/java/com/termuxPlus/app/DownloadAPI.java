package com.termuxPlus.app;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.termuxPlus.app.util.ResultReturner;

import java.io.File;

public class DownloadAPI {

    static void onReceive(TermuxApiReceiver apiReceiver, final Context context, final Intent intent) {
        ResultReturner.returnData(apiReceiver, intent, out -> {
            final Uri downloadUri = intent.getData();
            if (downloadUri == null) {
                out.println("No download URI specified");
                return;
            }

            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String path = intent.getStringExtra("path");

            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Request req = new Request(downloadUri);
            req.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            req.setVisibleInDownloadsUi(true);

            if (title != null)
                req.setTitle(title);

            if (description != null)
                req.setDescription(description);

            if (path != null)
                req.setDestinationUri(Uri.fromFile(new File(path)));

            manager.enqueue(req);
        });
    }
}
