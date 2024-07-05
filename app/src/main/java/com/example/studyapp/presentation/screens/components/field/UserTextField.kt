package com.example.studyapp.presentation.screens.components.field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.dp70
import com.example.studyapp.presentation.theme.dp76
import com.example.studyapp.presentation.utils.customTextFieldColors

@Composable
fun UserTextField(
    onValueChange: (String) -> Unit,
    value: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        textStyle = typography.titleMedium.copy(color = colorScheme.onBackground)
            .copy(textAlign = TextAlign.Center),
        placeholder = {
            Text(
                text = stringResource(R.string.name),
                style = typography.titleMedium,
                color = colorScheme.surfaceTint
            )
        },
        isError = !isValid,
        colors = OutlinedTextFieldDefaults.customTextFieldColors(),
        shape = shapes.medium,
        modifier = Modifier
            .heightIn(min = dp70, max = dp76)
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun UserTextFieldPreview() {
    StudyAppTheme {
        UserTextField(
            isValid = true,
            onValueChange = {},
            value = "Js"
        )
    }
}