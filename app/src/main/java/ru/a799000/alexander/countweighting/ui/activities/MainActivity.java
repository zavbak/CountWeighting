package ru.a799000.alexander.countweighting.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.presenters.MainPr;
import ru.a799000.alexander.countweighting.mvp.view.MainView;
import ru.a799000.alexander.countweighting.ui.fargments.ListProductFragment;
import ru.a799000.alexander.countweighting.ui.fargments.MainFragment;
import ru.a799000.alexander.countweighting.ui.fargments.TestsRealmFragment;

public class MainActivity extends MvpAppCompatActivity implements MainView,MainFragment.CallBackMainActivities{

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

        getFragmentManager().findFragmentById(R.id.fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, MainFragment.getInstance(),MainFragment.TAG)
                .commit();
    }

    @Override
    public void setFragmentRealmTests() {
        getFragmentManager().findFragmentById(R.id.fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, TestsRealmFragment.getInstance(),TestsRealmFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setFragmentListProduct() {
        getFragmentManager().findFragmentById(R.id.fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, ListProductFragment.getInstance(),ListProductFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startScreenRealmTests() {
        mPresenter.commandStartScreenRealmTests();
    }

    @Override
    public void startScreenListProduct() {
        mPresenter.commandStartScreenListProduct();
    }

}
