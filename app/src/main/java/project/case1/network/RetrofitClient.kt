package project.case1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Android Emulator -> computer running Python.
    // For a physical phone, replace 10.0.2.2 with the PC's LAN IP.
    private const val BASE_URL = "http://10.0.2.2:5000/"

    val api: PythonApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PythonApiService::class.java)
    }
}
