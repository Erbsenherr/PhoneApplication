package com.example.feature.contact

/**
 * ContactUiState
 *
 * Purpose:
 * - Immutable state object that the production ContactScreen uses to render.
 * - Keeping the screen stateless makes it reusable by both the main app and the catalog.
 *
 * Notes:
 * - In a real app, a ViewModel would produce this state.
 * - Here we keep it minimal: just a message text for the input field.
 */
data class ContactUiState(
    val message: String // The text currently shown in the "message" input field
)