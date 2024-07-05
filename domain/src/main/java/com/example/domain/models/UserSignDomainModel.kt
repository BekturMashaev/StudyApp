package com.example.domain.models

import java.net.URI

data class UserSignDomainModel(
    val email: String = "",
    val password: String = "",
    val photoURI: URI? = null,
    val username: String = ""
)
