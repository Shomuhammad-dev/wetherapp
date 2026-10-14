package uz.ttpu.weatherpatterns.mvc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import uz.ttpu.weatherpatterns.di.WeatherModule
import uz.ttpu.weatherpatterns.mvc.controller.WeatherController
import uz.ttpu.weatherpatterns.mvc.model.WeatherModel
import uz.ttpu.weatherpatterns.ui.WeatherScreen

class MvcActivity : ComponentActivity(), WeatherView {

    private val model = WeatherModel(WeatherModule.repository)
    private val controller = WeatherController(model, view = this)
    private var status by mutableStateOf("Pick a city")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = controller::onCitySelected)
            }
        }
    }

    // render() parametr olmaydi, chunki View Modelni o'zi to'g'ridan-to'g'ri o'qiydi;
    // Controller unga ma'lumot "uzatmaydi", faqat "qayta chizish kerak" deb xabar beradi.
    // Agar Controller Weather'ni View'ga parametr sifatida uzatganida, View Modelni
    // umuman bilmay qolardi va bu allaqachon MVP'ga yaqinlashgan bo'lardi.
    override fun render() {
        val error = model.error
        val weather = model.weather
        status = when {
            model.isLoading -> "Loading…"
            error != null -> "Error: $error"
            weather != null -> "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
            else -> "Pick a city"
        }
    }
}
