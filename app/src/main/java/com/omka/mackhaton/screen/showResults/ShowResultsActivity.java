package com.omka.mackhaton.screen.showResults;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

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
        ShowResultsViewModel showResultsVM = ViewModelProviders.of(this).get(ShowResultsViewModel.class);
        binding.setShowResultsVM(showResultsVM);
        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                TransitionManager.beginDelayedTransition(
                        (ViewGroup) binding.getRoot());
                return super.onPreBind(binding);
            }
        });
        binding.executePendingBindings();
    }
}
