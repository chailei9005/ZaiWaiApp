package chailei.com.zaiwaiapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import chailei.com.zaiwaiapp.entitys.DataEntity;
import chailei.com.zaiwaiapp.fragments.FeedSubFragment;

/**
 * Created by Administrator on 16-1-19.
 */
public class FeedFragmentAdapter extends FragmentPagerAdapter{

    private List<DataEntity> list;

    public FeedFragmentAdapter(FragmentManager fm, List<DataEntity> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return FeedSubFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}
