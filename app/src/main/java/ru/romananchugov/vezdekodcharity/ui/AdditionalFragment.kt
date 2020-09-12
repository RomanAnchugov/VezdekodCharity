package ru.romananchugov.vezdekodcharity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_additional.*
import kotlinx.android.synthetic.main.layout_dropdown.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import ru.romananchugov.vezdekodcharity.R

class AdditionalFragment : Fragment() {

    val args: AdditionalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_additional, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    fun initViews() {
        toolbar.title.text = "Дополнительно"
        toolbar.backBtn.isVisible = true
        toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            dateContainer.isVisible = checkedId == R.id.radio2
        }

        authorContainer.setOnClickListener {
            //TODO
        }

        dateContainer.dropdownTitle.text = "Дата окончания"
        dateContainer.dropdownSelector.text = "Выберите дату"
        dateContainer.dropdownSelector.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.lightTextPlaceholder
            )
        )
        dateContainer.dropdownSelector.setOnClickListener {
            Toast.makeText(
                context,
                "Здесь должен был открываться DatePicker :(",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        //TODO: validation for confirmBtn
        confirmBtn.text = "Создать сбор"
        confirmBtn.setOnClickListener {
            findNavController().navigate(
                AdditionalFragmentDirections.actionAdditionalFragmentToFeedSnippetFragment(
                    args.paymentData
                )
            )
        }
    }


}