package com.ahr.reduce.data.data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User : RealmObject {
    @PrimaryKey
    val id: ObjectId = ObjectId.invoke()
    val firstName: String = ""
    val lastName: String = ""
    val email: String = ""
    val telephone: String = ""
    val birthDate: String = ""
    val gender: String = ""
    val timestamp: RealmInstant = RealmInstant.now()
}