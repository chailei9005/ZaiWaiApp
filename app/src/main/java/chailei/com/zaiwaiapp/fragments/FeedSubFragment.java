package chailei.com.zaiwaiapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chailei.com.zaiwaiapp.InformationActivity;
import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.adapters.FeedListViewAdapter;
import chailei.com.zaiwaiapp.entitys.DataEntity;
import chailei.com.zaiwaiapp.entitys.HotEntity;
import chailei.com.zaiwaiapp.entitys.PackData;
import chailei.com.zaiwaiapp.services.ServiceUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedSubFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {


    private static final String TITLE = "data";
    private FeedListViewAdapter mAdapter;
    private List<HotEntity.CustomerFeedListEntity> lists;

    public  Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int what = msg.what;
            switch (what){
                case 1:
                    lists = (List<HotEntity.CustomerFeedListEntity>) msg.obj;
                    mAdapter.addAll(lists);
                    Log.d("handler",lists.toString());
                    break;
                case 2:
                    break;
            }
        }
    };
    private String type;
    private String user;

    public FeedSubFragment() {
        // Required empty public constructor
    }

    public static FeedSubFragment newInstance(DataEntity data) {

        Bundle args = new Bundle();
        args.putSerializable(TITLE,data);
        FeedSubFragment fragment = new FeedSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_sub, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        TextView textView = (TextView) view.findViewById(R.id.feed_sub_txt);
//        textView.setText(getArguments().getString(TITLE));

        Bundle arguments = getArguments();
        DataEntity data = (DataEntity) arguments.getSerializable(TITLE);
        Log.d("data",""+data);
        if(data!= null){
            type = data.getType();
            user = data.getUser();
            Log.d("dataaa", type +":"+ user);
        }
//        Log.d("data",""+data.getTitle()+"--"+data.getType()+"--"+data.getUser());
        ListView listView = (ListView) view.findViewById(R.id.feed_sub_list_view);
        mAdapter = new FeedListViewAdapter(getContext());
        listView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(this);
//        String token = "369590829552297";
//        String userId = "368153098450592";
        String url = null;
        if("findRecommendCustomerFeedListBySourceUserId".equals(user)){
            url = "http://zaiwai.qunawan.com/"+ type +"/"+ user +"?sToken=369590829552297&sourceUserId=368153098450592";
        }else if("findCustomerFeedListForUser".equals(user)){
             url = "http://zaiwai.qunawan.com/"+ type +"/"+ user +"?sToken=369590829552297&userId=368153098450592";
        }else if("findCustomerFeedListByLocation".equals(user)){
                url  = "http://zaiwai.qunawan.com/"+ type +"/"+ user +"?sToken=369590829552297&lng=116.37002&lat=40.037056&userId=368153098450592";
        }

//        ServiceUtils.getHotService().getHotEntityList(type,user,Long.parseLong(token),Long.parseLong(userId)).enqueue(this);
        OkHttpClient client = new OkHttpClient();
        Request request =new Request.Builder()
                .url(url)
                .get()
                .build();
        Log.d("okhttp","okhttp"+22222222);
        Call call = client.newCall(request);
        call.enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
//                Toast.makeText(getContext(),"okHttp 有问题",Toast.LENGTH_SHORT).show();

            }
            //属于子线程
            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {

                String string = response.body().string();
                Log.d("okhttp", string);
                try {
                    List<HotEntity.CustomerFeedListEntity> list = getHotEntity(string);
//                    List<CategoryInfoSub.ListEntity> list = getList(string);
                    Log.d("9999999", list.toString());
//                        CategoryInformationSubFragment.this.adapter.addAll(list);
                    Message message = FeedSubFragment.this.sHandler.obtainMessage();
                    message.what = 1;
                    message.obj = list;
                    FeedSubFragment.this.sHandler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("9999","解析有错误");
                }
            }
        });
    }

    public List<HotEntity.CustomerFeedListEntity> getHotEntity(String jsonStr) throws JSONException {

        List<HotEntity.CustomerFeedListEntity> list = null;
        list = new ArrayList<>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray array = object.getJSONArray("customerFeedList");
        int length = array.length();
        for (int i = 0; i < length; i++) {
            JSONObject obj = array.getJSONObject(i);
            HotEntity.CustomerFeedListEntity customer = new HotEntity.CustomerFeedListEntity();
            HotEntity.CustomerFeedListEntity.AuthorEntity author = new HotEntity.CustomerFeedListEntity.AuthorEntity();
            JSONObject objAuthor = obj.getJSONObject("author");
            long userId = objAuthor.getLong("userId");
            author.setUserId(userId);
            String name = objAuthor.getString("name");
            author.setName(name);
            String picUrl = objAuthor.getString("picUrl");
            author.setPicUrl(picUrl);
            customer.setAuthor(author);

            JSONArray pictureArray = obj.getJSONArray("pictureList");
            List<HotEntity.CustomerFeedListEntity.PictureListEntity> picList = new ArrayList<>();
            int pictureLength = pictureArray.length();
            for (int j = 0; j < pictureLength; j++) {
                JSONObject objPic = pictureArray.getJSONObject(j);
                HotEntity.CustomerFeedListEntity.PictureListEntity pic = new HotEntity.CustomerFeedListEntity.PictureListEntity();
                double lng = objPic.getDouble("lng");
                pic.setLng(lng);
                double lat = objPic.getDouble("lat");
                pic.setLat(lat);
                String url = objPic.getString("url");
                pic.setUrl(url);
                int width = objPic.getInt("width");
                pic.setWidth(width);
                int height = objPic.getInt("high");
                pic.setHigh(height);
                picList.add(pic);
            }
            customer.setPictureList(picList);
            JSONObject feedObj = obj.getJSONObject("feed");
            HotEntity.CustomerFeedListEntity.FeedEntity feed = new HotEntity.CustomerFeedListEntity.FeedEntity();
            long feedId = feedObj.getLong("feedId");
            feed.setFeedId(feedId);
            String location = feedObj.getString("location");
            feed.setLocation(location);
            double lng = feedObj.getDouble("lng");
            feed.setLng(lng);
            double lat = feedObj.getDouble("lat");
            feed.setLat(lat);
            String content = feedObj.getString("content");
            feed.setContent(content);
            String addTime = feedObj.getString("addTime");
            feed.setAddTime(addTime);
            customer.setFeed(feed);
            list.add(customer);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getContext(), InformationActivity.class);
        Toast.makeText(getContext(),"点到了第"+position+"ge",Toast.LENGTH_SHORT).show();
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        PackData data = (PackData) v.getTag();
        Intent intent = new Intent(getContext(), InformationActivity.class);
        Toast.makeText(getContext(),"点到了第"+"ge",Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
