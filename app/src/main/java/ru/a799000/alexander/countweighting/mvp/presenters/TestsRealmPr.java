package ru.a799000.alexander.countweighting.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetAllProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.view.TestsRealmView;
import rx.android.schedulers.AndroidSchedulers;


@InjectViewState
public class TestsRealmPr extends MvpPresenter<TestsRealmView> {
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
                                str = str + "\n" + product.toString() + "\n";
                            }
                            getViewState().showTvMessageView(str);

                        }
                        , throwable ->
                                getViewState().showTvMessageView(throwable.getMessage()));


    }

    public void onCliskBtAddProduct() {

        Product product = Product.getBuilder()
                .code("10")
                .name("Мясо")
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
                        ,throwable -> getViewState().showTvMessageView(throwable.getMessage()));

    }

    public void onClickBtbtGetProductById() {

        GetProductByIdInteractor interactor = new GetProductByIdInteractor(2);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(prod -> getViewState().showTvMessageView("id = 2: " + prod.toString())
                        ,throwable -> getViewState().showTvMessageView(throwable.getMessage()));

    }

    public void onClickBtDellProdact() {
        DellProductByIdInteractor interactor = new DellProductByIdInteractor(4);
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(empty -> getViewState().showTvMessageView("dell id = 2: " + empty),throwable -> {
                    getViewState().showTvMessageView(throwable.toString());
                });

    }
}
