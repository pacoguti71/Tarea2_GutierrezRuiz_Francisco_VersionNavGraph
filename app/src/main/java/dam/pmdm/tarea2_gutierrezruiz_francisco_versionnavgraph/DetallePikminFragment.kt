package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentDetallePikminBinding

/**
 * Fragmento que muestra la información detallada de un objeto [Pikmin].
 *
 * Este fragmento se encarga de:
 * 1. Recibir los datos de un Pikmin desde un [Bundle] mediante [newInstance].
 * 2. Inflar su vista asociada mediante [FragmentDetallePikminBinding].
 * 3. Mostrar la información textual y gráfica del Pikmin seleccionado.
 * 4. Ocultar dinámicamente los campos vacíos para mantener una interfaz limpia.
 */
class DetallePikminFragment : Fragment() {

    /**
     * Variable de binding que proporciona acceso a las vistas del layout [FragmentDetallePikminBinding].
     *
     * Se inicializa en [onCreateView] y se libera en [onDestroyView] para evitar fugas de memoria.
     */
    private var _binding: FragmentDetallePikminBinding? = null // _binding sirve para encapsular el acceso a la vista del fragmento
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento

    /**
     * Infla el layout del fragmento y devuelve la vista raíz.
     *
     * @param inflater Objeto utilizado para inflar la vista.
     * @param container Contenedor padre donde se colocará la vista del fragmento.
     * @param savedInstanceState Estado previamente guardado, o `null` si es una nueva instancia.
     * @return La vista raíz del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetallePikminBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Llamado después de crear la vista. Inicializa el contenido del fragmento.
     *
     * Este método:
     * - Ajusta el padding según las barras del sistema.
     * - Recupera los argumentos pasados y los convierte en texto.
     * - Asigna los valores a las vistas correspondientes.
     * - Muestra un [Toast] informativo con el nombre del Pikmin.
     *
     * @param view Vista raíz del fragmento.
     * @param savedInstanceState Estado previamente guardado, o `null` si no lo hay.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Llamada al método de la superclase para establecer la vista
        super.onViewCreated(view, savedInstanceState)
        // Ajuste de márgenes con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera los argumentos pasados
        val args = requireArguments()

        // Obtiene los datos pasados al fragment
        val nombreResId = args.getInt("nombre", R.string.empty_string)
        val familiaResId = args.getInt("familia", R.string.empty_string)
        val nombreCientificoResId = args.getInt("nombreCientifico", R.string.empty_string)
        val descripcionResId = args.getInt("descripcion", R.string.empty_string)
        val caracteristica1ResId = args.getInt("caracteristica1", R.string.empty_string)
        val caracteristica2ResId = args.getInt("caracteristica2", R.string.empty_string)
        val caracteristica3ResId = args.getInt("caracteristica3", R.string.empty_string)
        val esTerrestre = args.getBoolean("esTerrestre", false)
        val esAcuatico = args.getBoolean("esAcuatico", false)
        val esAereo = args.getBoolean("esAereo", false)
        val imagen = args.getInt("imagen", 0)

        // Convierte los IDs de recursos en texto
        val nombre = getString(nombreResId)
        val familia = getString(familiaResId)
        val nombreCientifico = getString(nombreCientificoResId)
        val descripcion = getString(descripcionResId)
        val caracteristica1 = getString(caracteristica1ResId)
        val caracteristica2 = getString(caracteristica2ResId)
        val caracteristica3 = getString(caracteristica3ResId)

        // Muestra un mensaje breve con el nombre del Pikmin seleccionado
        val mensaje = getString(R.string.se_ha_seleccionado_un_pikmin, nombre)
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()

        // Oculta las vistas de las características si están vacías
        setVisibleIfNotEmpty(caracteristica1, binding.labelCaracteristica1, binding.caracteristica1Detalle)
        setVisibleIfNotEmpty(caracteristica2, binding.labelCaracteristica2, binding.caracteristica2Detalle)
        setVisibleIfNotEmpty(caracteristica3, binding.labelCaracteristica3, binding.caracteristica3Detalle)

        // Asigna los valores a las vistas
        binding.nombreDetalle.text = nombre
        binding.familiaDetalle.text = familia
        binding.nombreCientificoDetalle.text = nombreCientifico
        binding.descripcionDetalle.text = descripcion
        binding.caracteristica1Detalle.text = caracteristica1
        binding.caracteristica2Detalle.text = caracteristica2
        binding.caracteristica3Detalle.text = caracteristica3
        binding.checkBoxTerrestre.isChecked = esTerrestre
        binding.checkBoxAcuatico.isChecked = esAcuatico
        binding.checkBoxAereo.isChecked = esAereo
        binding.imagenDetalle.setImageResource(imagen)
    }

    /**
     * Oculta las vistas especificadas si el texto proporcionado está vacío o es nulo.
     *
     * @param text Texto a evaluar.
     * @param views Vistas cuya visibilidad se modificará.
     */
    private fun setVisibleIfNotEmpty(text: String?, vararg views: View) {
        val visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
        views.forEach { it.visibility = visibility }
    }

    /**
     * Libera la referencia al objeto de binding al destruir la vista,
     * evitando fugas de memoria cuando el fragmento se destruye o se reemplaza.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // companion object es un patrón de diseño que permite definir variables
    // y métodos estáticos en un objeto.
    companion object {
        /**
         * Crea una nueva instancia de [DetallePikminFragment] con los datos del Pikmin especificado.
         *
         * Los atributos del Pikmin se empaquetan en un [Bundle] y se adjuntan como argumentos.
         *
         * @param pikmin Objeto [Pikmin] cuyos datos se mostrarán en el fragmento.
         * @return Una instancia de [DetallePikminFragment] con los argumentos configurados.
         */
        fun newInstance(pikmin: Pikmin): DetallePikminFragment {
            val fragment = DetallePikminFragment()
            val args = Bundle().apply {
                putInt("nombre", pikmin.nombre)
                putInt("familia", pikmin.familia)
                putInt("nombreCientifico", pikmin.nombreCientifico)
                putBoolean("esTerrestre", pikmin.esTerrestre)
                putBoolean("esAcuatico", pikmin.esAcuatico)
                putBoolean("esAereo", pikmin.esAereo)
                putInt("descripcion", pikmin.descripcion)
                putInt("caracteristica1", pikmin.caracteristica1)
                putInt("caracteristica2", pikmin.caracteristica2)
                putInt("caracteristica3", pikmin.caracteristica3)
                putInt("imagen", pikmin.imagen)
            }
            fragment.arguments = args
            return fragment
        }
    }

}
