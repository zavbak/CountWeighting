package ru.a799000.alexander.countweighting.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.RealmConfiguration;

import ru.a799000.alexander.countweighting.mvp.model.interactors.DellAllInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellBarcodeInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.DellProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetAllProductInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.GetProductByIdInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveBarcodeInteractor;
import ru.a799000.alexander.countweighting.mvp.model.interactors.SaveProductInteractor;
import ru.a799000.alexander.countweighting.servises.realm.RealmHelper;


@Singleton
@Component(modules={AppModule.class,RealmModule.class})
public interface AppComponent {
    Context getContext();
    RealmConfiguration getRealmConfiguration();
    RealmHelper getRealmHelper();

    void injectSaveProductInteractor(SaveProductInteractor saveProductInteractor);
    void injectGetAllProductInteractor(GetAllProductInteractor getAllProductInteractor);
    void injectGetProductByIdInteractor(GetProductByIdInteractor getProductByIdInteractor);
    void injectDellProductByIdInteractor(DellProductByIdInteractor dellProductByIdInteractor);
    void injectSaveBarcodeInteractor(SaveBarcodeInteractor saveBarcodeInteractor);

    void injectDellBarcodeInteractor(DellBarcodeInteractor dellBarcodeInteractor);

    void injectDellAllInteractor(DellAllInteractor dellAllInteractor);
}
