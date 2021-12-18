package jp.cunit.apisqltrainning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);

        //define bottom navigation
        BottomNavigationView bottom_navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
        bottom_navi.getMenu().findItem(R.id.menu_item_cart).setChecked(true);
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

    }
}