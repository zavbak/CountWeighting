package ru.a799000.alexander.countweighting.ui.fargments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.presenters.TestsRealmPr;
import ru.a799000.alexander.countweighting.mvp.view.TestsRealmView;

/**
 * Created by user on 07.06.2017.
 */

public class TestsRealmFragment extends MvpAppCompatFragment implements TestsRealmView {

    public static final String TAG = "TestsRealmFragment";

    @InjectPresenter
    TestsRealmPr mPresenter;
    @BindView(R.id.tvMessage)
    TextView tvMessage;


    public static TestsRealmFragment getInstance() {
        TestsRealmFragment fragment = new TestsRealmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_tests_realm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btShow)
    void onClickBtShow(){
        mPresenter.onCliskBtShow();
    }

    @OnClick(R.id.btAddProdact)
    void onClickBtAddProduct(){
        mPresenter.onCliskBtAddProduct();
    }

    @OnClick(R.id.btDellProdact)
    void onClickBtDellProduct(){
        mPresenter.onClickBtDellProdact();
    }


    @OnClick(R.id.btGetProductById)
    void onClickBtGetProductById(){
        mPresenter.onClickBtbtGetProductById();
    }

    @OnClick(R.id.btAddBarcode)
    void onClickBtbtAddBarcode(){
        mPresenter.onClickBtAddBarcode();
    }

    @OnClick(R.id.btDellBarcode)
    void onClickBtDellBarcode(){
        mPresenter.onClickBtDellBarcode();
    }

    @OnClick(R.id.btDellAll)
    void onClickBtDellAll(){
        mPresenter.onClickBtDellAll();
    }


    @Override
    public void showTvMessageView(@NonNull CharSequence text) {
        tvMessage.setText(text);
    }
}
