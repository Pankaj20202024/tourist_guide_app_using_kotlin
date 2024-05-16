package com.example.touristguideapp.Domains

import java.io.Serializable

data class PopularDomain(
    var BestPlace: Boolean = false,
    var CategoryId: Int = 0,
    val Description: String = "",
    var Id: Int = 0,
    var ImagePath: String? = null,
    val Location: String = "",
    val Price: Int = 0,
    val priceId:Int = 0,
    val Star: Double = 0.0,
    val bed: Int = 0,
    val Title: String = "",
    val wifi: Boolean = false,
    val guide:Boolean = false,
    val guideNumber: String = "",
    val guideEmail: String = "",
    val guideName: String = "",
    val guidePrice: Int = 0
) : Serializable {
    constructor() : this(false, 0, "", 0, null, "", 0, 0, 0.0, 0, "", false, false, "", "", "", 0)
}
