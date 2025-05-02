package com.nameisjayant.androidpractise.ui.compose_navigation.navigation

import android.os.Parcelable
import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.nameisjayant.androidpractise.LocalNavigator
import com.nameisjayant.androidpractise.ui.compose_navigation.screens.HomeNavigationScreen
import com.nameisjayant.androidpractise.ui.compose_navigation.screens.NavigationDetailScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object FirstRoute

@Serializable
data class SecondRoute(
    val data: PersonData,
    val list: List<PersonData>
)

@Serializable
data class DetailScreen(
    val key: String,
    val data: List<PersonData>,

)

@Parcelize
@Serializable
data class PersonData(
    @SerialName("name")
    val name: String,
    @SerialName("age")
    val age: Int
) : Parcelable

@Serializable
data class ThirdScreen(
    val id: Int
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigationScreen(
    modifier: Modifier = Modifier
) {

    SharedTransitionLayout{
        NavHost(
            navController = LocalNavigator.current,
            startDestination = FirstRoute,
            modifier = modifier
        ) {
            composable<FirstRoute> {
                HomeNavigationScreen()
            }
            composable<SecondRoute>(
                typeMap = mapOf(
                    typeOf<PersonData>() to NavigationHelpers.parcelableType<PersonData>(),
                    typeOf<List<PersonData>>() to NavigationHelpers.parcelableListType<PersonData>(),
                )
            ) { navBackStackEntry ->
                val data = navBackStackEntry.toRoute<SecondRoute>().list
                NavigationDetailScreen(
                    data = data
                )
            }
        }
    }
}



@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = LocalNavigator.current,
        startDestination = FirstRoute,
        modifier = modifier
    ) {
        composable<FirstRoute>(
            deepLinks = listOf(navDeepLink<FirstRoute>("my-app://"))
        ) {
            HomeNavigationScreen()
        }
        composable<SecondRoute>(
            deepLinks = listOf(navDeepLink<SecondRoute>("my-app://second"))
        ) {
            Text(text = "Hello Second Screen")
        }
        composable<ThirdScreen>(
            deepLinks = listOf(navDeepLink<ThirdScreen>("my-app://third"))
        ) {

            Text(text = "Hello Third Screen ${it.toRoute<ThirdScreen>().id}", color = Color.Red)
        }
//        composable<DetailScreen>(
//            typeMap = mapOf(typeOf<List<PersonData>>() to NavigationHelpers.parcelableListType<PersonData>()),
//            deepLinks = listOf(
//                navDeepLink<DetailScreen>(
//                    "my-app://detail",
//                    typeMap = mapOf(typeOf<List<PersonData>>() to NavigationHelpers.parcelableListType<PersonData>()),
//                )
//            ),
//        ) {
//            val data = it.toRoute<DetailScreen>()
//            NavigationDetailScreen(
//                data = data
//            )

        // }
    }
}