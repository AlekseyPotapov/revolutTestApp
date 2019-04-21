package com.test.revoluttestapp.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.revoluttestapp.presentation.extensions.bindView
import com.test.revoluttestapp.presentation.model.Currency
import com.test.revoluttestapp.presentation.recycler.adapter.CurrencyListAdapter
import com.test.revoluttestapp.presentation.viewmodel.ConverterViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.test.revoluttestapp.R

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: ConverterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var currencyListAdapter: CurrencyListAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private val recycler by bindView<RecyclerView>(R.id.recycler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ConverterViewModel::class.java]
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = currencyListAdapter
        }

        observeUi()
    }

    private fun observeUi() {
        viewModel.progressState.observe(this, Observer<List<Currency>> {
            currencyListAdapter.setCurrencies(it)
        })
    }
}
