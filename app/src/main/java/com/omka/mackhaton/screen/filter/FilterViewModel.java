package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ahmet on 1/30/2018.
 */

public class FilterViewModel extends ViewModel {

    public static final int DEFAULT = 0;
    public static final int LOADING = 1;
    @IntDef({DEFAULT, LOADING})
    @Retention(RetentionPolicy.SOURCE)

    public @interface FilterStatus {
    }

    private MutableLiveData<Integer> communicationLiveData = new MutableLiveData<>();
    private ObservableField<String> minText = new ObservableField<>();
    private ObservableField<String> maxText = new ObservableField<>();

    public FilterViewModel() {
        communicationLiveData.setValue(DEFAULT);
        minText.addOnPropertyChangedCallback(new ObservableField.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (compare(minText, maxText) > 0) {
                    maxText.set(minText.get());
                }
            }
        });
        maxText.addOnPropertyChangedCallback(new ObservableField.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (compare(minText, maxText) > 0) {
                    minText.set(maxText.get());
                }
            }
        });
    }


    public void performFilter(View v) {
        communicationLiveData.postValue(LOADING);
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

    /**
     * compares two string assuming they can be converted to integer values
     *
     * @param first
     * @param second
     * @return
     */
    private int compare(ObservableField<String> first, ObservableField<String> second) {
        try {
            int f = Integer.parseInt(first.get());
            int s = Integer.parseInt(second.get());
            return f == s ? 0 : (f < s ? -1 : 1);
        } catch (Exception e) {
            return 1;
        }
    }

    public int getCurrentMin(){
        try {
           return Integer.parseInt(minText.get());
        } catch (Exception e){
            return 0;
        }
    }

    public int getCurrentMax(){
        try {
           return Integer.parseInt(maxText.get());
        } catch (Exception e){
            return 0;
        }
    }
}
