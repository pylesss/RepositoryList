package net.pyles.repositorylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import net.pyles.repositorylist.data.UserRepository
import net.pyles.repositorylist.data.local.UserEntity
import net.pyles.repositorylist.data.toUser
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository,
    userPager: Pager<Int, UserEntity>
) : ViewModel() {
    fun setLogin(login: String) = repository.setLogin(login)

    val userPagingFlow = userPager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUser() }
        }
        .cachedIn(viewModelScope)
}