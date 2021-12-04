package az.express.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item_list_view.view.*
import java.util.*

class CategoryListRecyclerAdapter(
    private val context: Context,
    categoryList: ArrayList<CategoryListObject>
) : RecyclerView.Adapter<CategoryListRecyclerAdapter.ViewHolder>() {
    private var filteredList: ArrayList<CategoryListObject> = categoryList
    private val filter = FilterCategories(categoryList, this)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, categoryListObject: CategoryListObject) {
            itemView.categoryName.text = categoryListObject.name
            itemView.stateImage.setImageDrawable(categoryListObject.icon)
            itemView.ratioProgress.setIndicatorColor(categoryListObject.progressColor)
            itemView.percent.text = context.resources.getString(
                R.string.percentage_pattern,
                categoryListObject.percentage.toString()
            )
            itemView.amount.text = categoryListObject.amount.formatForSpace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.category_item_list_view, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, filteredList[position])
    }
    fun filterList(text: String?) {
        filter.filter(text)
    }
    fun setList(list: java.util.ArrayList<CategoryListObject>) {
        this.filteredList = list
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = filteredList.size
    class FilterCategories(private val categories: ArrayList<CategoryListObject>, private val adapter: CategoryListRecyclerAdapter): Filter() {
        private var filteredList = arrayListOf<CategoryListObject>()

        @ExperimentalStdlibApi
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            if (!constraint.isNullOrEmpty()){
                filteredList.clear()
                categories.forEach{
                    val contactName = it.name
                    if (!constraint.isNullOrEmpty() && contactName.isNotEmpty()) {
                        if (contactName.toLowerCase(Locale.ROOT).contains((constraint as String).toLowerCase(
                                Locale.ROOT)))
                        {
                            filteredList.add(it)
                        }
                    }
                }
                results.values = filteredList
                results.count = filteredList.size
            } else {
                results.values = categories
                results.count = categories.size
            }

            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            adapter.setList(results?.values as ArrayList<CategoryListObject>)
            adapter.notifyDataSetChanged()
        }

    }
}