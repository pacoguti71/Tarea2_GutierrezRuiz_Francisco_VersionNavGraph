package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph

/**
 * Clase de datos que representa un Pikmin o criatura del universo Pikmin.
 *
 * Utiliza Resource IDs (Int) para campos de texto e imagen, lo que facilita
 * la gestión de recursos localizados (internacionalización) y la referencia
 * a drawables.
 *
 * @property nombre El ID del recurso de cadena (Int) para el nombre común del Pikmin.
 * @property familia El ID del recurso de cadena (Int) para la familia o grupo al que pertenece el Pikmin.
 * @property nombreCientifico El ID del recurso de cadena (Int) para el nombre científico del Pikmin.
 * @property esTerrestre Booleano que indica si el Pikmin puede operar en tierra.
 * @property esAcuatico Booleano que indica si el Pikmin puede operar en agua.
 * @property esAereo Booleano que indica si el Pikmin puede operar en el aire.
 * @property descripcion El ID del recurso de cadena (Int) para la descripción detallada del Pikmin.
 * @property caracteristica1 El ID del recurso de cadena (Int) para la primera característica o habilidad especial.
 * @property caracteristica2 El ID del recurso de cadena (Int) para la segunda característica o habilidad especial.
 * @property caracteristica3 El ID del recurso de cadena (Int) para la tercera característica o habilidad especial.
 * @property imagen El ID del recurso drawable (Int) para la imagen representativa del Pikmin.
 */
data class Pikmin(
    val nombre: Int,
    val familia: Int,
    val nombreCientifico: Int,
    val esTerrestre: Boolean,
    val esAcuatico: Boolean,
    val esAereo: Boolean,
    val descripcion: Int,
    val caracteristica1: Int,
    val caracteristica2: Int,
    val caracteristica3: Int,
    val imagen: Int
)