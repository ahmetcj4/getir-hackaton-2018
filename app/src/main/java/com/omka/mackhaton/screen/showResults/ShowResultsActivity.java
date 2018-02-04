package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.omka.mackhaton.R;
import com.omka.mackhaton.databinding.ActivityShowResultsBinding;

public class ShowResultsActivity extends AppCompatActivity {

    private ActivityShowResultsBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShowResultsActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_results);
        ShowResultsViewModel filterVM = ViewModelProviders.of(this).get(ShowResultsViewModel.class);
    }
}
