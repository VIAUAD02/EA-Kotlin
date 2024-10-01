package hu.bme.aut.filedemo.repository

import hu.bme.aut.filedemo.interactor.InternalStorageFileInteractor
import javax.inject.Inject

class FileRepository @Inject constructor(
    private val fileInteractor: InternalStorageFileInteractor
) {
    @Throws(Exception::class)
    suspend fun writeToInternalStorage(data: String) {
        fileInteractor.writeInternalFile(data)
    }

    @Throws(Exception::class)
    suspend fun readFromInternalStorage() : String {
        return fileInteractor.readInternalFile()
    }
}