package com.ahr.reduce.domain.data

import com.ahr.reduce.R

data class Product(
    val id: Int,
    val type: String,
    val name: String,
    val price: String,
    val photo: Int,
)

val products = listOf(
    Product(0, "Minyak Goreng", "Bimoli", "Rp. 15.000 / pcs",R.drawable.bimoli),
    Product(1, "Detergen", "Rinso Matic ", "Rp. 15.000 / pcs",R.drawable.rinso_matic_profes),
    Product(2, "Sabun mandi", "Dettol Bodywash", "Rp. 15.000 / pcs",R.drawable.dettol_bodywash),
    Product(3, "Sabun cuci tangan", "Dettol Handwash", "Rp. 15.000 / pcs",R.drawable.dettol_handwash),
    Product(4, "Minyak Goreng", "Bimoli", "Rp. 15.000 / pcs",R.drawable.bimoli),
    Product(5, "Detergen", "Rinso Matic ", "Rp. 15.000 / pcs",R.drawable.rinso_matic_profes),
    Product(6, "Sabun mandi", "Dettol Bodywash", "Rp. 15.000 / pcs",R.drawable.dettol_bodywash),
    Product(7, "Sabun cuci tangan", "Dettol Handwash", "Rp. 15.000 / pcs",R.drawable.dettol_handwash),
)
