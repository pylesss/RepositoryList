package net.pyles.repositorylist.data.remote

interface NetworkDataSource {
    suspend fun loadUsers(): List<UserDto>
    suspend fun loadRepositories(login: String): List<ProjectDto>
}