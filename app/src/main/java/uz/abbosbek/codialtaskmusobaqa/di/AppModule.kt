package uz.abbosbek.codialtaskmusobaqa.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abbosbek.codialtaskmusobaqa.data.source.ApiService
import uz.abbosbek.codialtaskmusobaqa.domain.repository.AuthRepository
import uz.abbosbek.codialtaskmusobaqa.domain.repository.DiscountRepository
import uz.abbosbek.codialtaskmusobaqa.domain.repository.impl.AuthRepositoryImpl
import uz.abbosbek.codialtaskmusobaqa.domain.repository.impl.DiscountRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    private val BASE_URL = "https://rizonwebappapi.pythonanywhere.com/"
    private val BASE_URL_2= "https://rizonwebappapi.pythonanywhere.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(api: ApiService,gson: Gson): AuthRepository {
        return AuthRepositoryImpl(api,gson)
    }

    @Provides
    @Singleton
    fun provideRepositoryDiscount(api: ApiService,gson: Gson): DiscountRepository {
        return DiscountRepositoryImpl(api,gson)
    }

    @Provides
    @Singleton
    fun provideClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(ChuckerInterceptor.Builder(context).build()).build()


    @Provides
    fun provideGson(): Gson = Gson()

}