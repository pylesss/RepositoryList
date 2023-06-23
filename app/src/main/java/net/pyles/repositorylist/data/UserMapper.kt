package net.pyles.repositorylist.data

import net.pyles.repositorylist.data.local.ProjectEntity
import net.pyles.repositorylist.data.local.UserEntity
import net.pyles.repositorylist.data.remote.ProjectDto
import net.pyles.repositorylist.data.remote.UserDto
import net.pyles.repositorylist.domain.Project
import net.pyles.repositorylist.domain.User

fun UserDto.toUserEntity(): UserEntity = UserEntity( login = login )

fun UserEntity.toUser(): User = User( login = login )

fun ProjectDto.toProjectEntity(login: String): ProjectEntity = ProjectEntity(
    name = name,
    login = login
)

fun ProjectEntity.toProject(): Project = Project( name = name )


