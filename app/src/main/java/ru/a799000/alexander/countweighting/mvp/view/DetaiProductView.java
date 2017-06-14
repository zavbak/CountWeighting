package ru.a799000.alexander.countweighting.mvp.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 13.06.2017.
 */

public interface DetaiProductView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void refreshView();

    @StateStrategyType(SkipStrategy.class)
    void finishView();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void refreshMessage();
}
