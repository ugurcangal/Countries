package com.ugurcangal.kotlincountries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countryTable")
data class CountryModel(

    //SerializedName'i apiden gelen isimlerden farklı bir değişken oluşturuyorsak kullanıyoruz.
    //SerializedName'de API'den gelen ismin birebir aynısını yazmak durumundayız.

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String?,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String?,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?,
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl: String?

) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}