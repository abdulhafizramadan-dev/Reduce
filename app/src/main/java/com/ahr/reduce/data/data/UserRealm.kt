package com.ahr.reduce.data.data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserRealm : RealmObject {
    @PrimaryKey
    @PersistedName("_id")
    var id: ObjectId = ObjectId.invoke()
    @PersistedName("owner_id")
    var ownerId: String = ""
    @PersistedName("first_name")
    var firstName: String = ""
    @PersistedName("last_name")
    var lastName: String = ""
    var email: String = ""
    var telephone: String = ""
    @PersistedName("birth_date")
    var birthDate: String = ""
    var gender: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}