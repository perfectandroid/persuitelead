package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.SetMpinModel
import com.perfect.persuitelead.Repository.SetMpinActivityRepository


class SetMpinActivityViewModel : ViewModel() {

    var setmpinLiveData: MutableLiveData<SetMpinModel>? = null

    fun setMpin(context: Context,strMPIN : String) : LiveData<SetMpinModel>? {
        setmpinLiveData = SetMpinActivityRepository.getServicesApiCall(context,strMPIN)
        return setmpinLiveData
    }

}