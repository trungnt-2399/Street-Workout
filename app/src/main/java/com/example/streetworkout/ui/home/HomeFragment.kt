package com.example.streetworkout.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.model.GroupMuscle
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val challengeAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val beginAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val normalAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val advanceAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val homeViewModel by viewModel<HomeViewModel>()

    override val layoutResources: Int
        get() = R.layout.fragment_home

    override fun initData() {
        initAdapter()
        homeViewModel.getData()
        observeData()
    }

    override fun initAction() {}

    private fun initAdapter() {
        recyclerViewChallenge.adapter = challengeAdapter
        recyclerViewMuscleBegin.adapter = beginAdapter
        recyclerViewMuscleNormal.adapter = normalAdapter
        recyclerViewMuscleAdvance.adapter = advanceAdapter
    }

    private fun openDetailExerciseFragment(item: GroupMuscle, position: Int) = with(item) {
        findNavController().navigate(
            HomeFragmentDirections.actionToDetailExerciseFragment(title, image, id)
        )
    }

    private fun observeData() {
        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
            challengeAdapter.submitList(it.challenge)
            beginAdapter.submitList(it.beginner)
            normalAdapter.submitList(it.normal)
            advanceAdapter.submitList(it.advance)
        })

        homeViewModel.error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }
}
