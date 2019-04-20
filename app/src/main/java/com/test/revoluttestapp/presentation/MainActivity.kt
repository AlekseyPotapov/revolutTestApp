package com.test.revoluttestapp.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.test.revoluttestapp.R
import com.test.revoluttestapp.presentation.extensions.bindView
import com.test.revoluttestapp.presentation.model.Currency
import com.test.revoluttestapp.presentation.viewmodel.ConverterViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: ConverterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    @Inject
//    lateinit var picasso: Picasso

    private val edittext by bindView<EditText>(R.id.test)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ConverterViewModel::class.java]

        observeUi()
    }

    private fun observeUi() {
        viewModel.progressState.observe(this, Observer<List<Currency>> {
            edittext.setText(it?.get(2)?.value)
        })
    }
}
