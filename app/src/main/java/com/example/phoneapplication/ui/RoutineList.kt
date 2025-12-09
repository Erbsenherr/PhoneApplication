import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.phoneapplication.taskClasses.SmartRoutineTask
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoutineList(routines: List<SmartRoutineTask>) {
    LazyColumn(
        modifier = Modifier // modifier is a class I use for the composition of UI elements
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(routines) { routine ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = routine.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = routine.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Divider()
        }
    }
}