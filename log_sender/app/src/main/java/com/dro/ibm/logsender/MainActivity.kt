package com.dro.ibm.logsender

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dro.ibm.logsender.databinding.ActivityMainBinding
import com.dro.ibm.logsender.models.body.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.gildor.coroutines.okhttp.await
import java.util.Date

class MainActivity : AppCompatActivity() {
    private var JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    private var client: OkHttpClient = OkHttpClient()
    private val scope = CoroutineScope(SupervisorJob() + CoroutineName("LogMessageSender"))

    private lateinit var binding: ActivityMainBinding

    private val sendMessageButton by lazy(LazyThreadSafetyMode.NONE) {
        binding.btnSendMessage
    }
    private val inputMessage by lazy(LazyThreadSafetyMode.NONE) {
        binding.inputMessage
    }

    private val gson = Gson()
    val gsonPretty = GsonBuilder().setPrettyPrinting().create()

    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        builder = AlertDialog.Builder(this)
        builder.setTitle("Log sent to server.")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

        sendMessageButton.setOnClickListener {
            val message: String = inputMessage.text.toString()
            val timestamp: String = Date().time.toString()

            sendMessageToServer(Log(message, timestamp))
            builder.show()
        }
    }

    private fun sendMessageToServer(log: Log): Job = scope.launch {
        val message: String

        val body: RequestBody = gson.toJson(log).toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url("${BuildConfig.BACKEND_DEPLOYED_URL}api/logs")
            .post(body)
            .build()

        val response = client.newCall(request).await()

        message = response.body.toString()

        builder.setMessage(gsonPretty.toJson(message))
    }
}
