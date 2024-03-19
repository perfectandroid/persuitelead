package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.CommonAppModel
import com.perfect.persuitelead.Repository.CommonAppRepository


class CommonAppViewModel : ViewModel()  {

    var commonAppLiveData: MutableLiveData<CommonAppModel>? = null

    fun getCommonApp(context: Context) : LiveData<CommonAppModel>? {
        commonAppLiveData = CommonAppRepository.getServicesApiCall(context)
        return commonAppLiveData
    }
}