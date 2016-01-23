package chailei.com.zaiwaiapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.customs.IconTransform;
import chailei.com.zaiwaiapp.entitys.HotEntity;
import chailei.com.zaiwaiapp.entitys.PackData;

/**
 * Created by Administrator on 16-1-19.
 */
public class FeedListViewAdapter extends BaseAdapter {

    private Context context;
    private List<HotEntity.CustomerFeedListEntity> list;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public FeedListViewAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        int size = list.size();
        Log.d("size", "size=" +size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.feed_item,parent,false);
            convertView.setTag(new ListViewHolder(convertView));
        }
        ListViewHolder holder = (ListViewHolder) convertView.getTag();
        convertView.setOnClickListener(onClickListener);
        PackData data = new PackData();
        HotEntity.CustomerFeedListEntity items = list.get(position);
        Picasso.with(context).
                load(Uri.parse(items.getAuthor().getPicUrl()))
                .resize(100, 100)
                .transform(new IconTransform()).
                into(holder.mIcon);
        data.setIcon_url(items.getAuthor().getPicUrl());
        holder.mName.setText(items.getAuthor().getName());
        data.setName(items.getAuthor().getName());
        long l = System.currentTimeMillis();
//        String s = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(l));
//        Log.d("time","time:"+s);
//        holder.mTime.setText(String.valueOf((Long.parseLong(s)-Long.parseLong(items.getFeed().getAddTime()))/100)+"分前");
        try {
            Date parse = new SimpleDateFormat("yyyyMMddHHmmss").parse(items.getFeed().getAddTime());
            holder.mTime.setText(new SimpleDateFormat("MM月dd日HH:mm").format(parse));
            data.setTime(new SimpleDateFormat("MM月dd日HH:mm").format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mContent.setText(items.getFeed().getContent());
        data.setContent(items.getFeed().getContent());
        holder.mGridView.setAdapter(new FeedItemGridViewAdapter(context,items.getPictureList()));
        data.setList(items.getPictureList());
        holder.mLocation.setText(items.getFeed().getLocation());
        convertView.setTag(data);
        return convertView;
    }
    public void addAll(Collection<HotEntity.CustomerFeedListEntity> collection){
        list.addAll(collection);
        Log.d("list",list.toString());
        notifyDataSetChanged();
    }

    public static class ListViewHolder{

        private final ImageView mIcon;
        private final TextView mName;
        private final TextView mTime;
        private final GridView mGridView;
        private final TextView mLocation;
        private final TextView mZan;
        private final TextView mComment;
        private final TextView mContent;

        public ListViewHolder(View view) {

            mIcon = (ImageView) view.findViewById(R.id.feed_sub_item_icon);
            mName = (TextView) view.findViewById(R.id.feed_sub_item_name);
            mTime = (TextView) view.findViewById(R.id.feed_sub_item_time);
            mGridView = (GridView) view.findViewById(R.id.feed_sub_item_grid_view);
            mLocation = (TextView) view.findViewById(R.id.feed_sub_item_location);
            mZan = (TextView) view.findViewById(R.id.feed_sub_item_zan);
            mComment = (TextView) view.findViewById(R.id.feed_sub_item_comment);
            mContent = (TextView) view.findViewById(R.id.feed_sub_item_content);

        }
    }
}
