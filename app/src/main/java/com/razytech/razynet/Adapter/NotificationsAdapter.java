package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.databinding.RownotificationBinding;

import java.util.List;

/**
 * Created by A.Noby on 6/17/2019.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder> {

    private List<NotificationsResponse> itemlist;
    private LayoutInflater layoutInflater;
    private NotificationsAdapter.NotificationsListener listener;
    Context context  ;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RownotificationBinding binding;

        public MyViewHolder(final RownotificationBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public NotificationsAdapter(Context context, List<NotificationsResponse> itemlist, NotificationsAdapter.NotificationsListener listener) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
    }

    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RownotificationBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rownotification, parent, false);
        return new NotificationsAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotificationsAdapter.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
        //   StaticMethods.LoadImage(context,holder.binding.countryPhoto,itemlist.get(position).getImage(),holder.binding.progress);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onnotificationClicked(itemlist.get(position));
                }
            }
        });
    }
    public void addItems(List<NotificationsResponse> items) {
        this.itemlist.addAll(items);
    }


    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface NotificationsListener {
        void onnotificationClicked(NotificationsResponse post);
    }
}