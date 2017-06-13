package ru.a799000.alexander.countweighting.ui.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.presenters.DetaiProductPr;
import ru.a799000.alexander.countweighting.mvp.view.DetaiProductView;

/**
 * Created by Alex on 13.06.2017.
 */

public class DetaiProductFragment extends MvpAppCompatFragment implements DetaiProductView {

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

    public static final String TAG = "ListProductFragment";
    public static final String ID = "id";




    public static DetaiProductFragment getInstance(String id) {
        DetaiProductFragment fragment = new DetaiProductFragment();
        Bundle args = new Bundle();
        args.putString(ID,id);
        fragment.setArguments(args);
        return fragment;
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
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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
}
