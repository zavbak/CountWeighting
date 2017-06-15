package ru.a799000.alexander.countweighting.ui.activities;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 14.06.2017.
 */

public interface BarcodeSet {
    void registerBarcodeReceiver(String fragmentTag);
    void unregisterReceiver();
}
