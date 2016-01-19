package chailei.com.zaiwaiapp.adapters;

import android.content.Context;
import android.net.Uri;
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.entitys.HotEntity;

/**
 * Created by Administrator on 16-1-19.
 */
public class FeedListViewAdapter extends BaseAdapter {

    private Context context;
    private List<HotEntity.CustomerFeedListEntity> list;

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
        HotEntity.CustomerFeedListEntity items = list.get(position);
        Picasso.with(context).
                load(Uri.parse(items.getAuthor().getPicUrl()))
                .resize(100,100).
                into(holder.mIcon);
        holder.mName.setText(items.getAuthor().getName());
        holder.mTime.setText(String.valueOf(items.getAuthor().getLastRequestTime()));
        holder.mContent.setText(items.getFeed().getContent());
        holder.mGridView.setAdapter(new FeedItemGridViewAdapter(context,items.getPictureList()));
        holder.mLocation.setText(items.getFeed().getLocation());
        return convertView;
    }
    public void addAll(Collection<HotEntity.CustomerFeedListEntity> collection){
//        list.addAll(collection);
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
