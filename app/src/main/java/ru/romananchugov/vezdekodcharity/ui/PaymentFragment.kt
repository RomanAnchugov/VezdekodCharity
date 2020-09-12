package ru.romananchugov.vezdekodcharity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.android.synthetic.main.fragment_payment.*
import ru.romananchugov.vezdekodcharity.R

class PaymentFragment : Fragment() {

    private val args: PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_payment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbarContent.title = args.paymentData.paymentName
        paymentImage.load(args.paymentData.imageUri) {
            crossfade(true)
        }
        authorName.text = args.paymentData.authorName
        paymentInfo.text = args.paymentData.paymentTarget

    }
}