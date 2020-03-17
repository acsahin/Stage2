package com.acs.stage2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.acs.stage2.R
import com.acs.stage2.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel
    private var memberString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get the member info from fragment arguments
        arguments?.let {
            memberString = DetailsFragmentArgs.fromBundle(it).memberName
        }
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.setData(memberString)
        observeLiveData()
    }


    //Fill the texts with member information
    private fun observeLiveData() {
        viewModel.memberLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                nameText.text = it.name +", "+it.age.toString()
                locationText.text = it.location
                githubText.text = it.github
                positionText.text = it.hipo.position+", "+it.hipo.years_in_hipo.toString()+" years"
            }
        })
    }
}
