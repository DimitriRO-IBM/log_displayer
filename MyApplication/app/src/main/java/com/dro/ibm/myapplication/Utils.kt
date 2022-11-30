package com.dro.ibm.myapplication

import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Utils {
    fun <T> Call<T>.toSingle(): Single<Response<T>> {
        val single = SingleSubject.create<Response<T>>()
        this.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                single.onError(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                single.onSuccess(response)
            }
        })

        return single
    }
}