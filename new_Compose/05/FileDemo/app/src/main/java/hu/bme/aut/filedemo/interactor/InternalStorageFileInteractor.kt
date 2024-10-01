package hu.bme.aut.filedemo.interactor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class InternalStorageFileInteractor @Inject constructor(@ApplicationContext val context: Context) {

    @Throws(Exception::class)
    fun writeInternalFile(data: String) {
        lateinit var outputStream: FileOutputStream
        try {
            outputStream = context.openFileOutput("internaltext.txt", Context.MODE_PRIVATE)
            outputStream.write(data.toByteArray())
            outputStream.flush()
        } catch (e: Exception) {
            throw e
        } finally {
            try {
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(Exception::class)
    fun readInternalFile(): String {
        lateinit var inputStream: FileInputStream
        var result = ""
        try {
            inputStream = context.openFileInput("internaltext.txt")
            result = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            throw e
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return result
    }

}