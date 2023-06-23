package net.pyles.repositorylist.presentation.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import net.pyles.repositorylist.presentation.components.AppTopBar
import net.pyles.repositorylist.viewmodel.UsersViewModel

@Composable
@ExperimentalMaterialApi
fun UsersScreen(
    modifier: Modifier = Modifier,
    navigateToRepositoriesScreen: () -> Unit,
    viewModel: UsersViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBar(
                icon = Icons.Filled.Home,
                iconDescription = "Users Page",
                header = "Users",
                onPress = {}
            )
        },
        modifier = modifier.fillMaxSize(),
        content = { paddingValues ->
            UserList(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                scaffoldState = scaffoldState,
                navigateToRepositoriesScreen = navigateToRepositoriesScreen
            )
        }
    )
}