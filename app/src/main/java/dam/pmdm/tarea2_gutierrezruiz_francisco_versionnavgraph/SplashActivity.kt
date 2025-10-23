package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad encargada de mostrar la pantalla de bienvenida (Splash Screen)
 * al iniciar la aplicación.
 *
 * Utiliza una lógica condicional para manejar dos escenarios:
 * 1. Android 12 (API 31) o superior: Confía en el Splash Screen nativo del sistema.
 * 2. Inferior a Android 12: Muestra una pantalla de carga manual ([R.layout.activity_splash])
 * durante un tiempo de retardo definido, para simular el comportamiento.
 */
class SplashActivity : AppCompatActivity() {

    // Constante que define el tiempo de retardo para el splash manual en milisegundos (2 segundos)
    private val tiempoRetardo: Long = 2000

    /**
     * Método que se ejecuta al crear la actividad.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado previamente guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la superclase
        super.onCreate(savedInstanceState)

        // Verifica si la versión de Android es menor a Android 12 (API 31, 'S')
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Caso 1: Android antiguo (Pre-API 31) - Se gestiona el splash manualmente.
            // Establece el layout de la actividad para mostrar la imagen o animación de bienvenida
            setContentView(R.layout.activity_splash)
            // Crea una instancia del Handler en el hilo principal (MainLooper)
            Handler(Looper.getMainLooper()).postDelayed({
                // Inicia la actividad principal después del tiempo de retardo
                mostrarSplashScreen()
            }, tiempoRetardo)
        } else {
            // Caso 2: Android moderno (API 31 o superior) - El sistema gestiona el splash.
            // No establecemos layout (R.layout.activity_splash) porque el sistema
            // ya muestra automáticamente el splash screen definido en el tema de la app.
            // Inicia la actividad principal
            mostrarSplashScreen()
        }
    }

    /**
     * Inicia la actividad principal ([MainActivity]) y finaliza la actividad actual ([SplashActivity]).
     *
     * Esta función se utiliza como acción final tanto para el splash manual como para el nativo.
     */
    fun mostrarSplashScreen() {
        // Crea un Intent para iniciar la actividad principal
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        // Inicia la actividad principal
        startActivity(intent)
        // Finaliza la actividad actual
        finish()
    }
}