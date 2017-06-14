package ru.a799000.alexander.countweighting.mvp.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by user on 07.06.2017.
 */

public interface MainView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showSnackbarView(@NonNull CharSequence mess);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void setFragmentMainFragmentView();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void setFragmentRealmTests();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void setFragmentListProduct();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void popBackStackImmediateView();
}
