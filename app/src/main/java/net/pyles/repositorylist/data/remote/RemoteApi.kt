package net.pyles.repositorylist.data.remote

import net.pyles.repositorylist.core.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApi {
    @Headers("authorization: Bearer $API_KEY")
    @GET("$USERS_KEY?")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): List<UserDto>

    @Headers("authorization: Bearer $API_KEY")
    @GET("$USERS_KEY/{login}/$PROJECTS_KEY?")
    suspend fun getProjects(
        @Path("login") login: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): List<ProjectDto>

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val USERS_KEY = "users"
        const val PROJECTS_KEY = "repos"
    }
}
