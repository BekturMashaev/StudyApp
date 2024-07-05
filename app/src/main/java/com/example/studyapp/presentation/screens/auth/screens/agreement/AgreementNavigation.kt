package com.example.studyapp.presentation.screens.auth.screens.agreement

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

fun NavController.navigateToAgreement() {
    navigate(AgreementDestination)
}

fun NavGraphBuilder.agreementScreen(
    onCloseClick: () -> Unit
) {
    dialog<AgreementDestination>(
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ){
        AgreementScreen(onBackClick = onCloseClick)
    }
}