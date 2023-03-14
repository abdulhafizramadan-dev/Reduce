package com.ahr.reduce.data.data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Product : RealmObject {
    @PrimaryKey
    val id: ObjectId = ObjectId.invoke()
    val type: String = ""
    val name: String = ""
    val price: Double = 0.0
    val photo: String = ""
    val timestamp: RealmInstant = RealmInstant.now()
}