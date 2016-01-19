package chailei.com.zaiwaiapp.data;

import java.util.ArrayList;
import java.util.List;

import chailei.com.zaiwaiapp.entitys.DataEntity;

/**
 * Created by Administrator on 16-1-19.
 */
public class DataInit {
    private static List<DataEntity> list;
    static {
        list = new ArrayList<>();
        list.add(new DataEntity("关注","feedService","findCustomerFeedListForUser"));
        list.add(new DataEntity("热门","recommendFeedService","findRecommendCustomerFeedListBySourceUserId"));
//        list.add(new DataEntity("附近","feedService","findCustomerFeedListByLocation"));

    }
    public static List<DataEntity> getFeedList(){
        return list;
    }
}
