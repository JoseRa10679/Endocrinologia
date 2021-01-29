package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class CalcioViewModel: ViewModel() {
    private val calculos = Calculos()

    private val _rCalcio: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getrCalcio() = _rCalcio as LiveData<String>
    fun setrCalcio(a: String, c: String){
        val c1 = calculos.calcio(a.toDouble(), c.toDouble())
       _rCalcio?.value = c1.format(2) + ccVal
    }



    companion object{
        const val ccVal = " mg/dl\n\nValores normales entre\n8.5 y 10.5 mg/dl"
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }

}