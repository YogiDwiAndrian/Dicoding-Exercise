package com.yogi.cinemakita.data

import com.yogi.cinemakita.R
import com.yogi.cinemakita.models.Movie

object MovieData {

    private val moviePreview = arrayOf(
        "https://www.imdb.com/videoembed/vi94288665",
        "https://www.imdb.com/videoembed/vi3588536857",
        "https://www.imdb.com/videoembed/vi528070681",
        "https://www.imdb.com/videoembed/vi2966010393",
        "https://www.imdb.com/videoembed/vi1451538969",
        "https://www.imdb.com/videoembed/vi1473100313",
        "https://www.imdb.com/videoembed/vi65124889",
        "https://www.imdb.com/videoembed/vi2984557081",
        "https://www.imdb.com/videoembed/vi1887746585",
        "https://www.imdb.com/videoembed/vi2158166297",
        "https://www.imdb.com/videoembed/vi768195097",
        "https://www.imdb.com/videoembed/vi3076504345",
        "https://www.imdb.com/videoembed/vi2354100761",
        "https://www.imdb.com/videoembed/vi3886594585",
        "",
        "https://www.imdb.com/videoembed/vi447330841",
        "https://www.imdb.com/videoembed/vi1489877529",
        "https://www.imdb.com/videoembed/vi222608153",
        "https://www.imdb.com/videoembed/vi2678503961",
        "https://www.imdb.com/videoembed/vi1186773529"
    )

    private val moviePoster = intArrayOf(
        R.drawable.poster_a_star,
        R.drawable.poster_aquaman,
        R.drawable.poster_avengerinfinity,
        R.drawable.poster_birdbox,
        R.drawable.poster_bohemian,
        R.drawable.poster_bumblebee,
        R.drawable.poster_creed,
        R.drawable.poster_deadpool,
        R.drawable.poster_dragon,
        R.drawable.poster_dragonball,
        R.drawable.poster_glass,
        R.drawable.poster_hunterkiller,
        R.drawable.poster_marrypopins,
        R.drawable.poster_mortalengine,
        R.drawable.poster_preman,
        R.drawable.poster_robinhood,
        R.drawable.poster_spiderman,
        R.drawable.poster_thegirl,
        R.drawable.poster_themule,
        R.drawable.poster_venom
    )

    private val movieTittle = arrayOf(
        "A Star Is Born",
        "Aquaman",
        "Avengers: Infinity War",
        "Bird Box",
        "Bohemian Rhapsody",
        "Bumblebee",
        "Creed II",
        "Once Upon a Deadpool",
        "How to Train Your Dragon: The Hidden World",
        "Dragon Ball Super: Broly",
        "Glass",
        "Hunter Killer",
        "Mary Poppins Returns",
        "Mortal Engines",
        "Preman Pensiun",
        "Robin Hood",
        "Spider-Man: Into the Spider-Verse",
        "The Girl in the Spider's Web",
        "The Mule",
        "Venom"
    )

    private val movieRelease = arrayOf(
        2018,
        2018,
        2018,
        2018,
        2018,
        2018,
        2018,
        2018,
        2019,
        2018,
        2019,
        2018,
        2018,
        2018,
        2019,
        2018,
        2018,
        2018,
        2018,
        2018
    )

    private val movieGenre = arrayOf(
        "Drama, Music, Romance",
        "Action, Adventure, Fantasy",
        "Action, Adventure, Sci-Fi",
        "Drama, Horror, Sci-Fi",
        "Biography, Drama, Music",
        "Action, Adventure, Sci-Fi",
        "Drama, Sport",
        "Action, Adventure, Comedy",
        "Animation, Action, Adventure",
        "Animation, Action, Adventure",
        "Drama, Sci-Fi, Thriller",
        "Action, Thriller",
        "Comedy, Family, Fantasy",
        "Action, Adventure, Fantasy",
        "Comedy, Drama",
        "Action, Adventure, Thriller",
        "Animation, Action, Adventure",
        "Action, Crime, Drama",
        "Crime, Drama, Thriller",
        "Action, Sci-Fi, Thriller"
    )

    private val movieRating = floatArrayOf(
        7.7f,
        7.0f,
        8.5f,
        6.6f,
        8.0f,
        6.8f,
        6.9f,
        7.7f,
        7.5f,
        7.9f,
        6.7f,
        6.6f,
        6.8f,
        6.1f,
        7.3f,
        5.3f,
        6.1f,
        7.0f,
        6.7f,
        9.1f
    )

    private val movieSynopsis = arrayOf(
        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
        "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
        "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
        "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
        "A kidnapped Fred Savage is forced to endure Deadpool's PG-13 rendition of Deadpool 2 as a Princess Bride-esque story that's full of magic, wonder & zero F's.",
        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
        "Earth is peaceful following the Tournament of Power. Realizing that the universes still hold many more strong people yet to see, Goku spends all his days training to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan called 'Broly' who they've never seen before. The Saiyans were supposed to have been almost completely wiped out in the destruction of Planet Vegeta, so what's this one doing on Earth? This encounter between the three Saiyans who have followed completely different destinies turns into a stupendous battle, with even Frieza (back from Hell) getting caught up in the mix.",
        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
        "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war.",
        "In Depression-era London, a now-grown Jane and Michael Banks, along with Michael's three children, are visited by the enigmatic Mary Poppins following a personal loss. Through her unique magical skills, and with the aid of her friend Jack, she helps the family rediscover the joy and wonder missing in their lives.",
        "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
        "After three years, the business of Muslihat (Epi Kusnandar), who has retired as a thug, has a problem. Sales decline. Muslihat also faces new problems when Safira (Safira Maharani), her only daughter, grows up in adolescence and begins to be visited by boys. A bigger problem: frictions between his former subordinates.",
        "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
        "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson Kingpin Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
        "In Stockholm, Sweden, hacker Lisbeth Salander is hired by Frans Balder, a computer engineer, to retrieve a program that he believes it is too dangerous to exist.",
        "Earl Stone, a man in his eighties, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he's just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates.",
        "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own."
    )

    val listData: ArrayList<Movie>
        get() {
            val list = arrayListOf<Movie>()
            for (position in movieTittle.indices) {
                val movie = Movie()
                movie.release = movieRelease[position]
                movie.tittle = movieTittle[position]
                movie.synopsis = movieSynopsis[position]
                movie.genre = movieGenre[position]
                movie.rating = movieRating[position]
                movie.preview = moviePreview[position]
                movie.poster = moviePoster[position]
                list.add(movie)
            }
            return list
        }
}