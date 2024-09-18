package hu.bme.aut.aitforum.data

data class Post(
    var uid: String = "", // the user id who has created this post object
    var author: String = "", // username who has created the message
    var title: String = "",
    var body: String = "",
    var imgUrl: String = ""
)