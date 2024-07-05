package com.example.studyapp.presentation.screens.auth.screens.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.dp160
import com.example.studyapp.presentation.theme.dp46
import com.example.studyapp.presentation.theme.dp48
import com.example.studyapp.presentation.theme.dp66

@Composable
fun SuccessDialog(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val image = if (isSystemInDarkTheme()) R.drawable.success_dark else R.drawable.success_light
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = modifier.size(dp160)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onClose,
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = dp46, max = dp66)
            ) {
                Text(
                    text = "Agree",
                    color = White,
                    style = typography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
//    Dialog(
//        onDismissRequest = { /*TODO*/ }
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = modifier.background(colorScheme.background).clip(MaterialTheme.shapes.large)
//        ) {
//            Image(
//                painter = painterResource(id = image),
//                contentDescription = null,
//                modifier = modifier.size(dp160)
//            )
//            Button(
//                onClick = onClose,
//                modifier = modifier
//                    .fillMaxWidth()
//                    .heightIn(min = dp46, max = dp66)
//            ) {
//                Text(
//                    text = "Agree",
//                    color = White,
//                    style = typography.displayMedium,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
}

@Preview
@Composable
private fun SuccessDialogPreview() {
    StudyAppTheme {
        SuccessDialog(onClose = {})
    }
}