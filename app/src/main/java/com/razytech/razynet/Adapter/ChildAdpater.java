package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.databinding.RowchilddataBinding;

import java.util.List;

/**
 * Created by A.Noby on 6/17/2019.
 */
public class ChildAdpater extends RecyclerView.Adapter<ChildAdpater.MyViewHolder> {

    private List<ChildResponse> itemlist;
    private LayoutInflater layoutInflater;
    private ChildAdpater.ChildListener listener;
    Context context  ;
    int row_index =  -1  ;
    boolean show_Wallet ,  show_Selected;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowchilddataBinding binding;

        public MyViewHolder(final RowchilddataBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public ChildAdpater(Context context, List<ChildResponse> itemlist, ChildAdpater.ChildListener listener,boolean show_Wallet) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.show_Wallet =  show_Wallet ;
        this.show_Selected = true ;
    }
    public ChildAdpater(Context context, List<ChildResponse> itemlist, ChildAdpater.ChildListener listener,boolean show_Wallet
            ,boolean show_Selected) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.show_Wallet =  show_Wallet ;
        this.show_Selected  = show_Selected ;
    }

    @Override
    public ChildAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowchilddataBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowchilddata, parent, false);
        return new ChildAdpater.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ChildAdpater.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
        holder.binding.setShowwallet(show_Wallet);
        StaticMethods.LoadImage(context,holder.binding.imgChild,itemlist.get(position).getImageUrl(),null);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onChildClicked(itemlist.get(position));
                    row_index =  position ;
                    notifyDataSetChanged();
                }
            }
        });
        if (show_Selected) {
            if (row_index == position) {
                holder.binding.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            } else {
                holder.binding.cardView.setBackgroundColor(context.getResources().getColor(R.color.Black));
            }
        }
    }
    public void addItems(List<ChildResponse> items) {
        this.itemlist.addAll(items);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface ChildListener {
        void onChildClicked(ChildResponse post);
    }
}