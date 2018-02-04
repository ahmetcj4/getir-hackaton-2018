package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.omka.mackhaton.R;
import com.omka.mackhaton.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity {

    private ActivityFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        FilterViewModel filterVM = ViewModelProviders.of(this).get(FilterViewModel.class);
        binding.setFilterVM(filterVM);
        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                TransitionManager.beginDelayedTransition(
                        (ViewGroup) binding.getRoot());
                return super.onPreBind(binding);
            }
        });
        filterVM.getCommunicationLiveData().observe(this, b -> {
            if (b == 1) {
                SelectedDate selectedDate = binding.dateRangePicker.getSelectedDate();
                Toast.makeText(this,
                        filterVM.getMinText().get() + " \n" +
                                filterVM.getMaxText().get() + " \n" +
                                selectedDate.getFirstDate().toString() + " \n" +
                                selectedDate.getFirstDate().toString()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
