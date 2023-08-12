package com.curso.android.app.practica.proyectofinal.view
import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import com.airbnb.lottie.LottieAnimationView
import com.curso.android.app.practica.proyectofinal.R
import com.curso.android.app.practica.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var primeraEjecucion: Boolean = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.comparador.observe(this) {
            if (!primeraEjecucion){
                binding.textoResultado.text = "Los textos son ${it.valor}!!!"
                binding.textoResultado.visibility = View.VISIBLE
                binding.textInput1.text?.clear()
                binding.textInput2.text?.clear()
                mostrarAnimaciones(binding.animacion, it.valor)
            }else{
                primeraEjecucion = !primeraEjecucion

            }

        }



        binding.botoncomparador.setOnClickListener {
            //LISTENER DEL BOTON
            mainViewModel.comparar(binding.textInput1.text.toString(), binding.textInput2.text.toString())

            //Se usa para cerrar el teclado asi se ve la animacion correctamente
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun mostrarAnimaciones(imageView: LottieAnimationView, valor: String) {
        //METODO QUE SE ENCARGA DE MOSTRAR Y OCULTAR LA ANIMACION
        var animation: Int
        val colorDeFondo: Color
        if (valor.contains("iguales")) {
            animation = R.raw.bien
            //colorDeFondo = Color.rgb(160, 196, 157).toColor()
            colorDeFondo = Color.rgb(171, 194, 112).toColor()
        } else {
            animation = R.raw.mal
            colorDeFondo = Color.rgb(253, 138, 138).toColor()
        }

        binding.animacion.visibility = View.VISIBLE
        imageView.setAnimation(animation)
        imageView.playAnimation()

        binding.layoutPrincipal.setBackgroundColor(colorDeFondo.toArgb())


        //AGREGO EL LISTENER PARA QUE UNA VEZ TERMINE LA ANIMACION VUELVA A OCULTARSE:
        imageView.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                binding.animacion.visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }

        })





    }
}
