package TrackRoom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.annasutha.marvelsuperheros.MainActivity;

import DAO.SuperHeroDao;
import Entity.SuperHeroEntity;
import Utils.DateTypeConverter;


@Database(entities = {SuperHeroEntity.class},version = 60, exportSchema = false)


@TypeConverters({DateTypeConverter.class})
public abstract class TrackRoomDatabase extends RoomDatabase {
    private static TrackRoomDatabase INSTANCE;
    public abstract SuperHeroDao superHeroDao();

    private static final Migration migration2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE rep_table (realName TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, team TEXT NOT NULL,firstAppearance TEXT NOT NULL,createdBy TEXT NOT NULL,publisher TEXT NOT NULL,imageUrl TEXT NOT NULL,bio TEXT NOT NULL)");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_rep_table ON Hero_Table (realName)");
        }
    };

public static TrackRoomDatabase getDatabase(final Context context){
    if (INSTANCE == null) {
        synchronized (TrackRoomDatabase.class) {
            if (INSTANCE == null) {
                // Create database here
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TrackRoomDatabase.class, "track_database")
                        .addMigrations(migration2_3)
                        .build();
            }
        }
    }
    return INSTANCE;
}

/*@Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }*/
}
