package com.example.studyapp.presentation.screens.components.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.Red
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.dp1
import com.example.studyapp.presentation.theme.dp10
import com.example.studyapp.presentation.theme.dp20
import com.example.studyapp.presentation.theme.dp200
import com.example.studyapp.presentation.theme.dp70
import com.example.studyapp.presentation.theme.dp76
import com.example.studyapp.presentation.utils.customTextFieldColors

@Composable
fun EmailField(
    onValueChange: (String) -> Unit,
    value: String,
    isValid: Boolean,
    errorTextMessage: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(R.string.email),
            color = colorScheme.onBackground,
            style = typography.titleMedium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium.copy(color = colorScheme.onBackground),
            placeholder = {
                Text(
                    text = stringResource(R.string.study_email_com),
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
                        style = typography.displaySmall.copy(color = Red)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.customTextFieldColors(),
            shape = shapes.medium,
            modifier = Modifier
                .heightIn(min = dp70)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun UserTextFieldPreview() {
    StudyAppTheme {
        EmailField(
            value = "gtgdtgdtgdtgtd",
            onValueChange = {},
            isValid = false,
            errorTextMessage = "frfr"
        )
    }
}