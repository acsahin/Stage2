package com.acs.stage2.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acs.stage2.model.Hipo
import com.acs.stage2.model.Member
import com.acs.stage2.viewmodel.service.Database
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application){
    val memberLiveData = MutableLiveData<Member>()
    private val gson = GsonBuilder().create()

    fun setData(info: String) {
        var theMember = gson.fromJson(info, Member::class.java)
        memberLiveData.value = theMember
    }
}