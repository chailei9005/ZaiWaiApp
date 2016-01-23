package chailei.com.zaiwaiapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import chailei.com.zaiwaiapp.entitys.PackData;

public class InformationActivity extends AppCompatActivity {

    private TextView textView;
    private ListView info_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
//        textView = (TextView) findViewById(R.id.info_txt);
//        textView.setText(""+getIntent().getLongExtra("id",-1));
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PackData data = (PackData) bundle.getSerializable("data");
        Toast.makeText(this,"data="+data.toString(),Toast.LENGTH_SHORT).show();
        Toolbar info_toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        setSupportActionBar(info_toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("帖子详情");

        info_listView = (ListView) findViewById(R.id.info_listView);
        List<String> list =new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("chailei"+i);
        }
        info_listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
        info_listView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.info_header,null));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
