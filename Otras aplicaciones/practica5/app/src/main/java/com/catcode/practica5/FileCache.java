package com.catcode.practica5;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.net.URLEncoder;

/**
 * Created by Carlos on 11/10/2017.
 */

public class FileCache {
    private File cacheDir;

    public FileCache(Context context) {
        if(android.os.Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
        )){
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(),
                    "LazyList");
        }else {
            cacheDir = context.getCacheDir();
        }
        if(!cacheDir.exists())
            cacheDir.mkdir();
    }

    public File getfile(String url){
        String filename = String.valueOf(url.hashCode());

        // String filename = URLEncoder.encode(url);

        File f = new File(cacheDir, filename);
        return f;
    }

    public void clear(){
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f:files)
            f.delete();
    }
}

