package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.FragmentListadoPikminsBinding

class ListadoPikminsFragment : Fragment() {

    private var _binding: FragmentListadoPikminsBinding? =
        null // _binding sirve para encapsular el acceso a la vista del fragmento
    private val binding get() = _binding!! // Hace get a _binding para acceder a la vista del fragmento
    private lateinit var adapter: PikminAdapter // Adapter para el RecyclerView

    private val elementos: List<Pikmin> =
        CreadorPikmins.listadoPikmins

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListadoPikminsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onPikminClickedAction = { pikmin: Pikmin ->
            val bundle = Bundle().apply {
                putString("pikminId", pikmin.id.toString())
            }

            findNavController().navigate(
                R.id.action_listadoPikminsFragment_to_detallePikminFragment,
                bundle
            )
        }

        adapter = PikminAdapter(elementos, onPikminClickedAction)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}