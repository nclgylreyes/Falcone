package com.example.falcone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import com.example.falcone.data.PlanetsItem
import com.example.falcone.data.VehicleItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start_again.*
import kotlinx.android.synthetic.main.activity_try_again.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://findfalcone.herokuapp.com")
            .build()
            .create(ApiInterface::class.java)

        var planetData = arrayListOf<Array<String>>()
        val listOfPlanets = arrayListOf<String>()

        var vehicleData = arrayListOf<Array<String>>()
        val listOfVehicles = arrayListOf<String>()

        getDataPlanets(retrofitBuilder){ result ->
            planetData = result
            for (planet in planetData){
                listOfPlanets.add(planet.get(0))
            }
            val planetAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listOfPlanets)
            autoPlanetOne.setAdapter(planetAdapter)
            autoPlanetTwo.setAdapter(planetAdapter)
            autoPlanetThree.setAdapter(planetAdapter)
            autoPlanetFour.setAdapter(planetAdapter)
        }

        getDataVehicles(retrofitBuilder){ result ->
            vehicleData = result
            for (vehicle in vehicleData){
                listOfVehicles.add(vehicle.get(0))
            }
            val vehicleAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listOfVehicles)
            autoVehicleOne.setAdapter(vehicleAdapter)
            autoVehicleTwo.setAdapter(vehicleAdapter)
            autoVehicleThree.setAdapter(vehicleAdapter)
            autoVehicleFour.setAdapter(vehicleAdapter)
        }

    }
    fun getDataPlanets(retrofitBuilder:ApiInterface, callback: (ArrayList<Array<String>>) -> Unit){
        val retrofit = retrofitBuilder.getPlanets()
        val planetStorage = arrayListOf<Array<String>>()

        retrofit.enqueue(object : Callback<List<PlanetsItem>?> {
            override fun onResponse(
                call: Call<List<PlanetsItem>?>,
                response: Response<List<PlanetsItem>?>
            ) {
                val responseBody = response.body()!!
                for(planet in responseBody){
                    val planetInfo = arrayOf(planet.name,planet.distance.toString())
                    planetStorage.add(planetInfo)
                }
                callback (planetStorage)
            }

            override fun onFailure(call: Call<List<PlanetsItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getDataVehicles(retrofitBuilder:ApiInterface, callback: (ArrayList<Array<String>>) -> Unit){
        val retrofit = retrofitBuilder.getVehicles()
        val vehicleStorage = arrayListOf<Array<String>>()

      retrofit.enqueue(object : Callback<List<VehicleItem>?> {
          override fun onResponse(
              call: Call<List<VehicleItem>?>,
              response: Response<List<VehicleItem>?>
          ) {
              val responseBody = response.body()!!
              for(vehicle in responseBody){
                  val vehicleInfo = arrayOf(vehicle.name,vehicle.total_no.toString(), vehicle.max_distance.toString(), vehicle.speed.toString())
                  vehicleStorage.add(vehicleInfo)
              }
              callback (vehicleStorage)
          }

          override fun onFailure(call: Call<List<VehicleItem>?>, t: Throwable) {
              TODO("Not yet implemented")
          }
      })
    }
}