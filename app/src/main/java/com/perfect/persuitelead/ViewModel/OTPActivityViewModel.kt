package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.OTPModel
import com.perfect.persuitelead.Repository.OTPActivityRepository

class OTPActivityViewModel : ViewModel() {

    var otpLiveData: MutableLiveData<OTPModel>? = null

    fun getOTP(context: Context,strMOTP :  String) : LiveData<OTPModel>? {
        otpLiveData = OTPActivityRepository.getServicesApiCall(context, strMOTP)
        return otpLiveData
    }

}