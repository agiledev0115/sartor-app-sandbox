package com.sartor.data.model

import com.sartor.util.constants.Constant

data class Brands(


    //previous
    val followers : List<Any>,
    var likes : List<Any>,
    val isTop : Boolean,
    val image : String,
    val title : String,
    val brandID : String,

    )
{
    fun fullImageUrl (): String = Constant.BASE_URL+image
}
