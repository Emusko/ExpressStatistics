package az.express.statistics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.express.statistics.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var categoryAdapter: CategoryListRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.populateCategoryList(this)

        showPieChart()

        initializeCategoryRecyclerView()

        setSearchView()
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return onQueryTextChange(query)
            }

            override fun onQueryTextChange(query: String?): Boolean {
                categoryAdapter.filterList(query)
                return true
            }

        })
    }


    private fun initializeCategoryRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        categoryAdapter = CategoryListRecyclerAdapter(
            this,
            viewModel.categories
        )
        binding.recyclerView.adapter = categoryAdapter

    }

    private fun showPieChart() {
        val pieEntries = arrayListOf<PieEntry>().apply {
            viewModel.typePieMap.forEach { (key, value) ->
                this.add(PieEntry(value.toFloat(), key))
            }
        }

        val pieData = PieData(PieDataSet(pieEntries, null).apply {
            this.valueTextSize = 12f
            this.colors = viewModel.chartLibraryColorList
            this.sliceSpace = 5f
        })

        pieData.setDrawValues(false)
        binding.pieChart.let {
            it.data = pieData
            it.centerText = getString(R.string.statistics_title_pattern, 1300000F.formatForSpace(), "AZN")
            it.description = null
            it.setDrawSliceText(false)
            it.holeRadius = 95.0f
            it.setTouchEnabled(false)
            it.legend.isEnabled = false
            it.setOnChartValueSelectedListener(object :
                OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    binding.pieChart.onTouchListener.setLastHighlighted(null)
                    binding.pieChart.highlightValues(null)
                }

                override fun onNothingSelected() {
                }

            })
            it.invalidate()
        }
    }
}