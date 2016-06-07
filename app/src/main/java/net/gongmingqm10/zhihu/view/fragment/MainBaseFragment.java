package net.gongmingqm10.zhihu.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MainBaseFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getTitleResId());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract int getTitleResId();
}
