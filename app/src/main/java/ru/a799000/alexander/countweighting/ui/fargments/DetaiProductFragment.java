package ru.a799000.alexander.countweighting.ui.fargments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.model.interactors.BarcodeSeporatorIterator;
import ru.a799000.alexander.countweighting.mvp.presenters.DetaiProductPr;
import ru.a799000.alexander.countweighting.mvp.view.DetaiProductView;
import ru.a799000.alexander.countweighting.servises.barcode.TakeBarcode;
import ru.a799000.alexander.countweighting.ui.activities.BarcodeSet;
import ru.a799000.alexander.countweighting.ui.activities.CallBaskMainActivities;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Alex on 13.06.2017.
 */

public class DetaiProductFragment extends MvpAppCompatFragment implements DetaiProductView,TakeBarcode {

    @InjectPresenter
    DetaiProductPr mPresenter;

    @BindView(R.id.tvID)
    TextView tvID;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvSites)
    TextView tvSites;
    @BindView(R.id.tvMessage)
    TextView tvMessage;

    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edBarcode)
    EditText edBarcode;

    @BindView(R.id.edStart)
    EditText edStart;

    @BindView(R.id.edFinish)
    EditText edFinish;
    @BindView(R.id.edCof)
    EditText edCof;

    public static final String TAG = "DetaiProductFragment";
    public static final String ID = "id";
    private CompositeSubscription mCompositeSubscription;




    public static DetaiProductFragment getInstance(String id) {
        DetaiProductFragment fragment = new DetaiProductFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((BarcodeSet) getActivity()).unregisterReceiver();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((BarcodeSet) getActivity()).registerBarcodeReceiver(TAG);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        Bundle args = getArguments();
        mPresenter.setID(args.getString(ID));
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(RxTextView.textChanges(edName)
                .skip(2)
                .subscribe(this::changeName));

        mCompositeSubscription.add(RxTextView.textChanges(edBarcode)
                .skip(2)
                .filter(charSequence -> (!charSequence.toString().equals(mPresenter.getBarcode().toString())))
                .subscribe(this::changeBarcode));

        mCompositeSubscription.add(RxTextView.textChanges(edStart)
                .skip(2)
                .subscribe(this::changeStart));

        mCompositeSubscription.add(RxTextView.textChanges(edFinish)
                .skip(2)
                .subscribe(this::changeFinish));

        mCompositeSubscription.add(RxTextView.textChanges(edCof)
                .skip(2)
                .subscribe(this::changeCoef));

    }

    void changeCoef(CharSequence coef) {
        mPresenter.changeCoef(coef);
    }

    void changeFinish(CharSequence finish) {
        mPresenter.changeFinish(finish);
    }

    void changeStart(CharSequence start) {
        mPresenter.changeStart(start);
    }

    void changeName(CharSequence name) {
        mPresenter.changeName(name);
    }

    void changeBarcode(CharSequence barcode) {
        mPresenter.changeBarcode(barcode);
    }

    @Override
    public void refreshView() {
        tvID.setText(mPresenter.getID());
        edName.setText(mPresenter.getName());
        edBarcode.setText(mPresenter.getBarcode());
        tvMessage.setText(mPresenter.getMessage());
        edStart.setText(mPresenter.getStart());
        edFinish.setText(mPresenter.getFinish());
        edCof.setText(mPresenter.getedCof());
        tvWeight.setText(mPresenter.geteWeight());
        tvSites.setText(mPresenter.geteSites());
    }




    @Override
    public void finishView() {
        ((CallBaskMainActivities) getActivity()).popBackStackImmediate();
    }

    @Override
    public void refreshMessage() {
        tvMessage.setText(mPresenter.getMessage());
        edBarcode.setText(mPresenter.getBarcode());
    }

    @OnClick(R.id.btSave)
    public void OnClickBtSave() {
        mPresenter.OnClickSave();
    }

    @OnClick(R.id.btCounting)
    public void OnClickBtCancel() {
        mPresenter.OnClickBtCounting();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void takeBarcode(String barcode) {
        mPresenter.takeBarcode(barcode);
    }
}
