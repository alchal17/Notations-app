package com.example.notations.ui.pages

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object NotationListPage : Routes

    @Serializable
    data object CreationPage : Routes

    @Serializable
    data class EditionPage(val id: Long): Routes
}