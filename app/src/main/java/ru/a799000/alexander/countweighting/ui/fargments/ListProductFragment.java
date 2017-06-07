package ru.a799000.alexander.countweighting.ui.fargments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.presenters.ListProductPr;
import ru.a799000.alexander.countweighting.mvp.view.ListProductView;
import ru.a799000.alexander.countweighting.ui.adapters.AdapterListProduct;


public class ListProductFragment extends MvpAppCompatFragment implements ListProductView {

    public static final String TAG = "ListProductFragment";

    @InjectPresenter
    ListProductPr mPresenter;

    @BindView(R.id.tvMessage)
    TextView tvMessage;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static ListProductFragment getInstance() {
        ListProductFragment fragment = new ListProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_product_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void showTvMessageView(@NonNull CharSequence text) {
        tvMessage.setText(text);
    }

    @Override
    public void refreshList(RealmResults<Product> list) {
        mRecyclerView.setAdapter(new AdapterListProduct(list, id -> mPresenter.clickItem(id)));
    }
}
