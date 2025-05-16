package com.hcmus.information.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hcmus.information.dao.UserInfoDao;
import com.hcmus.information.model.UserInfo;

@Database(entities = {UserInfo.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract UserInfoDao userInfoDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "my-database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}