package ru.a799000.alexander.countweighting.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.presenters.MainPr;
import ru.a799000.alexander.countweighting.mvp.view.DetaiProductView;
import ru.a799000.alexander.countweighting.mvp.view.MainView;
import ru.a799000.alexander.countweighting.ui.fargments.DetaiProductFragment;
import ru.a799000.alexander.countweighting.ui.fargments.ListProductFragment;
import ru.a799000.alexander.countweighting.ui.fargments.MainFragment;
import ru.a799000.alexander.countweighting.ui.fargments.TestsRealmFragment;

public class MainActivity extends MvpAppCompatActivity implements MainView,CallBaskMainActivities {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @InjectPresenter
    MainPr mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void onClickFab(){
        mPresenter.clickFab();
    }
    @Override
    public void showSnackbarView(@NonNull CharSequence mess){
        Snackbar.make(fab, mess, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void setFragmentMainFragmentView() {


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, MainFragment.getInstance(),MainFragment.TAG)
                .commit();
    }

    @Override
    public void setFragmentRealmTests() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, TestsRealmFragment.getInstance(),TestsRealmFragment.TAG)
                .addToBackStack("myStack")
                .commit();
    }



    @Override
    public void setFragmentListProduct() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, ListProductFragment.getInstance(),ListProductFragment.TAG)
                .addToBackStack("myStack")
                .commit();
    }

    @Override
    public void popBackStackImmediateView() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void startScreenRealmTests() {
        mPresenter.commandStartScreenRealmTests();
    }

    @Override
    public void startScreenListProduct() {
        mPresenter.commandStartScreenListProduct();
    }

    @Override
    public void startDetailFragment(String id) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, DetaiProductFragment.getInstance(id),DetaiProductFragment.TAG)
                .addToBackStack("myStack")
                .commit();
    }

    @Override
    public void popBackStackImmediate() {
        mPresenter.popBackStackImmediate();
    }

}
