package jp.cunit.apisqltrainning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import jp.cunit.apisqltrainning.adapter.ViewpageAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationBarActivity extends AppCompatActivity {

    private ViewPager2 viewpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);
        viewpage = findViewById(R.id.viewpage2);

        ViewpageAdapter viewpageAdapter = new ViewpageAdapter(this);
        viewpage.setAdapter(viewpageAdapter);

        //define bottom navigation
        BottomNavigationView bottom_navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
        bottom_navi.getMenu().findItem(R.id.menu_item_cart).setChecked(true);
        bottom_navi.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_item_home:
                    viewpage.setCurrentItem(0);
                    break;
                case R.id.menu_item_setting:
                    viewpage.setCurrentItem(1);
                    break;
                case R.id.menu_item_cart:
                    viewpage.setCurrentItem(2);
                    break;
            }
            return true;
        });
        //end

        //set checked of item when change fragment
        viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottom_navi.getMenu().findItem(R.id.menu_item_home).setChecked(true);
                        break;
                    case 1:
                        bottom_navi.getMenu().findItem(R.id.menu_item_setting).setChecked(true);
                        break;
                    case 2:
                        bottom_navi.getMenu().findItem(R.id.menu_item_cart).setChecked(true);
                        break;
                }
            }
        });

    }
}