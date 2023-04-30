package com.hsa.pakcables.database.copy
//
//
//class DataRepository private constructor(private val mDatabase: KlozerzDB)
//{
//
//    fun getSyncCount():Int{
//        return mDatabase.syncDao().getSyncCount(mDatabase.profileDao().getUser().userId)
//    }
//
//    companion object {
//        private  var sInstance: DataRepository?=null
//
//        fun getInstance(): DataRepository? {
//            return sInstance
//        }
//
//        fun init(database: KlozerzDB){
//            synchronized(DataRepository::class.java) {
//                if (sInstance == null) {
//                    sInstance = DataRepository(database)
//                }
//            }
//        }
//
//    }
//
//}