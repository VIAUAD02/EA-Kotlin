package hu.ait.aitforum.data

data class Post(
    var uid: String = "",
    var author: String = "",
    var title: String = "",
    var body: String = "",
    var imgUrl: String = ""
)

data class PostWithId(
    val postId: String,
    val post: Post
)