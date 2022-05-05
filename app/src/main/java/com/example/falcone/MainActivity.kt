package com.example.falcone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import com.example.falcone.data.PlanetsItem
import kotlinx.android.synthetic.main.activity_main.*
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
}