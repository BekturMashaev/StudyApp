package com.example.studyapp.presentation.screens.auth.screens.create.profile.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.Blue
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.dp100
import com.example.studyapp.presentation.theme.dp20
import com.example.studyapp.presentation.theme.dp28

@Composable
fun PictureCard(
    model: Uri?,
    addPhoto: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(dp100)
            .clickable { addPhoto() },
    ) {
        Card(
            modifier = modifier.fillMaxSize(),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    placeholder = painterResource(id = if (isSystemInDarkTheme()) R.drawable.photo_dark else R.drawable.photo_light),
                    model = model,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                )
            }
        }
        IconButton(
            onClick = addPhoto,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Blue
            ),
            modifier = modifier
                .size(dp28)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = White,
                modifier = modifier
                    .size(dp20)
            )
        }
    }
}