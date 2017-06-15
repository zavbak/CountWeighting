package ru.a799000.alexander.countweighting.ui.fargments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import ru.a799000.alexander.countweighting.ui.activities.CallBaskMainActivities;
import ru.a799000.alexander.countweighting.ui.adapters.AdapretProdact;
import ru.a799000.alexander.countweighting.ui.adapters.AdapterListProduct;


public class ListProductFragment extends MvpAppCompatFragment implements ListProductView {

    public static final String TAG = "ListProductFragment";



    @InjectPresenter
    ListProductPr mPresenter;

    //@BindView(R.id.tvMessage)
    //TextView tvMessage;

    //@BindView(R.id.recycler_view)
    //RecyclerView mRecyclerView;

    @BindView(R.id.lview)
    ListView mListView;


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
        View view = inflater.inflate(R.layout.list_product_fragment_new, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction()== KeyEvent.ACTION_DOWN){
                    return mPresenter.pressKey(keyCode);
                }
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }


    @Override
    public void showTvMessageView(@NonNull CharSequence text) {
        //tvMessage.setText(text);
    }

    @Override
    public void refreshList(RealmResults<Product> list) {
        //mRecyclerView.setAdapter(new AdapterListProduct(list, id -> mPresenter.clickItem(id)));
        AdapretProdact adapter = new AdapretProdact(list);
        mListView.setAdapter(adapter);
        mListView.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange(View v, boolean hasFocus)
            {
                //v.setBackgroundColor(hasFocus ? Color.GRAY : Color.BLACK);
            }
        });
        mListView.requestFocus();
        mListView.requestFocus(5);
        mListView.setClickable(true);

    }

    @Override
    public void startDetailProduct(String id) {
        ((CallBaskMainActivities) getActivity()).startDetailFragment(id);
    }
}
