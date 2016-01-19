package chailei.com.zaiwaiapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import chailei.com.zaiwaiapp.fragments.EventFragment;
import chailei.com.zaiwaiapp.fragments.FeedFragment;
import chailei.com.zaiwaiapp.fragments.FoundFragment;
import chailei.com.zaiwaiapp.fragments.MineFragment;
import chailei.com.zaiwaiapp.fragments.NotifyFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadio;
    private FeedFragment mFeedFragment;
    private FoundFragment mFoundFragment;
    private EventFragment mEventFragment;
    private NotifyFragment mNotifyFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadio = (RadioGroup) findViewById(R.id.main_radioGroup);

        mFeedFragment = new FeedFragment();
        mFoundFragment = new FoundFragment();
        mEventFragment = new EventFragment();
        mNotifyFragment = new NotifyFragment();
        mMineFragment = new MineFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_contain, mFeedFragment,"feed");
        transaction.add(R.id.main_contain, mFoundFragment,"found");
        transaction.add(R.id.main_contain, mEventFragment,"event");
        transaction.add(R.id.main_contain, mNotifyFragment,"notify");
        transaction.add(R.id.main_contain, mMineFragment,"mine");

        transaction.show(mFeedFragment);
        transaction.hide(mFoundFragment);
        transaction.hide(mEventFragment);
        transaction.hide(mNotifyFragment);
        transaction.hide(mMineFragment);
        transaction.commit();
        mRadio.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.main_tab_feed:
                transaction.show(mFeedFragment);
                transaction.hide(mFoundFragment);
                transaction.hide(mEventFragment);
                transaction.hide(mNotifyFragment);
                transaction.hide(mMineFragment);
                break;
            case R.id.main_tab_found:
                transaction.hide(mFeedFragment);
                transaction.show(mFoundFragment);
                transaction.hide(mEventFragment);
                transaction.hide(mNotifyFragment);
                transaction.hide(mMineFragment);
                break;
            case R.id.main_tab_event:
                transaction.hide(mFeedFragment);
                transaction.hide(mFoundFragment);
                transaction.show(mEventFragment);
                transaction.hide(mNotifyFragment);
                transaction.hide(mMineFragment);
                break;
            case R.id.main_tab_notify:
                transaction.hide(mFeedFragment);
                transaction.hide(mFoundFragment);
                transaction.hide(mEventFragment);
                transaction.show(mNotifyFragment);
                transaction.hide(mMineFragment);
                break;
            case R.id.main_tab_mine:
                transaction.hide(mFeedFragment);
                transaction.hide(mFoundFragment);
                transaction.hide(mEventFragment);
                transaction.hide(mNotifyFragment);
                transaction.show(mMineFragment);
                break;
        }
        transaction.commit();
    }
}
