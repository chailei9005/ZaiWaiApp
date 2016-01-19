package chailei.com.zaiwaiapp.services;

import chailei.com.zaiwaiapp.entitys.HotEntity;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Administrator on 16-1-19.
 */
public class ServiceUtils {
//http://zaiwai.qunawan.com/
// recommendFeedService/findRecommendCustomerFeedListBySourceUserId?sToken=369590829552297&sourceUserId=368153098450592

    //http://appimg.zaiwai.com/2016/1/8d4dbce57757f61423a7687a9f3cf34c0!width720

    public  interface ZaiWaiService{
            @GET("/{type}/{user}")
            Call<HotEntity> getHotEntityList(@Path("type") String recommend,
                                             @Path("user") String user,
                                             @Query("sToken") long token,
                                             @Query("sourceUserId") long userId);
    }
    public static ZaiWaiService hotService;
    static {

        hotService = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://zaiwai.qunawan.com").build().create(ZaiWaiService.class);
    }

    public static ZaiWaiService getHotService(){
        return hotService;
    }


}
