package com.ahr.reduce.domain.data

data class Product(
    val id: Int = 0,
    val type: String = "",
    val name: String = "",
    val price: Long = 0,
    val photo: String = "",
    var documentId: String? = null,
)