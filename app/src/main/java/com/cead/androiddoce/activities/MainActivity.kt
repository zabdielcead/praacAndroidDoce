package com.cead.androiddoce.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.cead.androiddoce.R
import com.cead.androiddoce.fragments.AlertsFragment
import com.cead.androiddoce.fragments.EmailFragment
import com.cead.androiddoce.fragments.InfoFragment

class MainActivity : AppCompatActivity() {


    /*
     Navigation drawer: panel lateral de navegacion

     para los styles a partir de la version 21 la hie con click derecho en styles.xml -> new -> values resource filae en available auqlifiers seleccionamos api y puse 21
     */

    var toolbar: Toolbar? =  null

    var drawerLayout: DrawerLayout ? = null // drawer es el menu
    var navigationView: NavigationView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navview)


        setFragmentByDefault()


        //para ver si esta viendose el navigationview
        drawerLayout!!.addDrawerListener(object: DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(p0: Int) {

            }

            override fun onDrawerSlide(p0: View, p1: Float) {

            }

            override fun onDrawerClosed(p0: View) {
                Toast.makeText(this@MainActivity,"onDrawerClosed", Toast.LENGTH_SHORT).show()
            }

            override fun onDrawerOpened(p0: View) {
                Toast.makeText(this@MainActivity,"onDrawerOpened", Toast.LENGTH_SHORT).show()
            }

        })


        navigationView!!.setNavigationItemSelectedListener (object : NavigationView.OnNavigationItemSelectedListener{  // clcik en los iconos Email, Alerts, Informacion
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    var fregamentTransaction =  false
                    var fragment: Fragment ? = null

                    when(item.itemId){  //abriremos un fragment

                        R.id.menu_mail -> {
                            fragment = EmailFragment()
                            fregamentTransaction = true
                        }

                        R.id.menu_alert -> {
                            fragment = AlertsFragment()
                            fregamentTransaction = true
                        }

                        R.id.menu_info -> {
                            fragment = InfoFragment()
                            fregamentTransaction = true
                        }
                        R.id.menu_opcion_1 -> {
                            Toast.makeText(this@MainActivity,"opcion 1", Toast.LENGTH_LONG).show()
                        }

                    }

                    if(fregamentTransaction){
                        changeFragment(fragment, item)
                        drawerLayout!!.closeDrawers()

                    }

                    return true
                }

            }
        )


    }


    private fun changeFragment(fragment: Fragment?, item : MenuItem){
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment!!).commit()
        item.isChecked = true
        supportActionBar!!.title = item.title

    }

    private fun setFragmentByDefault(){
       // supportFragmentManager!!.beginTransaction().replace(R.id.content_frame, EmailFragment()).commit()
        var item : MenuItem = navigationView!!.menu.getItem(0)
        //item.setChecked(true)
        //supportActionBar!!.title = item.title
        //toolbar!!.title = item.title
        changeFragment(EmailFragment(), item)

    }


    // Toolbar

    private fun setToolbar(){
        toolbar =  findViewById(R.id.toolbar)
        toolbar!!.title = "logo1"
        toolbar!!.subtitle = "sublogo2"
        toolbar!!.setLogo(android.R.drawable.ic_menu_info_details)
       // toolbar!!.setNavigationIcon(R.drawable.ic_home); // your drawable
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //para que se vea el icono de la hamburguesa
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home) // para el icono de la hamburguesa

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){

            android.R.id.home ->{
                Toast.makeText(this@MainActivity, "Hamburguesa", Toast.LENGTH_SHORT).show()
                drawerLayout!!.openDrawer(GravityCompat.START) //
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
