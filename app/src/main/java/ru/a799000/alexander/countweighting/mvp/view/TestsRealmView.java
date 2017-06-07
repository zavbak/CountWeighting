package ru.a799000.alexander.countweighting.mvp.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by user on 07.06.2017.
 */

public interface TestsRealmView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showTvMessageView(@NonNull CharSequence text);
}
