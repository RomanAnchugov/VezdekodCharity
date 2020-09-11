package ru.romananchugov.vezdekodcharity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_payment_type.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import ru.romananchugov.vezdekodcharity.R
import ru.romananchugov.vezdekodcharity.model.PaymentType

class PaymentTypeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_payment_type, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title.isVisible = true
        //Я не даун, знаю, что хардкодить нельзя, но тип так быстрее - хакатон же ;)
        toolbar.title.text = "Тип сбора"
        toolbar.backBtn.isVisible = true
        toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        targetPaymentContainer.setOnClickListener {
            findNavController().navigate(
                PaymentTypeFragmentDirections.actionPaymentTypeFragmentToPaymentCreationFragment(
                    paymentType = PaymentType.TARGET
                )
            )
        }
        regularPaymentContainer.setOnClickListener {
            findNavController().navigate(
                PaymentTypeFragmentDirections.actionPaymentTypeFragmentToPaymentCreationFragment(
                    paymentType = PaymentType.REGULAR
                )
            )
        }
    }
}