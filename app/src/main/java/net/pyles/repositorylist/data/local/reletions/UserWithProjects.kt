package net.pyles.repositorylist.data.local.reletions

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import net.pyles.repositorylist.data.local.ProjectEntity
import net.pyles.repositorylist.data.local.UserEntity

@Entity
data class UserWithProjects(
    @Embedded
    val userEntity: UserEntity,
    @Relation(
        parentColumn = "login",
        entityColumn = "login"
    )
    val projects: List<ProjectEntity>
)
