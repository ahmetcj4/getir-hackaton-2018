package com.omka.mackhaton.common;

import android.databinding.BindingAdapter;

import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker;

public class StaticDataBindingAdapters {
    @BindingAdapter("initWithRange")
    public static void initializePicker(SublimeDatePicker p, boolean canPickRange) {
        p.init(null, canPickRange, null);
    }
}