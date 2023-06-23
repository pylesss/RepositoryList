package net.pyles.repositorylist.presentation.projects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import net.pyles.repositorylist.R
import net.pyles.repositorylist.presentation.components.ListItem
import net.pyles.repositorylist.presentation.users.UserListState
import net.pyles.repositorylist.viewmodel.ProjectsViewModel

@Composable
@ExperimentalMaterialApi
fun ProjectsList(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: ProjectsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val projects = viewModel.projectPagingFlow.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
    ) {
        Text(
            text = stringResource(R.string.list_of_projects),
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
                    vertical = dimensionResource(id = R.dimen.vertical_margin)
                ),
            fontSize = 30.sp
        )
        LazyColumn {
            items(projects) { project ->
                ProjectListItem(
                    text = project?.name ?: "",
                    onPress = navigateBack
                )
            }
            item {
                ProjectListState(
                    projects = projects,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}