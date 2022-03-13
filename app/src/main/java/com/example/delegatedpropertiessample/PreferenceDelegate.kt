package com.example.delegatedpropertiessample

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 移譲先オブジェクトの最低条件.
 * - getValueオペレータが実装されていること
 * - mutableなプロパティの場合は
 *   setValueオペレータが実装されていること
 *
 * - Delegated propertiesの基本はgetValue/setValueオペレータ
 * - プロパティの情報はpropertyパラメータから参照可能
 * - thisRefパラメータはプロパティが宣言されているクラスのインスタンス
 *   - 特定の方に制限することも可能
 * - バインド時に処理やプロパティの検査等をしたいときはprovideDelegate
 */
class PreferenceDelegate(
    private val preference: SharedPreferences,
    private val key: String,
    private val default: String
) : ReadWriteProperty<PreferenceModel, String> {

    /**
     *
     * @param thisRef プロパティが宣言されているクラスインスタンス
     */
    override operator fun getValue(thisRef: PreferenceModel, property: KProperty<*>): String {
        // val preference = getPreferenceForClass(thisRef)
        // 利用側のプロパティ名をプリファレンスのキー名に設定する
        return preference.getString(key, default)!!
    }

    override operator fun setValue(
        thisRef: PreferenceModel,
        property: KProperty<*>,
        value: String
    ) {
        val editor = preference.edit()
        editor.putString(key, value)
        editor.apply()
    }
}