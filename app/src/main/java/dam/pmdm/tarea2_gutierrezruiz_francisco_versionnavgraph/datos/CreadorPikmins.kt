package dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.datos

import dam.pmdm.tarea2_gutierrezruiz_francisco_versionnavgraph.R

/**
 * Objeto singleton que crea y almacena la lista de todos los [Pikmin] de la aplicación.
 *
 * Proporciona una propiedad [listadoPikmins] que contiene todos los Pikmins definidos.
 */
object CreadorPikmins {
    /** Lista inmutable de [Pikmin] creada al inicializar el objeto */
    val listadoPikmins: List<Pikmin> = crearListaPikmin()

    /**
     * Crea la lista de Pikmins con sus datos completos.
     *
     * Cada [Pikmin] se inicializa con:
     * - Identificador único.
     * - Nombre y familia (referencias a recursos string).
     * - Nombre científico (referencia a recurso string).
     * - Características de movilidad: terrestre, acuático o aéreo.
     * - Descripción y tres características adicionales (referencias a recursos string).
     * - Imagen asociada (referencia a recurso drawable).
     *
     * @return Lista de [Pikmin] con todos los datos inicializados.
     */
    private fun crearListaPikmin(): List<Pikmin> {
        return listOf(
            Pikmin(
                id = "pikmin_rojo",
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
                id = "pikmin_amarillo",
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
                id = "pikmin_azul",
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
                id = "pikmin_blanco",
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
                id = "pikmin_morado",
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
                id = "pikmin_petreo",
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
                id = "pikmin_alado",
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
                id = "pikmin_gelido",
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
                id = "pikmin_luminoso",
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
                id = "bulbo",
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
                id = "liendre_astada",
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
                id = "pisaatomos_hoja",
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
                id = "insecto_noducristal",
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
                id = "pirobabosa",
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
                id = "estatican_lanudo",
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
                id = "bulbo_emperatriz",
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
                id = "espectro_acuatico",
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
                id = "lumiloma",
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
                id = "cepucranco",
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
                id = "comejen_acuatico",
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
                id = "acuatirador",
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
                id = "berberechido_soplon",
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
                id = "burbujero",
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
                id = "verraco_volador",
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
                id = "moscardon_ladron",
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