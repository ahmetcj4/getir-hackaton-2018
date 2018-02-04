package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.omka.mackhaton.common.Functions;
import com.omka.mackhaton.entity.response.Record;
import com.omka.mackhaton.task.SearchTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmet on 2/4/2018.
 */

public class ShowResultsViewModel extends ViewModel {
    ObservableField<List<ResultItemViewModel>> items = new ObservableField<>(new ArrayList<>());
    ObservableField<List<ResultITabsViewModel>> itemTabs = new ObservableField<>(new ArrayList<>());

    public ShowResultsViewModel() {
        List<Record> load = SearchTask.getInstance().load();
        if (load != null) {
            items.get().clear();
            for (Record record : load) {
                items.get().add(Functions.resultItemVMConverter().apply(record));
            }
            int lastIndex = (int) Math.ceil(items.get().size()/10.0);
            itemTabs.get().clear();
            for (int j = 1; j <= lastIndex; j++) {
                itemTabs.get().add(Functions.resultTabVMConverter().apply(j));
            }
        }
    }

    public ObservableField<List<ResultItemViewModel>> getItems() {
        return items;
    }

    public ObservableField<List<ResultITabsViewModel>> getItemTabs() {
        return itemTabs;
    }
}
