package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class GetAllProductInteractor implements Interactor {

    @Inject
    Realm mRealm;


    /**
     * get All
     */
    public GetAllProductInteractor() {
        App.getAppComponent().injectGetAllProductInteractor(this);
    }

    @Override
    public Observable<RealmResults<Product>> getObservable() {
        try {

            RealmQuery<Product> query = mRealm.where(Product.class);
            RealmResults<Product> results = query.findAll();
            return Observable.just(results);

        } catch (Exception e) {
            e.printStackTrace();
            return Observable.error(e);

        }
    }


}
