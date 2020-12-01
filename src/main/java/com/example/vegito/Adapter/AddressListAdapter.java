package com.example.vegito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vegito.Models.GetAddresss.BaseAddressResponse;
import com.example.vegito.R;
import com.example.vegito.Utils.CollectionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    private List<BaseAddressResponse> list;
    private Context context;

    public AddressListAdapter(List<BaseAddressResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_address_list, viewGroup, false);
        return new AddressListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListAdapter.ViewHolder viewHolder, int position) {
        String shippingAdd = " Shipping Address : ";
        String billingAdd = " Billing Address : ";
        boolean isShippingAddress = list.get(position).isShippingAddress();
        String toShow = isShippingAddress ? shippingAdd : billingAdd;
        viewHolder.addressdetails.setText(toShow + "\n" + list.get(position).getStreet() + " " +
                list.get(position).getAddressLine1() + " " + list.get(position).getAddressLine2() + " " +
                list.get(position).getLandmark() + " " + list.get(position).getCity() + " " +
                list.get(position).getState() + " " + list.get(position).getPincode() + " " + "\n");
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.length(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.addressdetails)
        TextView addressdetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

