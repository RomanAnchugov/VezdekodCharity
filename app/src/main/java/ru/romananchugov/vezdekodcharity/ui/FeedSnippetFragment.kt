package ru.romananchugov.vezdekodcharity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.android.synthetic.main.fragment_feed_snippet.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import ru.romananchugov.vezdekodcharity.R

class FeedSnippetFragment : Fragment() {

    val args: FeedSnippetFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_feed_snippet, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title.text = "Стена"
        toolbar.backBtn.isVisible = true
        toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        paymentImage.load(
            args.paymentData.imageUri
        ) {
            crossfade(true)
        }

        paymentName.text = args.paymentData.paymentName
        paymentInfo.text = "${args.paymentData.authorName} • ${args.paymentData.paymentAmount}₽"

        helpBtn.setOnClickListener {
            if (progressIndicator.isVisible.not()) {
                progressIndicator.isVisible = true
            }

            it.post {
                progressIndicator.layoutParams =
                    (progressIndicator.layoutParams as ConstraintLayout.LayoutParams).apply {
                        if (width < progress.width) {
                            width =
                                (width + (progress.width * 0.1).toInt()).coerceAtMost(progress.width)
                        }
                        if (width > 0) {
                            paymentStatus.text =
                                "Собрано ${
                                    (args.paymentData.paymentAmount * width / progress.width).coerceAtMost(
                                        args.paymentData.paymentAmount
                                    )
                                }₽ из ${args.paymentData.paymentAmount}₽"
                        }
                    }
            }
        }

        snippetContainer.setOnClickListener {
            findNavController().navigate(
                FeedSnippetFragmentDirections.actionFeedSnippetFragmentToPaymentFragment(
                    args.paymentData
                )
            )
        }
    }
}
