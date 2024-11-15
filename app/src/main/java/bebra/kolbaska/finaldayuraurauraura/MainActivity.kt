package bebra.kolbaska.finaldayuraurauraura

import android.os.Bundle
import android.text.style.LineHeightSpan
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import bebra.kolbaska.finaldayuraurauraura.ui.theme.FinaldayurauraurauraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinaldayurauraurauraTheme {
                Greeting()
            }
        }
    }
}

sealed class Screen {
    data object Home : Screen()
    data object BookDet : Screen()
}

@Composable
fun Greeting() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    val hotels = listOf(
        "Résidence Pierre & Vacances Premium les Crets",
        "Duplex - Plein sud - Pied des pistes - 50m2 - Mottaret",
        "Meribel le chalet d'eugénie",
        "Chalet de 3 chambres a Les Allues",
        "Résidence Premium L'Hévana",
        "L'Hévana - maeva Home - Appartement",
        "La Belette - Duplex au charme savoyard avec vue sur la montagne",
        "Superbe Appartement au Plateau Rond",
        "Appartement Méribel",
        "L'Appart à Méribel"
    )

    val images = listOf(
        R.drawable._000,
        R.drawable._001,
        R.drawable._002,
        R.drawable._003,
        R.drawable._004,
        R.drawable._005,
        R.drawable._006,
        R.drawable._007,
        R.drawable._008,
        R.drawable._009,
    )

    val ratings = listOf(
        8.3,
        9.4,
        7.8,
        7.1,
        7.9,
        8.2,
        6.7,
        6.9,
        9.0,
        7.0
    )

    val howDaleko = listOf(
        6.7,
        6.4,
        0.8,
        3.2,
        3.8,
        3.8,
        1.8,
        3.7,
        6.5,
        6.2
    )

    val hotelName = remember { mutableStateOf("") }

    when (currentScreen) {
        is Screen.Home -> Home(
            hotels = hotels,
            images = images,
            ratings = ratings,
            howDaleko = howDaleko,
            bookdet = { currentScreen = Screen.BookDet },
            hotelName = hotelName
        )

        is Screen.BookDet -> BookDet ({ currentScreen = Screen.Home }, hotelName = hotelName)
    }
}

@Composable
fun Home(
    hotels: List<String>,
    images: List<Int>,
    ratings: List<Double>,
    howDaleko: List<Double>,
    bookdet: () -> Unit,
    hotelName: MutableState<String>
) {
    var searchItem by remember { mutableStateOf("") }

    val filteredHotels = hotels.filter { it.contains(searchItem, ignoreCase = true) }

    val statBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = statBarHeight)
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "The Alps' Hotel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Spacer(Modifier.width(8.dp))
                Image(
                    painterResource(R.drawable.france_national_flag),
                    contentDescription = "france"
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "pers"
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        TextField(
            value = searchItem,
            onValueChange = { searchItem = it },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            placeholder = { Text("Search a hotel name") }
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(filteredHotels.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 8.dp, bottom = 8.dp)
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(images[index]),
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                filteredHotels[index],
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    ratings[index].toString(),
                                    fontSize = 14.sp
                                )
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = "star",
                                    tint = Color.Yellow,
                                    modifier = Modifier
                                        .size(18.dp)
                                )
                                if (ratings[index] >= 4) {
                                    Icon(
                                        Icons.Rounded.Star,
                                        contentDescription = "star",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                            .size(18.dp)
                                    )
                                }
                                if (ratings[index] >= 6) {
                                    Icon(
                                        Icons.Rounded.Star,
                                        contentDescription = "star",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                            .size(18.dp)
                                    )
                                }
                                if (ratings[index] >= 8) {
                                    Icon(
                                        Icons.Rounded.Star,
                                        contentDescription = "star",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                            .size(18.dp)
                                    )
                                }
                                if (ratings[index] >= 10) {
                                    Icon(
                                        Icons.Rounded.Star,
                                        contentDescription = "star",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                            .size(18.dp)
                                    )
                                }
                            }
                            Text(
                                "${howDaleko[index]} km from Alps' ski lift",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(30.dp)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        bookdet()
                                        hotelName.value = filteredHotels[index]
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Book it",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookDet(back: () -> Unit, hotelName: MutableState<String>) {
    val statBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Column(
        modifier = Modifier
            .padding(top = statBarHeight)
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { back() }) {
                    Icon(
                        Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        contentDescription = "back",
                        modifier = Modifier
                            .size(48.dp)
                    )
                }
                Text(
                    "Booking"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Guest reviews")
            }
            Spacer(Modifier.width(32.dp))
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Room selection"
                )
            }
        }
        Spacer(Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                hotelName.value,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinaldayurauraurauraTheme {
        Greeting()
    }
}