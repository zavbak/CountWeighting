package ru.a799000.alexander.countweighting.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellAllInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellBarcodeInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetAllProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.Interactor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveBarcodeInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.view.TestsRealmView;
import rx.android.schedulers.AndroidSchedulers;


@InjectViewState
public class TestsRealmPr extends MvpPresenter<TestsRealmView> {

    private String printBarcodes(RealmList<Barcode> barcodes){
        String str = "\nBarcodes(" + barcodes.size() +  "): \n";
        for(Barcode barcode:barcodes){
            str = str + "   " + barcode.toString() + "\n";
        }

        return str;
    }


    public void onCliskBtShow() {
        GetAllProductInteractor interactor = new GetAllProductInteractor();
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(
                        resultO -> {
                            RealmResults<Product> results = (RealmResults<Product>) resultO;
                            String str = "Result: \n";
                            for (Product product : results) {
                                str = str + "\n" + product.toString() + " "  + printBarcodes(product.getBarcodes()) + "\n" ;
                            }
                            getViewState().showTvMessageView(str);

                        }
                        , throwable ->
                                getViewState().showTvMessageView(throwable.getMessage()));


    }

    public void onCliskBtAddProduct() {

        Product product = Product.getBuilder()
                .code("10")
                .name("Мясо1")
                .initBarcode("566546546")
                .start(10)
                .finish(13)
                .coef((float) 0.1)
                .unit("кг")
                .build();


        SaveProductInteractor interactor = new SaveProductInteractor(product);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(prod -> getViewState().showTvMessageView(prod.toString())
                        , throwable -> getViewState().showTvMessageView(throwable.getMessage()));

    }

    public void onClickBtbtGetProductById() {

        GetProductByIdInteractor interactor = new GetProductByIdInteractor(2);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(prod -> getViewState().showTvMessageView("id = 2: " + prod.toString())
                        , throwable -> getViewState().showTvMessageView(throwable.getMessage()));

    }

    public void onClickBtDellProdact() {
        DellProductByIdInteractor interactor = new DellProductByIdInteractor(4);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(empty -> getViewState().showTvMessageView("dell id = 2: " + empty), throwable -> {
                    getViewState().showTvMessageView(throwable.toString());
                });

    }

    public void onClickBtAddBarcode() {
        Barcode barcode = Barcode.getBuilder()
                .setBarcode("77777")
                .setPlaces(1)
                .setWeight((float) 2.5)
                .build();
        //barcode.setId(2);

        SaveBarcodeInteractor interactor = new SaveBarcodeInteractor(6, barcode);
        interactor.getObservable().
                subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(barc -> getViewState().showTvMessageView(barc.toString())
                        , throwable -> getViewState().showTvMessageView(throwable.getMessage()));

    }

    public void onClickBtDellBarcode() {
        DellBarcodeInteractor interactor = new DellBarcodeInteractor(5);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(empty -> getViewState().showTvMessageView("dell id = 3: " + empty), throwable -> {
                    getViewState().showTvMessageView(throwable.toString());
                });

    }

    public void onClickBtDellAll() {
        Interactor interactor = new  DellAllInteractor();
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(empty -> getViewState().showTvMessageView("dell All: " + empty), throwable -> {
                    getViewState().showTvMessageView(throwable.toString());
                });

    }
}
