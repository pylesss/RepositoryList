package net.pyles.repositorylist.data

import net.pyles.repositorylist.data.local.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {

    private var userLogin = ""

    private var userSize = 0

    override fun setLogin(login: String) {
        userLogin = login
    }

    override fun getLogin(): String = userLogin

    override fun setUserSize(size: Int) {
        userSize = size
    }

    override fun getUserSize(): Int = userSize

    override suspend fun getProjectSize(login: String): Int {
        return userDao.getUserProjects(login).size
    }
}