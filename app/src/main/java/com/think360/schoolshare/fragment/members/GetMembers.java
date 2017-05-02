package com.think360.schoolshare.fragment.members;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by think360 on 23/02/17.
 */

public interface GetMembers {

    @GET("get-member")
    Call<List<Members>> getMembersByGroupId(@Query("chapter_id") String string);
}
