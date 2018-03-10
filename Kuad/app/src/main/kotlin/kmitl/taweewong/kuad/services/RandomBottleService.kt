package kmitl.taweewong.kuad.services

import kmitl.taweewong.kuad.descriptions.ErrorMessage.NO_ERROR_MESSAGE
import kmitl.taweewong.kuad.descriptions.ErrorMessage.RANDOM_BOTTLE_NOT_FOUND
import kmitl.taweewong.kuad.factories.KuadRetrofitFactory
import kmitl.taweewong.kuad.models.Bottle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RandomBottleService {

    interface OnGetRandomBottleCompleteListener {
        fun onGetRandomBottleSuccess(bottle: Bottle)
        fun onGetRandomBottleFailed(message: String)
    }

    private val kuadApi = KuadRetrofitFactory.build()

    fun getRandomBottle(listener: OnGetRandomBottleCompleteListener) {
        val call = kuadApi.getRandomBottle()

        call.enqueue(object : Callback<Bottle> {
            override fun onResponse(call: Call<Bottle>?, response: Response<Bottle>) {
                if (response.isSuccessful) {
                    val bottle = response.body()
                    if (bottle != null) {
                        listener.onGetRandomBottleSuccess(bottle)
                    } else {
                        listener.onGetRandomBottleFailed(RANDOM_BOTTLE_NOT_FOUND)
                    }
                } else {
                    listener.onGetRandomBottleFailed(response.toString())
                }
            }

            override fun onFailure(call: Call<Bottle>?, t: Throwable) {
                listener.onGetRandomBottleFailed(t.message?: NO_ERROR_MESSAGE)
            }
        })
    }
}