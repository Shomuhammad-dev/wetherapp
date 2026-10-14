package uz.ttpu.weatherpatterns.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import uz.ttpu.weatherpatterns.data.api.FakeWeatherApi

class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        val fakeApi = FakeWeatherApi(latencyMs = 0)
        repository = WeatherRepository(fakeApi)
    }

    @Test
    fun getWeather_success_returnsWeatherData() = runBlocking {
        val result = repository.getWeather("Tashkent")

        assertTrue(result.isSuccess)
        val weather = result.getOrNull()
        assertEquals("Tashkent", weather?.city)
        assertEquals(24, weather?.temperatureC)
        assertEquals("Sunny", weather?.condition)
    }

    @Test
    fun getWeather_unknownCity_returnsFailure() = runBlocking {
        val result = repository.getWeather("UnknownCity")

        assertTrue(result.isFailure)
    }
}
