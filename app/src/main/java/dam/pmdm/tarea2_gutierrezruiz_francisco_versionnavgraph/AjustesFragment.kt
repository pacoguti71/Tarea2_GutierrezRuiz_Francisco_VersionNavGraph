package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    private val binding get() = _binding!!

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
     * 1. Habilitar el modo de borde a borde (`enableEdgeToEdge`).
     * 2. Configurar la barra de herramientas (Toolbar) como ActionBar y habilitar el botón de retroceso.
     * 3. Configurar los insets de ventana para la vista principal.
     * 4. Inicializar [preferencesHelper].
     * 5. Llamar a [configurarModoOscuro] y [configurarIdioma].
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Habilita el modo de borde a borde si la actividad es AppCompatActivity
        requireActivity().enableEdgeToEdge()

        // Configura los insets de ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
        val esModoOscuroGuardado = preferencesHelper.esModoOscuro()
        binding.switchTema.isChecked = esModoOscuroGuardado
        binding.switchTema.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
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
        val idiomaActual = Locale.getDefault().language
        binding.switchIdioma.isChecked = idiomaActual == "es"
        binding.switchIdioma.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setIdioma("es")
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
        val locale = Locale.Builder().setLanguage(idioma).build()
        val listaIdiomas = LocaleListCompat.create(locale)
        AppCompatDelegate.setApplicationLocales(listaIdiomas)
        preferencesHelper.saveIdioma(idioma)
        requireActivity().recreate()
    }

    /** Limpia el binding al destruir la vista para evitar fugas de memoria. */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

