package com.example.vegito.Interface;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.vegito.Models.ProductList.ResultProductList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface RowClickInterface {


    void onRowClick(int adapterPosition, ArrayList<JSONObject> responseCategoryList, Context context );


    void onRowClickProduct(int adapterPosition, List<ResultProductList> resultProductLists, Context context);
}
