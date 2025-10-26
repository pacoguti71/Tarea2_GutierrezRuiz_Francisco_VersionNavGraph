package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.ItemCardviewLayoutBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.Pikmin

/**
 * Adapter para mostrar una lista de [Pikmin] en un [RecyclerView].
 *
 * @property elementos Lista de objetos [Pikmin] que se mostrarán.
 * @property onPikminClicked Lambda que se ejecuta al hacer clic en un elemento de la lista.
 */
class PikminAdapter(
    // Lista de objetos Pikmin que se mostrarán en el RecyclerView
    private val elementos: List<Pikmin>,
    // Lambda que se ejecutará cuando se haga clic en un elemento de la lista
    private val onPikminClicked: (Pikmin) -> Unit,
) : RecyclerView.Adapter<PikminViewHolder>() {

    /**
     * Crea un nuevo [PikminViewHolder] inflando el layout correspondiente.
     *
     * @param parent Vista padre que contiene el ViewHolder.
     * @param viewType Tipo de vista (no usado en este adapter).
     * @return Un [PikminViewHolder] listo para mostrar un elemento.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PikminViewHolder {
        // Obtiene el LayoutInflater desde el contexto del padre.
        val layoutInflater = LayoutInflater.from(parent.context)
        // Infla el layout usando el método estático de la clase de Binding. Esto devuelve un objeto ItemCardviewLayoutBinding, no una View.
        val binding = ItemCardviewLayoutBinding.inflate(layoutInflater, parent, false)
        // Pasa el objeto 'binding' directamente al constructor del ViewHolder.
        return PikminViewHolder(binding)
    }

    /**
     * Asocia los datos del [Pikmin] a la vista del [PikminViewHolder].
     *
     * @param holder ViewHolder que se va a actualizar.
     * @param position Posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: PikminViewHolder, position: Int) {
        // Obtiene el objeto Pikmin en la posición actual
        val pikmin = elementos[position]
        // Se llama a la función bind del ViewHolder para establecer los datos del pikmin
        holder.bind(pikmin)
        // Establece el OnClickListener en la vista del elemento.
        holder.itemView.setOnClickListener {
            onPikminClicked(pikmin)
        }
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Cantidad de elementos en [elementos].
     */
    override fun getItemCount(): Int = elementos.size
}