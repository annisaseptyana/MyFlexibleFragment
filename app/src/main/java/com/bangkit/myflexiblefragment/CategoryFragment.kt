package com.bangkit.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit

class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDetailCategory:Button = view.findViewById(R.id.btn_detail_category)
        btnDetailCategory.setOnClickListener{
            val categoryDetailsFragment = CategoryDetailsFragment()

            val bundle = Bundle()
            bundle.putString(CategoryDetailsFragment.CATEGORY_NAME, "Pets")
            val description = "This category is for \"Pets Stuff\""
            bundle.putString(CategoryDetailsFragment.CATEGORY_DESCRIPTION, description)

            categoryDetailsFragment.arguments = bundle

            val fragmentManager = parentFragmentManager
            fragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container, categoryDetailsFragment, CategoryDetailsFragment::class.java.simpleName)
            }

        }
    }
}