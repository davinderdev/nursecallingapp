package com.app.nursecalling.activities

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nursecalling.R
import com.app.nursecalling.adapters.EventsAdapter
import com.app.nursecalling.commoners.AppUtils
import com.app.nursecalling.model.EventsModel
import com.app.nursecalling.service.RetrofitInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eventsList= ArrayList<EventsModel>()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        eventsList.add(EventsModel(1,"CALL","ROOM","User",current.toString()))
        eventsList.add(EventsModel(1,"RESET","Reception","User",current.toString()))
        eventsList.add(EventsModel(1,"ATTACK","HALL","User",current.toString()))
        eventsList.add(EventsModel(1,"DECLINE","BACKYARD","User",current.toString()))
        eventsList.add(EventsModel(1,"CALL","UNI","User",current.toString()))
        eventsList.add(EventsModel(1,"CALL","ROOM","User",current.toString()))
        eventsList.add(EventsModel(1,"RESET","Reception","User",current.toString()))
        eventsList.add(EventsModel(1,"ATTACK","HALL","User",current.toString()))
        eventsList.add(EventsModel(1,"DECLINE","BACKYARD","User",current.toString()))
        eventsList.add(EventsModel(1,"CALL","UNI","User",current.toString()))

        eventsListView.adapter = EventsAdapter(eventsList)
        eventsListView.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        bottom_nav.selectedItemId = R.id.home
    }

   private fun getData(){
       val handler = Handler()
       val delay = 5000L // 5 seconds

       val runnable = object : Runnable {
           override fun run() {
               // Make API call using Retrofit and coroutine
               GlobalScope.launch {
                   try {
                       RetrofitInstance.apiInterface.getEvents(1,1111).enqueue(object : Callback<String?> {
                           override fun onResponse(call: Call<String?>, response: Response<String?>) {
                               println(response)
                           }

                           override fun onFailure(call: Call<String?>, t: Throwable) {
                               Toast.makeText(
                                   this@MainActivity,
                                   "${t.localizedMessage}",
                                   Toast.LENGTH_SHORT
                               ).show()
                           }
                       })
                       // Handle response
                   } catch (e: Exception) {
                       e.printStackTrace()
                   }
               }
               // Schedule the next call
               handler.postDelayed(this, delay)
           }
       }

// Start the scheduler
       handler.postDelayed(runnable, delay)





    }



    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "ONLINE MEDIC APP"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }


}