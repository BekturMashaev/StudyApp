package com.example.studyapp.presentation.screens.auth.screens.register.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.presentation.theme.Blue
import com.example.studyapp.presentation.theme.Gray
import com.example.studyapp.presentation.theme.Poppins
import com.example.studyapp.presentation.theme.TextFieldHintColor
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.sp13
import com.example.studyapp.presentation.theme.sp14

@Composable

fun RegisterAgreementCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val checkboxColors = CheckboxDefaults.colors(
        checkedColor = Blue,
        uncheckedColor = Gray,
        checkmarkColor = White
    )
    val annotatedText =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = TextFieldHintColor,
                    fontSize = sp13,
                    fontFamily = Poppins
                )
            ) {
                append("I agree with the ")
                pushStringAnnotation(tag = "terms", annotation = "terms")
                withStyle(style = SpanStyle(color = colorScheme.primary, fontSize = sp14)) {
                    append("terms and conditions")
                }
                pop()
                append(" and also the protection of my personal data on this application")
            }
        }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = checkboxColors
        )
        ClickableText(text = annotatedText) { offSet ->
            val startIndex = annotatedText.indexOf("terms and conditions")
            val endIndex = startIndex + "terms and conditions".length
            if (offSet in startIndex until endIndex) {
                onTermsClick()
            }
        }
    }
}

@Preview
@Composable
private fun RegisterAgreementCheckBoxPreview() {
    MaterialTheme {
        RegisterAgreementCheckBox(
            checked = true,
            onCheckedChange = {},
            onTermsClick = {},
        )
    }
}