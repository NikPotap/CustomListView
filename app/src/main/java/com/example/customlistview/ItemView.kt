package com.example.customlistview

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ItemView : AppCompatActivity() {
    private lateinit var toolbarItemViewTB: Toolbar
    private lateinit var itemIconIV: ImageView
    private lateinit var itemNameET: TextView
    private lateinit var itemPriceET: TextView
    private lateinit var itemDescriptionET: TextView
    private var item: ProductList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setVariables()
        item = intent.extras?.getSerializable(ProductList::class.java.simpleName) as ProductList?
        itemNameET.text = item?.name
        itemPriceET.text = item?.price
        itemDescriptionET.text = item?.descr
        itemIconIV.setImageURI(Uri.parse(item?.pict))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_view_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
    private fun setVariables() {
        toolbarItemViewTB = findViewById(R.id.toolbarItemViewTB)
        setSupportActionBar(toolbarItemViewTB)
        itemIconIV = findViewById(R.id.itemIconIV)
        itemNameET = findViewById(R.id.itemNameTV)
        itemPriceET = findViewById(R.id.itemPriceTV)
        itemDescriptionET = findViewById(R.id.itemDescriptionTV)
    }

}