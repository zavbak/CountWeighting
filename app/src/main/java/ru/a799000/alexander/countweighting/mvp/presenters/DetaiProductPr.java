package ru.a799000.alexander.countweighting.mvp.presenters;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.a799000.alexander.countweighting.mvp.model.interactors.BarcodeSeporatorIterator;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.view.DetaiProductView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 13.06.2017.
 */

@InjectViewState
public class DetaiProductPr extends MvpPresenter<DetaiProductView> {


    String mId;
    Product mProduct;
    BarcodeSeporatorIterator mSeporatorIterator;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if(mId != null){
            GetProductByIdInteractor interactor = new GetProductByIdInteractor(Long.parseLong(mId));
            interactor.getObservable()
                    .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                    .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                    .subscribe(this::initProduct);
        }else{
            mProduct = new Product();
        }



    }


    void initProduct(Product product){
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

        refreshMessage();
        getViewState().refreshView();

    }

    void refreshMessage(){
        mSeporatorIterator = new BarcodeSeporatorIterator(mProduct.getInitBarcode(),mProduct);
        getViewState().refreshMessage();
    }



    public void setID(String id) {
        mId = id;
    }


    public CharSequence getID() {
        return mProduct.getId()==0?"( )":Long.toString(mProduct.getId());
    }
    public CharSequence getName() {
        return mProduct.getName()==null?"":mProduct.getName();
    }

    public CharSequence getBarcode() {
        if(mSeporatorIterator.getError() ){
            return mProduct.getInitBarcode();
        }

        final SpannableStringBuilder text = new SpannableStringBuilder(mProduct.getInitBarcode());
        final ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));
        text.setSpan(style, mProduct.getStart()-1, mProduct.getFinish(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return text;
    }
    public CharSequence getMessage() {
        if(mSeporatorIterator.getError()){
            return mSeporatorIterator.getMessError() + " "
                    + " Cимволов: " + mSeporatorIterator.getCountSimbol();
        }

        return "Вес: " + Float.toString(mSeporatorIterator.getWeight())
                 + " Cимволов: " + mSeporatorIterator.getCountSimbol();

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

    public void changeName(CharSequence name) {
        mProduct.setName(name.toString());
    }

    public void changeBarcode(CharSequence barcode) {
        mProduct.setInitBarcode(barcode.toString());
        refreshMessage();
    }

    public void changeStart(CharSequence start) {
        int iStars = 0;
        try {
            iStars = Integer.parseInt(start.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            iStars = 0;
        }

        mProduct.setStart(iStars);
        refreshMessage();
    }

    public void changeFinish(CharSequence finish) {

        int iFinish = 0;
        try {
            iFinish = Integer.parseInt(finish.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            iFinish = 0;
        }

        mProduct.setFinish(iFinish);
        refreshMessage();
    }

    public void changeCoef(CharSequence coef) {
        float iCoef = 0;
        try {
            iCoef = Float.parseFloat(coef.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            iCoef = 0;
        }
        mProduct.setCoef(iCoef);
        refreshMessage();
    }

    public void OnClickBtCounting() {
        getViewState().finishView();
    }

    public void OnClickSave() {
        SaveProductInteractor interactor = new SaveProductInteractor(mProduct);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(this::initProduct);
        getViewState().finishView();
    }

    public void takeBarcode(String barcode) {
        mProduct.setInitBarcode(barcode);
        getViewState().refreshView();
        getViewState().refreshMessage();
    }
}
