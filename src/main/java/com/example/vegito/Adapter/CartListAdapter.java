package com.example.vegito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vegito.Interface.ProductTotalValueInterface;
import com.example.vegito.Models.CartList.ResultGetCartList;
import com.example.vegito.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    List<ResultGetCartList> responseCartList;
    ProductTotalValueInterface productTotalValueInterface;
    int minteger = 0;
    int total_qty;
    Context context;


    public void setListener(ProductTotalValueInterface productTotalValueInterface) {
        this.productTotalValueInterface = productTotalValueInterface;
    }

    public CartListAdapter(List<ResultGetCartList> results, Context context) {
        responseCartList = results;
        this.context = context;

    }

    @NonNull
    @Override
    public CartListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_cart_list, viewGroup, false);
        return new CartListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.MyViewHolder myViewHolder, int i) {
        String nameofProduct = responseCartList.get(i).getProdImage();
        String finalProductName = nameofProduct.substring(nameofProduct.lastIndexOf('/') + 1);
        String vegieName = finalProductName.substring(0, finalProductName.indexOf("."));
        if (!vegieName.contains("-")) {
            Context context = myViewHolder.iv_profileImage.getContext();
            int id = context.getResources().getIdentifier(vegieName, "drawable", context.getPackageName());
            myViewHolder.iv_profileImage.setImageResource(id);
        } else {
            Context context = myViewHolder.iv_profileImage.getContext();
            int id = context.getResources().getIdentifier(vegieName.replace("-", ""), "drawable", context.getPackageName());
            myViewHolder.iv_profileImage.setImageResource(id);
        }

        myViewHolder.tvvegname.setText(responseCartList.get(i).getProdName());
        myViewHolder.tv_qty_total.setText(String.valueOf(responseCartList.get(i).getUserCartItemProdQty()));
        int totalPrice = Integer.valueOf(responseCartList.get(i).getProdUnitPrice()) * responseCartList.get(i).getUserCartItemProdQty();
        myViewHolder.tvprice.setText(context.getResources().getString(R.string.rs) + " " + String.valueOf(totalPrice) + ".00");


        myViewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minteger = Integer.parseInt(myViewHolder.tv_qty_total.getText().toString()) + 1;
                display(minteger, myViewHolder, responseCartList.get(i).getProdUnitPrice(), i);

            }
        });

        myViewHolder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minteger = Integer.parseInt(myViewHolder.tv_qty_total.getText().toString()) - 1;
                display(minteger, myViewHolder, responseCartList.get(i).getProdUnitPrice(), i);

            }
        });


    }

    private void display(int number, MyViewHolder myViewHolder, String prodUnitPrice, int i) {
        if (number >= 1) {
            productTotalValueInterface.rowClickListener(responseCartList, minteger, i);
            myViewHolder.tv_qty_total.setText("" + number);
            myViewHolder.tvprice.setText(context.getResources().getString(R.string.rs) + " " + Integer.parseInt(prodUnitPrice) * number + ".00");
        } else {
            Toast.makeText(context, "Can not select less than 1 item", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return responseCartList != null ? responseCartList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView((R.id.ivCateogry))
        ImageView iv_profileImage;
        @BindView((R.id.tvvegname))
        TextView tvvegname;
        @BindView((R.id.tvprice))
        TextView tvprice;
        @BindView((R.id.tv_qty_total))
        TextView tv_qty_total;
        @BindView(R.id.decrease)
        ImageView decrease;
        @BindView(R.id.increase)
        ImageView increase;
        @BindView(R.id.deleteitem)
        ImageView deleteitem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.deleteitem)
        void OnDeleteItemClick() {
            productTotalValueInterface.rowDeleteItemClickListener(responseCartList, getAdapterPosition());
        }

    }
}
