package com.example.vegito.Fragment.ProductDetailFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vegito.Interface.CallBackListener;
import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.ProductList.ResultProductList;
import com.example.vegito.R;
import com.example.vegito.Utils.BaseFragment;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ProductDetailFragment extends BaseFragment implements ProductFragmentContract.productFragmentView {

    Context context;
    @BindView(R.id.decrease)
    TextView decrease;
    @BindView(R.id.increase)
    TextView increase;
    @BindView(R.id.integer_number)
    TextView integer_number;
    @BindView(R.id.tv_veggiename)
    TextView tv_veggiename;
    @BindView(R.id.tv_rupees)
    TextView tv_rupees;
    @BindView(R.id.btnAddToCart)
    Button btnAddToCart;
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profileImage;
    List<ResultProductList> resultProductList;
    private CallBackListener callBackListener;

    int minteger = 0;
    int mPosition;
    ProductFragmentContract.productFragmentPresenter productFragmentPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_prodcut_details,
                container, false);
        ButterKnife.bind(this, view);
        context = this.getActivity();
        productFragmentPresenter = new ProductDetailsPresenter(this);
        Bundle bundle = getArguments();
        mPosition = bundle.getInt("position");
        resultProductList = (List<ResultProductList>) bundle.getSerializable(getActivity().getResources().getString(R.string.productDetails));
        tv_veggiename.setText(resultProductList.get(mPosition).getProdName());
        productRupees(resultProductList.get(mPosition));
        tv_rupees.setText(getResources().getString(R.string.rs) + " " + productRupees(resultProductList.get(mPosition)) + ".00/kg");
        if (!getVegieName(resultProductList.get(mPosition)).contains("-")) {
            Context context = profileImage.getContext();
            int id = context.getResources().getIdentifier(getVegieName(resultProductList.get(mPosition)), "drawable", context.getPackageName());
            profileImage.setImageResource(id);
        } else {
            Context context = profileImage.getContext();
            int id = context.getResources().getIdentifier(getVegieName(resultProductList.get(mPosition)).replace("-", ""), "drawable", context.getPackageName());
            profileImage.setImageResource(id);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof CallBackListener)
            callBackListener = (CallBackListener) getActivity();
    }


    private String productRupees(ResultProductList resultProductList) {
        if (resultProductList.getUnitData().size() > 1) {
            return resultProductList.getUnitData().get(2).getProdUnitPrice();
        } else {
            return resultProductList.getUnitData().get(0).getProdUnitPrice();

        }
    }

    private String getVegieName(ResultProductList resultProductList) {
        String nameofProduct = resultProductList.getProdImage();
        String finalProductName = nameofProduct.substring(nameofProduct.lastIndexOf('/') + 1);
        String vegieName = finalProductName.substring(0, finalProductName.indexOf("."));
        return vegieName;
    }

    @OnClick(R.id.increase)
    void increase() {
        minteger = minteger + 1;
        display(minteger);
    }

    @OnClick(R.id.decrease)
    void decrease() {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {
        if (number >= 0) {

            integer_number.setText("" + number);
            tv_rupees.setText(getResources().getString(R.string.rs) + " " + Integer.parseInt(productRupees(resultProductList.get(mPosition))) * number + ".00/kg");
        }

    }

    @OnClick(R.id.btnAddToCart)
    void btnAddToCart() {
        addProductToCart(resultProductList, mPosition);
    }

    private void addProductToCart(List<ResultProductList> resultProductLists, int adapterPosition) {
        String timeStamp = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());

        productFragmentPresenter.addProductToCart(new RequestAddToCart(resultProductLists.get(adapterPosition).getProdId(), minteger,
                Integer.parseInt(SessionManager.getStringFromPreferences(context, getActivity().getResources().getString(R.string.cartID))),
                productUnitID(resultProductLists.get(adapterPosition)), timeStamp));
    }

    private int productUnitID(ResultProductList resultProductList) {
        if (resultProductList.getUnitData().size() > 1) {
            return resultProductList.getUnitData().get(2).getProdUnitId();
        } else {
            return resultProductList.getUnitData().get(0).getProdUnitId();

        }
    }


    @Override
    public void getSuccessResult(Response<ResponseAddToCart> response) {
        if (response.body().getCode().equals("200")) {
            showToast(response.body().getMsg());
            productFragmentPresenter.getCartList(Constant.UserID);
        }


    }

    @Override
    public void getCartListSuccess(ResponseGetCartList response) {
        if(callBackListener != null)
            callBackListener.onCallBack(response);
    }
}
