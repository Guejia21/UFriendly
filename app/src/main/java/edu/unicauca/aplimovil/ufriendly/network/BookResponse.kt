package edu.unicauca.aplimovil.ufriendly.network

import com.google.gson.annotations.SerializedName

// API: https://openlibrary.org/dev/docs/api/search

data class BookResponse(
    @SerializedName("docs") val books: List<BookDoc>
)

data class BookDoc(
    @SerializedName("title")        val title: String?,
    @SerializedName("author_name") val authors: List<String>?,
    @SerializedName("first_publish_year") val year: Int?,
    @SerializedName("cover_i")     val coverId: Long?,      // use to build cover URL
    @SerializedName("key")         val key: String?         // e.g. /works/OL27448W
) {
    // Cover URL is not in the response — it has to be built from the cover ID
    fun coverUrl(size: String = "M") =
        coverId?.let { "https://covers.openlibrary.org/b/id/$it-$size.jpg" }
}