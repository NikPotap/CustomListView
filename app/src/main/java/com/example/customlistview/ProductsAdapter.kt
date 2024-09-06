package com.example.customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductsAdapter(context: Context, productList: MutableList<ProductList>) :
    ArrayAdapter<ProductList>(context, R.layout.list_item, productList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val product = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val itemIconIV = view?.findViewById<ImageView>(R.id.itemIconIV)
        val itemNameTV = view?.findViewById<TextView>(R.id.itemNameTV)
        val itemPriceTV = view?.findViewById<TextView>(R.id.itemPriceTV)
        itemIconIV?.setImageBitmap(product?.pict)
        itemNameTV?.text = product?.name
        itemPriceTV?.text = product?.price
        return view!!
    }
}