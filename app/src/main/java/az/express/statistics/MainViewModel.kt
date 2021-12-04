package az.express.statistics

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val categories = arrayListOf<CategoryListObject>()


    fun populateCategoryList(context: Context) {
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_transport_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_transport)!!,
                "Transport",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_grocery_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_grocery)!!,
                "Grocery",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_house_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_house)!!,
                "House",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_entertainment_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_entertainment)!!,
                "Entertainment",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_mobile_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_mobile)!!,
                "Mobile",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_health_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_health)!!,
                "Health",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_shopping_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_shopping)!!,
                "Shopping",
                22F,
                1500F
            )
        )
        categories.add(
            CategoryListObject(
                ContextCompat.getColor(context, R.color.category_other_color),
                ContextCompat.getDrawable(context, R.drawable.ic_category_others)!!,
                "Other",
                22F,
                1500F
            )
        )
        setPieMap(categories)
    }
    val chartLibraryColorList = arrayListOf<Int>().apply {
        add(Color.parseColor("#6FD233"))
        add(Color.parseColor("#F58220"))
        add(Color.parseColor("#2DB6F5"))
        add(Color.parseColor("#F5C324"))
        add(Color.parseColor("#9300ED"))
        add(Color.parseColor("#E289F2"))
        add(Color.parseColor("#275FCD"))
        add(Color.parseColor("#4F8BFF"))
    }
    val typePieMap = HashMap<String, Int>()
    private fun setPieMap(categories: ArrayList<CategoryListObject>){
        categories.forEach {
            typePieMap[it.name] = it.amount.toInt()
        }
    }
}