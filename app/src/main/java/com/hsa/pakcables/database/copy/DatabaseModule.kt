package com.hsa.pakcables.database.copy
//
//import android.content.Context
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//class DatabaseModule {
//
//    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
//    @Provides
//    fun provideKlozerzDB(
//        @ApplicationContext app: Context
//    ) = Room.databaseBuilder(app,
//        KlozerzDB::class.java, KlozerzDB.databaseName
//    )
//        .addCallback(object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//
//            }
//        })
//        .fallbackToDestructiveMigration()
//        .allowMainThreadQueries()
//        .build() // The reason we can construct a database for the repo
//
//    @Singleton
//    @Provides
//    fun provideProfileDao(db: KlozerzDB) = db.profileDao()
//
//    @Singleton
//    @Provides
//    fun provideLeadAppointmentDao(db: KlozerzDB) = db.appointmentDao()
//
//    @Singleton
//    @Provides
//    fun provideCalenderDao(db: KlozerzDB) = db.calenderDao()
//
//    @Singleton
//    @Provides
//    fun provideLeasdDao(db: KlozerzDB) = db.leadsDao()
//
//    @Singleton
//    @Provides
//    fun provideProgressNotesDao(db: KlozerzDB) = db.leadsNotesDao()
//
//    @Singleton
//    @Provides
//    fun provideLeadPaginationDataDao(db: KlozerzDB) = db.leadPaginationDao()
//
//    @Singleton
//    @Provides
//    fun provideContactsDao(db: KlozerzDB) = db.contactsDao()
//
//    @Singleton
//    @Provides
//    fun provideContactsPaginationDataDao(db: KlozerzDB) = db.contactsPaginationDao()
//
//    @Singleton
//    @Provides
//    fun provideAccountsDao(db: KlozerzDB) = db.accountsDao()
//
//    @Singleton
//    @Provides
//    fun provideAccountsPaginationDataDao(db: KlozerzDB) = db.accountsPaginationDao()
//
//    @Singleton
//    @Provides
//    fun provideContactLinkedItemsDao(db: KlozerzDB) = db.contactLinkedItemsDao()
//
//    @Singleton
//    @Provides
//    fun provideLeadNotesPaginationDao(db: KlozerzDB) = db.leadNotesPaginationDao()
//
//    @Singleton
//    @Provides
//    fun provideUsersDao(db: KlozerzDB) = db.usersDao()
//
//    @Singleton
//    @Provides
//    fun provideAccountNotesDao(db: KlozerzDB) = db.accountNotesDao()
//
//    @Singleton
//    @Provides
//    fun provideAccountNotesPaginationDao(db: KlozerzDB) = db.accountNotesPaginationDao()
//
//    @Singleton
//    @Provides
//    fun provideAppointmentPaginationDao(db: KlozerzDB) = db.appointmentPaginationDao()
//
//}