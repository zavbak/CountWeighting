package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmHelper;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class DellBarcodeInteractor implements Interactor {

    @Inject
    Realm mRealm;
    long mIdBarcode;


    /**
     * get Product by ID
     */
    public DellBarcodeInteractor(long idBarcode) {
        App.getAppComponent().injectDellBarcodeInteractor(this);
        mIdBarcode = idBarcode;
    }


    @Override
    public Observable getObservable() {


        try {
            mRealm.beginTransaction();

            Barcode barcodeRealm = mRealm.where(Barcode.class).equalTo(RealmTable.ID, mIdBarcode).findFirst();
            barcodeRealm.deleteFromRealm();

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
