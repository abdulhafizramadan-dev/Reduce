package com.ahr.reduce.domain.data

data class Product(
    val id: Int = 0,
    val type: String = "",
    val name: String = "",
    val price: Long = 0,
    val photo: String = "",
    var documentId: String? = null,
)

val dummyProduct = Product(
    id = 0,
    type = "Minyak Goreng",
    name = "Bimoli",
    price = 10000,
    photo = "https://firebasestorage.googleapis.com/v0/b/reduce-a6a72.appspot.com/o/product%2Fbimoli.png?alt=media&token=0dd3b01b-8a78-4301-9534-34c472a8af42",
    documentId = "4j34YUtYYJ0EIhb4tcFK"
)