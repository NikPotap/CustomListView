package com.example.customlistview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MakingShop : AppCompatActivity() {
    private val GALLERY_REQUEST = 1448729
    var bitmap: Bitmap? = null
    var products: MutableList<ProductList> = mutableListOf()
    private lateinit var toolbarMakeShopTB: Toolbar
    private lateinit var inputIconIV: ImageView
    private lateinit var inputNameET: EditText
    private lateinit var inputPriceET: EditText
    private lateinit var saveItemBTN: Button
    private lateinit var productListLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_making_shop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setVariables()

        inputIconIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
        saveItemBTN.setOnClickListener { View ->
            closeKeyboard(View)
            val productName = inputNameET.text.toString()
            val productPrice = inputPriceET.text.toString()
            if (testOfData(this, productName, productPrice, inputIconIV.tag.toString()) == false) return@setOnClickListener
            val productImage = bitmap
            products.add(ProductList(productName, productPrice + " ₽", productImage))
            val listAdapter = ProductsAdapter(this@MakingShop, products)
            productListLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            inputNameET.text.clear()
            inputPriceET.text.clear()
            inputIconIV.setImageResource(R.drawable.baseline_production_quantity_limits_24)
            inputIconIV.setTag("false")
        }
    }

    private fun setVariables() {
        toolbarMakeShopTB = findViewById(R.id.toolbarMakeShopTB)
        setSupportActionBar(toolbarMakeShopTB)
        inputIconIV = findViewById(R.id.inputIconIV)
        inputNameET = findViewById(R.id.inputNameET)
        inputPriceET = findViewById(R.id.inputPriceET)
        saveItemBTN = findViewById(R.id.saveItemBTN)
        productListLV = findViewById(R.id.productListLV)
    }

    private fun closeKeyboard(View: View) {
        val imm =
            View.context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(View.windowToken, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        inputIconIV = findViewById(R.id.inputIconIV)
        when (requestCode){
            GALLERY_REQUEST -> if (resultCode === RESULT_OK){
                val selPic: Uri? = data?.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selPic)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                inputIconIV.setImageBitmap(bitmap)
                inputIconIV.setTag("true")
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.making_shop_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}