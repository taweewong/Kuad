package kmitl.taweewong.kuad.utilities

import android.content.Context.MODE_PRIVATE
import android.support.v7.app.AppCompatActivity

object SharedPreferenceUtil {
    private const val USER_PREFERENCE_NAME = "userPrefs"
    private const val USER_ID_PREFERENCE_KEY = "userIdKey"
    private const val USER_ID_NOT_FOUND = "user id not found"

    fun saveUserId(activity: AppCompatActivity, userId: String) {
        val editor = activity.getSharedPreferences(USER_PREFERENCE_NAME, MODE_PRIVATE).edit()
        editor.putString(USER_ID_PREFERENCE_KEY, userId)
        editor.apply()
    }

    fun retrieveUserId(activity: AppCompatActivity): String {
        val prefs = activity.getSharedPreferences(USER_PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(USER_ID_PREFERENCE_KEY, USER_ID_NOT_FOUND)
    }

    fun clearPreferences(activity: AppCompatActivity) {
        val editor = activity.getSharedPreferences(USER_PREFERENCE_NAME, MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}