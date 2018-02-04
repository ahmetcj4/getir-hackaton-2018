package com.omka.mackhaton.common.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker;

import java.util.List;

public class StaticDataBindingAdapters {
    @BindingAdapter("initWithRange")
    public static void initializePicker(SublimeDatePicker p, boolean canPickRange) {
        p.init(null, canPickRange, null);
    }
    @BindingAdapter(value = {"itemList","itemLayout"},requireAll = true)
    public static <T> void createAdapter(RecyclerView rv, List<T> itemList, int itemLayout) {
        rv.setAdapter(new BaseAdapter<T>() {
            @Override
            protected T getObjForPosition(int position) {
                return itemList.get(position);
            }

            @Override
            protected int getLayoutIdForPosition(int position) {
                return itemLayout;
            }

            @Override
            public int getItemCount() {
                return itemList==null?0:itemList.size();
            }
        });
    }

    @BindingAdapter("divider")
    public static void addDivider(RecyclerView rv, DividerItemDecoration itemDecoration){
        rv.addItemDecoration(itemDecoration);
    }
}