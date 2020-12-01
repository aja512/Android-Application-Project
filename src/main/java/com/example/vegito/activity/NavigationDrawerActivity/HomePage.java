package com.example.vegito.activity.NavigationDrawerActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vegito.Fragment.CartFragment.CartFragment;
import com.example.vegito.Fragment.CategoryFragment;
import com.example.vegito.Fragment.FavoriteFragment;
import com.example.vegito.Fragment.ProfileFragment.ProfileFragment;
import com.example.vegito.Fragment.PromotionFragment;
import com.example.vegito.Interface.CallBackListener;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.LoginModel.Result;
import com.example.vegito.R;
import com.example.vegito.Room.DatabaseClient;
import com.example.vegito.Utils.BaseActivty;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.SessionManager;
import com.example.vegito.Utils.StatusBar;
import com.example.vegito.activity.LoginScreen.LoginScreenActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

public class HomePage extends BaseActivty implements HomePageContract.iHomePageView, CallBackListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    Toolbar toolbar;
    private ImageView imgNavHeaderBg;
    private de.hdodenhof.circleimageview.CircleImageView imgProfile;
    private TextView txtName, txtWebsite, tv_subscribe;
    // urls to load navigation header background image
    // and profile image

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    Context context;
    ImageView menuLeft, iv_cart;
    TextView textCartItemCount;
    int mCartItemCount = 0;
    String timeStamp;
    HomePageContract.iHomePagePresenter homePagePresenter;
    Result taskList = new Result();
    private CallBackListener callBackListener;
    TextView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        homePagePresenter = new HomePagePresenter(this);
        StatusBar.MatchStatusBGEqualsAndAboveLollipop(this, context);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_subscribe = (TextView) findViewById(R.id.tv_subscribe);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        logout = (TextView) findViewById(R.id.logout);
        navHeader = navigationView.getHeaderView(0);
        imgProfile = navHeader.findViewById(R.id.img_profile);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        timeStamp = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
        getDataFromDataBase();
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        navigationView.setItemIconTintList(null);
        callBackListener = this;

        loadNavHeader();
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.putStringInPreferences(context, null, getResources().getString(R.string.cartID));
                DeleteTask(taskList);

            }
        });
    }

    @OnClick(R.id.tv_subscribe)
    void onClick(){

        
    }

    private void DeleteTask(Result taskList) {

        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .accountInfoDao()
                        .delete();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                finish();

                Intent intent = new Intent(context, LoginScreenActivity.class);
                startActivity(intent);

            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();
    }


    private void getDataFromDataBase() {
        class GetTasks extends AsyncTask<Void, Void, Result> {

            @Override
            protected Result doInBackground(Void... voids) {
                taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .accountInfoDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(Result tasks) {
                super.onPostExecute(tasks);
                Constant.UserID = tasks.getUserId();
                SessionManager.putStringInPreferences(context, tasks.getIsUserSubscribed(), getResources().getString(R.string.userSubscribed));
                if (SessionManager.getStringFromPreferences(context, getResources().getString(R.string.userSubscribed)) != null) {
                    if (SessionManager.getStringFromPreferences(context, getResources().getString(R.string.userSubscribed)).equals("Y")) {
                        tv_subscribe.setVisibility(View.GONE);
                    } else {
                        tv_subscribe.setVisibility(View.VISIBLE);
                    }
                }
                homePagePresenter.creatCart(Constant.UserID, timeStamp);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }



    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {

        Glide.with(this).load(R.drawable.profileuser)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);


        // loading header background image
        Glide.with(this).load(R.drawable.spl)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);


    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 1:
                // photos
                drawer.closeDrawers();
            case 2:
                // movies fragment
                PromotionFragment promotionFragment = new PromotionFragment();
                return promotionFragment;
            case 3:
                // notifications fragment
                FavoriteFragment favoritesFragment = new FavoriteFragment();
                return favoritesFragment;

            case 4:
                // settings fragment
                CategoryFragment categoryFragment = new CategoryFragment();
                return categoryFragment;
            default:
                return new ProfileFragment();
        }
    }


    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }


        if (shouldLoadHomeFragOnBackPress) {

            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
        setupBadge();


        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_cart: {
                CartFragment cartFragment = new CartFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.userId), taskList.getUserId());
                cartFragment.setArguments(bundle);
                LoadFragment(cartFragment, context);
                //homePagePresenter.getCartList(taskList.getUserId());
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadFragment(CartFragment cartFragment, Context context) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, cartFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void getCreateCartSuccess(Integer cartId) {

        if (cartId != null) {
            SessionManager.putStringInPreferences(context, String.valueOf(cartId), getResources().getString(R.string.cartID));
            homePagePresenter.getCartList(Constant.UserID);
        }

    }

    @Override
    public void getCartListSuccess(ResponseGetCartList response) {

        if (callBackListener != null)
            callBackListener.onCallBack(response);

    }


    @Override
    public void onCallBack(ResponseGetCartList response) {

        if (response.getResults() != null) {
            if (response.getResults().size() > 0) {
                int count = response.getResults().size();
                textCartItemCount.setText(String.valueOf(count));
                textCartItemCount.setVisibility(View.VISIBLE);
            } else {
                textCartItemCount.setVisibility(View.GONE);
            }
        }

    }


}
