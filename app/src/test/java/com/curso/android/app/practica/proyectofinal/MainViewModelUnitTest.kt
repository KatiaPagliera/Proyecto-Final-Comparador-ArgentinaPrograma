package com.curso.android.app.practica.proyectofinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.proyectofinal.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
    @Test
    fun chequearQueElViewModelSeIniciaEnVacio() = runTest{
        val value = viewModel.comparador.value?.valor.toString()
        assertEquals("", value)

    }


    @Test
    fun chequearLaComparacionDeStringsIguales() = runTest {
        val value = viewModel.comparador.value?.evaluar("a", "a")
        assertEquals("iguales", viewModel.comparador.value?.valor)
    }

    @Test
    fun chequearLaComparacionDeStringsDistintos() = runTest {
        val value = viewModel.comparador.value?.evaluar("a", "b")
        assertEquals("distintos", viewModel.comparador.value?.valor)

    }

    @Test
    fun mainViewModel_TestVerificarComparar() = runTest{
        launch {
            viewModel.comparar("a", "a")
        }
        advanceUntilIdle()

        val value = viewModel.comparador.value?.valor
        assertEquals("iguales", value)

    }


}