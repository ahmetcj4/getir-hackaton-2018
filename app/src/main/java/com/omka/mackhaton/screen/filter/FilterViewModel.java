package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by ahmet on 1/30/2018.
 */

public class FilterViewModel extends ViewModel {
    private MutableLiveData<Integer> communicationLiveData = new MutableLiveData<>();
    private ObservableField<String> minText = new ObservableField<>();
    private ObservableField<String> maxText = new ObservableField<>();

    public FilterViewModel() {}

    public void performFilter(View v) {
        communicationLiveData.postValue(1);
    }

    public ObservableField<String> getMinText() {
        return minText;
    }

    public void setMinText(String minText) {
        this.minText.set(minText);
    }

    public ObservableField<String> getMaxText() {
        return maxText;
    }

    public void setMaxText(String maxText) {
        this.maxText.set(maxText);
    }

    public MutableLiveData<Integer> getCommunicationLiveData() {
        return communicationLiveData;
    }
}
