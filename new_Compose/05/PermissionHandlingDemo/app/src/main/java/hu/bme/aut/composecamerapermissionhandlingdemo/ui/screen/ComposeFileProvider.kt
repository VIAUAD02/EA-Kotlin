package hu.bme.aut.composecamerapermissionhandlingdemo.ui.screen

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import hu.bme.aut.composecamerapermissionhandlingdemo.R
import java.io.File

class ComposeFileProvider : FileProvider(
    R.xml.filepaths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}