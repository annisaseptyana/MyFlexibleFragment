package com.bangkit.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategoryDetailsFragment : Fragment() {

    lateinit var tvCategoryName: TextView
    lateinit var tvCategoryDescription: TextView
    lateinit var btnProfile: Button
    lateinit var btnDialog: Button

    companion object {
        var CATEGORY_NAME = "category_name"
        var CATEGORY_DESCRIPTION = "category_description"

        fun newInstance(param1: String, param2: String) =
            CategoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCategoryName: TextView = view.findViewById(R.id.tv_category_name)
        val tvCategoryDescription: TextView = view.findViewById(R.id.tv_category_description)
        val btnProfile: Button = view.findViewById(R.id.btn_profile)
        val btnDialog: Button = view.findViewById(R.id.btn_dialog)

        if (arguments != null) {
            val categoryName = arguments?.getString(CATEGORY_NAME)
            val categoryDescription = arguments?.getString(CATEGORY_DESCRIPTION)

            tvCategoryName.text = categoryName
            tvCategoryDescription.text = categoryDescription
        }

        btnDialog.setOnClickListener{
            val quizDialogFragment = QuizDialogFragment()

            val fragmentManager = childFragmentManager
            quizDialogFragment.show(fragmentManager, QuizDialogFragment::class.java.simpleName)
        }

        btnProfile.setOnClickListener{
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }

    }

    internal var quizDialogListener: QuizDialogFragment.OnQuizDialogListener = object : QuizDialogFragment.OnQuizDialogListener {
        override fun onQuizChosen(text: String?) {
            // maunya sih ganti textcolor soalnya quiz
            Toast.makeText(requireActivity(), text, Toast.LENGTH_LONG).show()
        }
    }

}