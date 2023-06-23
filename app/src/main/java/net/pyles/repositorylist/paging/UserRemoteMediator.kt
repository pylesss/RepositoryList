package net.pyles.repositorylist.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import net.pyles.repositorylist.core.Constants.Companion.PAGE_SIZE
import net.pyles.repositorylist.data.UserRepository
import net.pyles.repositorylist.data.local.UserDb
import net.pyles.repositorylist.data.local.UserEntity
import net.pyles.repositorylist.data.remote.RemoteApi
import net.pyles.repositorylist.data.toUserEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class UserRemoteMediator @Inject constructor(
    private val userDb: UserDb,
    private val remoteApi: RemoteApi,
    private val repository: UserRepository
): RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>,
    ): MediatorResult {
        return try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            val position = repository.getUserSize()
            val users = remoteApi.getUsers(
                perPage = state.config.pageSize,
                since = position
            )
            repository.setUserSize(position + PAGE_SIZE)
            userDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDb.userDao.clearAllUsers()
                }
                val userEntities = users.map { it.toUserEntity() }
                userDb.userDao.upsertAllUsers(userEntities)
            }
            MediatorResult.Success( endOfPaginationReached = users.isEmpty() )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}