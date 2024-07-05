package com.example.studyapp.presentation.screens.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.dp300
import com.example.studyapp.presentation.theme.dp46
import com.example.studyapp.presentation.theme.dp50
import com.example.studyapp.presentation.theme.dp60
import com.example.studyapp.presentation.theme.dp66

@Composable
fun SubmitButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.primary
            ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min= dp46 ,max= dp66)
    ) {
        Text(
            text = text,
            color = White,
            style = typography.displayMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun SubmitButtonPreview() {
    SubmitButton(
        text = "Register",
        onButtonClick = {}
    )
}