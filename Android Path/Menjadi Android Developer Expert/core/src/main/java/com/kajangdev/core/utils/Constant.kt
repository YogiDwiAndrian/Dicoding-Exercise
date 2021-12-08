package com.kajangdev.core.utils

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/"
    const val API_KEY = "327fde979536bb577c7eeaa27534c59f"

    const val DEFAULT_BACKDROP =
            "http://placehold.jp/64/a8b3bf/61666b/533x300.png?text=More%20Poster%0AComing%20Soon"
    const val DEFAULT_POSTER =
            "http://placehold.jp/64/a8b3bf/61666b/500x750.png?text=More%20Poster%0AComing%20Soon"

    fun getGenreNameMv(genre_id: List<Int>?): String {
        val name: ArrayList<String> = ArrayList()
        for (item in genre_id!!.indices) {
            name.add(getGenre(genre_id[item]))
        }
        return name.toString().replace("[", "").replace("]", "")
    }

    fun getGenreNameTv(genre_id: List<Int>): String {
        val name: ArrayList<String> = ArrayList()
        for (item in genre_id.indices) {
            name.add(getGenreTV(genre_id[item]))
        }
        return name.toString().replace("[", "").replace("]", "")
    }

    private fun getGenre(id: Int): String {
        val genreMap = HashMap<Int, String>()
        genreMap[28] = "Action"
        genreMap[12] = "Adventure"
        genreMap[16] = "Animation"
        genreMap[35] = "Comedy"
        genreMap[80] = "Crime"
        genreMap[99] = "Documentary"
        genreMap[18] = "Drama"
        genreMap[10751] = "Family"
        genreMap[14] = "Fantasy"
        genreMap[36] = "History"
        genreMap[27] = "Horror"
        genreMap[10402] = "Music"
        genreMap[9648] = "Mystery"
        genreMap[10749] = "Romance"
        genreMap[878] = "Science Fiction"
        genreMap[10770] = "TV Movie"
        genreMap[53] = "Thriller"
        genreMap[10752] = "War"
        genreMap[37] = "Western"

        return genreMap[id]!!
    }

    private fun getGenreTV(id: Int): String {
        val genreMap = HashMap<Int, String>()
        genreMap[28] = "Action"
        genreMap[12] = "Adventure"
        genreMap[16] = "Animation"
        genreMap[35] = "Comedy"
        genreMap[80] = "Crime"
        genreMap[99] = "Documentary"
        genreMap[18] = "Drama"
        genreMap[10751] = "Family"
        genreMap[14] = "Fantasy"
        genreMap[36] = "History"
        genreMap[27] = "Horror"
        genreMap[10402] = "Music"
        genreMap[9648] = "Mystery"
        genreMap[37] = "Western"
        genreMap[10765] = "Sci-Fi & Fantasy"
        genreMap[10766] = "Soap"
        genreMap[10767] = "Talk"
        genreMap[10768] = "War & Politics"
        genreMap[10763] = "News"
        genreMap[10764] = "Reality"
        genreMap[10762] = "Kids"
        genreMap[10759] = "Action & Adventure"
        genreMap[10749] = "Romance"
        genreMap[878] = "Science Fiction"
        genreMap[10770] = "TV Movie"
        genreMap[53] = "Thriller"
        genreMap[10752] = "War"

        return genreMap[id]!!
    }
}