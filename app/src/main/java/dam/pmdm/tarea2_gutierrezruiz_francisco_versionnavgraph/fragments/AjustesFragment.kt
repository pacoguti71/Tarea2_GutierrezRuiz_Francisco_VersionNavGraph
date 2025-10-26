package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.R
import java.util.Locale

/**
 * Fragmento de ajustes de la aplicación.
 *
 * Gestiona las preferencias de usuario mediante un archivo XML de preferencias
 * (`R.xml.ajustes`) y permite cambiar dinámicamente:
 * 1. El tema de la aplicación (modo claro / oscuro).
 * 2. El idioma de la interfaz (español / inglés).
 *
 * Utiliza `SwitchPreferenceCompat` para cada opción y aplica los cambios
 * inmediatamente mediante `AppCompatDelegate`. Los valores se guardan automáticamente
 * en `SharedPreferences`.
 */
class AjustesFragment : PreferenceFragmentCompat() {

    /**
     * Método llamado al crear las preferencias del fragmento.
     *
     * - Establece el recurso XML de preferencias.
     * - Configura listeners para los switches de tema e idioma.
     *
     * @param savedInstanceState Estado previo del fragmento, si existe.
     * @param rootKey Clave raíz de las preferencias, si se usan jerarquías.
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Establece el recurso de preferencias
        setPreferencesFromResource(R.xml.ajustes, rootKey)

        // Configura el cambio de tema
        findPreference<SwitchPreferenceCompat>("tema")?.setOnPreferenceChangeListener { preference, newValue ->
            // Guarda el valor de la preferencia
            val modoOscuro = newValue as Boolean
            // Cambia el tema
            AppCompatDelegate.setDefaultNightMode(
                if (modoOscuro) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            // Recrea la actividad
            requireActivity().recreate()
            // Devuelve true para que se guarde la preferencia
            true
        }

        // Configura el cambio de idioma
        findPreference<SwitchPreferenceCompat>("idioma")?.setOnPreferenceChangeListener { preference, newValue ->
            // Guarda el valor de la preferencia
            val espanol = newValue as Boolean
            // Cambia el idioma
            val codigo = if (espanol) "es" else "en"
            // Establece la localización
            val locale = Locale.Builder().setLanguage(codigo).build()
            // Establece la localización
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale))
            // Recrea la actividad
            requireActivity().recreate()
            // Devuelve true para que se guarde la preferencia
            true
        }
    }
}

