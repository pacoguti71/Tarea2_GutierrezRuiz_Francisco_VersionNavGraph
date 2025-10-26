package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.helpers.PreferencesHelper
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentAjustesBinding
import java.util.Locale

/**
 * Fragmento encargado de gestionar los ajustes de la aplicación,
 * incluyendo el modo oscuro y el idioma de la interfaz.
 */
class AjustesFragment : Fragment() {

    /**
     * Variable de binding que proporciona acceso a las vistas del layout [FragmentAjustesBinding].
     *
     * Se inicializa en [onCreateView] y se libera en [onDestroyView] para evitar fugas de memoria.
     */
    private var _binding: FragmentAjustesBinding? = null
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento

    /**
     * Instancia de [PreferencesHelper] para gestionar el almacenamiento y recuperación
     * de las preferencias del usuario (modo oscuro e idioma). Se inicializa en [onViewCreated].
     */
    private lateinit var preferencesHelper: PreferencesHelper

    /**
     * Método llamado al crear la vista del fragment.
     *
     * Infla el layout mediante View Binding y devuelve la vista raíz.
     *
     * @return La vista raíz inflada.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Infla el layout utilizando View Binding
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        // Devuelve la vista raíz
        return binding.root
    }

    /**
     * Método llamado cuando la vista del fragment ha sido creada.
     *
     * Se encarga de:
     * 1. Inicializar [preferencesHelper].
     * 2. Llamar a [configurarModoOscuro] y [configurarIdioma].
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Llama al método onViewCreated de la superclase
        super.onViewCreated(view, savedInstanceState)

        // Inicializa la variable preferencesHelper
        preferencesHelper = PreferencesHelper(requireContext())

        // Configura el modo oscuro
        configurarModoOscuro()
        // Configura el idioma
        configurarIdioma()
    }

    /**
     * Configura el comportamiento del interruptor de modo oscuro.
     *
     * - Lee el valor almacenado en las preferencias.
     * - Actualiza el estado del interruptor.
     * - Cambia el modo de la aplicación según la selección del usuario.
     */
    private fun configurarModoOscuro() {
        // Lee el valor almacenado en las preferencias
        val esModoOscuroGuardado = preferencesHelper.esModoOscuro()
        // Actualiza el estado del interruptor
        binding.switchTema.isChecked = esModoOscuroGuardado
        // Cambia el modo de la aplicación según la selección del usuario
        binding.switchTema.setOnCheckedChangeListener { _, isChecked ->
            // Si está marcado, establece el modo oscuro
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                // Si no está marcado, establece el modo claro
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Guarda el valor en las preferencias
            preferencesHelper.saveModoOscuro(isChecked)
        }
    }

    /**
     * Configura el comportamiento del interruptor de idioma.
     *
     * - Detecta el idioma actual del sistema.
     * - Actualiza el estado del interruptor.
     * - Cambia el idioma entre español e inglés según la selección del usuario.
     */
    private fun configurarIdioma() {
        // Detecta el idioma actual del sistema
        val idiomaActual = Locale.getDefault().language
        // Actualiza el estado del interruptor según el idioma actual
        binding.switchIdioma.isChecked = idiomaActual == "es"
        // Cambia el idioma según la selección del usuario
        binding.switchIdioma.setOnCheckedChangeListener { _, isChecked ->
            // Si está marcado, establece el idioma español
            if (isChecked) {
                setIdioma("es")
                // Si no está marcado, establece el idioma inglés
            } else {
                setIdioma("en")
            }
        }
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param idioma Código ISO del idioma (por ejemplo, "es" o "en").
     *
     * Actualiza las configuraciones regionales mediante [AppCompatDelegate.setApplicationLocales],
     * guarda la preferencia en [PreferencesHelper] y recrea la actividad para aplicar los cambios.
     */
    private fun setIdioma(idioma: String) {
        // La variable locale almacena el idioma seleccionado
        val locale = Locale.Builder().setLanguage(idioma).build()
        // La variable listaIdiomas almacena la lista de idiomas a aplicar
        val listaIdiomas = LocaleListCompat.create(locale)
        // Aplica el idioma
        AppCompatDelegate.setApplicationLocales(listaIdiomas)
        // Guarda la preferencia
        preferencesHelper.saveIdioma(idioma)
        // Recrea la actividad para aplicar los cambios
        requireActivity().recreate()
    }

    /** Limpia el binding al destruir la vista para evitar fugas de memoria. */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}