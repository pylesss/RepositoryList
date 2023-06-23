package net.pyles.repositorylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import net.pyles.repositorylist.data.local.ProjectEntity
import net.pyles.repositorylist.data.toProject
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    projectPager: Pager<Int, ProjectEntity>
): ViewModel() {

    val projectPagingFlow = projectPager
        .flow
        .map { pagingData ->
            pagingData.map { it.toProject()}
        }
        .cachedIn(viewModelScope)

}