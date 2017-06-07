package ru.a799000.alexander.countweighting.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetAllProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.Interactor;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.mvp.view.ListProductView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Alex on 07.06.2017.
 */

@InjectViewState
public class ListProductPr extends MvpPresenter<ListProductView>{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showTvMessageView("Добавить нажмите (1) ");
    }

    private void refreshList(){
        GetAllProductInteractor interactor = new GetAllProductInteractor();
        interactor.getObservable()
                .subscribeOn(AndroidSchedulers.mainThread()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(
                        resultO -> {
                            getViewState().refreshList((RealmResults<Product>) resultO);
                        }
                        , throwable ->
                                getViewState().showTvMessageView(throwable.getMessage()));
    }

    public void clickItem(String id) {
        getViewState().showTvMessageView(id);
    }

    public void onStart() {
        refreshList();
    }
}
