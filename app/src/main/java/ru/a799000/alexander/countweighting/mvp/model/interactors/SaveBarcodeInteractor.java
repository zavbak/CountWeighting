package ru.a799000.alexander.countweighting.mvp.model.interactors;

import javax.inject.Inject;

import io.realm.Realm;
import ru.a799000.alexander.countweighting.app.App;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;
import ru.a799000.alexander.countweighting.servises.realm.RealmHelper;
import ru.a799000.alexander.countweighting.servises.realm.RealmTable;
import rx.Observable;


public class SaveBarcodeInteractor implements Interactor {

    @Inject
    Realm mRealm;
    long mIdProduct;
    Barcode mBarcode;
    @Inject
    RealmHelper mRealmHelper;


    /**
     * get Product by ID
     */
    public SaveBarcodeInteractor(long idProduct,Barcode barcode) {
        App.getAppComponent().injectSaveBarcodeInteractor(this);
        mIdProduct = idProduct;
        mBarcode   = barcode;
    }


    @Override
    public Observable<Barcode> getObservable() {

        Barcode barcodeRealm;

        try {
            mRealm.beginTransaction();

            Product realmProduct = mRealm.where(Product.class).equalTo(RealmTable.ID, mIdProduct).findFirst();
            if(realmProduct == null){
                return Observable.error(new Exception("Товар не найден"));
            }

            Boolean isNew = false;

            if (mBarcode.getId() == 0) {
                barcodeRealm = mRealm.createObject(Barcode.class,mRealmHelper.getNextId(Barcode.class));
                isNew = true;
            } else {
                barcodeRealm = mRealm.where(Barcode.class).equalTo(RealmTable.ID, mBarcode.getId()).findFirst();
            }

            barcodeRealm.setBarcode(mBarcode.getBarcode());
            barcodeRealm.setWeight(mBarcode.getWeight());
            barcodeRealm.setPlaces(mBarcode.getPlaces());

            if(isNew) realmProduct.getBarcodes().add(barcodeRealm);

            mRealm.commitTransaction();

            return Observable.just(barcodeRealm);
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
