package ru.a799000.alexander.countweighting.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 13.06.2017.
 */

public interface DetaiProductView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void refreshView();
}
