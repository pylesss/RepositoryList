package net.pyles.repositorylist.presentation.projects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import net.pyles.repositorylist.presentation.components.AppTopBar
import net.pyles.repositorylist.viewmodel.ProjectsViewModel

@Composable
@ExperimentalMaterialApi
fun ProjectsScreen(
    modifier:Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: ProjectsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBar(
                icon = Icons.Filled.ArrowBack,
                iconDescription = "Projects page",
                header = "Projects",
                onPress = {
                    navigateBack()
                }
            )
        },
        modifier = modifier.fillMaxSize(),
        content = { paddingValues ->
            ProjectsList(
                modifier = Modifier.padding(paddingValues),
                navigateBack = navigateBack,
                viewModel = viewModel,
                scaffoldState = scaffoldState
            )
        }
    )

}