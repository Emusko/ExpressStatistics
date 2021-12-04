package az.express.statistics

import android.graphics.drawable.Drawable

data class CategoryListObject(
    val progressColor: Int,
    val icon: Drawable,
    val name: String,
    val percentage: Float,
    val amount: Float
)
