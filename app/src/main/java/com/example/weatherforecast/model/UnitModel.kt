package com.example.weatherforecast.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "unit")
data class UnitModel(
    @PrimaryKey
    var uuid: UUID = UUID.randomUUID(),
    var unit: String
)
