package com.omka.mackhaton.common;

import android.arch.core.util.Function;
import android.support.annotation.NonNull;

import com.omka.mackhaton.entity.response.Record;
import com.omka.mackhaton.screen.showResults.ResultITabsViewModel;
import com.omka.mackhaton.screen.showResults.ResultItemViewModel;

/**
 * Created by ahmet on 2/4/2018.
 */

public class Functions {
    @NonNull
    public static Function<Integer, ResultITabsViewModel> resultTabVMConverter() {
        return ResultITabsViewModel::new;
    }

    @NonNull
    public static Function<Record, ResultItemViewModel> resultItemVMConverter() {
        return record ->
                new ResultItemViewModel(
                        record.getTotalCount(),
                        record.getId().getKey(),
                        record.getId().getValue(),
                        record.getId().getCreatedAt());
    }
}
