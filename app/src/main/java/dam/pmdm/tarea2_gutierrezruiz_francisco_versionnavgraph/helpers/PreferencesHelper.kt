package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.helpers

import android.content.Context
import android.content.SharedPreferences

// --- CONSTANTES PRIVADAS A NIVEL DE FICHERO ---
// Se definen fuera de la clase y son privadas, por lo que solo son visibles dentro de este fichero.
/** Nombre del archivo de preferencias donde se almacenarán las configuraciones de la aplicación. */
private const val PREFERENCES_FILE_NAME = "app_settings"
/** Clave para almacenar y recuperar el estado del modo oscuro (booleano). */
private const val KEY_DARK_MODE = "dark_mode"
/** Clave para almacenar y recuperar el código de idioma (String). */
private const val KEY_LANGUAGE = "language"

/**
 * Clase auxiliar para gestionar el almacenamiento y recuperación de preferencias
 * de usuario de la aplicación utilizando [SharedPreferences].
 *
 * Se utiliza para persistir la configuración del modo oscuro y el idioma seleccionado.
 *
 * @property context El contexto de la aplicación o actividad, necesario para acceder a [SharedPreferences].
 */
class PreferencesHelper(context: Context) {
    /**
     * Instancia de [SharedPreferences] para acceder al archivo de preferencias.
     * [Context.MODE_PRIVATE] asegura que solo esta aplicación pueda acceder a este fichero.
     */
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    /**
     * Lee y devuelve el estado actual del modo oscuro guardado.
     *
     * @return `true` si el modo oscuro está activo, `false` en caso contrario o si no hay valor guardado.
     */
    fun esModoOscuro(): Boolean {
        // Leemos el valor booleano. Si no se encuentra la clave, devuelve 'false' como valor por defecto.
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false)
    }

    /**
     * Lee y devuelve el código de idioma guardado.
     *
     * Nota: Aunque el valor guardado se establece explícitamente al cambiar el idioma,
     * esta función proporciona una forma de leerlo. No la uso en todo el código, pero la dejo aquí por si la necesitas en el futuro.
     *
     * @return El código de idioma guardado (ej. "es", "en"), o "es" si no se encuentra un valor.
     */
    fun getIdioma(): String {
        // Lee el valor String. Si no se encuentra, devuelve "es" por defecto. El operador elvis (?:) asegura que si se devuelve un nulo, lo convertimos a "es".
        return sharedPreferences.getString(KEY_LANGUAGE, "es") ?: "es"
    }

    /**
     * Guarda el estado del modo oscuro en las preferencias.
     *
     * @param esModoOscuro `true` para activar el modo oscuro, `false` para desactivarlo.
     */
    fun saveModoOscuro(esModoOscuro: Boolean) {
        // Obtiene un editor para poder modificar las preferencias y aplica los cambios inmediatamente.
        sharedPreferences.edit().apply {
            // Guarda el valor booleano con su clave correspondiente.
            putBoolean(KEY_DARK_MODE, esModoOscuro)
            // Guarda los cambios de forma asíncrona
            apply()
        }
    }

    /**
     * Guarda el código de idioma seleccionado en las preferencias.
     *
     * @param idioma El código de idioma a guardar (ej. "es", "en").
     */
    fun saveIdioma(idioma: String) {
        // Obtiene un editor para poder modificar las preferencias y aplica los cambios inmediatamente.
        sharedPreferences.edit().apply {
            // Guarda el valor String con su clave correspondiente.
            putString(KEY_LANGUAGE, idioma)
            // Guarda los cambios de forma asíncrona
            apply()
        }
    }

}