package com.example.startscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.startscreens.ui.theme.*
import com.example.startscreens.ui.theme.data.FruitData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartScreensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val items = ArrayList<FruitData>()

                    items.add(
                        FruitData(
                            R.drawable.fruits,
                            "WaterMelons are known ...",
                            "Have you wondered where to get source of watermelons? Emmutua " +
                                    "Green Grocer has many water melons to add vitamins to your body",
                            mainColor = ColorGreen,
                            backGroundColor = Color.White
                        ),
                    )
                    items.add(
                        FruitData(
                            R.drawable.eating,
                            "Men and Wemen are enjoying!!",
                            "We are here to link you with best sellers, with cheap and quality watermelons" +
                                    " We got you sorted",
                            mainColor = ColorBlue,
                            backGroundColor = Color.White
                        )
                    )
                    val pagerState = rememberPagerState(initialPage = 0)

                    WelcomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = ColorGreen), pagerState = pagerState, item = items
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    item: List<FruitData>
) {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(state = pagerState, count = item.size) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = item[page].backGroundColor),
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = item[page].image),
                        contentDescription = ""
                    )
                }
            }
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                elevation = 0.dp,
                shape = BoxShape.large,
                backgroundColor = item[pagerState.currentPage].backGroundColor
            ) {
                Box() {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PagerIndicator(
                            currentPage = pagerState.currentPage,
                            items = item
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp),
                            text = item[pagerState.currentPage].title,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = Poppins,
                            fontSize = 20.sp,
                            color = item[pagerState.currentPage].mainColor,
                            textAlign = TextAlign.Right
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp, top = 20.dp, end = 20.dp),
                            text = item[pagerState.currentPage].desc,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = Poppins,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier
                                .padding(30.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                if (pagerState.currentPage != 1) {
                                    TextButton(onClick = { /*TODO*/ }) {
                                        Text(
                                            text = "Skip Now",
                                            color = Color(0xFF292D32),
                                            textAlign = TextAlign.Right,
                                            fontFamily = Poppins,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            GlobalScope.launch(Dispatchers.Main) {
                                                pagerState.scrollToPage(
                                                    pagerState.currentPage + 1,
                                                    pageOffset = 0f
                                                )
                                            }
                                        },
                                        border = BorderStroke(
                                            14.dp,
                                            item[pagerState.currentPage].mainColor
                                        ),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = item[pagerState.currentPage].mainColor),
                                        modifier = Modifier.size(50.dp),
                                        shape = RoundedCornerShape(50),

                                        ) {
                                        Icon(
                                            tint = item[pagerState.currentPage].mainColor,
                                            painter = painterResource(id = R.drawable.arrow),
                                            contentDescription = ""
                                        )
                                    }
                                } else {
                                    //show get Started Button
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(backgroundColor = item[pagerState.currentPage].mainColor),
                                        elevation = ButtonDefaults.elevation(0.dp),
                                        contentPadding = PaddingValues(16.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Get Started",
                                            color = Color.White, fontSize = 16.sp
                                        )

                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    items: List<FruitData>
) {
    Row(
        modifier = Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(items.size) {
            PageIndicator(isSelected = it == currentPage, color = items[it].mainColor)
        }
    }
}

@Composable
fun PageIndicator(
    isSelected: Boolean,
    color: Color
) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) color else Color.Gray.copy(0.5f))
    )
}

