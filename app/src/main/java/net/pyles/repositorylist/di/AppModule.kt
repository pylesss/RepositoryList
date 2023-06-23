package net.pyles.repositorylist.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pyles.repositorylist.core.Constants.Companion.PAGE_SIZE
import net.pyles.repositorylist.core.Constants.Companion.USER_TABLE
import net.pyles.repositorylist.data.UserRepository
import net.pyles.repositorylist.data.UserRepositoryImpl
import net.pyles.repositorylist.data.local.ProjectEntity
import net.pyles.repositorylist.data.local.UserDao
import net.pyles.repositorylist.data.local.UserDb
import net.pyles.repositorylist.data.local.UserEntity
import net.pyles.repositorylist.data.remote.RemoteApi
import net.pyles.repositorylist.data.remote.RemoteApi.Companion.BASE_URL
import net.pyles.repositorylist.paging.ProjectRemoteMediator
import net.pyles.repositorylist.paging.UserRemoteMediator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        UserDb::class.java,
        USER_TABLE,
    ).build()

    @Provides
    @Singleton
    fun provideRemoteApi(): RemoteApi = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun providesUserDao(
        userDb: UserDb
    ) = userDb.userDao

    @Provides
    @Singleton
    fun providesUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(
        userDao = userDao
    )

    @Provides
    @Singleton
    fun provideUserPager(
        userDb: UserDb,
        remoteApi: RemoteApi,
        repository: UserRepository
    ): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig( pageSize = PAGE_SIZE),
            remoteMediator = UserRemoteMediator(
                userDb = userDb,
                remoteApi = remoteApi,
                repository = repository
            ),
            pagingSourceFactory = {
                userDb.userDao.userPagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun providesProjectPager(
        userDb: UserDb,
        remoteApi: RemoteApi,
        repository: UserRepository
    ): Pager<Int, ProjectEntity> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = ProjectRemoteMediator(
                userDb,
                remoteApi,
                repository
            ),
            pagingSourceFactory = {
                userDb.userDao.projectPagingSource(repository.getLogin())
            }
        )
    }
}
