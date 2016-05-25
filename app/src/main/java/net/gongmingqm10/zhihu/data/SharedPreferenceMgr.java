package net.gongmingqm10.zhihu.data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceMgr {

    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREFERENCE_NAME = "zhihu";

    public SharedPreferenceMgr(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }


}
