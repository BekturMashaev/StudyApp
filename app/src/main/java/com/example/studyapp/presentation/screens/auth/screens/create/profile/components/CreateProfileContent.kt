package com.example.studyapp.presentation.screens.auth.screens.create.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.screens.auth.screens.create.profile.CreateProfileInteractions
import com.example.studyapp.presentation.screens.components.buttons.SubmitButton
import com.example.studyapp.presentation.screens.components.field.UserTextField
import com.example.studyapp.presentation.theme.StudyAppTheme

@Composable
fun CreateProfileContent(
    innerPadding: PaddingValues,
    onInteractions: (CreateProfileInteractions) -> Unit,
    isValid: Boolean,
    textFieldValue: String,
    modifier: Modifier = Modifier
) {
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri.let {
                onInteractions(CreateProfileInteractions.OnPhotoAdded(uri))
                selectedImage = uri
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        PictureCard(
            model = selectedImage,
            addPhoto = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )
        UserTextField(
            onValueChange = {
                onInteractions(CreateProfileInteractions.OnUserNameChanged(it))
            },
            value = textFieldValue,
            isValid = isValid
        )
        SubmitButton(
            text = stringResource(R.string.continue_btn),
            onButtonClick = {
                onInteractions(CreateProfileInteractions.OnContinueButtonClick)
            }
        )
    }
}

@Preview
@Composable
private fun CreateProfileContentPreview() {
    StudyAppTheme {
        CreateProfileContent(
            innerPadding = PaddingValues(),
            onInteractions = {},
            textFieldValue = "aa",
            isValid = true,
        )
    }
}
