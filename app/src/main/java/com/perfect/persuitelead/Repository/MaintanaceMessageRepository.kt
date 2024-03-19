package com.perfect.persuitelead.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.perfect.persuitelead.Api.ApiInterface
import com.perfect.persuitelead.Helper.Config
import com.perfect.persuitelead.Helper.ProdsuitApplication
import com.perfect.persuitelead.Model.MaintananceMessageModel


import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

object MaintanaceMessageRepository {

    var TAG = "MaintanaceMessageRepository"
    val maintanaceSetterGetter = MutableLiveData<MaintananceMessageModel>()

    fun getServicesApiCall(context: Context): MutableLiveData<MaintananceMessageModel> {
        maintananceMessage(context)
        return maintanaceSetterGetter
    }

    private fun maintananceMessage(context: Context) {
        try {
            maintanaceSetterGetter.value = MaintananceMessageModel("")
            val BASE_URLSP = context.getSharedPreferences(Config.SHARED_PREF7, 0)
            val client = OkHttpClient.Builder()
                .sslSocketFactory(Config.getSSLSocketFactory(context))
                .hostnameVerifier(Config.getHostnameVerifier())
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URLSP.getString("BASE_URL", null))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            val apiService = retrofit.create(ApiInterface::class.java!!)
            val requestObject1 = JSONObject()
            try {
                val BankKeySP = context.getSharedPreferences(Config.SHARED_PREF9, 0)
                requestObject1.put("BankKey", ProdsuitApplication.encryptStart(BankKeySP.getString("BANK_KEY", null)))
                requestObject1.put("ReqMode", ProdsuitApplication.encryptStart("11"))
                Log.e(TAG,"541  requestObject1   "+BASE_URLSP.getString("BASE_URL", null))
                Log.e(TAG,"541  requestObject1   "+requestObject1)
                Log.e(TAG,"541  requestObject1   "+requestObject1)
                Log.i("response44","maintance body.............  "+requestObject1)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            val body = RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                requestObject1.toString()
            )
            val call = apiService.getMaintenanceMessage(body)
            call.enqueue(object : retrofit2.Callback<String> {
                override fun onResponse(
                    call: retrofit2.Call<String>, response:
                    Response<String>
                ) {
                    try {
                        Log.e(TAG,"541  response   "+response.body())
                        val jObject = JSONObject(response.body())
                        val users = ArrayList<MaintananceMessageModel>()
                        users.add(MaintananceMessageModel(response.body()))
                        val msg = users[0].message
                        maintanaceSetterGetter.value = MaintananceMessageModel(msg)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context,""+e.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    Toast.makeText(context,""+Config.SOME_TECHNICAL_ISSUES, Toast.LENGTH_SHORT).show()
                    Log.e(TAG,"541  response   "+t.message)
                }
            })
         }
        catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG,"541  response   "+e.toString())
            Toast.makeText(context,""+Config.SOME_TECHNICAL_ISSUES,Toast.LENGTH_SHORT).show()
        }
    }

}

