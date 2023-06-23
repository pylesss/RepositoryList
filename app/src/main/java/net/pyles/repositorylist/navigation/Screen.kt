package net.pyles.repositorylist.navigation

import net.pyles.repositorylist.core.Constants.Companion.REPOSITORIES_SCREEN
import net.pyles.repositorylist.core.Constants.Companion.USERS_SCREEN

sealed class Screen(val route: String) {
    object UsersScreen: Screen(USERS_SCREEN)
    object RepositoriesScreen: Screen(REPOSITORIES_SCREEN)
}