package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.omka.mackhaton.R;
import com.omka.mackhaton.databinding.ActivityShowResultsBinding;

public class ShowResultsActivity extends AppCompatActivity {

    private ActivityShowResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_results);
        ShowResultsViewModel filterVM = ViewModelProviders.of(this).get(ShowResultsViewModel.class);
    }
}
