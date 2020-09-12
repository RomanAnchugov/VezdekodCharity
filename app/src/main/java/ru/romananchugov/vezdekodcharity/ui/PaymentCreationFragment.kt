package ru.romananchugov.vezdekodcharity.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.esafirm.imagepicker.features.ImagePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_payment_creation.*
import kotlinx.android.synthetic.main.layout_dropdown.view.*
import kotlinx.android.synthetic.main.layout_text_input.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import ru.romananchugov.vezdekodcharity.R
import ru.romananchugov.vezdekodcharity.model.PaymentData
import ru.romananchugov.vezdekodcharity.model.PaymentType

class PaymentCreationFragment : Fragment() {

    private val args: PaymentCreationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_payment_creation, container, false)


    var imageUri: Uri? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageLoadBtn.setOnClickListener {
        }
        initViews()
    }

    private fun initViews() {
        toolbar.title.text = when (args.paymentType) {
            PaymentType.REGULAR -> "Регулярный сбор"
            PaymentType.TARGET -> "Целевой сбор"
        }
        toolbar.backBtn.isVisible = true
        toolbar.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        paymentNameInput.inputTitleTv.text = "Название сбора"
        paymentNameInput.inputFieldEt.hint = "Название сбора"

        paymentAmountInput.inputTitleTv.text = when (args.paymentType) {
            PaymentType.REGULAR -> "Сумма в месяц, ₽"
            PaymentType.TARGET -> "Сумма, ₽"
        }
        //TODO: formating with rubles
        paymentAmountInput.inputFieldEt.hint = when (args.paymentType) {
            PaymentType.REGULAR -> "Сколько нужно в месяц?"
            PaymentType.TARGET -> "Сколько нужно собрать?"
        }

        paymentTarget.inputTitleTv.text = "Цель"
        paymentTarget.inputFieldEt.hint = when (args.paymentType) {
            PaymentType.REGULAR -> "Например, поддержка приюта"
            PaymentType.TARGET -> "Например, лечение человека"
        }

        paymentDescription.inputTitleTv.text = "Описание"
        paymentDescription.inputFieldEt.hint = "На что пойдут деньги и как они кому-то помогут"

        whereToGetMoneyContainer.dropdownTitle.text = "Куда получать деньги"
        whereToGetMoneyContainer.dropdownSelector.text = "Счёт VK Pay • 1234"

        // TODO: bottomSheet for selection
        authorContainer.isVisible = args.paymentType == PaymentType.REGULAR
        authorContainer.dropdownTitle.text = "Автор"
        authorContainer.dropdownSelector.text = "Роман Анчугов"
        authorContainer.dropdownSelector.setOnClickListener {
            Toast.makeText(context, "Здесь должен был открываться BottomSheet", Toast.LENGTH_SHORT)
                .show()
        }

        confirmBtn.text = when (args.paymentType) {
            PaymentType.REGULAR -> "Далее"
            PaymentType.TARGET -> "Создать сбор"
        }

        imageLoadBtn.setOnClickListener {
            ImagePicker.create(this)
                .start()
        }
        imageDeleteBtn.setOnClickListener {
            imageLoadBtn.setImageResource(R.drawable.bacgkround_empty)
            imageLoadBtn.setOnClickListener {
                ImagePicker.create(this)
                    .start()
            }
            imageLoadHint.isVisible = true
            it.isVisible = false
        }

        //TODO: simple validation
        confirmBtn.setOnClickListener {
            if (imageUri != null && paymentNameInput.inputFieldEt.text.isNotBlank()
                && paymentAmountInput.inputFieldEt.text.isNotBlank()
                && paymentTarget.inputFieldEt.text.isNotBlank()
                && paymentDescription.inputFieldEt.text.isNotBlank()
            ) {
                findNavController().navigate(
                    PaymentCreationFragmentDirections.actionPaymentCreationFragmentToAdditionalFragment(
                        PaymentData(
                            imageUri = imageUri!!,
                            authorName = "Mocked Name",
                            paymentType = args.paymentType,
                            paymentAmount = 12345,
                            paymentName = paymentNameInput.inputFieldEt.text.toString(),
                            paymentTarget = paymentTarget.inputFieldEt.text.toString(),
                            paymentDescription = paymentDescription.inputFieldEt.text.toString()
                        )
                    )
                )
            } else {
                Snackbar.make(
                    requireView(),
                    "Заполните, пожалуйста, все поля",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            ImagePicker.getImages(data).firstOrNull()?.let {
                imageUri = it.uri
                imageLoadBtn.load(it.uri) {
                    crossfade(true)
                }
                imageLoadBtn.setOnClickListener(null)
                imageDeleteBtn.isVisible = true
                imageLoadHint.isVisible = false
            }

        }
    }
}