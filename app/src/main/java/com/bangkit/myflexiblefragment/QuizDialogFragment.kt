package com.bangkit.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class QuizDialogFragment : DialogFragment() {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgQuiz: RadioGroup
    private lateinit var rbTaeyeon: RadioButton
    private lateinit var rbWendy: RadioButton
    private lateinit var rbYuju: RadioButton
    private lateinit var rbHyejin: RadioButton
    private var quizDialogListener: OnQuizDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgQuiz = view.findViewById(R.id.rg_quiz)
        rbTaeyeon = view.findViewById(R.id.rb_taeyeon)
        rbWendy = view.findViewById(R.id.rb_wendy)
        rbHyejin = view.findViewById(R.id.rb_hyejin)
        rbYuju = view.findViewById(R.id.rb_yuju)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgQuiz.checkedRadioButtonId
            if  (checkedRadioButtonId != -1) {
                var singer: String? = when (checkedRadioButtonId) {
                    R.id.rb_yuju -> rbYuju.text.toString().trim()
                    R.id.rb_taeyeon -> rbTaeyeon.text.toString().trim()
                    R.id.rb_hyejin -> rbHyejin.text.toString().trim()
                    R.id.rb_wendy -> rbWendy.text.toString().trim()
                    else -> null
                }
                quizDialogListener?.onQuizChosen(singer)
                dialog?.dismiss()
            }
        }

        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if (fragment is CategoryDetailsFragment) {
           this.quizDialogListener = fragment.quizDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.quizDialogListener = null
    }

    interface OnQuizDialogListener {
        fun onQuizChosen(text: String?)
    }
}