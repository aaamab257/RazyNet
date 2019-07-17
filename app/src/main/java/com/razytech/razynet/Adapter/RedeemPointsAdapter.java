package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.databinding.RowredeempointsBinding;

import java.util.List;

/**
 * Created by A.Noby on 6/12/2019.
 */
public class RedeemPointsAdapter extends RecyclerView.Adapter<RedeemPointsAdapter.MyViewHolder> {

    private List<RedeemPointsResponse> itemlist;
    private LayoutInflater layoutInflater;
    private RedeemPointsAdapter.RedeemListener listener;
    Context context  ;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowredeempointsBinding binding;

        public MyViewHolder(final RowredeempointsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public RedeemPointsAdapter(Context context, List<RedeemPointsResponse> itemlist, RedeemPointsAdapter.RedeemListener listener) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
    }

    @Override
    public RedeemPointsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowredeempointsBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowredeempoints, parent, false);
        return new RedeemPointsAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RedeemPointsAdapter.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
       StaticMethods.LoadImage(context,holder.binding.countryPhoto,itemlist.get(position).getImageUrl(),holder.binding.progress);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onredeemClicked(itemlist.get(position));
                }
            }
        });
    }
    public void addItems(List<RedeemPointsResponse> items) {
        this.itemlist.addAll(items);
    }


    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface RedeemListener {
        void onredeemClicked(RedeemPointsResponse post);
    }
}


