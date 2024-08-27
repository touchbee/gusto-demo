package com.gusto.lunchmenu.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gusto.lunchmenu.R
import com.gusto.lunchmenu.data.model.LunchMenu
import com.gusto.lunchmenu.data.model.LunchMenuItem
import com.gusto.lunchmenu.data.model.displayString
import java.time.DayOfWeek

@Composable
fun LunchMenuWeekList(
    isThisWeek: Boolean,
    weekDay: DayOfWeek,
    lunchMenu: LunchMenu,
    modifier: Modifier = Modifier
) {
    @Composable
    fun DividerItem() {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.divider),
            contentDescription = stringResource(id = R.string.list_divider_image)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    @Composable
    fun MenuDayItem(
        isThisWeek: Boolean,
        isDayHighlighted: Boolean,
        @StringRes dayStringId: Int,
        menuItem: LunchMenuItem
    ) {
        Column(
            modifier = if (isThisWeek && isDayHighlighted) {
                Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            } else {
                Modifier.fillMaxWidth()
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = dayStringId),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                menuItem.displayString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuDayItem(
            isThisWeek = isThisWeek,
            isDayHighlighted = weekDay == DayOfWeek.MONDAY,
            dayStringId = R.string.weekday_monday,
            menuItem = lunchMenu.itemMonday
        )

        DividerItem()

        MenuDayItem(
            isThisWeek = isThisWeek,
            isDayHighlighted = weekDay == DayOfWeek.TUESDAY,
            dayStringId = R.string.weekday_tuesday,
            menuItem = lunchMenu.itemTuesday
        )

        DividerItem()

        MenuDayItem(
            isThisWeek = isThisWeek,
            isDayHighlighted = weekDay == DayOfWeek.WEDNESDAY,
            dayStringId = R.string.weekday_wednesday,
            menuItem = lunchMenu.itemWednesday
        )

        DividerItem()

        MenuDayItem(
            isThisWeek = isThisWeek,
            isDayHighlighted = weekDay == DayOfWeek.THURSDAY,
            dayStringId = R.string.weekday_thursday,
            menuItem = lunchMenu.itemThursday
        )

        DividerItem()

        MenuDayItem(
            isThisWeek = isThisWeek,
            isDayHighlighted = weekDay == DayOfWeek.FRIDAY,
            dayStringId = R.string.weekday_friday,
            menuItem = lunchMenu.itemFriday
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LunchMenuWeekListPreview() {

    LunchMenuWeekList(
        isThisWeek = false,
        weekDay = DayOfWeek.TUESDAY,
        lunchMenu = LunchMenu(
            itemMonday = LunchMenuItem(
                "Entree",
                "Sushi",
                "Dessert"
            ),
            itemTuesday = LunchMenuItem(
                "Entree",
                "Spaghetti",
                "Dessert"
            ),
            itemWednesday = LunchMenuItem(
                "Entree",
                "Pizza",
                "Dessert"
            ),
            itemThursday = LunchMenuItem(
                "Entree",
                "Burrito",
                "Dessert"
            ),
            itemFriday = LunchMenuItem(
                "Entree",
                "Bowl",
                "Dessert"
            )
        )
    )
}