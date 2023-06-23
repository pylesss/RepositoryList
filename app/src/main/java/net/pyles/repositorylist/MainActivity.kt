package net.pyles.repositorylist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.pyles.repositorylist.navigation.NavGraph
import net.pyles.repositorylist.ui.theme.RepositoryListTheme

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepositoryListTheme {
                NavGraph(
                    navController = rememberNavController()
                )
            }
        }
    }
}