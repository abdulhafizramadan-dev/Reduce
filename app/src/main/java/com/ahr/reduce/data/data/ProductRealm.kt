package com.ahr.reduce.data.data

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
    var price: Double = 0.0
    var photo: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}