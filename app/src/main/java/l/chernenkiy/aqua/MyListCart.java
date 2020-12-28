package l.chernenkiy.aqua;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import l.chernenkiy.aqua.Fish.Fish;
import l.chernenkiy.aqua.Helpers.CartHelper;
import l.chernenkiy.aqua.Order.Order;
import l.chernenkiy.aqua.ShoppingBasket.ShopBaskFishAdapter;

import static l.chernenkiy.aqua.MainActivity.cartEquipmentItem;
import static l.chernenkiy.aqua.MainActivity.cartItems;

public class MyListCart extends AppCompatActivity {

    public static ListView lvLoadCartFish;
    public static ListView lvLoadCartEquip;
    public ShopBaskFishAdapter shopBaskFishAdapter;
    public ShopBaskFishAdapter shopBaskEquipAdapter;
    private boolean flag;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list_cart);

        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent (MyListCart.this, MainActivity.class);
                finish();
            }
        });

        loadData();
        TextView TvLastShop = findViewById(R.id.txt_last_shop);
        TextView TvCommonToCatalog = findViewById(R.id.txt_common_to_catalog);



        shopBaskFishAdapter = new ShopBaskFishAdapter (getApplicationContext(), cartItems);
        shopBaskEquipAdapter = new ShopBaskFishAdapter (getApplicationContext(), cartEquipmentItem);

        lvLoadCartFish = findViewById(R.id.myListCartFish);
        lvLoadCartEquip = findViewById(R.id.myListCartEquip);
        lvLoadCartFish.setAdapter(shopBaskFishAdapter);
        lvLoadCartEquip.setAdapter(shopBaskEquipAdapter);

        final FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyListCart.this, Order.class);
                startActivity(intent);
            }
        });

        if(!cartItems.isEmpty()){
            TvLastShop.setVisibility(View.INVISIBLE);
            TvCommonToCatalog.setVisibility(View.INVISIBLE);
        }
        else{
            fab.setVisibility(View.INVISIBLE);

        }

        lvLoadCartFish.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    fab.animate().scaleX(1f).scaleY(1f).start();
                    flag = true;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (flag){
                    fab.animate().scaleX(0f).scaleY(0f).start();
                    flag = false;
                }
            }
        });

        lvLoadCartEquip.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    fab.animate().scaleX(1f).scaleY(1f).start();
                    flag = true;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (flag){
                    fab.animate().scaleX(0f).scaleY(0f).start();
                    flag = false;
                }
            }
        });

        calcAndSetFinalSum();



        TvCommonToCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (MyListCart.this, Fish.class);
                    startActivity(intent);
                }
                catch (Exception e){
                e.printStackTrace();
                }
            }
        });



    }

    public void calcAndSetFinalSum() {
        String finalSum = String.valueOf(CartHelper.finalSumOrder ());
        TextView viewFinalSum = findViewById(R.id.txt_final_sum_last_shop);
        if (cartItems.isEmpty() && cartEquipmentItem.isEmpty ()){
            viewFinalSum.setVisibility(View.INVISIBLE);
        }
        viewFinalSum.setText("Сумма: " + finalSum + " грн.");
    }

    public void loadData(){
        SharedPreferences sharedPref = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String fish = sharedPref.getString("fish", null);
        String equipment = sharedPref.getString("equipment", null);
        Type type = new TypeToken<ArrayList<HashMap>> () {}.getType();
        cartItems = gson.fromJson(fish, type);
        cartEquipmentItem = gson.fromJson(equipment, type);

        if(cartItems == null||cartEquipmentItem == null){
            cartItems = new ArrayList<>();
            cartEquipmentItem = new ArrayList<>();

        }
    }
}