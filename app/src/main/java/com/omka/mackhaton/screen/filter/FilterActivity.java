package com.omka.mackhaton.screen.filter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.omka.mackhaton.R;
import com.omka.mackhaton.databinding.ActivityFilterBinding;
import com.omka.mackhaton.entity.request.SearchRequest;
import com.omka.mackhaton.screen.showResults.ShowResultsActivity;
import com.omka.mackhaton.task.SearchTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FilterActivity extends AppCompatActivity {

    private ActivityFilterBinding binding;
    private MutableLiveData<Integer> communicationLiveData;

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
        communicationLiveData = filterVM.getCommunicationLiveData();
        communicationLiveData.observe(this, b -> {
            updateLayout(b);
            switch (b) {
                case FilterViewModel.DEFAULT:
                    break;
                case FilterViewModel.LOADING:
                    SelectedDate selectedDate = binding.dateRangePicker.getSelectedDate();

                    SearchRequest searchRequest = new SearchRequest(
                            formatDateToString(selectedDate.getFirstDate()),
                            formatDateToString(selectedDate.getEndDate()),
                            filterVM.getCurrentMin(),
                            filterVM.getCurrentMax()
                    );

                    SearchTask.getInstance().search(searchRequest, (status, message) -> {
                        switch (status) {
                            case SearchTask.SUCCESS:
                                ShowResultsActivity.start(this);
                                break;
                            case SearchTask.FAIL:
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        communicationLiveData.postValue(FilterViewModel.DEFAULT);
                    });
                    break;
            }
        });
    }

    private String formatDateToString(Calendar firstDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setCalendar(firstDate);
        return fmt.format(firstDate.getTime());
    }

    private void updateLayout(int b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ConstraintSet constraint1 = new ConstraintSet();
            constraint1.clone(this, R.layout.activity_filter);
            ConstraintSet constraint2 = new ConstraintSet();
            constraint2.clone(this, R.layout.activity_filter_alt);

            TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot());
            ConstraintSet constraint = b == 0 ? constraint1 : constraint2;
            constraint.applyTo((ConstraintLayout) binding.getRoot());
        }
    }

    @Override
    public void onBackPressed() {
        if (FilterViewModel.LOADING == communicationLiveData.getValue()) {
            communicationLiveData.postValue(FilterViewModel.DEFAULT);
        } else {
            super.onBackPressed();
        }
    }
}
