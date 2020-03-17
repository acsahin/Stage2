package com.acs.stage2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.acs.stage2.R
import com.acs.stage2.model.Member
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.item_member.view.*


//RecyclerView adapter
//Shows the Members with names

class MemberAdapter(val memberList : ArrayList<Member>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>(){
    private val gson = GsonBuilder().create()

    class MemberViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
    }

    //Connection between adapter and layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_member, parent, false)
        return MemberViewHolder(view)
    }

    //The size of recyclerview equals to size of member list
    override fun getItemCount(): Int {
        return memberList.size
    }


    //Fill the recycler view with member names
    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.view.memberNameText.text = memberList[position].name

        //Get the info of clicked member and send it to the details fragment
        holder.view.setOnClickListener {
            val action = MembersFragmentDirections.actionMembersFragmentToDetailsFragment(gson.toJson(memberList[position]))
            Navigation.findNavController(it).navigate(action)
        }

    }

    //Update member list on changes
    fun updateMemberList(newMemberList : List<Member>) {
        memberList.clear()
        memberList.addAll(newMemberList)
        notifyDataSetChanged()
    }
}