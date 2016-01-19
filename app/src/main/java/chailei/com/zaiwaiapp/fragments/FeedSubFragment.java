package chailei.com.zaiwaiapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.adapters.FeedListViewAdapter;
import chailei.com.zaiwaiapp.entitys.DataEntity;
import chailei.com.zaiwaiapp.entitys.HotEntity;
import chailei.com.zaiwaiapp.services.ServiceUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedSubFragment extends Fragment implements Callback<HotEntity> {


    private static final String TITLE = "data";
    private FeedListViewAdapter mAdapter;

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
        String type = null;
        String user = null;
        if(data!= null){
            type = data.getType();
            user = data.getUser();
            Log.d("dataaa",type+":"+user);
        }
//        Log.d("data",""+data.getTitle()+"--"+data.getType()+"--"+data.getUser());
        ListView listView = (ListView) view.findViewById(R.id.feed_sub_list_view);
        mAdapter = new FeedListViewAdapter(getContext());
        listView.setAdapter(mAdapter);
        String token = "369590829552297";
        String userId = "368153098450592";

        ServiceUtils.getHotService().getHotEntityList(type,user,Long.parseLong(token),Long.parseLong(userId)).enqueue(this);

    }

    @Override
    public void onResponse(Response<HotEntity> response, Retrofit retrofit) {

        if(response != null){
            Toast.makeText(getContext(),"网络挺好",Toast.LENGTH_SHORT).show();
            mAdapter.addAll(response.body().getCustomerFeedList());
            Log.d("response",response.body().getCustomerFeedList().get(0).getAuthor().getName());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(),"网络有问题",Toast.LENGTH_SHORT).show();
    }
}
