package ru.a799000.alexander.countweighting.mvp.model.interactors;


import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;

public class DellAllInteractor implements Interactor{

    @Inject
    Realm mRealm;


    public DellAllInteractor() {
        App.getAppComponent().injectDellAllInteractor(this);
    }

    @Override
    public Observable getObservable() {

        try {
            mRealm.beginTransaction();

            RealmQuery<Product> query = mRealm.where(Product.class);
            RealmResults<Product> results = query.findAll();
            results.deleteAllFromRealm();


            RealmQuery<Barcode> queryBarcode = mRealm.where(Barcode.class);
            RealmResults<Barcode> resultsBarcode = queryBarcode.findAll();
            resultsBarcode.deleteAllFromRealm();

            mRealm.commitTransaction();

            return Observable.empty();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                mRealm.cancelTransaction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return Observable.error(e);
        }

    }
}
