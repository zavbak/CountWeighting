package ru.a799000.alexander.countweighting.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.a799000.alexander.countweighting.mvp.view.MainView;

/**
 * Created by user on 07.06.2017.
 */
@InjectViewState
public class MainPr extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setFragmentMainFragmentView();
    }

    public void clickFab() {
        getViewState().showSnackbarView("click FAB!");
    }

    public void commandStartScreenRealmTests() {
        getViewState().setFragmentRealmTests();
    }

    public void commandStartScreenListProduct() {
        getViewState().setFragmentListProduct();
    }
}
