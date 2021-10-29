package com.example.meowsic.model;

import android.content.Context;

import com.example.meowsic.bean.MusicBean;

import java.util.List;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public interface MusicModel {
    void showLocalMusic(OnMusicListener onMusicListener, Context context);
    interface OnMusicListener{
        void OnComplete(List<MusicBean> beans);
    }
}
