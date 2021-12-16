package jp.cunit.apisqltrainning.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import jp.cunit.apisqltrainning.model.Category;

@Dao
public interface CategoryDao {
    @Insert
    void insertCategory(Category category);

    @Query("SELECT * FROM tb_category")
    List<Category> getAllCategory();
}
