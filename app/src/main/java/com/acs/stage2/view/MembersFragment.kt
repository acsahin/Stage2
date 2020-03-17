package com.acs.stage2.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.acs.stage2.R
import com.acs.stage2.viewmodel.MembersViewModel
import kotlinx.android.synthetic.main.fragment_members.*

//The main fragment
//Shows the members list
//Contains add button and searchview(edittext)

class MembersFragment : Fragment() {
    private var situation = 0
    private lateinit var viewModel: MembersViewModel
    private val memberAdapter = MemberAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)

        //If situation is zero, the application is started yet
        //So, get the data from asset file just once
        //Then change the situation
        if(situation == 0 && viewModel.members.value==null) {
            viewModel.refreshData()
        }
        situation = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = memberAdapter

        observeLiveData()

        //Add me to the list on click button
        addMemberButton.setOnClickListener {
            viewModel.updateTheData()
        }


        //Listen the search button
        searchTextView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                memberAdapter.updateMemberList(viewModel.filterList(s.toString()))
            }
        })
    }


    //Show the member list in recycler view
    private fun observeLiveData() {
        viewModel.members.observe(viewLifecycleOwner, Observer {
            it?.let {
                memberAdapter.updateMemberList(it)
                memberCount.text = viewModel.members.value?.size.toString()+" members"
                if(!searchTextView.text.toString().equals("")) {

                    //If there is any change, update the member list
                    memberAdapter.updateMemberList(viewModel.filterList(searchTextView.text.toString()))
                }
            }
        })
    }

}
