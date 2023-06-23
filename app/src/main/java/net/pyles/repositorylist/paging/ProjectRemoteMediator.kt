package net.pyles.repositorylist.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import net.pyles.repositorylist.core.Constants.Companion.PAGE_SIZE
import net.pyles.repositorylist.data.UserRepository
import net.pyles.repositorylist.data.local.ProjectEntity
import net.pyles.repositorylist.data.local.UserDb
import net.pyles.repositorylist.data.remote.RemoteApi
import net.pyles.repositorylist.data.toProjectEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ProjectRemoteMediator @Inject constructor(
    private val userDb: UserDb,
    private val remoteApi: RemoteApi,
    private val repository: UserRepository
): RemoteMediator<Int, ProjectEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProjectEntity>
    ): MediatorResult {
        return try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            val login = repository.getLogin()
            val position = repository.getProjectSize(login)
            val projects = remoteApi.getProjects(
                perPage = state.config.pageSize,
                page = position / PAGE_SIZE + 1,
                login = login
            )
            userDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDb.userDao.cleanAllProjects()
                }
                val projectEntities = projects.map { it.toProjectEntity(login) }
                userDb.userDao.upsertUserProjects(projectEntities)
            }
            MediatorResult.Success( endOfPaginationReached = projects.isEmpty() )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}