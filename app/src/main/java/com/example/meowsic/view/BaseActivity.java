package com.example.meowsic.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meowsic.presenter.BasePresenter;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public abstract class BaseActivity<T extends BasePresenter,V extends BaseView> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V)this,this);

        init();
        registerSDk();
    }

    protected abstract T createPresenter();

    protected void init() {}
    protected void registerSDk() {}

    @Override
    protected void onStart() {
        super.onStart();
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

        //解绑
        presenter.detachView();
        presenter = null;
    }
}
