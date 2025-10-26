package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.adapter.PikminAdapter
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.R
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentListadoPikminsBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.CreadorPikmins
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.Pikmin

/**
 * Fragment que muestra un listado de todos los [Pikmin] en un [RecyclerView].
 *
 * Cada Pikmin se muestra en una cuadrícula de 3 columnas mediante un [GridLayoutManager].
 * Al pulsar sobre un Pikmin, se navega al [DetallePikminFragment] mostrando sus detalles.
 */
class ListadoPikminsFragment : Fragment() {

    /**
     * Variable de binding que proporciona acceso a las vistas del layout [FragmentListadoPikminsBinding].
     *
     * Se inicializa en [onCreateView] y se libera en [onDestroyView] para evitar fugas de memoria.
     */
    private var _binding: FragmentListadoPikminsBinding? =
        null
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento

    /** Adapter que gestiona la visualización de los Pikmins en el RecyclerView */
    private lateinit var adapter: PikminAdapter // Adapter para el RecyclerView

    /** Lista de Pikmins a mostrar, obtenida desde el objeto singleton [CreadorPikmins] */
    private val elementos: List<Pikmin> =
        CreadorPikmins.listadoPikmins


    /**
     * Infla el layout del fragmento y retorna la vista raíz.
     *
     * @param inflater LayoutInflater para inflar el layout.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado previo del fragmento, si lo hubiera.
     * @return La vista raíz del fragmento inflada.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListadoPikminsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configura la vista una vez creada.
     *
     * Inicializa el [RecyclerView] con un [GridLayoutManager] de 3 columnas y
     * asigna un [PikminAdapter] para mostrar los elementos.
     *
     * También define la acción a realizar al pulsar un Pikmin: navegar
     * al [DetallePikminFragment] pasando el ID del Pikmin seleccionado.
     *
     * @param view Vista creada por el fragmento.
     * @param savedInstanceState Estado previo del fragmento, si lo hubiera.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Llama al método onViewCreated de la superclase
        super.onViewCreated(view, savedInstanceState)
        // Crea un lambda para manejar la acción al pulsar un Pikmin. Guarda el Pikmin (el id) en una variable Bundle.
        val onPikminClickedAction = { pikmin: Pikmin ->
            val bundle = Bundle().apply {
                putString("pikminId", pikmin.id)
            }
            // Navega al detalle del Pikmin, pasando el id como argumento.
            findNavController().navigate(
                R.id.action_listadoPikminsFragment_to_detallePikminFragment,
                bundle
            )
        }
        // Crea el adapter con la lista de elementos y la acción definida anteriormente.
        adapter = PikminAdapter(elementos, onPikminClickedAction)
        // Configura el RecyclerView con el adapter y un GridLayoutManager de 3 columnas.
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        // Asigna el adapter al RecyclerView.
        binding.recyclerView.adapter = adapter
    }

    /**
     * Libera el binding al destruir la vista para evitar fugas de memoria.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}