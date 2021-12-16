package jp.cunit.apisqltrainning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.cunit.apisqltrainning.R;
import jp.cunit.apisqltrainning.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private List<Category> mlistCategory;

    public void setData(List<Category> list){
        this.mlistCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = mlistCategory.get(position);
        if(category == null){
            return;
        }

        holder.tv_itname.setText(category.getName());
        holder.tv_itdes.setText(category.getDescription());
    }

    @Override
    public int getItemCount() {
        if(mlistCategory != null){
            return mlistCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_itname;
        private TextView tv_itdes;
        private Button btn_update;
        private Button btn_delete;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_itname = itemView.findViewById(R.id.tv_itname);
            tv_itdes = itemView.findViewById(R.id.tv_itdes);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
