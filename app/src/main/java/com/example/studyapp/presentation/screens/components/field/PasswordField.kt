package com.example.studyapp.presentation.screens.components.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.Red
import com.example.studyapp.presentation.theme.dp10
import com.example.studyapp.presentation.theme.dp20
import com.example.studyapp.presentation.theme.dp24
import com.example.studyapp.presentation.theme.dp70
import com.example.studyapp.presentation.theme.dp76
import com.example.studyapp.presentation.utils.customTextFieldColors

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    errorTextMessage: String,
    modifier: Modifier = Modifier,
) {
    var isPasswordVisible by remember { mutableStateOf(true) }
    Column {
        Text(
            text = stringResource(R.string.password),
            color = colorScheme.onBackground,
            style = typography.titleMedium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium.copy(color = colorScheme.onBackground),
            placeholder = {
                Text(
                    text = stringResource(R.string.your_password),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorScheme.surfaceTint
                )
            },
            isError = !isValid,
            supportingText = {
                if (!isValid) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = dp10),
                        text = errorTextMessage,
                        style = MaterialTheme.typography.displaySmall.copy(color = Red)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.customTextFieldColors(),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TrailingVisibilityIcon(
                    onButtonClick = { isPasswordVisible = !isPasswordVisible },
                    isPasswordVisible = isPasswordVisible
                )
            },
            modifier = Modifier
                .heightIn(min = dp70)
                .fillMaxWidth()
        )
    }

}

@Composable
fun TrailingVisibilityIcon(
    isPasswordVisible: Boolean,
    onButtonClick: () -> Unit,
) {
    IconButton(onClick = onButtonClick) {
        Icon(
            if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
            contentDescription = "navigate back icon",
            tint = colorScheme.onBackground,
            modifier = Modifier.size(dp24)
        )
    }
}

@Preview
@Composable
private fun PasswordFieldPreview() {
    MaterialTheme {
        PasswordField(
            value = "dede",
            onValueChange = {},
            isValid = true,
            errorTextMessage = ""
        )
    }
}