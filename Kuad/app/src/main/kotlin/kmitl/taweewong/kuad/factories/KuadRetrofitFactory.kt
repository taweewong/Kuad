package kmitl.taweewong.kuad.factories

import kmitl.taweewong.kuad.api.KuadApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KuadRetrofitFactory {

    fun build(): KuadApi {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(KuadApi.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(KuadApi::class.java)
    }
}