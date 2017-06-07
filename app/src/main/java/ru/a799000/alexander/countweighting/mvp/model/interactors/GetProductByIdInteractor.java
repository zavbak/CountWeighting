package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class GetProductByIdInteractor implements Interactor {

    @Inject
    Realm mRealm;
    long mId;


    /**
     * get Product by ID
     */
    public GetProductByIdInteractor(long id) {
        App.getAppComponent().injectGetProductByIdInteractor(this);
        mId = id;
    }


    @Override
    public Observable<Product> getObservable() {
        try {
            Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, mId).findFirst();
            return Observable.just(product);

        } catch (Exception e) {
            e.printStackTrace();
            return Observable.error(e);

        }
    }


}
