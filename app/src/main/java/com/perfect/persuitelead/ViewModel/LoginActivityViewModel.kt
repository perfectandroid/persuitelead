package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.LoginModel
import com.perfect.persuitelead.Repository.LoginActivityRepository

class LoginActivityViewModel : ViewModel() {

    var loginLiveData: MutableLiveData<LoginModel>? = null

    fun getUser(context: Context,strEPhone : String) : LiveData<LoginModel>? {
        loginLiveData = LoginActivityRepository.getServicesApiCall(context, strEPhone)
        return loginLiveData
    }

}