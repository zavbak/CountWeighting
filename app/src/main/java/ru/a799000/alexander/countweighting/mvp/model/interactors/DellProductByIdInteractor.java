package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class DellProductByIdInteractor implements Interactor {

    @Inject
    Realm mRealm;
    long mId;


    /**
     * get Product by ID
     */
    public DellProductByIdInteractor(long id) {
        App.getAppComponent().injectDellProductByIdInteractor(this);
        mId = id;
    }


    @Override
    public Observable getObservable() {
        try {
            mRealm.beginTransaction();
            Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, mId).findFirst();
            product.deleteFromRealm();
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
