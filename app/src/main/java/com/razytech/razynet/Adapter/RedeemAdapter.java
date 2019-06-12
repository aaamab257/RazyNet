package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.databinding.RowredeemBinding;

import java.util.List;

/**
 * Created by A.Noby on 6/12/2019.
 */
public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.MyViewHolder> {

    private List<RedeemResponse> itemlist;
    private LayoutInflater layoutInflater;
    private RedeemAdapter.RedeemListener listener;
    Context context  ;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowredeemBinding binding;

        public MyViewHolder(final RowredeemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public RedeemAdapter(Context context, List<RedeemResponse> itemlist, RedeemAdapter.RedeemListener listener) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
    }

    @Override
    public RedeemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowredeemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowredeem, parent, false);
        return new RedeemAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RedeemAdapter.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
     //   StaticMethods.LoadImage(context,holder.binding.countryPhoto,itemlist.get(position).getImage(),holder.binding.progress);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onredeemClicked(itemlist.get(position));
                }
            }
        });
    }
    public void addItems(List<RedeemResponse> items) {
        this.itemlist.addAll(items);
    }


    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface RedeemListener {
        void onredeemClicked(RedeemResponse post);
    }
}

