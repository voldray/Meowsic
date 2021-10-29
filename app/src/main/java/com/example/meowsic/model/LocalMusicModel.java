package com.example.meowsic.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.meowsic.bean.MusicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public class LocalMusicModel implements MusicModel {
    @Override
    public void showLocalMusic(OnMusicListener onMusicListener, Context context) {
        onMusicListener.OnComplete(getLocalMusic(context));
    }
    private List<MusicBean> getLocalMusic(Context context){
        if (context == null) return null;

        context = context.getApplicationContext();
        List<MusicBean> beans = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = resolver.query(uri,null,null,null);
        int id = 0;
        while (cursor != null && cursor.moveToNext()) {
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            if(duration < 90000) continue;

            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            id++;
            String sid = String.valueOf(id);
            MusicBean bean = new MusicBean(sid,title,artist,album,path,path,duration);
            beans.add(bean);
        }
        if (cursor != null && !cursor.isClosed()) cursor.close();
        return beans;
    }
}
