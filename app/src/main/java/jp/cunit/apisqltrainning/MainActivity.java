package jp.cunit.apisqltrainning;

import androidx.appcompat.app.AppCompatActivity;
import jp.cunit.apisqltrainning.api.Retrofit;
import jp.cunit.apisqltrainning.model.Post;
import jp.cunit.apisqltrainning.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Button btn_retrofit, btn_room, btn_download, btn_tabviewfrag, btn_bottomnav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_retrofit = findViewById(R.id.btn_retrofit);
        btn_room = findViewById(R.id.btn_room);
        btn_download = findViewById(R.id.btn_download);
        btn_tabviewfrag = findViewById(R.id.btn_tabviewfrag);
        btn_bottomnav = findViewById(R.id.btn_bottomnav);

        //define bottom navigation
        BottomNavigationView bottom_navi = (BottomNavigationView)findViewById(R.id.bottom_navi);
        bottom_navi.getMenu().findItem(R.id.menu_item_home).setChecked(true);
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

        //
        btn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itRetro = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(itRetro);
            }
        });

        //
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itRoom = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(itRoom);
            }
        });

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itRoom = new Intent(MainActivity.this, DownloadManagerActivity.class);
                startActivity(itRoom);
            }
        });

        btn_tabviewfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itRoom = new Intent(MainActivity.this, TabViewpage2FrapmentActivity.class);
                startActivity(itRoom);
            }
        });

        btn_bottomnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itRoom = new Intent(MainActivity.this, BottomNavigationBarActivity.class);
                startActivity(itRoom);
            }
        });


    }
}