package com.perfect.persuitelead.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perfect.persuitelead.Model.VersionModel



class VersionCheckViewModel : ViewModel() {

    var versionData: MutableLiveData<VersionModel>? = null

    fun getVersion(context: Context) : LiveData<VersionModel>? {
      //  versionData = VersionRepository.getServicesApiCall(context)
        return versionData
    }
}