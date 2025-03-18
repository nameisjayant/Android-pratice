package com.nameisjayant.androidpractise.ui.compose_navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nameisjayant.androidpractise.LocalNavigator
import com.nameisjayant.androidpractise.R
import com.nameisjayant.androidpractise.ui.compose_navigation.navigation.PersonData
import com.nameisjayant.androidpractise.ui.compose_navigation.navigation.SecondRoute

@Composable
fun HomeNavigationScreen(
    modifier: Modifier = Modifier
) {

    val data = """{
    "status": 1,
    "message": "success",
    "data": {
        "total_count": 60,
        "limit": 20,
        "page": 1,
        "total_pages": 3,
        "data": [
            {
                "order_id": 69967,
                "added_on": "31 Jan 25 13:16",
                "amount": 304,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1729_80d1dffd.pdf",
                "item_count": 1
            },
            {
                "order_id": 69966,
                "added_on": "31 Jan 25 13:12",
                "amount": 19,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1728_ea3c5d07.pdf",
                "item_count": 1
            },
            {
                "order_id": 69964,
                "added_on": "31 Jan 25 12:46",
                "amount": 5,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1727_4abf3dd4.pdf",
                "item_count": 1
            },
            {
                "order_id": 69963,
                "added_on": "31 Jan 25 11:42",
                "amount": 76,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1726_af06d408.pdf",
                "item_count": 1
            },
            {
                "order_id": 69962,
                "added_on": "31 Jan 25 11:40",
                "amount": 5,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1725_d2165499.pdf",
                "item_count": 1
            },
            {
                "order_id": 69961,
                "added_on": "31 Jan 25 11:39",
                "amount": 20,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1724_39c538f5.pdf",
                "item_count": 1
            },
            {
                "order_id": 69956,
                "added_on": "31 Jan 25 10:32",
                "amount": 19,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1722_00f16cb2.pdf",
                "item_count": 1
            },
            {
                "order_id": 69955,
                "added_on": "31 Jan 25 10:24",
                "amount": 20,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1721_4afcb653.pdf",
                "item_count": 1
            },
            {
                "order_id": 69954,
                "added_on": "31 Jan 25 10:24",
                "amount": 20,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1720_080774fd.pdf",
                "item_count": 1
            },
            {
                "order_id": 69953,
                "added_on": "31 Jan 25 10:22",
                "amount": 76,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1719_5fd942fd.pdf",
                "item_count": 1
            },
            {
                "order_id": 69945,
                "added_on": "28 Jan 25 17:02",
                "amount": 9,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1712_f117deec.pdf",
                "item_count": 1
            },
            {
                "order_id": 69944,
                "added_on": "28 Jan 25 16:45",
                "amount": 1,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1711_e9a1560d.pdf",
                "item_count": 1
            },
            {
                "order_id": 69943,
                "added_on": "28 Jan 25 16:44",
                "amount": 1,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1710_b11b5a9f.pdf",
                "item_count": 1
            },
            {
                "order_id": 69942,
                "added_on": "28 Jan 25 16:40",
                "amount": 38,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1709_394a31f5.pdf",
                "item_count": 1
            },
            {
                "order_id": 69941,
                "added_on": "28 Jan 25 15:47",
                "amount": 541,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1708_86d5ed75.pdf",
                "item_count": 2
            },
            {
                "order_id": 69940,
                "added_on": "28 Jan 25 15:45",
                "amount": 20,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1707_4e79f894.pdf",
                "item_count": 1
            },
            {
                "order_id": 69939,
                "added_on": "28 Jan 25 15:38",
                "amount": 1,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1706_a358a57a.pdf",
                "item_count": 1
            },
            {
                "order_id": 69938,
                "added_on": "28 Jan 25 15:37",
                "amount": 1,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1705_59b4b531.pdf",
                "item_count": 1
            },
            {
                "order_id": 69937,
                "added_on": "28 Jan 25 15:34",
                "amount": 4,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1704_767277da.pdf",
                "item_count": 1
            },
            {
                "order_id": 69936,
                "added_on": "28 Jan 25 15:31",
                "amount": 19,
                "invoice_url": "https://awfis-live-data.s3.ap-south-1.amazonaws.com/FB/1703_060fe7bd.pdf",
                "item_count": 1
            }
        ]
    }
}
    """
    val navigator = LocalNavigator.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navigator.navigate(
                SecondRoute(
                    data = PersonData(
                        name = "Jayant",
                        age = 26
                    ),
                    list = listOf(
                        PersonData(
                            name = "Jayant",
                            age = 26
                        ),
                        PersonData(
                            name = "Mayank",
                            age = 30
                        )
                    )
                )
            )
        }) {
            Text(text = stringResource(R.string.move_to_detail_screen))
        }
    }

}