package com.ahr.reduce.data.data

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ProductRealm : RealmObject {
    @PrimaryKey
    @PersistedName("_id")
    var id: ObjectId = ObjectId.invoke()
    var type: String = ""
    var name: String = ""
    var price: Double = 15000.0
    var photo: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}

val dummyProductRealms = realmListOf(
    ProductRealm().apply {
        type = "Deterjen cair"
        name = "Rinso Matic"
    },
    ProductRealm().apply {
        type = "Pelembut pakaian"
        name = "Molto Pewangi"
    },
    ProductRealm().apply {
        type = "Minyak goreng"
        name = "Bimoli"
    },
    ProductRealm().apply {
        type = "Sabun cuci tangan"
        name = "Dettol Handwash"
    },
    ProductRealm().apply {
        type = "Sabun mandi"
        name = "Dettol Bodywash"
    },
    ProductRealm().apply {
        type = "Makanan hewan"
        name = "Whiskas Dry Tuna"
    },
)
