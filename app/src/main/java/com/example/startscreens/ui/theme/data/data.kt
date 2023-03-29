package com.example.startscreens.ui.theme.data

import androidx.compose.ui.graphics.Color
import com.example.startscreens.ui.theme.ColorGreen


    data class FruitData(
        val image : Int,
        val title : String,
        val desc : String,
        val mainColor : Color = ColorGreen,
        val backGroundColor: Color
    )

