package com.kajangdev.core.di

import androidx.room.Room
import com.kajangdev.core.data.Repository
import com.kajangdev.core.data.source.local.LocalDataSource
import com.kajangdev.core.data.source.local.room.MovieKitaDatabase
import com.kajangdev.core.data.source.remote.RemoteDataSource
import com.kajangdev.core.data.source.remote.network.ApiService
import com.kajangdev.core.domain.repository.IRepository
import com.kajangdev.core.utils.AppExecutors
import com.kajangdev.core.utils.Constant.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieKitaDatabase>().movieKitaDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("kajang".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
                androidContext(),
                MovieKitaDatabase::class.java, "MovieKita.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostName = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostName, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostName, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostName, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
    }
    single {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRepository> {
        Repository(
                get(),
                get(),
                get()
        )
    }
}