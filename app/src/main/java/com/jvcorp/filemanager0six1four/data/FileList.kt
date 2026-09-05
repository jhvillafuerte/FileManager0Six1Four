package com.jvcorp.filemanager0six1four.data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun FileScreen(startPath: String) {
    var currentPath by remember { mutableStateOf(File(startPath)) }
    var files by remember { mutableStateOf(currentPath.listFiles()?.toList() ?: emptyList()) }

    Column {
        // Breadcrumb / current path
        Text(
            text = "📂 ${currentPath.absolutePath}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )

        // Back button if not at root
        if (currentPath.parentFile != null) {
            Text(
                text = "⬅️ Back",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        currentPath = currentPath.parentFile!!
                        files = currentPath.listFiles()?.toList() ?: emptyList()
                    }
            )
            HorizontalDivider()
        }

        // File list
        FileList(
            files = files,
            onFileClick = { file ->
                if (file.isDirectory) {
                    currentPath = file
                    files = file.listFiles()?.toList() ?: emptyList()
                } else {
                    // TODO: handle opening files later
                }
            }
        )
    }
}

@Composable
fun FileList(files: List<File>, onFileClick: (File) -> Unit) {
    LazyColumn {
        items(files) { file ->
            Text(
                text = if (file.isDirectory) "📁 ${file.name}" else "📄 ${file.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onFileClick(file) }
            )
            HorizontalDivider()
        }
    }
}
