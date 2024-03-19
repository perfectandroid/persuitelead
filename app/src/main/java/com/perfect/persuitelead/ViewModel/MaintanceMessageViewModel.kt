package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.MaintananceMessageModel
import com.perfect.persuitelead.Repository.MaintanaceMessageRepository


class MaintanceMessageViewModel : ViewModel() {

    var maintanaceLiveData: MutableLiveData<MaintananceMessageModel>? = null

    fun getMaintanceMessgae(context: Context) : LiveData<MaintananceMessageModel>? {
        maintanaceLiveData = MaintanaceMessageRepository.getServicesApiCall(context)
        return maintanaceLiveData
    }

}