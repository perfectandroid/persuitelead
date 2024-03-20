package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.MpinModel
import com.perfect.persuitelead.Repository.MpinActivityRepository


class MpinActivityViewModel : ViewModel() {

    var mpinLiveData: MutableLiveData<MpinModel>? = null

    fun veryfyMpin(context: Context,strMPIN :  String) : LiveData<MpinModel>? {
        mpinLiveData = MpinActivityRepository.getServicesApiCall(context, strMPIN)
        return mpinLiveData
    }

}