package com.example.vegito.Interface;

import com.example.vegito.Models.CartList.ResultGetCartList;

import java.util.List;

public interface ProductTotalValueInterface {
    void rowClickListener(List<ResultGetCartList> responseCartList, int minteger, int adapterPosition);

    void rowDeleteItemClickListener(List<ResultGetCartList> responseCartList, int adapterPosition);
}
