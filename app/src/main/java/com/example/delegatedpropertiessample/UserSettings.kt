package com.example.delegatedpropertiessample

import android.content.Context

class UserSettings(override val context: Context) : PreferenceModel {
    // property.nameはuserName
    var userName by PreferenceLoader("userName default value")
    var iconUrl by PreferenceLoader("iconUrl default value")
}