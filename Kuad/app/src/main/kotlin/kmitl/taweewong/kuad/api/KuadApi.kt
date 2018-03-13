package kmitl.taweewong.kuad.api

import kmitl.taweewong.kuad.models.Bottle
import retrofit2.Call
import retrofit2.http.GET

interface KuadApi {

    companion object {
        const val BASE = "https://us-central1-kuad-9e21b.cloudfunctions.net"
    }

    @GET("/randomBottle")
    fun getRandomBottle(): Call<Bottle>
}