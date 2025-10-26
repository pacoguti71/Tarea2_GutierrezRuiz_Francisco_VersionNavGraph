package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos

/**
 * Representa un Pikmin con todos sus datos y características.
 *
 * @property id Identificador único del Pikmin (string).
 * @property nombre Recurso de string con el nombre del Pikmin.
 * @property familia Recurso de string que indica la familia a la que pertenece.
 * @property nombreCientifico Recurso de string con el nombre científico del Pikmin.
 * @property esTerrestre Indica si el Pikmin puede moverse por tierra.
 * @property esAcuatico Indica si el Pikmin puede moverse por agua.
 * @property esAereo Indica si el Pikmin puede volar o moverse por el aire.
 * @property descripcion Recurso de string con la descripción del Pikmin.
 * @property caracteristica1 Recurso de string con la primera característica destacada.
 * @property caracteristica2 Recurso de string con la segunda característica destacada.
 * @property caracteristica3 Recurso de string con la tercera característica destacada.
 * @property imagen Recurso drawable que representa la imagen del Pikmin.
 */
data class Pikmin(
    val id: String,
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
    val imagen: Int,
)