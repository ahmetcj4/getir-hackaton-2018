package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * Created by ahmet on 2/4/2018.
 */

public class ResultITabsViewModel extends ViewModel {
    private ObservableField<String> tabName = new ObservableField<>();

    public ResultITabsViewModel(Integer tabName) {
        this.tabName.set(String.valueOf(tabName)); ;
    }


    public ObservableField<String> getTabName() {
        return tabName;
    }
}
