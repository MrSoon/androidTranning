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

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Button btn_retrofit, btn_room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_retrofit = findViewById(R.id.btn_retrofit);
        btn_room = findViewById(R.id.btn_room);


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

    }
}