package net.gongmingqm10.zhihu.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.gongmingqm10.zhihu.ZhihuApp;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.presenter.BaseView;
import net.gongmingqm10.zhihu.presenter.Presenter;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        setupComponent(((ZhihuApp) getApplication()).component());

        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    protected abstract void setupComponent(ZhihuAppComponent appComponent);

    protected abstract int getLayoutRes();

    @Override
    public void loading() {
        loading("");
    }

    @Override
    public void loading(String message) {
        loaded();

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage(message);
        loadingDialog.setIndeterminate(false);
        loadingDialog.setCanceledOnTouchOutside(false);

        if (!isFinishing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void loaded() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().stop();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    abstract Presenter getPresenter();
}
