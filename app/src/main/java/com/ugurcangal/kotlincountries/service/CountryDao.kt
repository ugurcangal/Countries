package com.ugurcangal.kotlincountries.service

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ugurcangal.kotlincountries.model.CountryModel

@Dao
interface CountryDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: CountryModel) : List<Long> // Long -> Primary idleri ifade eder.

    //suspend -> coroutine -- fonksiyonları durdurup devam ettirmeye yarar
    //vararg -> multiple country objects

    @Query("SELECT * FROM countryTable")
    suspend fun getAllCountries() : List<CountryModel>

    @Query("SELECT * FROM countryTable WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : CountryModel

    @Query("DELETE FROM countryTable")
    suspend fun deleteAllCountries()
}