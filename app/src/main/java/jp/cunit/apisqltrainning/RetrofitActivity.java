package jp.cunit.apisqltrainning;

import androidx.appcompat.app.AppCompatActivity;
import jp.cunit.apisqltrainning.api.Retrofit;
import jp.cunit.apisqltrainning.model.Post;
import jp.cunit.apisqltrainning.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RetrofitActivity extends AppCompatActivity {
    private String TAG = "RetrofitActivity";
    private Button btn_api, btn_api_post;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        btn_api = findViewById(R.id.btn_api);
        btn_api_post = findViewById(R.id.btn_api_post);
        tv = findViewById(R.id.tv);

        //GET
        btn_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.retrofit.getUserInfo().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(RetrofitActivity.this, "ok", Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < user.getData().size(); i++)
                            {
                                Log.d(TAG, "Name : " + user.getData().get(i).getEmployeeName());
                            }

                        }else{
                            Toast.makeText(RetrofitActivity.this, "False " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(RetrofitActivity.this, "False to get api", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //POST
        btn_api_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post(100,1,"sondo","verry strong boy");
                Retrofit.retrofit.sendPost(post).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post pn = response.body();
                        if(pn != null && response.isSuccessful()){
                            tv.setText(post.toString());
                        }else{
                            Toast.makeText(RetrofitActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(RetrofitActivity.this, "Post false", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}