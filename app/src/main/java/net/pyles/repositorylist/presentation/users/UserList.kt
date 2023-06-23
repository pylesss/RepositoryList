package net.pyles.repositorylist.presentation.users

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import net.pyles.repositorylist.R
import net.pyles.repositorylist.domain.User
import net.pyles.repositorylist.presentation.components.ListItem
import net.pyles.repositorylist.viewmodel.UsersViewModel

@Composable
@ExperimentalMaterialApi
fun UserList(
    modifier: Modifier = Modifier,
    navigateToRepositoriesScreen: () -> Unit,
    viewModel: UsersViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val users = viewModel.userPagingFlow.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
    ) {
        Text(
            text = stringResource(id = R.string.list_of_users),
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
                    vertical = dimensionResource(id = R.dimen.vertical_margin)
                ),
            fontSize = 30.sp
        )
        LazyColumn {
            items(users) { user ->
                Log.d("MyTag", "Hello")
                ListItem(
                    text = user?.login ?: "",
                    onPress = navigateToRepositoriesScreen,
                    viewModel = viewModel
                )
            }
            item {
                UserListState(
                    users = users,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}