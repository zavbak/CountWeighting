package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmModel;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmHelper;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class SaveProductInteractor implements Interactor {

    @Inject
    Realm mRealm;
    @Inject
    RealmHelper mRealmHelper;

    Product mProduct;

    public SaveProductInteractor(Product product) {
        mProduct = product;
        App.getAppComponent().injectSaveProductInteractor(this);
    }


    @Override
    public Observable<Product> getObservable() {

        Product realmProduct;
        try {
            mRealm.beginTransaction();

            if (mProduct.getId() == 0) {
                realmProduct = mRealm.createObject(Product.class,mRealmHelper.getNextId(Product.class));
            } else {
                realmProduct = mRealm.where(Product.class).equalTo(RealmTable.ID, mProduct.getId()).findFirst();
            }

            realmProduct.setCode(mProduct.getCode());
            realmProduct.setName(mProduct.getName());
            realmProduct.setUnit(mProduct.getUnit());
            realmProduct.setInitBarcode(mProduct.getInitBarcode());
            realmProduct.setStart(mProduct.getStart());
            realmProduct.setFinish(mProduct.getFinish());
            realmProduct.setCoef(mProduct.getCoef());

            mRealm.commitTransaction();

            return Observable.just(realmProduct);
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
