package com.example.smartdoor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class SecurityActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private val promptInfo: BiometricPrompt.PromptInfo by lazy {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("지문 인식")
            .setSubtitle("지문을 인식한 후 사용이 가능합니다.")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    showToast("인식에 성공하였습니다")
                    Log.d("디버긍ㅇ","ㅇㅇㅇㅇㅇ")
                    goToMain()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("정보가 일치하지 않습니다")
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }

    private fun goToMain() {
        Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(this)
            finish()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this@SecurityActivity, msg, Toast.LENGTH_SHORT).show()
    }
}