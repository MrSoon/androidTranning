package jp.cunit.apisqltrainning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import jp.cunit.apisqltrainning.adapter.ViewpageAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabViewpage2FrapmentActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager2 viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpage2_frapment);
        tablayout = findViewById(R.id.tablayout);
        viewpage = findViewById(R.id.viewpage);

        //define bottom navigation
        BottomNavigationView bottom_navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
        bottom_navi.getMenu().findItem(R.id.menu_item_setting).setChecked(true);
        bottom_navi.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_item_home:
                    Intent it = new Intent(this, MainActivity.class);
                    startActivity(it);
                    break;
                case R.id.menu_item_setting:
                    Intent it1 = new Intent(this, TabViewpage2FrapmentActivity.class);
                    startActivity(it1);
                    break;
                case R.id.menu_item_cart:
                    Intent it2 = new Intent(this, BottomNavigationBarActivity.class);
                    startActivity(it2);
                    break;
            }
            return true;
        });
        //end

        ViewpageAdapter viewpageAdapter = new ViewpageAdapter(this);
        viewpage.setAdapter(viewpageAdapter);
        new TabLayoutMediator(tablayout, viewpage, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Home");
                    break;
                case 1:
                    tab.setText("Info");
                    break;
                case 2:
                    tab.setText("Detail");
                    break;
            }

        }).attach();

    }
}