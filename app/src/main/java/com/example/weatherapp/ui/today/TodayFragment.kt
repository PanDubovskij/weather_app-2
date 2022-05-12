package com.example.weatherapp.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherapp.WeatherResponse
import com.example.weatherapp.WeatherService
import com.example.weatherapp.databinding.FragmentTodayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private lateinit var current_weather: TextView
    private lateinit var humidity: TextView
    private lateinit var wind: TextView
    private lateinit var pressure: TextView
    private lateinit var wind_direction: TextView
    private lateinit var rain: TextView
    private var relativeLayout: RelativeLayout? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        val root: View = binding.root
        current_weather = binding.currentWeather
        humidity = binding.humidity
        wind = binding.wind
        pressure = binding.pressure
        wind_direction = binding.windDirection
        rain = binding.rain
        getCurrentData()
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    current_weather.text =
                        weatherResponse.main!!.temp.toString() + " F | " + weatherResponse.weather[0].description
                    humidity.text = weatherResponse.main!!.humidity.toString() + "%"
                    //  rain.text = weatherResponse.rain!!.h3.toString()
                    pressure.text = weatherResponse.main!!.pressure.toString() + "hPa"
                    wind.text = weatherResponse.wind!!.speed.toString() + "km/h"
                    wind_direction.text = weatherResponse.wind!!.deg.toString()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                current_weather.text = t.message
            }
        })
    }

    companion object {
        var BaseUrl = "https://api.openweathermap.org/"
        var AppId = "ebeedd862eea68ac749ca982ade2290d"
        var lat = "51.5085"
        var lon = "-0.1257"
    }
}