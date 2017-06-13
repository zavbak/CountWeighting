package ru.a799000.alexander.countweighting.ui.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;

import butterknife.OnClick;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.presenters.MainFragmentPr;
import ru.a799000.alexander.countweighting.mvp.view.MainFragmentView;
import ru.a799000.alexander.countweighting.ui.activities.CallBaskMainActivities;

/**
 * Created by user on 07.06.2017.
 */

public class MainFragment extends MvpAppCompatFragment implements MainFragmentView {

    public static final String TAG = "MainFragment";

    @InjectPresenter
    MainFragmentPr mPresenter;



    public static MainFragment getInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btStartTestsRealm)
    void onCliskBtStartTestRealm() {
        ((CallBaskMainActivities) getActivity()).startScreenRealmTests();
    }


    @OnClick(R.id.btListProduct)
    void onCliskBtListProduct() {
        ((CallBaskMainActivities) getActivity()).startScreenListProduct();
    }

}
