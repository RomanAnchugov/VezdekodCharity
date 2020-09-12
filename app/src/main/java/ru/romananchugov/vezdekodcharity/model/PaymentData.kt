package ru.romananchugov.vezdekodcharity.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentData(
    val imageUri: Uri,
    val authorName: String,
    val paymentName: String,
    val paymentType: PaymentType,
    val paymentAmount: Int,
    val paymentTarget: String,
    val paymentDescription: String
) : Parcelable