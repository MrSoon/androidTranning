package jp.cunit.apisqltrainning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.cunit.apisqltrainning.adapter.CategoryAdapter;
import jp.cunit.apisqltrainning.database.CategoryDatabase;
import jp.cunit.apisqltrainning.model.Category;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private String TAG = "CategoryActivity";
    private EditText edt_name,edt_des;
    private Button btn_save;
    private RecyclerView rcv_category;
    private List<Category> mlistCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        intUi();

        categoryAdapter = new CategoryAdapter();
        mlistCategory = new ArrayList<>();
        categoryAdapter.setData(mlistCategory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_category.setLayoutManager(linearLayoutManager);
        rcv_category.setAdapter(categoryAdapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });
        loadData();
    }

    private void addCategory() {
        String name = edt_name.getText().toString().trim();
        String des = edt_des.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(des)){
            Toast.makeText(this, "Name and Description not null!", Toast.LENGTH_SHORT).show();
            return;
        }

        Category category = new Category(name,des);
        CategoryDatabase.getInstance(this).categoryDao().insertCategory(category);
        Toast.makeText(this, "Insert compled !", Toast.LENGTH_SHORT).show();

        edt_name.setText("");
        edt_des.setText("");

        mlistCategory = CategoryDatabase.getInstance(this).categoryDao().getAllCategory();

        if(mlistCategory.size() > 0){
            loadData();
        }
    }

    private void loadData(){
        mlistCategory = CategoryDatabase.getInstance(this).categoryDao().getAllCategory();
        categoryAdapter.setData(mlistCategory);
    }

    private void intUi(){
        edt_name = findViewById(R.id.edt_name);
        edt_des = findViewById(R.id.edt_des);
        btn_save = findViewById(R.id.btn_save);
        rcv_category = findViewById(R.id.rcv_category);
    }
}