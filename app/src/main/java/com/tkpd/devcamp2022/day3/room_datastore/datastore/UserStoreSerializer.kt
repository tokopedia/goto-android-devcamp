package com.tkpd.devcamp2022.day3.room_datastore.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.tkpd.devcamp2022.UserStore
import java.io.InputStream
import java.io.OutputStream

//TODO(3,1) - Create Schema for Proto DataStore
//TODO(3,2) - Create Serializer for Proto DataStore
//object UserStoreSerializer: Serializer<UserStore> {
//
//    override val defaultValue: UserStore = UserStore.getDefaultInstance()
//    override suspend fun readFrom(input: InputStream): UserStore {
//        try {
//            return UserStore.parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw  CorruptionException("Cannot read proto", exception)
//        }
//    }
//
//    override suspend fun writeTo(t: UserStore, output: OutputStream) = t.writeTo(output)
//}