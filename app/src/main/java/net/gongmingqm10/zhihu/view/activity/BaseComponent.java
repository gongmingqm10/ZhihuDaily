package net.gongmingqm10.zhihu.view.activity;

import android.app.Activity;

import net.gongmingqm10.zhihu.ZhihuAppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ZhihuAppComponent.class, modules = ActivityModule.class)
public interface BaseComponent {
    Activity activity();
}
