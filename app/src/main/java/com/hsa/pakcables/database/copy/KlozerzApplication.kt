package com.hsa.pakcables.database.copy
//
//import android.app.Application
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import dagger.hilt.android.HiltAndroidApp
//
//import com.google.gson.Gson
//import com.ssasoft.korlezz.Service.DataPostService
//import com.ssasoft.korlezz.models.ProfileModel
//import com.ssasoft.korlezz.db.DataRepository
//import com.ssasoft.korlezz.db.KlozerzDB
//
//@HiltAndroidApp
//class KlozerzApplication: Application(){
//
////    /**
////     * Create the singleton [ImageLoader].
////     * This is used by [rememberImagePainter] to load images in the app.
////     */
////    override fun newImageLoader(): ImageLoader {
////        return ImageLoader.Builder(this)
////            .componentRegistry {
////                add(UnsplashSizingInterceptor)
////            }
////            .build()
////    }
//
//
//    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
//        name = "user"
//    )
//    private val USER_EMAIL = stringPreferencesKey("user_email")
//    private lateinit var me: KlozerzApplication
//    override fun onCreate() {
//        super.onCreate()
//        me = this
//        KlozerzDB.init(applicationContext)
//        if(instance==null){
//            instance=this
//        }
//        DataRepository.init(getDatabase()!!)
//        val dataPostService = DataPostService(applicationContext)
//
//    }
//
//    companion object{
//        var instance:KlozerzApplication?=null
//    }
//
//    fun getDatabase(): KlozerzDB? {
//        return KlozerzDB.Companion.getInstance()
//    }
//
//    fun getRepository() : DataRepository?{
//        return DataRepository.Companion.getInstance()
//    }
//
//
//    fun saveProfile(profileModel: ProfileModel?, token: String?): Boolean {
//        val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("credentials", MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putString("profile", Gson().toJson(profileModel))
//        editor.putString("token", token)
//        return editor.commit()
//    }
//
//    fun getProfile(): ProfileModel {
//        val sharedPref: SharedPreferences = applicationContext.getSharedPreferences("credentials", MODE_PRIVATE)
//        val credentials = sharedPref.getString("profile", "")
//        return Gson().fromJson(credentials, ProfileModel::class.java)
//    }
//
//}