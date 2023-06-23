package net.pyles.repositorylist.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.pyles.repositorylist.navigation.Screen.RepositoriesScreen
import net.pyles.repositorylist.navigation.Screen.UsersScreen
import net.pyles.repositorylist.presentation.projects.ProjectsScreen
import net.pyles.repositorylist.presentation.users.UsersScreen

@Composable
@ExperimentalMaterialApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = UsersScreen.route,
    ) {
        composable(
            route = UsersScreen.route
        ) {
            UsersScreen(
                navigateToRepositoriesScreen = {
                    navController.navigate( route = RepositoriesScreen.route )
                }
            )
        }
        composable(
            route = RepositoriesScreen.route
        ) {
            ProjectsScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}