import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstapp.R
import com.example.myfirstapp.auth.LoginViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onClose: () -> Unit) {
    val loginViewModel = viewModel<LoginViewModel>(factory = LoginViewModel.Factory)
    val loginUiState by rememberUpdatedState(newValue = loginViewModel.uiState)
    var isShaking by remember { mutableStateOf(false) }

    // Proximity sensor variables
    var proximityText by remember { mutableStateOf("") }
    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    DisposableEffect(sensorManager, proximitySensor) {
        val proximityListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent) {
                val proximityValue = event.values[0]
                proximityText = "Proximity: $proximityValue cm"
            }
        }

        sensorManager.registerListener(proximityListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(proximityListener)
        }
    }

    // Trigger the shaking animation continuously
    LaunchedEffect(isShaking) {
        while (true) {
            delay(300)
            isShaking = !isShaking
        }
    }

    // Trigger the shaking animation when the login button is clicked
    LaunchedEffect(loginViewModel.uiState) {
        if (loginUiState.isAuthenticating) {
            isShaking = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.login)) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation icon click */ }) {
                        Icon(Icons.Filled.Lock, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)

            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.displayMedium
                        .copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold),

                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .graphicsLayer(
                            rotationZ = if (isShaking) {
                                4f
                            } else {
                                -4f
                            }
                        )
                )
                Spacer(modifier = Modifier.weight(0.3f))

                var username by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.weight(0.3f))

                AnimatedVisibility(
                    visible = username.isNotEmpty() && password.isNotEmpty(),
                    enter = slideInHorizontally(
                        initialOffsetX = { fullWidth -> -fullWidth * 2 },
                        animationSpec = tween(
                            durationMillis = 150,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                    exit = slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth * 2 },
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = FastOutLinearInEasing
                        )
                    )
                ) {
                    Button(
                        onClick = {
                            loginViewModel.login(username, password)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Login")
                    }
                }

                Spacer(modifier = Modifier.weight(3f))

                Text(
                    text = proximityText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally),
                style = TextStyle.Default.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                if (loginUiState.isAuthenticating) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .padding(bottom = 8.dp)
                    )
                }

                if (loginUiState.authenticationError != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.error)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onError
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Login failed: ${loginUiState.authenticationError!!.message}",
                            color = MaterialTheme.colorScheme.onError
                        )
                    }
                }
            }
        }
    )

    LaunchedEffect(loginUiState.authenticationCompleted) {
        if (loginUiState.authenticationCompleted) {
            onClose()
        }
        isShaking = false
    }
}
