package com.example.weatherforecast.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity("favorite")
data class FavoritesModel(
    @PrimaryKey
    var uid: UUID = UUID.randomUUID(),
    val city: String,
    val country: String

)
