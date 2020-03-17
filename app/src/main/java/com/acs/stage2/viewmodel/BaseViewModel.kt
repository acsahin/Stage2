package com.acs.stage2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//To use coroutines in fragments' view models
//This viewmodel is made
//Instead of implementing them to viewmodels separately
abstract class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}