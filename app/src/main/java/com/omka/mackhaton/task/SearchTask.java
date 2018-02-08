package com.omka.mackhaton.task;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.omka.mackhaton.entity.request.SearchRequest;
import com.omka.mackhaton.entity.response.Record;
import com.omka.mackhaton.entity.response.SearchResult;
import com.omka.mackhaton.network.NetworkApi;
import com.omka.mackhaton.network.NetworkCallback;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by ahmet on 2/4/2018.
 */

public class SearchTask {
    private static SearchTask instance;
    private List<Record> records;

    public static SearchTask getInstance() {
        if (instance==null){
            instance = new SearchTask();
        }
        return instance;
    }

    public int pageCount(){
        if (recordsIsEmpty())return 0;
        return (int) Math.ceil(records.size()/10.0);
    }

    public List<Record> load(int i){
        if (recordsIsEmpty()||i>=pageCount())return null;
        int fromIndex = i * 10;
        int toIndex = Math.min((i+1) * 10,records.size());
        return records.subList(fromIndex,toIndex);
    }

    public void fetch(SearchRequest searchRequest, @NonNull SearchResultListener listener) {
        NetworkApi.getInstance().search(searchRequest, new NetworkCallback<SearchResult>() {
            @Override
            public void onSuccess(SearchResult response) {
                switch (response.getCode()){
                    case 0:
                        records = response.getRecords();
                        if (recordsIsEmpty()){
                            listener.onResult(FAIL,"No Matching Results");
                        } else {
                            listener.onResult(SUCCESS,response.getMsg());
                        }
                        break;
                    default:
                        listener.onResult(FAIL,response.getMsg());
                        break;
                }
            }

            @Override
            public void onServiceFailure(int httpResponseCode, String message) {
                listener.onResult(FAIL,message);
            }

            @Override
            public void onNetworkFailure(Throwable message) {
                listener.onResult(FAIL,"Unknown Error");
            }
        });
    }

    public boolean recordsIsEmpty() {
        return records==null|| records.isEmpty();
    }

    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    @IntDef({FAIL, SUCCESS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SearchResultStatus{}

    public interface SearchResultListener{
        void onResult(@SearchResultStatus int status,String message);
    }
}
