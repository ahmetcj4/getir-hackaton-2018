package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * Created by ahmet on 1/30/2018.
 */

public class FilterViewModel extends ViewModel {
    ObservableField<String> text = new ObservableField<>();

    public FilterViewModel() {
        text.set("Hello World!");
    }

    public ObservableField<String> getText() {
        return text;
    }

    public void setText(ObservableField<String> text) {
        this.text = text;
    }
}
