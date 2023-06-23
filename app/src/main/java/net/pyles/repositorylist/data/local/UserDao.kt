package net.pyles.repositorylist.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import net.pyles.repositorylist.core.Constants.Companion.PROJECT_TABLE
import net.pyles.repositorylist.core.Constants.Companion.USER_TABLE

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAllUsers(userEntities: List<UserEntity>)

    @Query("DELETE FROM $USER_TABLE")
    suspend fun clearAllUsers()

    @Query("SELECT * FROM $USER_TABLE")
    fun userPagingSource(): PagingSource<Int, UserEntity>

    @Upsert
    suspend fun upsertUserProjects(projectEntity: List<ProjectEntity>)

    @Query("DELETE FROM $PROJECT_TABLE")
    suspend fun cleanAllProjects()

    @Query("SELECT * FROM $PROJECT_TABLE WHERE login = :login")
    fun projectPagingSource(login: String): PagingSource<Int, ProjectEntity>

    @Query("SELECT * FROM $PROJECT_TABLE WHERE login = :login")
    suspend fun getUserProjects(login: String): List<ProjectEntity>
}