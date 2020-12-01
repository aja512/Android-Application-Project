package com.example.vegito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vegito.Fragment.ProfileFragment.ProfileFragment;
import com.example.vegito.Interface.ProductItemClickListenerInterface;
import com.example.vegito.Models.ProductList.ResultProductList;
import com.example.vegito.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    ProductItemClickListenerInterface mProductItemClickListenerInterface;
    List<ResultProductList> resultProductLists;
    Context context;
    ProfileFragment fragment;

    public void setProductItemClickedResultListener(ProductItemClickListenerInterface listener) {
        this.mProductItemClickListenerInterface = listener;
    }


    public ProductListAdapter(ProfileFragment fragment, List<ResultProductList> response, Context context) {
        this.fragment = fragment;
        resultProductLists = response;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listproduct, viewGroup, false);
        return new ProductListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder viewHolder, int i) {
        String nameofProduct = resultProductLists.get(i).getProdImage();
        String finalProductName = nameofProduct.substring(nameofProduct.lastIndexOf('/') + 1);
        String vegieName = finalProductName.substring(0, finalProductName.indexOf("."));
        if (!vegieName.contains("-")) {
            Context context = viewHolder.ivCateogry.getContext();
            int id = context.getResources().getIdentifier(vegieName, "drawable", context.getPackageName());
            viewHolder.ivCateogry.setImageResource(id);
        } else {
            Context context = viewHolder.ivCateogry.getContext();
            int id = context.getResources().getIdentifier(vegieName.replace("-", ""), "drawable", context.getPackageName());
            viewHolder.ivCateogry.setImageResource(id);
        }


        viewHolder.tvvegname.setText(resultProductLists.get(i).getProdName());
        if (resultProductLists.get(i).getUnitData().size() > 1) {
            viewHolder.tvprice.setText(context.getResources().getString(R.string.rs)
                    + " " + resultProductLists.get(i).getUnitData().get(2).getProdUnitPrice()
                    + ".00/kg");
        } else {
            viewHolder.tvprice.setText(context.getResources().getString(R.string.rs)
                    + " " + resultProductLists.get(i).getUnitData().get(0).getProdUnitPrice()
                    + ".00/kg");
        }
    }

    @Override
    public int getItemCount() {
        return resultProductLists != null ? resultProductLists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_productdetail)
        LinearLayout ll_productdetail;

        @BindView(R.id.ivCateogry)
        ImageView ivCateogry;

        @BindView(R.id.tvvegname)
        TextView tvvegname;

        @BindView(R.id.tvprice)
        TextView tvprice;

        @BindView(R.id.heartimg)
        ImageView heartimg;
        @BindView(R.id.redheart)
        ImageView redheart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.favouriteLayout)
        void OnClick() {
            int position = getAdapterPosition();
            boolean isSelected = resultProductLists.get(position).isSelected();
            resultProductLists.get(position).setSelected(!isSelected);

            if (resultProductLists.get(position).isSelected()) {
                mProductItemClickListenerInterface.onFavoriteClickProduct(getAdapterPosition(), resultProductLists, context);
                redheart.setVisibility(View.VISIBLE);
                heartimg.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.ll_productdetail)
        void OnClickListItem() {
            mProductItemClickListenerInterface.onRowClickProduct(getAdapterPosition(), resultProductLists, context);

        }
    }


}
