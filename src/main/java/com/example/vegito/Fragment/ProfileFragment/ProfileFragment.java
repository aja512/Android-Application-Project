package com.example.vegito.Fragment.ProfileFragment;

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

import com.example.vegito.Adapter.CategoryListAdapter;
import com.example.vegito.Adapter.ProductListAdapter;
import com.example.vegito.Fragment.ProductDetailFragment.ProductDetailFragment;
import com.example.vegito.Interface.CallBackListener;
import com.example.vegito.Interface.ProductItemClickListenerInterface;
import com.example.vegito.Interface.RowClickInterface;
import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.ProductList.ResponseProductList;
import com.example.vegito.Models.ProductList.ResultProductList;
import com.example.vegito.R;
import com.example.vegito.Utils.BaseFragment;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.OfflineJsonLoader;
import com.example.vegito.Utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

public class ProfileFragment extends BaseFragment implements
        ProfileFragmentContract.iprofileFragmentView, RowClickInterface,
        ProductItemClickListenerInterface {


    RecyclerView rv_category;
    RecyclerView rv_allprducts;
    Context context;
    private static final String TAG_PROFILE = "productDetails";
    public static String CURRENT_TAG = TAG_PROFILE;
    OfflineJsonLoader offlineJsonLoader = new OfflineJsonLoader();
    ArrayList<JSONObject> arrayList = new ArrayList();
    ProfileFragmentContract.iprofileFragmentPresenter iprofileFragmentPresenter;
    ViewGroup view;
    List<ResultProductList> mResultProdList;
    ProductListAdapter mProdListAdapter = null;
    String cartId;
    private CallBackListener callBackListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.profile_fragment, container, false);
        //return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_category = (RecyclerView) view.findViewById(R.id.rv_category);
        rv_allprducts = (RecyclerView) view.findViewById(R.id.rv_allprducts);
        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_allprducts.setLayoutManager(LayoutManagaer);
        iprofileFragmentPresenter = new ProfileFragmentPresenter(this);
        parseJsonDataCategoryList(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof CallBackListener)
            callBackListener = (CallBackListener) getActivity();
    }

    @Override
    public void getResponseProductList(Response<ResponseProductList> response, Context context) {
        if (response != null) {

            if (response.body().getCode() != null && response.body().getCode().equalsIgnoreCase("200")) {
                if (response.body().getResults() != null) {
                    mResultProdList = response.body().getResults();
                    mProdListAdapter = new ProductListAdapter(this, mResultProdList, context);
                    rv_allprducts.setAdapter(mProdListAdapter);
                    mProdListAdapter.setProductItemClickedResultListener(this);

                }
            }
        }
    }

    @Override
    public void getAddedResponse(Response<ResponseAddToCart> response) {

        if (response.body().getCode().equals("200")) {
            if (response.body().getMsg() != null) {
                showToast(response.body().getMsg());
                iprofileFragmentPresenter.getCartList(Constant.UserID);
            }
        }

    }

    @Override
    public void getCartListSuccess(ResponseGetCartList response) {

        if(callBackListener != null)
            callBackListener.onCallBack(response);

    }

    @Override
    public void onRowClick(int adapterPosition, ArrayList<JSONObject> responseCategoryList, Context context) {

        try {

            iprofileFragmentPresenter.getProductList(responseCategoryList.get(adapterPosition).getInt("categoryid"), context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRowClickProduct(int adapterPosition, List<ResultProductList> resultProductLists, Context context) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", adapterPosition);
        bundle.putSerializable(context.getResources().getString(R.string.productDetails), (Serializable) resultProductLists);
        productDetailFragment.setArguments(bundle);
        LoadFragment(productDetailFragment, context);
    }

    @Override
    public void onFavoriteClickProduct(int adapterPosition, List<ResultProductList> resultProductLists, Context context) {
        String timeStamp = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
        cartId = SessionManager.getStringFromPreferences(context, getActivity().getResources().getString(R.string.cartID));
        iprofileFragmentPresenter.addProductToCart(new RequestAddToCart(resultProductLists.get(adapterPosition).getProdId(), 1,
                Integer.parseInt(cartId), productUnitID(resultProductLists.get(adapterPosition)), timeStamp));

    }



    private int productUnitID(ResultProductList resultProductList) {
        if (resultProductList.getUnitData().size() > 1) {
            return resultProductList.getUnitData().get(2).getProdUnitId();
        } else {
            return resultProductList.getUnitData().get(0).getProdUnitId();

        }
    }


    private void LoadFragment(Fragment fragment, Context context) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void parseJsonDataCategoryList(Context context) {
        try {

            JSONArray jsonarray = new JSONArray(offlineJsonLoader.loadJSONFromAsset(context, "Categorylist.json"));

            for (int i = 0; i < jsonarray.length(); i++) {
                arrayList.add(jsonarray.getJSONObject(i));
            }
            sendCategoryListToAdapter(arrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendCategoryListToAdapter(ArrayList<JSONObject> arrayList) {
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        CategoryListAdapter horizontalAdapter = new CategoryListAdapter(arrayList, context);
        horizontalAdapter.setListener(this);
        rv_category.setLayoutManager(horizontalLayoutManagaer);
        rv_category.setAdapter(horizontalAdapter);
        iprofileFragmentPresenter.getProductList(0, context);
    }


//

}
