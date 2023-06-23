package net.pyles.repositorylist.presentation.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import net.pyles.repositorylist.domain.Project
import net.pyles.repositorylist.domain.User

@Composable
fun ProjectListState(
    projects: LazyPagingItems<Project>,
    scaffoldState: ScaffoldState
) {
    when (projects.loadState.refresh) {
        is LoadState.Loading -> {
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Refresh Loading"
                    )
                    CircularProgressIndicator(color = Color.Black)
                }
            }
        }
        is LoadState.Error -> {
            val snackBarConnection = "Error connection"
            LaunchedEffect(scaffoldState, snackBarConnection) {
                scaffoldState.snackbarHostState.showSnackbar(snackBarConnection)
            }
        }
        is LoadState.NotLoading -> {

        }
    }

    when (projects.loadState.append) {
        is LoadState.Loading -> {
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Pagination Loading")
                    CircularProgressIndicator(color = Color.Black)
                }
            }
        }
        is LoadState.Error -> {
            val snackBarConnection = "Error connection"
            LaunchedEffect(scaffoldState, snackBarConnection) {
                scaffoldState.snackbarHostState.showSnackbar(snackBarConnection)
            }
        }
        is LoadState.NotLoading -> {

        }
    }
}