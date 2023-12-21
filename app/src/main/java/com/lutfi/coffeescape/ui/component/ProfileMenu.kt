package com.lutfi.coffeescape.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lutfi.coffeescape.R

sealed class ProfileMenuItem(
    val title: String,
    val icon: Int,
    val onClick: () -> Unit
)

object ProfileInformation : ProfileMenuItem("Profile Information", R.drawable.baseline_perm_contact_calendar_24, onClick = {})
object LanguageItem : ProfileMenuItem("Language", R.drawable.baseline_g_translate_24, onClick = {})
object ThemeItem : ProfileMenuItem("Theme", R.drawable.baseline_light_mode_24,onClick = {})
object AboutItem : ProfileMenuItem("About", R.drawable.baseline_info_24,onClick = {})
object PrivacyPolicyItem : ProfileMenuItem("Privacy Policy", R.drawable.baseline_privacy_tip_24, onClick = {})
@Composable
fun ProfileMenuContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ProfileMenuItemRow(ProfileInformation)
        Divider()
        ProfileMenuItemRow(LanguageItem)
        Divider()
        ProfileMenuItemRow(ThemeItem)
        Divider()
        ProfileMenuItemRow(AboutItem)
        Divider()
        ProfileMenuItemRow(PrivacyPolicyItem)
    }
}

@Composable
fun ProfileMenuItemRow(menuItem: ProfileMenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { menuItem.onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = menuItem.icon),
            contentDescription = "Navigate",
            tint = Color.Gray,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        BasicText(
            text = menuItem.title,
            modifier = Modifier
                .weight(5f)
                .padding(end = 16.dp),
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Navigate",
            tint = Color.Gray,
            modifier = Modifier.weight(1f)
        )
    }
}