package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Actividad principal de la aplicación, muestra una lista de objetos [Pikmin] en un [RecyclerView].
 *
 * Esta actividad es responsable de:
 * 1. Inicializar la interfaz de usuario.
 * 2. Aplicar el tema (modo oscuro o claro) según las preferencias guardadas en [PreferencesHelper].
 * 3. Mostrar la lista de Pikmin utilizando [PikminAdapter].
 * 4. Gestionar la navegación a fragmentos secundarios, como [DetallePikminFragment] y [AjustesFragment].
 * 5. Controlar el menú de opciones (Acerca de y Ajustes).
 */
class MainActivity : AppCompatActivity() {
    /**
     * Binding generado automáticamente para acceder a las vistas definidas en el layout [ActivityMainBinding].
     * Permite interactuar con los elementos visuales de la actividad sin necesidad de usar [findViewById].
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Adaptador que gestiona la visualización de los objetos [Pikmin] en el [RecyclerView].
     * Se inicializa con una lista de Pikmins y un listener que maneja el clic en cada elemento.
     */
    private lateinit var adapter: PikminAdapter

    /**
     * Lista inmutable de objetos [Pikmin] que se muestran en el [RecyclerView].
     * Se inicializa al crear la actividad con los datos generados por [crearListaPikmin].
     */
    private val elementos: List<Pikmin> =
        crearListaPikmin()

    /**
     * Método de ciclo de vida llamado al crear la actividad.
     *
     * Configura el tema según las preferencias guardadas, inicializa la interfaz de usuario,
     * prepara el adaptador del RecyclerView y establece los listeners del menú.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad o `null` si es la primera vez que se crea.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState)
        // Habilita el modo de aristas redondeadas para la barra de estado
        enableEdgeToEdge()
        // Infla el diseño de la actividad utilizando el binding. En la variable binding se almacena el diseño inflado con todos sus elementos
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establece el diseño de la actividad. root es la vista raíz del diseño
        setContentView(binding.root)

        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración inicial de la vista principal
        restaurarVistaPrincipal()

        // Crea un objeto PreferencesHelper para gestionar las preferencias del usuario
        val preferencesHelper = PreferencesHelper(this)
        // Recupera el estado de la preferencia de modo oscuro
        val esModoOscuro = preferencesHelper.esModoOscuro()
        // Comprueba si el modo oscuro está activado y lo aplica. Esto es necesario para que el tema sea correcto al inicio.
        if (esModoOscuro) {
            // Si estaba guardado como oscuro, aplica el modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Si no, aplica el modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Asigna un layout al reciclerView para gestionar el diseño en cuadrícula de 3 columnas
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Crea un objeto adaptador con los datos de la lista de objetos Pikmin y lo asigna al RecyclerView.
        // Define un lambda como manejador del clic, que recibe el objeto Pikmin seleccionado.
        adapter = PikminAdapter(elementos) { pikminSeleccionado ->
            // Crea un fragmento con el detalle del Pikmin seleccionado
            val fragment = DetallePikminFragment.newInstance(pikminSeleccionado)
            // Muestra el fragmento en el contenedor
            mostrarFragment(fragment)
        }

        // Asigna el adaptador al RecyclerView
        binding.recyclerView.adapter = adapter

        // Listener para la pila de fragmentos (Back Stack)
        supportFragmentManager.addOnBackStackChangedListener {
            // Si la pila ha cambiado, comprueba si hay fragmentos en pantalla
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Hay un fragmento en pantalla: actualizamos la UI para el estado de fragmento
                actualizarToolbarYVisibilidad()
            } else {
                // No hay fragmentos: restauramos la vista principal (lista)
                restaurarVistaPrincipal()
            }
        }

        // Comprobación por si la actividad se recrea (ej: cambio de tema)
        if (savedInstanceState != null && supportFragmentManager.backStackEntryCount > 0) {
            // Si la actividad se recrea y ya había un fragmento, nos aseguramos de que la UI esté correcta.
            // El listener de arriba se encargará, pero una llamada explícita aquí es más segura.
            actualizarToolbarYVisibilidad()
        } else {
            // Mostrar Snackbar solo en el primer inicio, no en recreaciones
            Snackbar.make(
                binding.main,
                getString(R.string.bienvenido_al_mundo_pikmin),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Restaura la vista principal de la actividad tras cerrar un fragmento.
     *
     * Esta función:
     * - Oculta el contenedor de fragmentos y muestra el [RecyclerView].
     * - Restaura el título de la toolbar con el nombre de la aplicación.
     * - Elimina el icono de navegación.
     * - Reinicia el menú principal de opciones.
     */
    private fun restaurarVistaPrincipal() {
        // Oculta el contenedor de fragmentos
        binding.fragmentContainer.visibility = View.GONE
        // Muestra el RecyclerView
        binding.recyclerView.visibility = View.VISIBLE
        // Oculta la flecha de navegación hacia atrás
        binding.toolbar.navigationIcon = null
        // Configura el título de la Toolbar con el nombre de la app
        binding.toolbarTitle.text = getString(R.string.app_name)
        // Limpia el menú
        binding.toolbar.menu.clear()
        // Infla el menú principal
        binding.toolbar.inflateMenu(R.menu.menu)
        // Configura el listener del menú principal
        configurarMenuPrincipal()
    }

    /**
     * Muestra un fragmento en el contenedor principal de la actividad.
     *
     * Reemplaza cualquier fragmento existente en [R.id.fragmentContainer] por el
     * fragmento especificado y añade la transacción a la pila de retroceso, lo que
     * permite que el usuario vuelva al fragmento anterior al pulsar "Atrás".
     *
     * @param fragment El [Fragment] que se desea mostrar en la interfaz.
     */
    private fun mostrarFragment(fragment: Fragment) {
        // Muestra el fragmento en el contenedor
        supportFragmentManager.beginTransaction()
            // Reemplaza el fragmento existente
            .replace(R.id.fragmentContainer, fragment)
            // Añade la transacción a la pila de retroceso
            .addToBackStack(null)
            // Confirma la transacción
            .commit()
    }

    /**
     * Actualiza la UI para mostrar el estado de un fragmento.
     * Muestra el contenedor, la flecha de "atrás" y el título correcto.
     */
    private fun actualizarToolbarYVisibilidad() {
        // Oculta el RecyclerView
        binding.recyclerView.visibility = View.GONE
        // Muestra el contenedor de fragmentos
        binding.fragmentContainer.visibility = View.VISIBLE
        // Variable para obtener el atributo de estilo
        val typedValue = TypedValue()
        // Obtiene el atributo de estilo de la toolbar de la app para la flecha de navegación
        theme.resolveAttribute(androidx.appcompat.R.attr.homeAsUpIndicator, typedValue, true)
        // Obtiene el drawable asociado
        val drawable = AppCompatResources.getDrawable(this, typedValue.resourceId)
        // Configura la flecha de navegación
        binding.toolbar.navigationIcon = drawable
        // Configura el listener de la flecha de navegación
        binding.toolbar.setNavigationOnClickListener {
            // Si se pulsa, se elimina el último fragmento de la pila
            onBackPressedDispatcher.onBackPressed()
        }

        // Limpia el menú principal, ya que no se usa en los fragmentos
        binding.toolbar.menu.clear()

        // Actualiza el título de la Toolbar según el fragmento que está visible
        val fragmentoVisible = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        binding.toolbarTitle.text = when (fragmentoVisible) {
            // Si el fragmento visible es AjustesFragment
            is AjustesFragment -> getString(R.string.ajustes)
            // Si el fragmento visible es DetallePikminFragment
            is DetallePikminFragment -> getString(R.string.detalle_pikmin)
            // Si no es ninguno de los anteriores
            else -> getString(R.string.app_name)
        }
    }

    /**
     * Configura los listeners del menú principal de la toolbar.
     *
     * Gestiona los clics en los elementos del menú:
     * - **Ajustes:** abre el [AjustesFragment].
     * - **Acerca de:** muestra un diálogo con la versión y autor de la aplicación.
     */
    private fun configurarMenuPrincipal() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            // Maneja el clic en un elemento del menú
            when (item.itemId) {
                // Si se pulsa el ícono de ajustes, muestra el fragment de ajustes
                R.id.action_ajustes -> {
                    mostrarFragment(AjustesFragment())
                    true
                }
                // Si se pulsa el ícono de acerca de, muestra un diálogo con la información de la app
                R.id.action_acercade -> {
                    val packageInfo = packageManager.getPackageInfo(packageName, 0)
                    val versionName = packageInfo.versionName
                    AlertDialog.Builder(this)
                        .setIcon(R.drawable.icono)
                        .setTitle(getString(R.string.acerca_de))
                        .setMessage(
                            getString(R.string.desarrollado_por) + "\n" +
                                    getString(R.string.version, versionName)
                        )
                        .setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
                        .show()
                    true
                }
                // Si no es ninguno de los anteriores, no hace nada y devuelve false
                else -> false
            }
        }
    }

    /**
     * Función que crea y devuelve la lista de objetos [Pikmin] que se mostrarán.
     *
     * Todos los campos de texto se almacenan como Resource IDs (Int) para facilitar
     * la internacionalización y la gestión de recursos.
     *
     * @return [List] de objetos [Pikmin].
     */
    private fun crearListaPikmin(): List<Pikmin> {
        // Lista de pikmins
        return listOf(
            Pikmin(
                nombre = R.string.red_pikmin,
                familia = R.string.fam_red_pikmin,
                nombreCientifico = R.string.cn_red_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_red_pikmin,
                caracteristica1 = R.string.char1_red_pikmin,
                caracteristica2 = R.string.char2_red_pikmin,
                caracteristica3 = R.string.char3_red_pikmin,
                imagen = R.drawable.red_pikmin

            ),
            Pikmin(
                nombre = R.string.yellow_pikmin,
                familia = R.string.fam_yellow_pikmin,
                nombreCientifico = R.string.cn_yellow_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_yellow_pikmin,
                caracteristica1 = R.string.char1_yellow_pikmin,
                caracteristica2 = R.string.char2_yellow_pikmin,
                caracteristica3 = R.string.char3_yellow_pikmin,
                imagen = R.drawable.yellow_pikmin
            ),
            Pikmin(
                nombre = R.string.blue_pikmin,
                familia = R.string.fam_blue_pikmin,
                nombreCientifico = R.string.cn_blue_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_blue_pikmin,
                caracteristica1 = R.string.char1_blue_pikmin,
                caracteristica2 = R.string.char2_blue_pikmin,
                caracteristica3 = R.string.char3_blue_pikmin,
                imagen = R.drawable.blue_pikmin
            ),
            Pikmin(
                nombre = R.string.white_pikmin,
                familia = R.string.fam_white_pikmin,
                nombreCientifico = R.string.cn_white_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_white_pikmin,
                caracteristica1 = R.string.char1_white_pikmin,
                caracteristica2 = R.string.char2_white_pikmin,
                caracteristica3 = R.string.char3_white_pikmin,
                imagen = R.drawable.white_pikmin
            ),
            Pikmin(
                nombre = R.string.purple_pikmin,
                familia = R.string.fam_purple_pikmin,
                nombreCientifico = R.string.cn_purple_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_purple_pikmin,
                caracteristica1 = R.string.char1_purple_pikmin,
                caracteristica2 = R.string.char2_purple_pikmin,
                caracteristica3 = R.string.char3_purple_pikmin,
                imagen = R.drawable.purple_pikmin
            ),
            Pikmin(
                nombre = R.string.rock_pikmin,
                familia = R.string.fam_rock_pikmin,
                nombreCientifico = R.string.cn_rock_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_rock_pikmin,
                caracteristica1 = R.string.char1_rock_pikmin,
                caracteristica2 = R.string.char2_rock_pikmin,
                caracteristica3 = R.string.char3_rock_pikmin,
                imagen = R.drawable.rock_pikmin
            ),
            Pikmin(
                nombre = R.string.winged_pikmin,
                familia = R.string.fam_winged_pikmin,
                nombreCientifico = R.string.cn_winged_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_winged_pikmin,
                caracteristica1 = R.string.char1_winged_pikmin,
                caracteristica2 = R.string.char2_winged_pikmin,
                caracteristica3 = R.string.char3_winged_pikmin,
                imagen = R.drawable.winged_pikmin
            ),
            Pikmin(
                nombre = R.string.ice_pikmin,
                familia = R.string.fam_ice_pikmin,
                nombreCientifico = R.string.cn_ice_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_ice_pikmin,
                caracteristica1 = R.string.char1_ice_pikmin,
                caracteristica2 = R.string.char2_ice_pikmin,
                caracteristica3 = R.string.char3_ice_pikmin,
                imagen = R.drawable.ice_pikmin
            ),
            Pikmin(
                nombre = R.string.glow_pikmin,
                familia = R.string.fam_glow_pikmin,
                nombreCientifico = R.string.cn_glow_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_glow_pikmin,
                caracteristica1 = R.string.char1_glow_pikmin,
                caracteristica2 = R.string.char2_glow_pikmin,
                caracteristica3 = R.string.char3_glow_pikmin,
                imagen = R.drawable.glow_pikmin
            ),
            Pikmin(
                nombre = R.string.bulborb,
                familia = R.string.fam_bulborb,
                nombreCientifico = R.string.cn_bulborb,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_bulborb,
                caracteristica1 = R.string.char1_bulborb,
                caracteristica2 = R.string.char2_bulborb,
                caracteristica3 = R.string.char3_bulborb,
                imagen = R.drawable.bulborb
            ),
            Pikmin(
                nombre = R.string.joustmite,
                familia = R.string.fam_joustmite,
                nombreCientifico = R.string.cn_joustmite,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_joustmite,
                caracteristica1 = R.string.char1_joustmite,
                caracteristica2 = R.string.char2_joustmite,
                caracteristica3 = R.string.char3_joustmite,
                imagen = R.drawable.joustmite
            ),
            Pikmin(
                nombre = R.string.skitter_leaf,
                familia = R.string.fam_skitter_leaf,
                nombreCientifico = R.string.cn_skitter_leaf,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skitter_leaf,
                caracteristica1 = R.string.char1_skitter_leaf,
                caracteristica2 = R.string.char2_skitter_leaf,
                caracteristica3 = R.string.char3_skitter_leaf,
                imagen = R.drawable.skitter_leaf
            ),
            Pikmin(
                nombre = R.string.skutterchuck,
                familia = R.string.fam_skutterchuck,
                nombreCientifico = R.string.cn_skutterchuck,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skutterchuck,
                caracteristica1 = R.string.char1_skutterchuck,
                caracteristica2 = R.string.char2_skutterchuck,
                caracteristica3 = R.string.char3_skutterchuck,
                imagen = R.drawable.skutterchuck
            ),
            Pikmin(
                nombre = R.string.pyroclasmic_slooch,
                familia = R.string.fam_pyroclasmic_slooch,
                nombreCientifico = R.string.cn_pyroclasmic_slooch,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_pyroclasmic_slooch,
                caracteristica1 = R.string.char1_pyroclasmic_slooch,
                caracteristica2 = R.string.char2_pyroclasmic_slooch,
                caracteristica3 = R.string.char3_pyroclasmic_slooch,
                imagen = R.drawable.pyroclasmic_slooch
            ),
            Pikmin(
                nombre = R.string.bearded_amprat,
                familia = R.string.fam_bearded_amprat,
                nombreCientifico = R.string.cn_bearded_amprat,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_bearded_amprat,
                caracteristica1 = R.string.char1_bearded_amprat,
                caracteristica2 = R.string.char2_bearded_amprat,
                caracteristica3 = R.string.char3_bearded_amprat,
                imagen = R.drawable.bearded_amprat
            ),
            Pikmin(
                nombre = R.string.empress_bulblax,
                familia = R.string.fam_empress_bulblax,
                nombreCientifico = R.string.cn_empress_bulblax,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_empress_bulblax,
                caracteristica1 = R.string.char1_empress_bulblax,
                caracteristica2 = R.string.char2_empress_bulblax,
                caracteristica3 = R.string.char3_empress_bulblax,
                imagen = R.drawable.empress_bulblax
            ),
            Pikmin(
                nombre = R.string.waterwraith,
                familia = R.string.fam_waterwraith,
                nombreCientifico = R.string.cn_waterwraith,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_waterwraith,
                caracteristica1 = R.string.char1_waterwraith,
                caracteristica2 = R.string.char2_waterwraith,
                caracteristica3 = R.string.char3_waterwraith,
                imagen = R.drawable.waterwraith
            ),
            Pikmin(
                nombre = R.string.lumiknoll,
                familia = R.string.fam_lumiknoll,
                nombreCientifico = R.string.cn_lumiknoll,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_lumiknoll,
                caracteristica1 = R.string.char1_lumiknoll,
                caracteristica2 = R.string.char2_lumiknoll,
                caracteristica3 = R.string.char3_lumiknoll,
                imagen = R.drawable.lumiknoll
            ),
            Pikmin(
                nombre = R.string.peckish_aristocrab,
                familia = R.string.fam_peckish_aristocrab,
                nombreCientifico = R.string.cn_peckish_aristocrab,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_peckish_aristocrab,
                caracteristica1 = R.string.char1_peckish_aristocrab,
                caracteristica2 = R.string.char2_peckish_aristocrab,
                caracteristica3 = R.string.char3_peckish_aristocrab,
                imagen = R.drawable.peckish_aristocrab
            ),
            Pikmin(
                nombre = R.string.puckering_blinnow,
                familia = R.string.fam_puckering_blinnow,
                nombreCientifico = R.string.cn_puckering_blinnow,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_puckering_blinnow,
                caracteristica1 = R.string.char1_puckering_blinnow,
                caracteristica2 = R.string.char2_puckering_blinnow,
                caracteristica3 = R.string.char3_puckering_blinnow,
                imagen = R.drawable.puckering_blinnow
            ),
            Pikmin(
                nombre = R.string.skeeterskate,
                familia = R.string.fam_skeeterskate,
                nombreCientifico = R.string.cn_skeeterskate,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skeeterskate,
                caracteristica1 = R.string.char1_skeeterskate,
                caracteristica2 = R.string.char2_skeeterskate,
                caracteristica3 = R.string.char3_skeeterskate,
                imagen = R.drawable.skeeterskate
            ),
            Pikmin(
                nombre = R.string.toady_bloyster,
                familia = R.string.fam_toady_bloyster,
                nombreCientifico = R.string.cn_toady_bloyster,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_toady_bloyster,
                caracteristica1 = R.string.char1_toady_bloyster,
                caracteristica2 = R.string.char2_toady_bloyster,
                caracteristica3 = R.string.char3_toady_bloyster,
                imagen = R.drawable.toady_bloyster
            ),
            Pikmin(
                nombre = R.string.waddlepus,
                familia = R.string.fam_waddlepus,
                nombreCientifico = R.string.cn_waddlepus,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_waddlepus,
                caracteristica1 = R.string.char1_waddlepus,
                caracteristica2 = R.string.char2_waddlepus,
                caracteristica3 = R.string.char3_waddlepus,
                imagen = R.drawable.waddlepus
            ),
            Pikmin(
                nombre = R.string.puffy_blowhog,
                familia = R.string.fam_puffy_blowhog,
                nombreCientifico = R.string.cn_puffy_blowhog,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_puffy_blowhog,
                caracteristica1 = R.string.char1_puffy_blowhog,
                caracteristica2 = R.string.char2_puffy_blowhog,
                caracteristica3 = R.string.char3_puffy_blowhog,
                imagen = R.drawable.puffy_blowhog
            ),
            Pikmin(
                nombre = R.string.swooping_snitchbug,
                familia = R.string.fam_swooping_snitchbug,
                nombreCientifico = R.string.cn_swooping_snitchbug,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_swooping_snitchbug,
                caracteristica1 = R.string.char1_swooping_snitchbug,
                caracteristica2 = R.string.char2_swooping_snitchbug,
                caracteristica3 = R.string.char3_swooping_snitchbug,
                imagen = R.drawable.swooping_snitchbug
            )
        )
    }
}