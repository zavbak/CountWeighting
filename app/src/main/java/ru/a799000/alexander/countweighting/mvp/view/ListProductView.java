package ru.a799000.alexander.countweighting.mvp.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;

/**
 * Created by Alex on 07.06.2017.
 */

public interface ListProductView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTvMessageView(@NonNull CharSequence text);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void refreshList(RealmResults<Product> list);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startDetailProduct(String id);

}
