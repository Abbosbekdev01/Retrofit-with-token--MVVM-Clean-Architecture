package uz.abbosbek.codialtaskmusobaqa.data.localStorech

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object LocalStorage {
    /** Catch_file_name-> bu biz ma'lumotlarimiz saqlanadigan faly nomi  */
    private const val NAME = "Catch_file_name"
    private const val MODE = Context.MODE_PRIVATE


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor


    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
        editor = sharedPreferences.edit()
    }

    /*
     Save fun
     */

    fun saveToken(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    /*
    Get fun
     */
    fun getToken(): String {
        return sharedPreferences.getString(KEY_TOKEN, "").toString()
    }


    const val KEY_TOKEN = "KEYTOKEN"


}