package net.gongmingqm10.zhihu.view.fragment;

import android.os.Bundle;
import android.view.View;

import net.gongmingqm10.zhihu.R;

public class ShotsFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.shots);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shots;
    }
}
