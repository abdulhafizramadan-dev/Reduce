package com.ahr.reduce.domain.data

data class DetailAddressForm(
    val streetName: String = "",
    val ward: String = "",
    val subdistrict: String = "",
    val regency: String = "",
    val province: String = "",
    val completeAddress: String = "",
)
