package com.ahr.reduce.data.data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserRealm : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var token_id: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var email: String = ""
    var telephone: String = ""
    var birth_date: String = ""
    var gender: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}