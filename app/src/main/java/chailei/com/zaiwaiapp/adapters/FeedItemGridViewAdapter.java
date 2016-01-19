package chailei.com.zaiwaiapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import chailei.com.zaiwaiapp.R;
import chailei.com.zaiwaiapp.entitys.HotEntity;

/**
 * Created by Administrator on 16-1-19.
 */
public class FeedItemGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<HotEntity.CustomerFeedListEntity.PictureListEntity> list;

    public FeedItemGridViewAdapter(Context context, List<HotEntity.CustomerFeedListEntity.PictureListEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.feed_grid_item,parent,false);
            convertView.setTag(new GridViewHolder(convertView));
        }
        GridViewHolder holder = (GridViewHolder) convertView.getTag();
        HotEntity.CustomerFeedListEntity.PictureListEntity item = list.get(position);
        Picasso.with(context)
                .load(Uri.parse(String.format("http://appimg.zaiwai.com%s!%d",item.getUrl(),item.getWidth())))
                .resize(200,200)
                .into(holder.mImage);

        return convertView;
    }
    public static class GridViewHolder{

        private final ImageView mImage;

        public GridViewHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.feed_sub_item_image);

        }
    }
}
