package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by ahmet on 2/4/2018.
 */

public class ResultITabsViewModel extends ViewModel {
    private ObservableField<Integer> tabName = new ObservableField<>(0);
    private ObservableBoolean selected = new ObservableBoolean(false);

    public ResultITabsViewModel(int tabName) {
        this.tabName.set(tabName);
    }


    public ObservableField<Integer> getTabName() {
        return tabName;
    }

    public ObservableBoolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public int getTabIndex(){
        return tabName.get() - 1;
    }
    public void performClick(View v){
        if (selected.get()) return;
        selected.set(true);
    }
}
