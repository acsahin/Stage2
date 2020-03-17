package com.acs.stage2.viewmodel.service

import android.content.Context
import android.content.res.AssetManager
import android.os.AsyncTask
import android.os.Environment
import com.acs.stage2.model.Member
import com.acs.stage2.model.Model
import com.google.gson.GsonBuilder
import java.io.*
import java.lang.Exception

class Database(c : Context){
    val FILE_NAME = "hipo.json"
    var result = ""
    val context = c



    //This method is just used once
    //At the start of application
    //Retrieves data from assets
    suspend fun getData() : List<Member>{
        readData(FILE_NAME)
        val gson = GsonBuilder().create()
        val model = gson.fromJson(result, Model::class.java)
        return model.members
    }

    //Reading from the file
    suspend fun readData(fileName: String) : String{
        try {
            val assetManager : AssetManager = context.assets
            val inputStream : InputStream = assetManager.open(FILE_NAME)
            result = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}