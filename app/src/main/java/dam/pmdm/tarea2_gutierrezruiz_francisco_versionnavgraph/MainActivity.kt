package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Instancia del binding para la actividad
    private lateinit var binding: ActivityMainBinding

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

        // Aplica el modo oscuro si es es el que está guardado en las preferencias
        aplicarModoOscuro()
        // Muestra la vista principal (sin fragment)
        mostrarVistaPrincipal()
        // Listener para actualizar la flecha según el fragment mostrado. Se llama cuando se cambia de fragment
        supportFragmentManager.addOnBackStackChangedListener { actualizarFlechaToolbar() }
        // Muestra el fragment inicial (listado de pikmins mediante RecyclerView). No se añade a la pila de fragments para que no se pueda volver atrás y no se muestre la flecha de navegación
        mostrarFragment(ListadoPikminsFragment(), addToBackStack = false)
        // Muestra un snackbar con un mensaje de bienvenida
        Snackbar.make(
            binding.main,
            getString(R.string.bienvenido_al_mundo_pikmin),
            Snackbar.LENGTH_LONG
        ).show()
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

    private fun mostrarVistaPrincipal() {
        // Oculta la flecha de navegación
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

    private fun actualizarFlechaToolbar() {
        // Obtiene el fragment actual
        val fragmentActual = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        // Comprueba si es el fragment de listado de pikmins
        if (fragmentActual is ListadoPikminsFragment) {
            // Si es el fragment de listado, oculta la flecha y muestra el nombre de la app
            binding.toolbar.navigationIcon = null
            binding.toolbarTitle.text = getString(R.string.app_name)
        } else {
            // Si no es el fragment de listado, muestra la flecha y el nombre del fragment actual
            // Crea un objeto TypedValue para almacenar el valor del atributo
            val typedValue = TypedValue()
            // Obtiene el tema actual
            val tema = theme
            // Obtiene el valor del atributo homeAsUpIndicator del tema y lo almacena en el objeto TypedValue
            tema.resolveAttribute(androidx.appcompat.R.attr.homeAsUpIndicator, typedValue, true)
            // Establece la flecha de navegación en el toolbar con el drawable correspondiente al atributo
            binding.toolbar.navigationIcon =
                AppCompatResources.getDrawable(this, typedValue.resourceId)
            // Listener para volver atrás
            binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    fun mostrarFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        // Reemplaza el fragment actual con el nuevo
        val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
        // Si le ha llegado un fragment que SI debe añadir a la pila, lo añade
        if (addToBackStack) transaction.addToBackStack(null)
        // Realiza la transacción
        transaction.commit()
    }
}