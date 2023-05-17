package edu.temple.desserrtapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_ARRAY_KEY = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewModel = ViewModelProvider(this)[ItemViewModel::class.java]

        val items = generateTestData()

        val selectionFragment = SelectionFragment.newInstance(items)
        val displayFragment = DisplayFragment()

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, selectionFragment)
                .add(R.id.fragmentContainerView2, displayFragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }

        imageViewModel.setSelectedItem(items[0])
    }

    /**
     * Generate test info for app
     */
    fun generateTestData(): Array<Item> {
        return arrayOf(Item(R.drawable.ccf_original, "Original")
            , Item(R.drawable.ccf_freshstrawberry,"Fresh Strawberry")
            , Item(R.drawable.ccf_chocolatecaramelicious,"Chocolate Caramelicious Cheesecake ")
            , Item(R.drawable.ccf_pineappleupsidedown,"Pineapple Upside-Down")
            , Item(R.drawable.ccf_celebration,"Celebration")
            , Item(R.drawable.ccf_caramelapple,"Caramel Apple")
            , Item(R.drawable.ccf_verycherryghirardellichocolate,"Very Cherry Ghirardelli® Chocolate")
            , Item(R.drawable.ccf_lowlicious,"Low-Licious")
            , Item(R.drawable.ccf_cinnaboncinnamoncwirl,"Cinnabon® Cinnamon Swirl")
            , Item(R.drawable.ccf_godiva,"Godiva® Chocolate")
            , Item(R.drawable.ccf_coconutcreampie,"Coconut Cream Pie")
            , Item(R.drawable.ccf_saltedcaramel,"Salted Caramel"))
    }
}