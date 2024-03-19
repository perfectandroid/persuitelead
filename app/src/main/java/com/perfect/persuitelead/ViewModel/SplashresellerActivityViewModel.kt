package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.ResellerModel
import com.perfect.persuitelead.Repository.SplashresellerActivityRepository


class SplashresellerActivityViewModel : ViewModel() {

    var resellerLiveData: MutableLiveData<ResellerModel>? = null

    fun getReseller(context: Context) : LiveData<ResellerModel>? {
        resellerLiveData = SplashresellerActivityRepository.getServicesApiCall(context)
        return resellerLiveData
    }

}