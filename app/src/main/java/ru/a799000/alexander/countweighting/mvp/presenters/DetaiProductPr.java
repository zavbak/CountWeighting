package ru.a799000.alexander.countweighting.mvp.presenters;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.a799000.alexander.countweighting.mvp.model.interactors.GetProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.Interactor;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.view.DetaiProductView;
import ru.a799000.alexander.countweighting.ui.fargments.ListProductFragment;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 13.06.2017.
 */

@InjectViewState
public class DetaiProductPr extends MvpPresenter<DetaiProductView> {


    String mId;
    Product mProduct;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if(mId != null){
            Interactor interactor = new GetProductByIdInteractor(Long.parseLong(mId));
            interactor.getObservable()
                    .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                    .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                    .subscribe(o -> {
                        Product product = (Product) o;
                        mProduct = Product.getBuilder()
                                .id(product.getId())
                                .code(product.getCode())
                                .name(product.getName())
                                .initBarcode(product.getInitBarcode())
                                .unit(product.getUnit())
                                .start(product.getStart())
                                .finish(product.getFinish())
                                .coef(product.getCoef())
                                .build();

                    },throwable -> {
                        Log.d("anit",((Throwable) throwable).getMessage());
                    });
        }else{
            mProduct = new Product();
        }

    }

    public boolean pressKey(int keyCode) {
        return false;
    }

    public void setID(String id) {
        mId = id;
    }

    public void onStart() {
        getViewState().refreshView();
    }
    public CharSequence getID() {
        return mProduct.getId()==0?"":Long.toString(mProduct.getId());
    }
    public CharSequence getName() {
        return mProduct.getName()==null?"":mProduct.getName();
    }

    public CharSequence getBarcode() {
        return mProduct.getInitBarcode()==null?"":mProduct.getInitBarcode();
    }
    public CharSequence getMessage() {
        return "message";
    }


    public CharSequence getStart() {
        return mProduct.getStart()==0?"":Integer.toString(mProduct.getStart());
    }

    public CharSequence getFinish() {
        return mProduct.getFinish()==0?"":Integer.toString(mProduct.getFinish());
    }

    public CharSequence getedCof() {
        return mProduct.getCoef()==0?"":Float.toString(mProduct.getCoef());
    }

    public CharSequence geteWeight() {
        return "Вес: 320.50 " + mProduct.getUnit()==null?"":mProduct.getUnit();
    }


    public CharSequence geteSites() {
        return "Мест 12";
    }
}
