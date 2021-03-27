package com.example.myweatherdev.ui.main.view.details

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherdev.BuildConfig
import com.example.myweatherdev.R
import com.example.myweatherdev.databinding.FragmentDetailsBinding
import com.example.myweatherdev.ui.main.model.City
import com.example.myweatherdev.ui.main.model.Weather
import com.example.myweatherdev.ui.main.model.WeatherDTO
import com.example.myweatherdev.ui.main.utils.showSnackBar
import com.example.myweatherdev.ui.main.viewmodel.AppState
import com.example.myweatherdev.ui.main.viewmodel.DetailsViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.*
import java.io.IOException

private const val PROCESS_ERROR = "Обработка ошибки"
private const val MAIN_LINK = "https://api.weather.yandex.ru/v2/informers?"
private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })
            }
        }
    }

    private fun setWeather(weather: Weather) {
        with(binding) {
            val city = weatherBundle.city
            saveCity(city, weather)
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )

            weather.icon?.let {
                GlideToVectorYou.justLoadImage(
                    activity,
                    Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                    weatherIcon
                )
                temperatureValue.text = weather.temperature.toString()
                feelsLikeValue.text = weather.feelsLike.toString()
                weatherCondition.text = weather.condition
            }

            Picasso
                .get()
                .load(" https://freepngimg.com/thumb/city/36275-3-city-hd.png ")
                .into(headerIcon)
        }
    }

    private fun saveCity(city: City, weather: Weather) {
        viewModel.saveCityToDB(Weather(city, weather.temperature, weather.feelsLike, weather.condition))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}