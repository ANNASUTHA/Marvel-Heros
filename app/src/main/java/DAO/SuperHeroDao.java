package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Entity.SuperHeroEntity;

@Dao
public interface SuperHeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SuperHeroEntity superHeroEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SuperHeroEntity... superHeroEntities);

    @Query("SELECT * FROM Hero_Table WHERE realName")
    LiveData<List<SuperHeroEntity>> fetchHeroDetails();

    @Delete
    void delete(SuperHeroEntity superHeroEntity);
}
