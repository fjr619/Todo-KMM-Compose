package ui.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import domain.TodoTask
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import todokmmcompose.composeapp.generated.resources.Res
import todokmmcompose.composeapp.generated.resources.delete
import todokmmcompose.composeapp.generated.resources.star

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TaskView(
    task: TodoTask,
    showActive: Boolean = true,
    onSelect: (TodoTask) -> Unit,
    onComplete: (TodoTask, Boolean) -> Unit,
    onFavorite: (TodoTask, Boolean) -> Unit,
    onDelete: (TodoTask) -> Unit
) {
    Row(
        modifier = Modifier
            .animateItemPlacement()
            .fillMaxWidth()
            .clickable {
                if (showActive) onSelect(task)
                else onDelete(task)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onComplete(task, !task.completed) },
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.alpha(if (showActive) 1f else 0.5f),
                text = task.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                textDecoration = if (showActive) TextDecoration.None
                else TextDecoration.LineThrough
            )
        }
        IconButton(
            onClick = {
                if (showActive) onFavorite(task, !task.favorite)
                else onDelete(task)
            }
        ) {
            Icon(
                painter = painterResource(
                    if (showActive) Res.drawable.star
                    else Res.drawable.delete
                ),
                contentDescription = "Favorite Icon",
                tint = if (task.favorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                )
            )
        }
    }
}