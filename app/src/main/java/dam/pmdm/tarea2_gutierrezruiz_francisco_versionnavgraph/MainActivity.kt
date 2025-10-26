package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Instancia del binding para la actividad
    private lateinit var binding: ActivityMainBinding

    // Controlador de navegación
    private lateinit var navController: NavController

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

        // Configura el controlador de navegación. En la variable navGostFragment se almacena el fragment que contiene el navHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // En la variable navController se almacena el controlador de navegación
        navController = navHostFragment.navController

        // Configura la toolbar cuando se cambia de destino en el grafo de navegación
        navController.addOnDestinationChangedListener { _, destination, _ ->
            configurarToolbar(destination.id)
        }

        // Aplica el modo oscuro si es es el que está guardado en las preferencias
        aplicarModoOscuro()

        // Muestra un snackbar con un mensaje de bienvenida
        Snackbar.make(
            binding.main,
            getString(R.string.bienvenido_al_mundo_pikmin),
            Snackbar.LENGTH_LONG
        ).show()
    }


    private fun configurarToolbar(destinationId: Int) {
        // Valores por defecto
        var titulo = getString(R.string.app_name) // El título por defecto es el nombre de la aplicación
        var flechaAtras: Drawable? = null // La flecha de retroceso por defecto es null
        var navigationClickListener: (() -> Unit)? = null // El listener de clics por defecto es null
        var mostrarMenu = false // Por defecto no se muestra el menú

        // Configura los valores según el destino de navegación
        when (destinationId) {
            // Si el destino es el fragment de la lista de pikmins, se muestra el menú
            R.id.listadoPikminsFragment -> mostrarMenu = true
            // Si el destino es el fragment de ajustes, se muestra el botón de retroceso y su listener y el titulo
            R.id.ajustesFragment -> {
                titulo = getString(R.string.ajustes)
                flechaAtras = AppCompatResources.getDrawable(this, R.drawable.flecha_atras_icon)
                navigationClickListener = { navController.popBackStack() }
            }
            // Si el destino es el fragment de los detalles de un pikmin, se muestra el botón de retroceso y su listener y el titulo
            R.id.detallePikminFragment -> {
                titulo = getString(R.string.detalle_pikmin)
                flechaAtras = AppCompatResources.getDrawable(this, R.drawable.flecha_atras_icon)
                navigationClickListener = { navController.popBackStack() }
            }
        }

        // Aplica los valores al toolbar
        binding.toolbarTitle.text = titulo
        binding.toolbar.navigationIcon = flechaAtras
        binding.toolbar.setNavigationOnClickListener(
            if (navigationClickListener != null) View.OnClickListener { navigationClickListener() } // Si el listener no es null, se aplica el listener
            else null //
        )

        // Gestiona el menú
        // Limpia el menú actual
        binding.toolbar.menu.clear()
        // Si se debe mostrar el menú (sólo si el destino es el fragment de la lista de pikmins), infla el menú y configura el listener
        if (mostrarMenu) {
            binding.toolbar.inflateMenu(R.menu.menu)
            binding.toolbar.setOnMenuItemClickListener { item ->
                // Gestiona los clics en los elementos del menú
                when (item.itemId) {
                    // Si se ha pulsado el item de ajustes se navega al fragment de ajustes
                    R.id.action_ajustes -> {
                        navController.navigate(R.id.ajustesFragment)
                        true
                    }
                    // Si se ha pulsado el item de acerca de se muestra un AlertDialog con la información de la aplicación
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
                    else -> false
                }
            }
        }
    }

    private fun aplicarModoOscuro() {
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
    }

}