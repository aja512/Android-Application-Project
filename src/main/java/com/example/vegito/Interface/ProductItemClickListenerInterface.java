package com.example.vegito.Interface;

import android.content.Context;

import com.example.vegito.Models.ProductList.ResultProductList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface ProductItemClickListenerInterface {

    void onRowClickProduct(int adapterPosition, List<ResultProductList> resultProductLists, Context context);

    void onFavoriteClickProduct(int adapterPosition, List<ResultProductList> resultProductLists, Context context);

}
