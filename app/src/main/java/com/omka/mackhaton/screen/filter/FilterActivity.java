package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.omka.mackhaton.R;
import com.omka.mackhaton.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFilterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        FilterViewModel filterVM = ViewModelProviders.of(this).get(FilterViewModel.class);
        binding.setFilterVM(filterVM);
    }
}
