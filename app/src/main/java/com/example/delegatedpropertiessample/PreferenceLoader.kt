package com.example.delegatedpropertiessample

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 委譲先クラスを提供するクラス.
 *
 * 都度SharedPreferencesを取得しないように、
 * 本クラスにて provideDelegateオペレータを使用.
 */
class PreferenceLoader(private val default: String) {

    operator fun provideDelegate(
        thisRef: PreferenceModel,
        property: KProperty<*>
    ): ReadWriteProperty<PreferenceModel, String> {
        return PreferenceDelegate(
            thisRef.context.getSharedPreferences(
                thisRef.javaClass.simpleName,
                Context.MODE_PRIVATE
            ),
            property.name,
            default
        )

    }
}