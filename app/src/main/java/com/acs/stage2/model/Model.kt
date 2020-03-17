package com.acs.stage2.model

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("company")
    val company : String?,
    @SerializedName("team")
    val team : String,
    @SerializedName("members")
    val members : ArrayList<Member>)

class Member(val name : String?,
                  val age : Int?,
                  val location : String?,
                  val github : String?,
                  val hipo : Hipo)

class Hipo(val position : String?, val years_in_hipo : Int?)