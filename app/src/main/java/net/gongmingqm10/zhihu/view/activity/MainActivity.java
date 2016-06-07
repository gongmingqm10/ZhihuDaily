package net.gongmingqm10.zhihu.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerMainComponent;
import net.gongmingqm10.zhihu.dagger2.MainModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.data.SharedPreferenceMgr;
import net.gongmingqm10.zhihu.presenter.Presenter;
import net.gongmingqm10.zhihu.view.fragment.DesignersFragment;
import net.gongmingqm10.zhihu.view.fragment.ShotsFragment;
import net.gongmingqm10.zhihu.view.fragment.StoriesFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Inject
    LocationManager locationManager;

    @Inject
    SharedPreferenceMgr sharedPreferenceMgr;

    private Fragment designerFragment, shotsFragment, storiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        checkHomeMenu();
    }

    private void checkHomeMenu() {
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    protected void setupComponent(ZhihuAppComponent appComponent) {
        DaggerMainComponent.builder().zhihuAppComponent(appComponent)
                .mainModule(new MainModule())
                .build().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    Presenter getPresenter() {
        return null;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_shots:
                if (shotsFragment == null) {
                    shotsFragment = ShotsFragment.instantiate(this, ShotsFragment.class.getName());
                }
                startFragment(shotsFragment);
                break;
            case R.id.nav_designers:
                if (designerFragment == null) {
                    designerFragment = DesignersFragment.instantiate(this, DesignersFragment.class.getName());
                }
                startFragment(designerFragment);
                break;
            case R.id.nav_stories:
                if (storiesFragment == null) {
                    storiesFragment = StoriesFragment.instantiate(this, StoriesFragment.class.getName());
                }
                startFragment(storiesFragment);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }
}
