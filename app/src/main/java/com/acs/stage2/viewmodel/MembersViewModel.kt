package com.acs.stage2.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.acs.stage2.model.Hipo
import com.acs.stage2.model.Member
import com.acs.stage2.viewmodel.service.Database
import kotlinx.coroutines.launch

class MembersViewModel(application: Application) : BaseViewModel(application) {
    val members = MutableLiveData<List<Member>>()
    private val db = Database(application.applicationContext)

    //get the data from assets file
    //This method is called only once at the start
    fun refreshData() {
        launch {
            members.value = db.getData()
        }
    }

    // Press the button and add me
    fun updateTheData() {
        val meMember = Member("Ahmet Cemal Sahin",24,"Istanbul", "ahmetcemalsahin",Hipo("Intern",0))
        var tempList = members.value!!
        tempList = tempList.toCollection(arrayListOf())
        tempList.add(meMember)
        members.value = tempList
        Toast.makeText(getApplication(), "me is the new member :)", Toast.LENGTH_SHORT).show()
    }

    //When the searchview is used
    //This method copies the list to show filtered one
    //Instead of filter the original one
    fun giveData() : ArrayList<Member>? {
        return members.value?.toCollection(ArrayList())
    }


    //To filter the recycler view
    fun filterList(s: String) : ArrayList<Member>{
        var itemList = giveData()
        var tempList = ArrayList<Member>()
        for(d in itemList!!) {
            if(s.toLowerCase() in d.name.toString().toLowerCase()) {
                tempList.add(d)
            }
        }
        return tempList
    }


}