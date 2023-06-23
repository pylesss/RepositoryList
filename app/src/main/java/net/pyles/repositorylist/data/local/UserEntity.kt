package net.pyles.repositorylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.pyles.repositorylist.core.Constants.Companion.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey
    val login: String
)