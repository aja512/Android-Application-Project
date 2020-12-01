package com.example.vegito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vegito.Fragment.ProfileFragment.ProfileFragment;
import com.example.vegito.Interface.RowClickInterface;
import com.example.vegito.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    ArrayList<JSONObject> responseCategoryList;
    RowClickInterface listener;
    Context context;


    public void setListener(RowClickInterface listener) {
        this.listener = listener;
    }

    public CategoryListAdapter(ArrayList<JSONObject> response, Context context) {
        responseCategoryList = response;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listcategory, viewGroup, false);
        return new CategoryListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder viewHolder, int i) {

        try {
            viewHolder.tvCatName.setText(responseCategoryList.get(i).getString("categoryName"));
            Context context = viewHolder.ivCateogry.getContext();
            int id = context.getResources().getIdentifier(responseCategoryList.get(i).getString("image"), "drawable", context.getPackageName());
            viewHolder.ivCateogry.setImageResource(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return responseCategoryList != null ? responseCategoryList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvCatName)
        TextView tvCatName;
        @BindView(R.id.ivCateogry)
        ImageView ivCateogry;
        @BindView(R.id.card_view)
        CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        void onClick() {
            listener.onRowClick(getAdapterPosition(), responseCategoryList, context);
        }
    }


}
