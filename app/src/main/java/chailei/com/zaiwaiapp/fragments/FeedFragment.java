package chailei.com.zaiwaiapp.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.adapters.FeedFragmentAdapter;
import chailei.com.zaiwaiapp.data.DataInit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager pager = (ViewPager) view.findViewById(R.id.feed_pager);
        TabLayout tab = (TabLayout) view.findViewById(R.id.feed_tab);
        FeedFragmentAdapter adapter = new FeedFragmentAdapter(getChildFragmentManager(), DataInit.getFeedList());
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);

    }
}
