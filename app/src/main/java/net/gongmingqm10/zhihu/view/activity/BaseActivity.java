package net.gongmingqm10.zhihu.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.gongmingqm10.zhihu.presenter.BaseView;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

    }

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
}
