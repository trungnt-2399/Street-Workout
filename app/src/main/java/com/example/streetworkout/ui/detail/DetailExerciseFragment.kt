package com.example.streetworkout.ui.detail

import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment

class DetailExerciseFragment : BaseFragment() {
    override val layoutResources: Int
        get() = R.layout.fragment_detail_exercises

    override fun initData() {}

    override fun initAction() {}

    companion object {
        fun getBundle(title: String, imageUrl: String, id: Int) =
            DetailExerciseFragmentArgs(title, imageUrl, id).toBundle()
    }
}
