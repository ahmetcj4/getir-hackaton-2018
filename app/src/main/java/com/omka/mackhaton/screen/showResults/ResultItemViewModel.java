package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * Created by ahmet on 2/4/2018.
 */

public class ResultItemViewModel extends ViewModel {
    private ObservableField<String> totalCount = new ObservableField<>();
    private ObservableField<String> key = new ObservableField<>();
    private ObservableField<String> value = new ObservableField<>();
    private ObservableField<String> createdAt = new ObservableField<>();

    public ResultItemViewModel(int totalCount, String key, String value, String createdAt) {
        this.totalCount.set(String.valueOf(totalCount));
        this.key.set(key);
        this.value.set(value);
        this.createdAt.set(createdAt);
    }

    public ObservableField<String> getTotalCount() {
        return totalCount;
    }

    public ObservableField<String> getKey() {
        return key;
    }

    public ObservableField<String> getValue() {
        return value;
    }

    public ObservableField<String> getCreatedAt() {
        return createdAt;
    }
}
