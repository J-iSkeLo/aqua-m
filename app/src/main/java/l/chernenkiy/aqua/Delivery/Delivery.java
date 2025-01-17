package l.chernenkiy.aqua.Delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import l.chernenkiy.aqua.Helpers.SectionPageAdapter;
import l.chernenkiy.aqua.MainActivity;
import l.chernenkiy.aqua.MySettings;
import l.chernenkiy.aqua.R;

public class Delivery extends AppCompatActivity {
    Toolbar toolbar;
    MySettings mySettings = new MySettings();

    @Override
    protected void onPause() {
        super.onPause();
        mySettings.saveFishShopBask();
        mySettings.saveEquipShopBask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySettings.loadEquipShopBask(getApplicationContext());
        mySettings.loadFishShopBask(getApplicationContext());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);

        toolbar();

        ViewPager vp = findViewById (R.id.container);
        setupViewPager(vp);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);

    }

    private void setupViewPager(ViewPager vp){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Wholesale(), "Оптовым покупателям");
        adapter.addFragment(new Retail(), "Розничным покупателям");
        vp.setAdapter(adapter);
    }


    private void toolbar() {
        toolbar = findViewById(R.id.toolbarDelivery);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null ){
            return;
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(Delivery.this, MainActivity.class);
                finish();
            }
        });
    }
}
