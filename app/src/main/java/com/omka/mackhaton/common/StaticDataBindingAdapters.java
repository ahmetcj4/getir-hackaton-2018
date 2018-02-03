package com.omka.mackhaton.common;

import android.databinding.BindingAdapter;

import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;

public class StaticDataBindingAdapters {
    @BindingAdapter(value = {"options", "listener", "validationCallback"}, requireAll = true)
    public static void initializePicker(SublimeDatePicker p, SublimeOptions o, SublimeDatePicker.OnDateChangedListener a, SublimeDatePicker.DatePickerValidationCallback c) {
        p.init(o.getDateParams(), o.canPickDateRange(), a);
        p.setValidationCallback(c);
    }
}