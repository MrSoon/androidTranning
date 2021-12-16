package jp.cunit.apisqltrainning.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jp.cunit.apisqltrainning.model.Post;
import jp.cunit.apisqltrainning.model.User;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Retrofit {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-mm-dd HH:mm:ss")
            .create();
    //GET https://dummy.restapiexample.com/api/v1/employees
    // POST https://jsonplaceholder.typicode.com/posts
    Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Retrofit.class);

    @GET("api/v1/employees")
    Call<User> getUserInfo();

    @POST("posts")
    Call<Post> sendPost(@Body Post post);
}
