package ru.romananchugov.vezdekodcharity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_podcast_description.*
import kotlinx.android.synthetic.main.layout_text_input.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import ru.romananchugov.vezdekodcharity.model.PaymentType

class PodcastDescriptionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_podcast_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title.text = "Новый подкаст"
        toolbar.backBtn.isVisible = true
        toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        podcastNameInput.inputTitleTv.text="Название"
        podcastNameInput.inputFieldEt.hint="Введите название подкаста"

        podcastDescriptionInput.inputTitleTv.text="Описание подкаста"
        podcastDescriptionInput.inputFieldEt.hint=""
    }
}