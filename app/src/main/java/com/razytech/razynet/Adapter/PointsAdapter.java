package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.data.beans.PointsResponse;
import com.razytech.razynet.databinding.RowpointsBinding;

import java.util.List;
import java.util.Locale;

import static com.razytech.razynet.Utils.AppConstant.BTN_All;
import static com.razytech.razynet.Utils.AppConstant.BTN_IN;
import static com.razytech.razynet.Utils.AppConstant.BTN_OUT;

/**
 * Created by A.Noby on 6/18/2019.
 */
public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.MyViewHolder> {

    private List<PointsResponse> itemlist;
    private LayoutInflater layoutInflater;
    private PointsAdapter.PointsListener listener;
    Context context  ;
    int TabPosition  = BTN_All ;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowpointsBinding binding;

        public MyViewHolder(final RowpointsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public PointsAdapter(Context context, List<PointsResponse> itemlist, PointsAdapter.PointsListener listener  , int tabPosition) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.TabPosition =  tabPosition ;
    }

    public void filter(int TabPos , List<PointsResponse> pointsResponses  ) {
        itemlist.clear();
            for (int i = 0 ; i < pointsResponses.size() ; i++) {
                if (TabPos == BTN_All) {
                    itemlist.add(pointsResponses.get(i));
                } else if (TabPos == BTN_IN) {
                    if (pointsResponses.get(i).isIsin())
                        itemlist.add(pointsResponses.get(i));
                } else if (TabPos == BTN_OUT) {
                    if (!pointsResponses.get(i).isIsin())
                        itemlist.add(pointsResponses.get(i));
                }
            }
        Log.e("itemlistsize", AppConstant.pointsResponses.size() +"  "+pointsResponses.size()+"  "+itemlist.size());
         notifyDataSetChanged();
    }

    @Override
    public PointsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowpointsBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowpoints, parent, false);
        return new PointsAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PointsAdapter.MyViewHolder holder, final int position) {
//            if (TabPosition == BTN_All) {
//                holder.binding.setHandlers(itemlist.get(position));
//            } else if (TabPosition == BTN_IN) {
//                if (itemlist.get(position).isIsin())
//                    holder.binding.setHandlers(itemlist.get(position));
//            } else if (TabPosition == BTN_OUT) {
//                if (!itemlist.get(position).isIsin())
//                    holder.binding.setHandlers(itemlist.get(position));
//            }
        holder.binding.setHandlers(itemlist.get(position));
        //   StaticMethods.LoadImage(context,holder.binding.countryPhoto,itemlist.get(position).getImage(),holder.binding.progress);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(itemlist.get(position));
                }
            }
        });

    }
    public void addItems(List<PointsResponse> items) {
        this.itemlist.addAll(items);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface PointsListener {
        void onItemClicked(PointsResponse post);
    }
}
