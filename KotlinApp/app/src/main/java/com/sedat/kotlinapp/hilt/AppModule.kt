package com.sedat.kotlinapp.hilt

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.api.RetrofitApi
import com.sedat.kotlinapp.fragment.room.Database
import com.sedat.kotlinapp.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofit(): RetrofitApi{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context)
                .setDefaultRequestOptions(
                        RequestOptions().placeholder(R.drawable.downloading_icon_24).error(R.drawable.error_icon_24)
                )

    @Singleton
    @Provides
    fun injectDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "RoomDb"
        ).build()

    @Singleton
    @Provides
    fun injectDao(databse: Database) = databse.roomDao()

    @Singleton
    @Provides
    fun injectFirebaseFirestore() = FirebaseFirestore.getInstance()
}