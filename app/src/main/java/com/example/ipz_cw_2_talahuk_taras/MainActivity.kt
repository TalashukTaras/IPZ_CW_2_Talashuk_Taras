package com.example.ipz_cw_2_talahuk_taras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipz_cw_2_talahuk_taras.ui.theme.IPZ_CW_2_Talahuk_TarasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CW_2_Talahuk_TarasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignInScreen()
                }
            }
        }
    }
}

sealed class SignInState {
    object SignIn : SignInState()
    data class SignInSuccess(val email: String) : SignInState()
}

sealed class SignInEvent {
    data class SignInClicked(val email: String, val password: String) : SignInEvent()
    object SignOutClicked : SignInEvent()
}

@Composable
fun SignInScreen() {
    var signInState by remember { mutableStateOf<SignInState>(SignInState.SignIn) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (signInState) {
            is SignInState.SignIn -> {
                SignInContent(
                    onSignInClick = {
                        signInState = SignInState.SignInSuccess("user@example.com")
                    }
                )
            }
            is SignInState.SignInSuccess -> {
                SignInSuccessContent(
                    email = (signInState as SignInState.SignInSuccess).email,
                    onSignOutClick = {
                        signInState = SignInState.SignIn
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    IPZ_CW_2_Talahuk_TarasTheme {
        SignInScreen()
    }
}
