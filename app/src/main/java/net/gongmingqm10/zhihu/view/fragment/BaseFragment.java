package net.gongmingqm10.zhihu.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.gongmingqm10.zhihu.ZhihuApp;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.presenter.BaseView;
import net.gongmingqm10.zhihu.view.activity.BaseActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity baseActivity;

    // Fucking! onAttach(Context) is new in API23, it will not called below API23, while onAttach(Activity) is depracted.
    private void attachActivity() {
        try {
            if (getActivity() instanceof BaseActivity) {
                baseActivity = (BaseActivity) getActivity();
            } else {
                throw new ClassCastException("Fragment should be started by the BaseActivity");
            }
        } catch (ClassCastException ignored) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), null);
        ButterKnife.bind(this, view);
        setupComponent(((ZhihuApp) getActivity().getApplication()).component());
        attachActivity();
        return view;
    }

    @Override
    public void loading() {
        baseActivity.loading();
    }

    @Override
    public void loading(String message) {
        baseActivity.loading(message);
    }

    @Override
    public void loaded() {
        baseActivity.loaded();
    }

    @Override
    public void loading(int resId) {
        loading(getString(resId));
    }

    protected abstract int getLayoutRes();

    @Override
    public void showToast(String message) {
        baseActivity.showToast(message);
    }

    @Override
    public void showToast(int resId) {
        baseActivity.showToast(resId);
    }

    protected abstract void setupComponent(ZhihuAppComponent appComponent);
}
