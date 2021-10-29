package com.example.meowsic.presenter;

import android.util.Log;


import com.example.meowsic.bean.MusicBean;
import com.example.meowsic.model.LocalMusicModel;
import com.example.meowsic.model.MusicModel;
import com.example.meowsic.view.LocalMusicView;

import java.util.List;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public class MusicPresenter<T extends LocalMusicView> extends BasePresenter {

    private static final String TAG = MusicPresenter.class.getSimpleName();

    private MusicModel mMusicModel = new LocalMusicModel(); //向上转型

    public void fetch(){
        if (mLocalMusicView != null) {
            mMusicModel.showLocalMusic(new MusicModel.OnMusicListener() {
                @Override
                public void OnComplete(List<MusicBean> beans) {
                    Log.d(TAG, "OnComplete: "+(beans != null));
                    ((T) mLocalMusicView.get()).showLocalMusic(beans);
                }
            },context);
        }
    }
}
