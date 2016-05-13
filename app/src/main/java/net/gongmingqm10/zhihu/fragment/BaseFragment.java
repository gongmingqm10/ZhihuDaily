package net.gongmingqm10.zhihu.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.gongmingqm10.zhihu.presenter.BaseView;
import net.gongmingqm10.zhihu.view.activity.BaseActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof BaseActivity) {
                baseActivity = (BaseActivity) context;
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

    protected abstract int getLayoutRes();
}
