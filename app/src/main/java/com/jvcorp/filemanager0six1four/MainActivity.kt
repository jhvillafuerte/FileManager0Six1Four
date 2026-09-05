package com.jvcorp.filemanager0six1four

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.jvcorp.filemanager0six1four.data.FileScreen
import java.io.File

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Only check/manage all files access if API >= 30
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
        }

        // Root of external storage
        val root = File("/storage/emulated/0")
        Log.d("MainActivity", "Root path: ${root.absolutePath}")

        setContent {
            MaterialTheme {
                Surface {
                    // Call your FileScreen composable here
                    FileScreen(startPath = root.absolutePath)
                }
            }
        }
    }
}
