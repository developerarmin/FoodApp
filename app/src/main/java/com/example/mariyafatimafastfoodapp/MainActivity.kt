package com.example.mariyafatimafastfoodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mariyafatimafastfoodapp.ui.theme.MariyaFatimaFastFoodAppTheme
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.NeuAttrs
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MariyaFatimaFastFoodAppTheme {
                LandingPage()
            }
        }
    }
}

@Composable
fun LandingPage() {
    val touchedNavigation = remember { mutableStateOf(0) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    Box(Modifier.fillMaxSize().background(Color.White), Alignment.BottomCenter) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.size(45.dp))
            TopIcons()
            Spacer(modifier = Modifier.size(30.dp))
            FastFoodTitle()
            Spacer(modifier = Modifier.size(10.dp))
            FoodMenuList()
            Spacer(modifier = Modifier.size(20.dp))
            FastFoodTabView(
                Modifier
                    .padding(horizontal = 40.dp)
                    .height(28.dp)
                    .fillMaxWidth(),
                categories = tabViewList
            ) { selectedTabIndex = it }
            Spacer(modifier = Modifier.size(15.dp))
            when (selectedTabIndex) {
                0 -> {
                    LazyColumn(
                        Modifier
                            .height((150 * foodInformation.size + 60).dp)
                            .fillMaxWidth()
                    ) {
                        items(foodInformation.size) {
                            Spacer(modifier = Modifier.size(10.dp))
                            SwipeRight(foodInformation[it])
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    }
                }
            }
        }
        BottomNavigationMenu(touchedNavigation)
    }
}

@Composable
fun TopIcons() {
    Row(Modifier.padding(horizontal = 40.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = R.drawable.three_point),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Icon(painter = painterResource(id = R.drawable.search),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun FastFoodTitle() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Black
                )
            ) { append("Fresh Healthy\n") }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) { append("Unique Taste") }
        },
        fontSize = 24.sp,
        modifier = Modifier.padding(horizontal = 40.dp)
    )
}

@Composable
fun FoodMenuList() {
    LazyRow(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        items(menuPainterList.size) {
            Box(Modifier.padding(
                        start = if (it == 0) 40.dp else 16.dp,
                        end = if (it == menuPainterList.size - 1) 40.dp else 0.dp,
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(Modifier.size(95.dp, 105.dp).clip(RoundedCornerShape(10.dp))
                        .background(brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFFFFDFD),
                                    Color(0xFFFFBDBE)
                                )
                            )
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(text = menuNameList[it], fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF840002),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )
                }
                Image(painter = painterResource(id = menuPainterList[it]),
                    contentDescription = "",
                    modifier = Modifier.padding(bottom = (35 + it * 10).dp)
                        .size(100.dp)
                )
            }
        }
    }
}


@Composable
fun FastFoodTabView(
    modifier: Modifier = Modifier,
    categories: List<String>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedTabIndex, backgroundColor = Color.Transparent,
        contentColor = Color.White, indicator = {}, modifier = modifier
    ) {
        categories.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
                modifier = Modifier.width(72.dp).fillMaxHeight()
                    .clip(CircleShape)
                    .background(
                        if (selectedTabIndex == index)
                            Color(0xFFF7F7F7)
                        else Color.Transparent
                    )
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = item, fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selectedTabIndex == index)
                                    Color(0xFF111111)
                                else Color(0xFF9E9E9E)
                    )
                }
            }
        }
    }
}


@Composable
fun UnderFoodLayout() {
    Row {
        Row(Modifier.padding(start = 40.dp).weight(1f)
                .height(115.dp).clip(RoundedCornerShape(22.dp))
                .background(Color(0xFFE36B6C)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(Modifier.weight(1f).fillMaxHeight()
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFFE63D3F))
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Cart",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "Favorite",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(2.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodLayout(swipeableState: SwipeableState<Int>, foodInformation: FoodInformation) {
    Box(Modifier.offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .fillMaxSize().clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFFDFDFD))
    ) {
        Row(
            Modifier.fillMaxWidth().height(115.dp)
                .padding(horizontal = 30.dp)
        ) {
            Image(
                painter = painterResource(id = foodInformation.painter),
                contentDescription = "", modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(Modifier.padding(vertical = 18.dp).fillMaxHeight()) {
                Text(text = foodInformation.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = foodInformation.description,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF6F6F6F),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "$" + foodInformation.price,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF111111)
                )
            }
        }
    }
}

@Composable
fun neuShadow() = NeuAttrs(
    lightShadowColor = Color.Transparent,
    darkShadowColor = Color(0x05000000),
    shadowElevation = 10.dp,
    lightSource = LightSource.LEFT_TOP,
    shape = Flat(RoundedCorner(22.dp))
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRight(foodInformation: FoodInformation) {
    val squareSize = 90.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 2)

    Row(
        modifier = Modifier
            .padding(end = 40.dp)
            .fillMaxWidth()
            .neu(neuShadow())
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    orientation = Orientation.Horizontal
                ),
            contentAlignment = Alignment.Center
        ) {
            UnderFoodLayout()
            FoodLayout(swipeableState, foodInformation)
        }
    }
}


@Composable
fun BottomNavigationMenu(touchedNav: MutableState<Int>) {
    Row(Modifier.padding(horizontal = 20.dp, vertical = 30.dp).fillMaxWidth()
            .height(72.dp).background(Color(0xFFE63D3F), CircleShape)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in navPainterList.indices) {
            val backgroundColor by animateColorAsState(
                targetValue = if (i == touchedNav.value) Color(0xFFE36B6C)
                                else Color.Transparent
            )
            val width by animateDpAsState(
                targetValue = if (i == touchedNav.value) 110.dp else 42.dp
            )
            Box(Modifier.clickable { touchedNav.value = i },
                contentAlignment = Alignment.Center
            ) {
                Row(Modifier.width(width).height(42.dp)
                        .background(backgroundColor, CircleShape),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(painter = painterResource(id = navPainterList[i]),
                        contentDescription = "", tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    if (i == touchedNav.value) {
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = navNameList[i], fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

