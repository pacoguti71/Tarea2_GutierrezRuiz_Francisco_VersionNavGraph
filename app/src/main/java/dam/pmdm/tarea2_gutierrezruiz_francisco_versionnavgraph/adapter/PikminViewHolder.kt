package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.adapter

import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.ItemCardviewLayoutBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos.Pikmin

/**
 * ViewHolder que utiliza Data Binding para enlazar un objeto [Pikmin] a la vista.
 *
 * @param binding Instancia de [ItemCardviewLayoutBinding] generada por Data Binding.
 */
class PikminViewHolder(private val binding: ItemCardviewLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) { // El binding se pasa al constructor
    /**
     * Enlaza los datos de un objeto [Pikmin] a las vistas del ViewHolder.
     *
     * @param pikmin El objeto [Pikmin] con los datos a mostrar.
     */
    fun bind(pikmin: Pikmin) {
        // Asignamos los valores a las vistas
        binding.pikmin = pikmin
        // context es una referencia al contexto de la aplicación
        val context = binding.root.context
        // Asignamos el texto a la vista
        binding.nombre.text = context.getString(pikmin.nombre)
        // Asignamos la imagen a la vista
        binding.imagen.setImageResource(pikmin.imagen)
        // Ejecutamos el binding para actualizar las vistas
        binding.executePendingBindings()
    }
}