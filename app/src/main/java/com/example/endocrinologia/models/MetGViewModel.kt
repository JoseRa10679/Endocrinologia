package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class MetGViewModel: ViewModel() {
    private val calculos = Calculos()

    private val _resultado: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultado() = _resultado as LiveData<String>
    fun setresultado(g: String){
        val r = calculos.aC1(g.toInt())
        _resultado?.value = ccAC1 + r.format(2) + "%"
    }

    private val _resultadoQUICKI: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultadoQUICKI() = _resultadoQUICKI as LiveData<String>
    fun setresultadoQUICKI(i: String, g: String){
        val r = calculos.quicki(i.toDouble(), g.toDouble())
        _resultadoQUICKI?.value = ccQuicki + r.format(2)
    }

    private val _resultadoHOMA: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultadoHOMA() = _resultadoHOMA as LiveData<String>
    fun setresultadoHOMA(i: String, g: String){
        val m = calculos.homa(i.toDouble(), g.toDouble())
        _resultadoHOMA?.value = ccHoma + m.format(2)
    }




    companion object{
        const val ccAC1 = "AC1: "
        const val ccQuicki = "QUICKI: "
        const val ccHoma = "HOMA: "

        private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    }

}