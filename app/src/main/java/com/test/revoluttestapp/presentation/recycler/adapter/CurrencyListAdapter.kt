package com.test.revoluttestapp.presentation.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.revoluttestapp.presentation.model.Currency
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import com.test.revoluttestapp.R
import javax.inject.Inject


class CurrencyListAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<CurrencyListAdapter.CurrencyAdapterViewHolder>() {

    class CurrencyAdapterViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val title: TextView = itemView.findViewById(R.id.title)
        internal val subTitle: TextView = itemView.findViewById(R.id.subtitle)
        internal val value: EditText = itemView.findViewById(R.id.value)
        internal val image: ImageView = itemView.findViewById(R.id.image) as ImageView
    }

    private lateinit var currencies: List<Currency>

    fun setCurrencies(currencies: List<Currency>) {
        this.currencies = currencies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return CurrencyAdapterViewHolder(view)
    }

    override fun getItemCount(): Int =
        currencies.size

    override fun onBindViewHolder(holder: CurrencyAdapterViewHolder, position: Int) {
        val currency = currencies[position]

        holder.apply {
            title.text = currency.longName
            subTitle.text = currency.name
            picasso.load(currency.icon).into(image)
            value.setText(currency.value)
        }
    }

}