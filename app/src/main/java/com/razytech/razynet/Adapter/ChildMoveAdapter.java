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
import com.razytech.razynet.databinding.RowmoveBinding;
import com.razytech.razynet.databinding.RowmoveBinding;

import java.util.List;

/**
 * Created by A.Noby on 7/8/2019.
 */
public class ChildMoveAdapter extends RecyclerView.Adapter<ChildMoveAdapter.MyViewHolder> {

    private List<ChildResponse> itemlist;
    private LayoutInflater layoutInflater;
    private ChildMoveAdapter.ChildListener listener;
    Context context  ;
    int row_index =  -1  ;
    boolean show_Wallet;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RowmoveBinding binding;

        public MyViewHolder(final RowmoveBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public ChildMoveAdapter(Context context, List<ChildResponse> itemlist,
                            ChildMoveAdapter.ChildListener listener) {
        this.itemlist = itemlist;
        this.listener = listener;
        this.context =  context ;
        this.show_Wallet =  show_Wallet ;
    }

    @Override
    public ChildMoveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowmoveBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rowmove, parent, false);
        return new ChildMoveAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ChildMoveAdapter.MyViewHolder holder, final int position) {
        holder.binding.setHandlers (itemlist.get(position));
        StaticMethods.LoadImage(context,holder.binding.imgMoveItem,itemlist.get(position).getImageUrl(),null);
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
        if(row_index==position){
            holder.binding.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else
        {
            holder.binding.cardView.setBackgroundColor(context.getResources().getColor(R.color.Black));
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
