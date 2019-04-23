package com.test.revoluttestapp.presentation.recycler.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.revoluttestapp.presentation.model.Currency
import android.view.View
import android.view.LayoutInflater
import android.widget.*
import com.squareup.picasso.Picasso
import javax.inject.Inject
import android.view.View.OnFocusChangeListener
import com.test.revoluttestapp.R

class CurrencyListAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<CurrencyListAdapter.CurrencyAdapterViewHolder>() {

    private val LOG_TAG = "CurrencyListAdapter"

    class CurrencyAdapterViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val title: TextView = itemView.findViewById(R.id.title)
        internal val subTitle: TextView = itemView.findViewById(R.id.subtitle)
        internal val value: EditText = itemView.findViewById(R.id.value)
        internal val image: ImageView = itemView.findViewById(R.id.image) as ImageView

        internal var inFocus: Boolean = false
        internal var entity: Currency? = null
    }

    private var currencies: MutableList<Currency> = mutableListOf()
    private var onItemSelectListener: ((Currency) -> Unit)? = null
    private var onItemValueChangeListener: ((String, Currency) -> Unit)? = null

    fun setCurrencies(currencies: List<Currency>) {
        this.currencies = currencies as MutableList<Currency>
        notifyDataSetChanged()
    }

    fun setItemSelectListener(block: (Currency) -> Unit) {
        onItemSelectListener = block
    }

    fun setItemValueChangeListener(block: (String, Currency) -> Unit) {
        onItemValueChangeListener = block
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
            entity = currency
            title.text = currency.longName
            subTitle.text = currency.name
            picasso.load(currency.icon).into(image)
            value.setText(currency.value.toString())
            value.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    selectItem(entity!!)
                    holder.inFocus = true
                    onItemSelectListener?.invoke(entity!!)
                }
            }

            onItemValueChangeListener?.let {
                value.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (holder.inFocus) {
                            val text = value.text.toString()
                            Log.d(LOG_TAG, "string = ${text}")
                            //onItemValueChangeListener?.invoke(text, entity!!)
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }

                })
            }
        }
    }

    private fun selectItem(currency: Currency) {
        val index = currencies.indexOf(currency)
        val movingItem = currencies[index]
        currencies.removeAt(index)
        currencies.add(0, movingItem)
        notifyItemMoved(index, 0)
    }
}
