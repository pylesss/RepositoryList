package net.pyles.repositorylist.presentation.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.pyles.repositorylist.R
import net.pyles.repositorylist.ui.theme.Purple80
import net.pyles.repositorylist.viewmodel.ProjectsViewModel
import net.pyles.repositorylist.viewmodel.UsersViewModel

@Composable
fun ProjectListItem(
    text: String,
    onPress: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding)
            )
            .clickable {
                onPress()
            },
        elevation = 3.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Purple80,
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}