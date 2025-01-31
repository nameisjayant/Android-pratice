package com.nameisjayant.androidpractise.ui.compose_navigation.navigation

import android.os.Parcelable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.nameisjayant.androidpractise.LocalNavigator
import com.nameisjayant.androidpractise.ui.compose_navigation.screens.HomeNavigationScreen
import com.nameisjayant.androidpractise.ui.compose_navigation.screens.NavigationDetailScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object FirstScreen

@Serializable
data class DetailScreen(
    val key: String,
    val data: List<PersonData>
)

@Serializable
@Parcelize
data class PersonData(
    val name: String,
    val age: Int
) : Parcelable

@Serializable
data object SecondRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = LocalNavigator.current,
        startDestination = FirstScreen,
        modifier = modifier
    ) {
        composable<FirstScreen>(
            deepLinks = listOf(navDeepLink<FirstScreen>("my-app://"))
        ) {
            HomeNavigationScreen()
        }
        composable<SecondRoute>(
            deepLinks = listOf(navDeepLink<SecondRoute>("my-app://second"))
        ) {
            Text(text = "Hello Second Screen")
        }
        composable<DetailScreen>(
            typeMap = mapOf(typeOf<List<PersonData>>() to NavigationHelpers.parcelableListType<PersonData>()),
            //   deepLinks = listOf(navDeepLink<DetailScreen>("my-app://detail/{id}")),
        ) {
            val data = it.toRoute<DetailScreen>()
            NavigationDetailScreen(
                data = data
            )
        }
    }
}