package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.omka.mackhaton.common.Functions;
import com.omka.mackhaton.entity.response.Record;
import com.omka.mackhaton.task.SearchTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmet on 2/4/2018
 */

public class ShowResultsViewModel extends ViewModel {
    private ObservableField<List<ResultItemViewModel>> items = new ObservableField<>(new ArrayList<>());
    private ObservableField<List<ResultITabsViewModel>> itemTabs = new ObservableField<>(new ArrayList<>());

    public ShowResultsViewModel() {
        if (!SearchTask.getInstance().recordsIsEmpty()) {
            fillItemTabs();
            fillItems(0);
        }
    }

    private void fillItemTabs() {
        int lastIndex = SearchTask.getInstance().pageCount();
        List<ResultITabsViewModel> resultITabsViewModels = new ArrayList<>(lastIndex);
        for (int j = 1; j <= lastIndex; j++) {
            ResultITabsViewModel resultITabsViewModel = Functions.resultTabVMConverter().apply(j);
            resultITabsViewModel.getSelected().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {
                    if (resultITabsViewModel.getSelected().get()) {
                        for (ResultITabsViewModel itemTab : itemTabs.get()) {
                            if (itemTab.equals(resultITabsViewModel)) {
                                fillItems(itemTab.getTabIndex());
                            } else {
                                itemTab.getSelected().set(false);
                            }
                        }
                    }
                }
            });
            if (j==1) resultITabsViewModel.setSelected(true);
            resultITabsViewModels.add(resultITabsViewModel);
        }
        itemTabs.set(resultITabsViewModels);
    }

    private void fillItems(int i) {
        List<Record> load = SearchTask.getInstance().load(i);
        List<ResultItemViewModel> resultItemViewModels = new ArrayList<>();
        for (Record record : load) {

            resultItemViewModels.add(Functions.resultItemVMConverter().apply(record));
        }
        items.set(resultItemViewModels);
    }

    public ObservableField<List<ResultItemViewModel>> getItems() {
        return items;
    }

    public ObservableField<List<ResultITabsViewModel>> getItemTabs() {
        return itemTabs;
    }
}
