package com.curso.android.app.practica.proyectofinal.view

import androidx.databinding.DataBindingUtil.setContentView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import com.curso.android.app.practica.proyectofinal.R
import com.curso.android.app.practica.proyectofinal.databinding.ActivityMainBinding
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
    }

    @Test
    fun mainActivity_probarComparar(){

        Espresso.onView(
            ViewMatchers.withId(R.id.botoncomparador)

        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.textoResultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Los textos son iguales!!!")
            )
        )
    }
}