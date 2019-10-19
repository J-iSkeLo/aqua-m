package l.chernenkiy.aqua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static l.chernenkiy.aqua.CartListAdapter.cartItems;

public class MyListCart extends AppCompatActivity {
    public static ListView lvLoadCart;
    public CartListAdapter cartListAdapter;

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
                new Intent(MyListCart.this, MainActivity.class);
                finish();
            }
        });

        loadData();

        cartListAdapter = new CartListAdapter(getApplicationContext(), cartItems);

        lvLoadCart = findViewById(R.id.myListCart);
        lvLoadCart.setAdapter(cartListAdapter);

        calcAndSetFinalSum();

        TextView TvLastShop = findViewById(R.id.txt_last_shop);
        TextView TvCommonToCatalog = findViewById(R.id.txt_common_to_catalog);
        if(!cartItems.isEmpty()){
            TvLastShop.setVisibility(View.INVISIBLE);
            TvCommonToCatalog.setVisibility(View.INVISIBLE);
        }
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
        String finalSum = String.valueOf(CartHelper.countFinalSum(cartItems));
        TextView viewFinalSum = findViewById(R.id.txt_final_sum_last_shop);
        if (cartItems.isEmpty()){
            viewFinalSum.setVisibility(View.INVISIBLE);
        }
        viewFinalSum.setText("Сумма: " + finalSum + " грн.");
    }

    public void loadData(){
        SharedPreferences sharedPref = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("cart items", null);
        Type type = new TypeToken<ArrayList<HashMap>>() {}.getType();
        cartItems = gson.fromJson(json, type);

        if(cartItems == null){
            cartItems = new ArrayList<>();
        }
    }
}