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
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentDetallePikminBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.CreadorPikmins

class DetallePikminFragment : Fragment() {

    private var _binding: FragmentDetallePikminBinding? =
        null // _binding sirve para encapsular el acceso a la vista del fragmento
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetallePikminBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    private fun setVisibleIfNotEmpty(text: String?, vararg views: View) {
        val visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
        views.forEach { it.visibility = visibility }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
