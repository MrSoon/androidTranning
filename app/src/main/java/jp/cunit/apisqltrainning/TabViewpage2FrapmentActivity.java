package jp.cunit.apisqltrainning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import jp.cunit.apisqltrainning.adapter.ViewpageAdapter;

import android.os.Bundle;
import android.widget.TableLayout;

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