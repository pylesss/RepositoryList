package net.pyles.repositorylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.pyles.repositorylist.core.Constants.Companion.PROJECT_TABLE

@Entity(tableName = PROJECT_TABLE)
data class ProjectEntity(
    @PrimaryKey
    val name: String,
    val login: String
)
