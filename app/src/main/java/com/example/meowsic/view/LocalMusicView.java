package com.example.meowsic.view;


import com.example.meowsic.bean.MusicBean;

import java.util.List;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public interface LocalMusicView extends BaseView{
    void showLocalMusic(List<MusicBean> beans);
    //数据
}
