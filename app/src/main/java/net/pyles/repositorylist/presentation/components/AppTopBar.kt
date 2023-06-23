package net.pyles.repositorylist.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pyles.repositorylist.ui.theme.Purple40
import java.io.FileDescriptor

@Composable
fun AppTopBar (
    icon: ImageVector,
    iconDescription: String,
    header: String,
    onPress: () -> Unit
){
    TopAppBar(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = header,
                fontSize = 30.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onPress()
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    tint = Color.White
                )
            }
        },
        backgroundColor = Purple40
    )
}