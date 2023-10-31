package hu.ait.aitforum.ui.screen.writemessage

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import hu.ait.aitforum.data.Post
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.net.URLEncoder
import java.util.*


sealed interface WritePostUiState {
    object Init : WritePostUiState
    object LoadingPostUpload : WritePostUiState
    object PostUploadSuccess : WritePostUiState
    data class ErrorDuringPostUpload(val error: String?) : WritePostUiState

    object LoadingImageUpload : WritePostUiState
    data class ErrorDuringImageUpload(val error: String?) : WritePostUiState
    object ImageUploadSuccess : WritePostUiState
}
class WritePostViewModel: ViewModel() {
    companion object {
        const val COLLECTION_POSTS = "posts"
    }

    var writePostUiState: WritePostUiState by mutableStateOf(WritePostUiState.Init)
    private lateinit var auth: FirebaseAuth

    init {
        //auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
    }
    fun uploadPost(title: String, postBody: String, imgUrl: String = "") {
        writePostUiState = WritePostUiState.LoadingPostUpload

        val myPost = Post(
            uid = auth.currentUser!!.uid,
            author = auth.currentUser!!.email!!,
            title = title,
            body = postBody,
            imgUrl = imgUrl
        )

        val postsCollection =
            FirebaseFirestore.getInstance().collection(
                COLLECTION_POSTS)

        postsCollection.add(myPost).addOnSuccessListener {
            writePostUiState = WritePostUiState.PostUploadSuccess
        }.addOnFailureListener{
            writePostUiState = WritePostUiState.ErrorDuringPostUpload(it.message)
        }
    }


    @RequiresApi(Build.VERSION_CODES.P)
    public fun uploadPostImage(
        contentResolver: ContentResolver, imageUri: Uri,
        title: String, postBody: String
    ) {
        viewModelScope.launch {
            writePostUiState = WritePostUiState.LoadingImageUpload

            val source = ImageDecoder.createSource(contentResolver, imageUri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            val baos = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageInBytes = baos.toByteArray()

            // prepare the empty file in the cloud
            val storageRef = FirebaseStorage.getInstance().getReference()
            val newImage = URLEncoder.encode(UUID.randomUUID().toString(), "UTF-8") + ".jpg"
            val newImagesRef = storageRef.child("images/$newImage")

            // upload the jpeg byte array to the created empty file
            newImagesRef.putBytes(imageInBytes)
                .addOnFailureListener { e ->
                    writePostUiState = WritePostUiState.ErrorDuringImageUpload(e.message)
                }.addOnSuccessListener { taskSnapshot ->
                    writePostUiState = WritePostUiState.ImageUploadSuccess

                    newImagesRef.downloadUrl.addOnCompleteListener(
                        object : OnCompleteListener<Uri> {
                            override fun onComplete(task: Task<Uri>) {
                                // the public URL of the image is: task.result.toString()
                                uploadPost(title, postBody, task.result.toString())
                            }
                        })
                }
        }
    }

}