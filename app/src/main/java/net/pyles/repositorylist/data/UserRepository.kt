package net.pyles.repositorylist.data

interface UserRepository{

    fun setLogin(login: String)

    fun getLogin(): String

    fun setUserSize(size: Int)
    fun getUserSize(): Int
    suspend fun getProjectSize(login: String): Int

}