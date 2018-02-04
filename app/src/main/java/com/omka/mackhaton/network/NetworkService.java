package com.omka.mackhaton.network;


import com.omka.mackhaton.entity.request.SearchRequest;
import com.omka.mackhaton.entity.response.SearchResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ahmet on 2/4/2018.
 */
public interface NetworkService {

    @POST(NetworkConstants.SEARCH_RECORDS)
    Call<SearchResult> search(@Body SearchRequest searchRequest);
}