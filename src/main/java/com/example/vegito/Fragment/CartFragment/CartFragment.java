package com.example.vegito.Fragment.CartFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vegito.Adapter.CartListAdapter;
import com.example.vegito.Fragment.PlaceOrderFragment.PlaceOrder;
import com.example.vegito.Interface.CallBackListener;
import com.example.vegito.Interface.ProductTotalValueInterface;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.CartList.ResultGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.UpdateCart.RequestUpdateCart;
import com.example.vegito.Models.UpdateCart.ResponseUpdateCart;
import com.example.vegito.R;
import com.example.vegito.Utils.BaseFragment;
import com.example.vegito.Utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class CartFragment extends BaseFragment implements CartFragmentContract.cartFragmentView,
        ProductTotalValueInterface {

    Context context;
    @BindView(R.id.cartLayout)
    RecyclerView cartLayout;
    @BindView(R.id.tv_totalvalue)
    TextView tv_totalvalue;
    @BindView(R.id.btnCheckedOut)
    Button btnCheckedOut;
    @BindView(R.id.lin_result)
    LinearLayout lin_result;
    @BindView(R.id.noitemadded)
    TextView noitemadded;
    ResponseGetCartList responseGetCartList;
    int totalCheckedValueCost;
    int totalValue = 0;
    CartFragmentContract.cartFragmentPresenter cartFragmentPresenter;
    int userId;
    private CallBackListener callBackListener;
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_addtocart,
                container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        cartFragmentPresenter = new CartFragmentPresenter(this);
        cartFragmentPresenter.getCartList(Constant.UserID);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof CallBackListener)
            callBackListener = (CallBackListener) getActivity();
    }


    @Override
    public void getCartListSuccess(ResponseGetCartList response) {
        totalValue = 0;
        totalCheckedValueCost = 0;
        if (response.getCode().equalsIgnoreCase("200")) {
            if (response.getResults() != null) {
                if (response.getResults().size() > 0) {
                    lin_result.setVisibility(View.VISIBLE);
                    noitemadded.setVisibility(View.GONE);
                    for (int i = 0; i < response.getResults().size(); i++) {
                        totalCheckedValueCost = Integer.valueOf(response.getResults().get(i).getProdUnitPrice()) * response.getResults().get(i).getUserCartItemProdQty();
                        totalValue += totalCheckedValueCost;
                    }

                    tv_totalvalue.setText(String.valueOf(totalValue) + ".00");

                    LinearLayoutManager LayoutManagaer
                            = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    CartListAdapter adapter = new CartListAdapter(response.getResults(), context);
                    adapter.setListener(this);
                    cartLayout.setLayoutManager(LayoutManagaer);
                    cartLayout.setAdapter(adapter);
                    if (callBackListener != null)
                        callBackListener.onCallBack(response);
                } else {
                    lin_result.setVisibility(View.GONE);
                    noitemadded.setVisibility(View.VISIBLE);
                    if (callBackListener != null)
                        callBackListener.onCallBack(response);
                }
            }
        }
    }

    @Override
    public void getUpdateCartListSuccess(ResponseUpdateCart responseUpdateCart) {
        showToast(responseUpdateCart.getMsg());
        cartFragmentPresenter.getCartList(Constant.UserID);

    }

    @Override
    public void getDeleteSuccess(Response<ResponseDeleteCart> response) {

        showToast(response.body().getMsg());
        cartFragmentPresenter.getCartList(Constant.UserID);
    }

    @Override
    public void rowClickListener(List<ResultGetCartList> responseCartList, int minteger, int adapterPosition) {

        cartFragmentPresenter.updateCartList(new RequestUpdateCart(responseCartList.get(adapterPosition).getProdId(), minteger, responseCartList.get(adapterPosition).getUserCartId()));

    }

    @Override
    public void rowDeleteItemClickListener(List<ResultGetCartList> responseCartList, int adapterPosition) {
        cartFragmentPresenter.deleteProductFromCart
                (new RequestDeleteCart(responseCartList.get(adapterPosition).getUserCartId(), responseCartList.get(adapterPosition).getProdId()));
    }

    @OnClick(R.id.btnCheckedOut)
    void OnClickBtn() {
        fragment = new PlaceOrder();
        Constant.TotalValue=tv_totalvalue.getText().toString();
        LoadFragment(fragment, context);
    }

    private void LoadFragment(Fragment fragment, Context context) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
