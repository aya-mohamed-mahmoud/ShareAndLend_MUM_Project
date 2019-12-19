package com.example.shareandlend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.shareandlend.model.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {

    lateinit var tx: FragmentTransaction
    var fmanager: FragmentManager =  supportFragmentManager
    lateinit var loggedInUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //get user from firebase
        val intent = getIntent()
        loggedInUser = intent.getSerializableExtra("user") as User

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AvailableLendItemsFragment(), "Available Lend Items")
        adapter.addFragment(AvailableSharedItemsFragment(), "Available Shared Items")
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
              //  Toast.makeText(this@MainActivity, "Selected page position: $position", Toast.LENGTH_SHORT).show()

            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) { // Code goes here
            }
            override fun onPageScrollStateChanged(state: Int) { // Code goes here
            }
        })
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action1 ->
            {
                val intent = Intent(this, AddItemActivity::class.java)
                intent.putExtra("user",loggedInUser)
                startActivity(intent)
                true
            }
            R.id.action3 -> {
                val intent = Intent(this, UserSharedItemsActivity::class.java)
               intent.putExtra("user",loggedInUser)
                startActivity(intent)
                true
            }
            R.id.action2-> {
                val intent = Intent(this, UserLendItemsActivity::class.java)
                intent.putExtra("user",loggedInUser)
                startActivity(intent)
                true


            }

            R.id.action4-> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("user",loggedInUser)
                startActivity(intent)
                true


            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
