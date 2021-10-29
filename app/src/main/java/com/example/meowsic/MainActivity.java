package com.example.meowsic;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meowsic.adapter.MusicAdapter;
import com.example.meowsic.bean.MusicBean;
import com.example.meowsic.model.LocalMusicModel;
import com.example.meowsic.model.MusicModel;
import com.example.meowsic.presenter.MusicPresenter;
import com.example.meowsic.util.ImmersiveStatusBarUtil;
import com.example.meowsic.util.PermissionUtil;
import com.example.meowsic.view.BaseActivity;
import com.example.meowsic.view.LocalMusicView;


import java.util.List;

public class MainActivity extends BaseActivity<MusicPresenter,LocalMusicView> implements LocalMusicView {

    private RecyclerView mMusicRv;
    private MusicAdapter mMusicAdapter;
    private MusicModel mMusicModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMusicRv = findViewById(R.id.main_activity_rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);
        mMusicRv.setLayoutManager(layoutManager);
        mMusicModel = new LocalMusicModel();
        mMusicModel.showLocalMusic(new MusicModel.OnMusicListener() {
            @Override
            public void OnComplete(List<MusicBean> beans) {

            }
        },getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fetch();//查询本地音乐数据
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (!PermissionUtil.isGetPermission(this)) {
                PermissionUtil.getStorage(this);
            }
        }
    }

    @Override
    protected MusicPresenter createPresenter() {
        return new MusicPresenter();
    }

    @Override
    public void showLocalMusic(List<MusicBean> beans) {
        UpdateMusicAdapter(beans);
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    protected void init() {
        ImmersiveStatusBarUtil.transparentBar(this,true);
        if (!PermissionUtil.isGetPermission(this)) PermissionUtil.getStorage(this);
    }

    private void UpdateMusicAdapter(List<MusicBean> beans) {
        Log.d("111", "UpdateMusicAdapter: "+beans.size());
        if (mMusicAdapter != null) {
            mMusicAdapter = null;
            mMusicAdapter = new MusicAdapter(beans,this);
        }else mMusicAdapter = new MusicAdapter(beans,this);
        mMusicRv.setAdapter(mMusicAdapter);
        mMusicAdapter.notifyDataSetChanged();
        //setEventListener(beans);
    }
}
