package com.example.weatherapp.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTodayBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private lateinit var current_weather:TextView
    private lateinit var humidity:TextView
    private lateinit var wind:TextView
    private lateinit var pressure:TextView
    private lateinit var wind_direction:TextView
    private lateinit var rain:TextView
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
        rain.text="No rain"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    inner class weatherTask() : AsyncTask<String, String, String>() {
//        override fun onPreExecute() {
//            super.onPreExecute()
//            current_weather.text = "Prosessing.."
//        }
//
////        override fun doInBackground(vararg params: String?): String? {
////            var connection: HttpURLConnection? = null;
////            var response:String?
////            try{
////                response = "https://api.openweathermap.org/data/2.5/weather?q=London&units=metric&appid=ebeedd862eea68ac749ca982ade2290d"
////                var url = URL(response)
////                connection = url.openConnection() as HttpURLConnection?
////                connection?.connect();
////                // Создаем объекты для считывания данных из файла
////                var stream: InputStream? = connection?.getInputStream()
////                var reader = BufferedReader(InputStreamReader(stream))
////
////                // Генерируемая строка
////                var buffer: StringBuilder = java.lang.StringBuilder()
////                var line:String = "";
////
////                // Считываем файл и записываем все в строку
////                while((reader.readLine().also { line = it }) != null) {
////                    buffer.append(line).append("\n")
////                }
////                return buffer.toString()
////            }catch (e: Exception){
////                response = null
////            }finally {
////                // Закрываем соединения
////                if(connection != null)
////                    connection.disconnect()
////            }
////            return response
////        }
//override fun doInBackground(vararg params: String?): String? {
//    var response: String?
//    try {
//        response =
//            URL("https://api.openweathermap.org/data/2.5/weather?q=London&units=metric&appid=ebeedd862eea68ac749ca982ade2290d").readText(
//                Charsets.UTF_8
//            )
//    } catch (e: Exception) {
//        response = null
//    }
//    return response
//}
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            try {
//                if(result==null){
//                    current_weather.text="null"
//                }
//                val jsonObj = JSONObject(result)
//                val main = jsonObj.getJSONObject("main")
//                val wind_ = jsonObj.getJSONObject("wind")
//                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
//
//                val temp = main.getString("temp")+"°C"
//                val pressure_ = main.getString("pressure")
//                val humidity_ = main.getString("humidity")
//                val windSpeed = wind_.getString("speed")
//                val weatherDescription = weather.getString("description")
//
//                current_weather.text = temp + "|" + weatherDescription
//                humidity.text = humidity_
//                wind.text = windSpeed
//                pressure.text = pressure_
//
//               // wind_direction?.text = wind_.getString("deg")
//               // relativeLayout?.visibility=View.VISIBLE
//
//            } catch (e: Exception) {
//            }
//
//        }
//    }

}