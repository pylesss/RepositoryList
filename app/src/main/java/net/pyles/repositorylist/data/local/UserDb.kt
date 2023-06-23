package net.pyles.repositorylist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, ProjectEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDb : RoomDatabase(){

    abstract val userDao: UserDao
}