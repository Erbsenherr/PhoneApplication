package com.example.catalog.screens // Package location inside the catalog module

// Layout primitives from Compose: Column (vertical layout), Spacer (empty space), etc.
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme // Material3 theme access (colors, typography)
import androidx.compose.material3.Text         // Text composable
import androidx.compose.runtime.Composable    // Marks a function as a UI building block
import androidx.compose.ui.Modifier           // Used to describe layout/behavior for a composable
import androidx.compose.ui.unit.dp            // Density-independent pixels for sizes
import androidx.compose.ui.tooling.preview.Preview // Lets this composable render in the Preview pane
import androidx.room.util.TableInfo

// If you have a Compose AppTheme in your design-system module, import it so previews match your app
import com.example.designsystem.AppTheme
import com.example.feature.contact.ContactUiState

@Composable
fun ContactScreenV1() {
    // First: We wrap everything in a column
    Column(
        // We add a Modifier that we use to adjust layout etc...
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create a simple Headline
        Text(
            text = "Contact us",
            // we choose a font (?)
            style = MaterialTheme.typography.headlineSmall
        )

        // We now add a spacer, which just adds space between elements
        Spacer(
            modifier = Modifier
                .height(8.dp))

        // We add another line of Text with a 'body' style
        Text(
            text = "This is a minimal Contact screen. We'll add input and a button next.",
            style = MaterialTheme.typography.bodyLarge
        )

    }
}
@Preview(name = "Contact - V1 (Text only)", showBackground = true)
@Composable
private fun ContactScreenV1_Preview() {
    AppTheme {
        ContactScreenV1()
    }
}