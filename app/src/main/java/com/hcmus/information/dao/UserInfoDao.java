package com.hcmus.information.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.hcmus.information.model.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {
    @SuppressWarnings(RoomWarnings.QUERY_MISMATCH)
    @Query("SELECT studentId,image,fullName,gender,major,status FROM user_info")
    List<UserInfo> getAll();

    @Query("SELECT * FROM user_info WHERE studentId = :studentId")
    UserInfo getByStudentId(String studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserInfo... userInfos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserInfo> userInfos);

    @Query("DELETE FROM user_info")
    void deleteAll();

    @Query("DELETE FROM user_info WHERE studentId = :studentId")
    void deleteById(String studentId);

}
