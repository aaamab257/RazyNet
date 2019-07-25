package com.razytech.razynet.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.databinding.RowtopwalletBinding;
import com.razytech.razynet.databinding.RowtopwalletBinding;

import java.util.List;

/**
 * Created by A.Noby on 7/21/2019.
 */
public class TopWalletAdapter extends RecyclerView.Adapter<TopWalletAdapter.MyViewHolder> {

    private List<ChildResponse> itemlist;
    private LayoutInflater layoutInflater;
    private TopWalletAdapter.TopWalletListener listener;
    Context context  ;
    int row_index =  -1  ;
    boolean show_Wallet ,  show_Selected;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowtopwalletBinding binding;

        public MyViewHolder(final RowtopwalletBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public TopWalletAdapter(Context context, List<ChildResponse> itemlist, TopWalletAdapter.TopWalletListener listener,boolean show_Wallet) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.show_Wallet =  show_Wallet ;
        this.show_Selected = true ;
    }
    public TopWalletAdapter(Context context, List<ChildResponse> itemlist, TopWalletAdapter.TopWalletListener listener,boolean show_Wallet
            ,boolean show_Selected) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.show_Wallet =  show_Wallet ;
        this.show_Selected  = show_Selected ;
    }

    @Override
    public TopWalletAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowtopwalletBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowtopwallet, parent, false);
        return new TopWalletAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TopWalletAdapter.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
        StaticMethods.LoadImage(context,holder.binding.topimage,itemlist.get(position).getImageUrl(),null);
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onWalletClicked(itemlist.get(position));
                    row_index =  position ;
                    notifyDataSetChanged();
                }
            }
        });
    }
    public void addItems(List<ChildResponse> items) {
        this.itemlist.addAll(items);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    public interface TopWalletListener {
        void onWalletClicked(ChildResponse post);
    }
}