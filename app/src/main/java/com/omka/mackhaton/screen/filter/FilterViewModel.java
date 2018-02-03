package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.ViewModel;

import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;

/**
 * Created by ahmet on 1/30/2018.
 */

public class FilterViewModel extends ViewModel {

    public FilterViewModel() {}

    public SublimeOptions getOptions() {
        SublimeOptions options = new SublimeOptions();
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setDisplayOptions(SublimeOptions.ACTIVATE_DATE_PICKER);
        options.setCanPickDateRange(true);
        return options;
    }
    private SublimeDatePicker.OnDateChangedListener listener = (view, selectedDate) -> {};
    private SublimeDatePicker.DatePickerValidationCallback validationCallback = valid -> {};

    public SublimeDatePicker.DatePickerValidationCallback getValidationCallback() {
        return validationCallback;
    }

    public SublimeDatePicker.OnDateChangedListener getListener() {
        return listener;
    }
}
