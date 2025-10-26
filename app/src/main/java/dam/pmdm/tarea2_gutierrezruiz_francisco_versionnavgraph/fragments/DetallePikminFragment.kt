package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.R
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentAjustesBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentDetallePikminBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.CreadorPikmins

/**
 * Fragment que muestra los detalles completos de un [Pikmin] seleccionado.
 *
 * Recupera el ID del Pikmin desde los argumentos y obtiene sus datos
 * desde [CreadorPikmins.listadoPikmins]. Luego muestra:
 * - Nombre, familia y nombre científico.
 * - Descripción y hasta tres características.
 * - Imagen representativa.
 * - CheckBoxes indicando si es terrestre, acuático o aéreo.
 *
 * También oculta automáticamente las características vacías.
 */
class DetallePikminFragment : Fragment() {

    /**
     * Variable de binding que proporciona acceso a las vistas del layout [FragmentDetallePikminBinding].
     *
     * Se inicializa en [onCreateView] y se libera en [onDestroyView] para evitar fugas de memoria.
     */
    private var _binding: FragmentDetallePikminBinding? = null
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento

    /**
     * Infla el layout del fragmento y retorna la vista raíz.
     *
     * @param inflater LayoutInflater para inflar el layout.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado previo del fragmento, si lo hubiera.
     * @return La vista raíz del fragmento inflada.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetallePikminBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configura la vista una vez creada.
     *
     * Recupera el Pikmin seleccionado por su ID y asigna sus datos a las vistas.
     * Muestra un Toast indicando el Pikmin seleccionado y oculta características vacías.
     *
     * @param view Vista creada por el fragmento.
     * @param savedInstanceState Estado previo del fragmento, si lo hubiera.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Llamada al método de la superclase para establecer la vista
        super.onViewCreated(view, savedInstanceState)

        // Recupera los argumentos pasados
        val pikminId = arguments?.getString("pikminId")

        // Busca el Pikmin correspondiente en la lista de Pikmins
        val pikminSeleccionado = CreadorPikmins.listadoPikmins.find {
            it.id == pikminId
        }

        // Muestra un mensaje de error si no se encuentra el Pikmin seleccionado o si es nulo
        if (pikminSeleccionado == null) {
            Toast.makeText(
                requireContext(),
                "Error: Pikmin no válido o no encontrado",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // Si se encuentra el Pikmin, se convierte los IDs de recursos
        val nombre = getString(pikminSeleccionado.nombre)
        val imagen = pikminSeleccionado.imagen
        val familia = getString(pikminSeleccionado.familia)
        val nombreCientifico = getString(pikminSeleccionado.nombreCientifico)
        val esTerrestre = pikminSeleccionado.esTerrestre
        val esAcuatico = pikminSeleccionado.esAcuatico
        val esAereo = pikminSeleccionado.esAereo
        val descripcion = getString(pikminSeleccionado.descripcion)
        val caracteristica1 = getString(pikminSeleccionado.caracteristica1)
        val caracteristica2 = getString(pikminSeleccionado.caracteristica2)
        val caracteristica3 = getString(pikminSeleccionado.caracteristica3)

        // Muestra un mensaje breve con el nombre del Pikmin seleccionado
        val mensaje = getString(R.string.se_ha_seleccionado_un_pikmin, nombre)
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()

        // Oculta las vistas de las características si están vacías
        setVisibleIfNotEmpty(
            caracteristica1,
            binding.labelCaracteristica1,
            binding.caracteristica1Detalle
        )
        setVisibleIfNotEmpty(
            caracteristica2,
            binding.labelCaracteristica2,
            binding.caracteristica2Detalle
        )
        setVisibleIfNotEmpty(
            caracteristica3,
            binding.labelCaracteristica3,
            binding.caracteristica3Detalle
        )

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
     * Oculta o muestra un conjunto de vistas según si el texto proporcionado está vacío o no.
     *
     * @param text Texto que se evalúa. Si es nulo o vacío, se ocultan las vistas.
     * @param views Vistas a ocultar o mostrar.
     */
    private fun setVisibleIfNotEmpty(text: String?, vararg views: View) {
        val visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
        views.forEach { it.visibility = visibility }
    }

    /**
     * Libera el binding al destruir la vista para evitar fugas de memoria.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
