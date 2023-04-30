package com.hsa.pakcables.database.copy
//
//import android.content.Context
//import androidx.room.*
//import androidx.room.migration.Migration
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.ssasoft.korlezz.data.History
//import com.ssasoft.korlezz.models.*
//import com.ssasoft.korlezz.util.Converters
//import com.ssasoft.korlezz.util.ListConverter
//
//@Database(entities = arrayOf(
//    Appointment::class, ProfileModel::class,
//    Lead::class, RoleModel::class, SyncStatus::class, LeadPaginationDataModel::class,
//    Contacts::class, ContactPaginationDataModel::class, Account::class, AccountPaginationModel::class,
//    ContactLinkedItems::class, LeadNotes::class, LeadNotesPaginationDataModel::class, Users::class,
//    AccountNotes::class, AccountNotesPaginationDataModel::class, AppointmentPaginationModel::class),
//    version =244, exportSchema = true)
//@TypeConverters(Converters::class, ListConverter::class)
//abstract class KlozerzDB : RoomDatabase() {
//
//    abstract fun appointmentDao(): AppointmentDao
//    abstract fun profileDao(): ProfileDao
//    abstract fun calenderDao(): CalenderDao
//    abstract fun leadsDao(): LeadsDao
//    abstract fun syncDao(): SyncDao
//    abstract fun leadsNotesDao(): LeadsNotesDao
//    abstract fun leadPaginationDao(): LeadPaginationDao
//    abstract fun contactsDao(): ContactsDao
//    abstract fun contactsPaginationDao(): ContactsPaginationDao
//    abstract fun accountsDao():AccountDao
//    abstract fun accountsPaginationDao():AccountPaginationDao
//    abstract fun contactLinkedItemsDao():ContactLinkedItemsDao
//    abstract fun leadNotesPaginationDao():LeadNotesPaginationDao
//    abstract fun usersDao():UsersDao
//    abstract fun accountNotesDao():AccountsNotesDao
//    abstract fun accountNotesPaginationDao():AccountNotesPaginationDao
//    abstract fun appointmentPaginationDao():AppointmentPaginationDao
//
//
//    companion object {
//        val databaseName = "KlozerzDB"
//        var db: KlozerzDB? = null
//
//
//        val MigrationNew = object : Migration(230, 231) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }
//
//        fun getInstance(): KlozerzDB {
//
//            return db!!
//        }
//
//        fun init(context:Context)
//        {
//            if(db==null)
//            {
//                db = Room.databaseBuilder(context, KlozerzDB::class.java, databaseName)
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
////                    .addMigrations(MigrationNew)
//                    .build()
//            }
//        }
//    }
//
//
//
//
//    private class Migration225To226(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
//
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `Appointment2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                    " `serverId` INTEGER, `moduleId` INTEGER NOT NULL, `moduleName` TEXT NOT NULL, `appointmentType` TEXT, `userId` TEXT NOT NULL," +
//                    " `rating` TEXT, `facilityName` TEXT, `subject` TEXT NOT NULL, `archived_by` TEXT, `archivedStatus` TEXT," +
//                    " `taskNote` TEXT NOT NULL, `status` TEXT NOT NULL, `periority` TEXT NOT NULL, `gps` TEXT NOT NULL, `createdBy` TEXT," +
//                    " `modifiedby` TEXT, `modefiedDate` TEXT, `createdDate` TEXT, `taskDate` TEXT NOT NULL, `taskEnd` TEXT NOT NULL," +
//                    " `orgTaskDate` TEXT, `orgTaskEnd` TEXT, `parentId` TEXT NOT NULL, `timeZone` TEXT NOT NULL, `updateAll` INTEGER," +
//                    " `recurrenceId` TEXT, `userName` TEXT NOT NULL, `address` TEXT NOT NULL, `profileZone` TEXT NOT NULL, `attendeesEmail` TEXT," +
//                    " `title` TEXT NOT NULL, `errorCode` INTEGER, `errorMessage` TEXT, `isSynchronized` INTEGER, `eventId` INTEGER," +
//                    " `isUpdate` INTEGER, `recurrenceFlag` TEXT, `recurrenceStartDate` TEXT, `recurrenceStartTime` TEXT, `recurrenceEndTime` TEXT," +
//                    " `recurrenceDuration` TEXT, `recurrencePattern` TEXT, `recurrencePatternSub` TEXT, `recurrenceDaysCount` TEXT," +
//                    " `recurrenceMeetingDates` TEXT, `recurrenceEndDateOption` TEXT, `recurrenceEndDate` TEXT, `recurrenceWeekDays` TEXT," +
//                    " `recurrenceWeeksCount` TEXT, `recurrenceMonthType` TEXT, `recurrenceMonthCount` TEXT, `recurrenceMonthDate` TEXT)")
//            database.execSQL("INSERT INTO Appointment2 (`localId`,`serverId`,`moduleId`,`moduleName`,`appointmentType`,`userId`,`rating`," +
//                    "`facilityName`,`subject`,`archived_by`,`archivedStatus`,`taskNote`,`status`,`periority`,`gps`,`createdBy`,`modifiedby`," +
//                    "`modefiedDate`,`createdDate`,`taskDate`,`taskEnd`,`orgTaskDate`,`orgTaskEnd`,`parentId`,`timeZone`,`updateAll`,`recurrenceId`," +
//                    "`userName`,`address`,`profileZone`,`attendeesEmail`,`title`,`errorCode`,`errorMessage`,`isSynchronized`,`eventId`,`isUpdate`," +
//                    "`recurrenceFlag`,`recurrenceStartDate`,`recurrenceStartTime`,`recurrenceEndTime`,`recurrenceDuration`,`recurrencePattern`," +
//                    "`recurrencePatternSub`,`recurrenceDaysCount`,`recurrenceMeetingDates`,`recurrenceEndDateOption`,`recurrenceEndDate`," +
//                    "`recurrenceWeekDays`,`recurrenceWeeksCount`,`recurrenceMonthType`,`recurrenceMonthCount`,`recurrenceMonthDate`) " +
//                    "Select `localId`,`serverId`,`moduleId`,`moduleName`,`appointmentType`,`userId`,`rating`,"+
//                    "`facilityName`,`subject`,`archived_by`,`archivedStatus`,`taskNote`,`status`,`periority`,`gps`,`createdBy`,`modifiedby`," +
//                    "`modefiedDate`,`createdDate`,`taskDate`,`taskEnd`,`orgTaskDate`,`orgTaskEnd`,`parentId`,`timeZone`,`updateAll`,`recurrenceId`,"+
//                    "`userName`,`address`,`profileZone`,`attendeesEmail`,`title`,`errorCode`,`errorMessage`,`isSynchronized`,`eventId`,`isUpdate`," +
//                    "`recurrenceFlag`,`recurrenceStartDate`,`recurrenceStartTime`,`recurrenceEndTime`,`recurrenceDuration`,`recurrencePattern`," +
//                    "`recurrencePatternSub`,`recurrenceDaysCount`,`recurrenceMeetingDates`,`recurrenceEndDateOption`,`recurrenceEndDate`," +
//                    "`recurrenceWeekDays`,`recurrenceWeeksCount`,`recurrenceMonthType`,`recurrenceMonthCount`,`recurrenceMonthDate` From Appointment ")
//            database.execSQL("DROP TABLE Appointment")
//            database.execSQL("Alter table Appointment2 RENAME TO Appointment")
//            database.execSQL("CREATE INDEX IF NOT EXISTS `index_Appointment_serverId` ON `Appointment` (`serverId`)")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `ProfileModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                    " `userId` INTEGER NOT NULL, `firstName` TEXT NOT NULL, `middleName` TEXT NOT NULL, `lastName` TEXT NOT NULL," +
//                    " `emailAddress` TEXT NOT NULL, `companyId` INTEGER NOT NULL, `roleModelList` TEXT NOT NULL, `subUserIds` TEXT NOT NULL," +
//                    " `sessionId` TEXT NOT NULL, `timeZoneId` TEXT NOT NULL, `isSignedIn` INTEGER NOT NULL, `loginProcess` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO ProfileModel2 (`id`,`userId`,`firstName`,`middleName`,`lastName`,`emailAddress`,`companyId`," +
//                    "`roleModelList`,`subUserIds`,`sessionId`,`timeZoneId`,`isSignedIn`,`loginProcess`) Select `id`,`userId`,`firstName`," +
//                    "`middleName`,`lastName`,`emailAddress`,`companyId`," +
//                    "`roleModelList`,`subUserIds`,`sessionId`,`timeZoneId`,`isSignedIn`,`loginProcess` From ProfileModel")
//            database.execSQL("DROP TABLE ProfileModel")
//            database.execSQL("Alter table ProfileModel2 RENAME TO ProfileModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `Lead2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `serverID` TEXT NOT NULL," +
//                    "`name` TEXT NOT NULL, `type` TEXT NOT NULL, `subType` TEXT NOT NULL, `nPI` TEXT NOT NULL, `address1` TEXT NOT NULL," +
//                    "`address2` TEXT NOT NULL, `city` TEXT NOT NULL, `state` TEXT NOT NULL, `zipCode` TEXT NOT NULL, `phone` TEXT NOT NULL," +
//                    "`fax` TEXT NOT NULL, `email` TEXT NOT NULL, `website` TEXT NOT NULL, `howcanIearnyourtrustandbusiness` TEXT NOT NULL," +
//                    "`leadSource` TEXT NOT NULL, `rating` TEXT NOT NULL, `status` TEXT NOT NULL, `payorType` TEXT NOT NULL," +
//                    "`gpsPunch` TEXT NOT NULL, `created_by` TEXT NOT NULL, `modified_by` TEXT NOT NULL, `user_id` TEXT NOT NULL," +
//                    "`suggestedFrequency` TEXT NOT NULL, `biggestchallengeswithagenciesyoureutilizing` TEXT NOT NULL," +
//                    "`yourpatienthavefrequentvisittoER` TEXT NOT NULL, `ervisitscouldbeavoidedbyanursecall` TEXT NOT NULL," +
//                    "`appointmentscanceledduetotransportation` TEXT NOT NULL, `patientscalltheofficemorethanonceperweek` TEXT NOT NULL," +
//                    "`themostimportantthingtoconsiderduringagencyreference` TEXT NOT NULL, `howcanImakeyourjobeasier` TEXT NOT NULL," +
//                    "`whatthebesttimeformetocomeandvisit` TEXT NOT NULL, `patientsyouseeperdayonanaverage` TEXT NOT NULL," +
//                    "`howoftendoyouuseHomeHealthpermonthonaverage` TEXT NOT NULL, `doyouwanttogiveusachancetoshowwhatwecando` TEXT NOT NULL," +
//                    "`howoftendoyoushiftfornursing` TEXT NOT NULL, `howoftenyourefertoprivateduty` TEXT NOT NULL," +
//                    "`howoftendoyourefertotransportation` TEXT NOT NULL, `createdDate` TEXT NOT NULL, `modifiedDate` TEXT NOT NULL," +
//                    "`suggestedRating` TEXT NOT NULL, `leadToAccount` TEXT NOT NULL, `contactRole` TEXT NOT NULL, `archiveStatus` TEXT NOT NULL," +
//                    "`archivedBy` TEXT NOT NULL, `progressNoteLastModifiedDate` TEXT NOT NULL, `officeHours` TEXT NOT NULL," +
//                    "`errorCode` INTEGER NOT NULL, `errorMessage` TEXT NOT NULL, `dataType` TEXT NOT NULL, `isSynchronized` INTEGER NOT NULL)")
//            database.execSQL("INSERT INTO Lead2 (`localId`,`serverID`,`name`,`type`,`subType`,`nPI`,`address1`," +
//                    "`address2`,`city`,`state`,`zipCode`,`phone`,`fax`,`email`,`website`,`howcanIearnyourtrustandbusiness`,`leadSource`," +
//                    "`rating`,`status`,`payorType`,`gpsPunch`,`created_by`,`modified_by`,`user_id`,`suggestedFrequency`," +
//                    "`biggestchallengeswithagenciesyoureutilizing`,`yourpatienthavefrequentvisittoER`,`ervisitscouldbeavoidedbyanursecall`," +
//                    "`appointmentscanceledduetotransportation`,`patientscalltheofficemorethanonceperweek`,`themostimportantthingtoconsiderduringagencyreference`," +
//                    "`howcanImakeyourjobeasier`,`whatthebesttimeformetocomeandvisit`,`patientsyouseeperdayonanaverage`," +
//                    "`howoftendoyouuseHomeHealthpermonthonaverage`,`doyouwanttogiveusachancetoshowwhatwecando`,`howoftendoyoushiftfornursing`," +
//                    "`howoftenyourefertoprivateduty`,`howoftendoyourefertotransportation`,`createdDate`,`modifiedDate`,`suggestedRating`," +
//                    "`leadToAccount`,`contactRole`,`archiveStatus`,`archivedBy`,`progressNoteLastModifiedDate`," +
//                    "`officeHours`,`errorCode`,`errorMessage`,`dataType`,`isSynchronized`)" +
//                    "Select `localId`,`serverID`,`name`,`type`,`subType`,`nPI`,`address1`," +
//                    "`address2`,`city`,`state`,`zipCode`,`phone`,`fax`,`email`,`website`,`howcanIearnyourtrustandbusiness`,`leadSource`," +
//                    "`rating`,`status`,`payorType`,`gpsPunch`,`created_by`,`modified_by`,`user_id`,`suggestedFrequency`," +
//                    "`biggestchallengeswithagenciesyoureutilizing`,`yourpatienthavefrequentvisittoER`,`ervisitscouldbeavoidedbyanursecall`," +
//                    "`appointmentscanceledduetotransportation`,`patientscalltheofficemorethanonceperweek`,`themostimportantthingtoconsiderduringagencyreference`," +
//                    "`howcanImakeyourjobeasier`,`whatthebesttimeformetocomeandvisit`,`patientsyouseeperdayonanaverage`," +
//                    "`howoftendoyouuseHomeHealthpermonthonaverage`,`doyouwanttogiveusachancetoshowwhatwecando`,`howoftendoyoushiftfornursing`," +
//                    "`howoftenyourefertoprivateduty`,`howoftendoyourefertotransportation`,`createdDate`,`modifiedDate`,`suggestedRating`," +
//                    "`leadToAccount`,`contactRole`,`archiveStatus`,`archivedBy`,`progressNoteLastModifiedDate`," +
//                    "`officeHours`,`errorCode`,`errorMessage`,`dataType`,`isSynchronized` From Lead ")
//            database.execSQL("DROP TABLE Lead")
//            database.execSQL("Alter table Lead2 RENAME TO Lead")
//            database.execSQL("CREATE INDEX IF NOT EXISTS `index_Lead_name_phone_user_id` ON `Lead` (`name`, `phone`, `user_id`)")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `RoleModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                    " `roleId` INTEGER NOT NULL, `roleType` TEXT NOT NULL, `userId` INTEGER NOT NULL)")
//            database.execSQL("INSERT INTO RoleModel2 (`id`,`roleId`,`roleType`,`userId`) Select `id`,`roleId`,`roleType`,`userId` From RoleModel")
//            database.execSQL("DROP TABLE RoleModel")
//            database.execSQL("Alter table RoleModel2 RENAME TO RoleModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `SyncStatus2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`parentTableId` INTEGER NOT NULL, `requestType` TEXT NOT NULL, `noOfHits` INTEGER NOT NULL, `effectedDate` TEXT NOT NULL, " +
//                    "`status` TEXT NOT NULL, `priority` INTEGER NOT NULL, `requestTypeString` TEXT NOT NULL, `name` TEXT NOT NULL, " +
//                    "`operation` TEXT NOT NULL, `userId` INTEGER NOT NULL, `hasError` INTEGER NOT NULL, `errorCode` INTEGER NOT NULL, " +
//                    "`errorDetail` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO SyncStatus2 (`id`,`parentTableId`,`requestType`,`noOfHits`,`effectedDate`,`status`,`priority`," +
//                    "`requestTypeString`,`name`,`operation`,`userId`,`hasError`,`errorCode`,`errorDetail`) Select `id`,`parentTableId`,`requestType`," +
//                    "`noOfHits`,`effectedDate`,`status`,`priority`,`requestTypeString`,`name`,`operation`,`userId`,`hasError`,`errorCode`," +
//                    "`errorDetail` From SyncStatus")
//            database.execSQL("DROP TABLE SyncStatus")
//            database.execSQL("Alter table SyncStatus2 RENAME TO SyncStatus")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `LeadPaginationDataModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER, `currentPage` INTEGER, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, `startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO LeadPaginationDataModel2 (`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From LeadPaginationDataModel")
//            database.execSQL("DROP TABLE LeadPaginationDataModel")
//            database.execSQL("Alter table LeadPaginationDataModel2 RENAME TO LeadPaginationDataModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `Contacts2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`serverId` INTEGER NOT NULL, `fname` TEXT NOT NULL, `lname` TEXT NOT NULL, `leadName` TEXT NOT NULL, " +
//                    "`userName` TEXT NOT NULL, `contactRole` TEXT NOT NULL, `moduleName` TEXT NOT NULL, `address` TEXT NOT NULL, " +
//                    "`phone` TEXT NOT NULL, `email` TEXT NOT NULL, `mailingStreet` TEXT NOT NULL, `landLine` TEXT NOT NULL, " +
//                    "`mailingCity` TEXT NOT NULL, `mailingState` TEXT NOT NULL, `mailingZipCode` TEXT NOT NULL, `mailingCountry` TEXT NOT NULL, " +
//                    "`decisionMaker` TEXT NOT NULL, `contactIdsAsid` TEXT NOT NULL, `accountId` TEXT NOT NULL, `leadId` TEXT NOT NULL, " +
//                    "`userId` TEXT NOT NULL, `dob` TEXT NOT NULL, `moduleId` TEXT NOT NULL, `linkedItemId` TEXT NOT NULL, " +
//                    "`createdBy` TEXT NOT NULL, `createdDate` TEXT NOT NULL, `modifiedBy` TEXT NOT NULL, `modifiedDate` TEXT NOT NULL, " +
//                    "`archivedBy` TEXT NOT NULL, `archivedDate` TEXT NOT NULL, `archivedStatus` TEXT NOT NULL, `errorCode` INTEGER NOT NULL, " +
//                    "`errorMessage` TEXT NOT NULL, `isSynchronized` INTEGER NOT NULL)")
//            database.execSQL("INSERT INTO Contacts2 (`localId`,`serverId`,`fname`,`lname`,`leadName`,`userName`,`contactRole`,`moduleName`," +
//                    "`address`,`phone`,`email`,`mailingStreet`,`landLine`,`mailingCity`,`mailingState`,`mailingZipCode`,`mailingCountry`," +
//                    "`decisionMaker`,`contactIdsAsid`,`accountId`,`leadId`,`userId`,`dob`,`moduleId`,`linkedItemId`,`createdBy`,`createdDate`," +
//                    "`modifiedBy`,`modifiedDate`,`archivedBy`,`archivedDate`,`archivedStatus`,`errorCode`,`errorMessage`,`isSynchronized`)" +
//                    "Select `localId`,`serverId`,`fname`,`lname`,`leadName`,`userName`,`contactRole`,`moduleName`," +
//                    "`address`,`phone`,`email`,`mailingStreet`,`landLine`,`mailingCity`,`mailingState`,`mailingZipCode`,`mailingCountry`," +
//                    "`decisionMaker`,`contactIdsAsid`,`accountId`,`leadId`,`userId`,`dob`,`moduleId`,`linkedItemId`,`createdBy`,`createdDate`," +
//                    "`modifiedBy`,`modifiedDate`,`archivedBy`,`archivedDate`,`archivedStatus`,`errorCode`,`errorMessage`,`isSynchronized` From Contacts")
//            database.execSQL("DROP TABLE Contacts")
//            database.execSQL("Alter table Contacts2 RENAME TO Contacts")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `ContactPaginationDataModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO ContactPaginationDataModel2 (`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From ContactPaginationDataModel")
//            database.execSQL("DROP TABLE ContactPaginationDataModel")
//            database.execSQL("Alter table ContactPaginationDataModel2 RENAME TO ContactPaginationDataModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `Account2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`serverID` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `subType` TEXT NOT NULL, `nPI` TEXT NOT NULL, " +
//                    "`address1` TEXT NOT NULL, `address2` TEXT NOT NULL, `city` TEXT NOT NULL, `state` TEXT NOT NULL, `zipCode` TEXT NOT NULL, " +
//                    "`phone` TEXT NOT NULL, `fax` TEXT NOT NULL, `email` TEXT NOT NULL, `website` TEXT NOT NULL, " +
//                    "`howcanIearnyourtrustandbusiness` TEXT NOT NULL, `leadSource` TEXT NOT NULL, `rating` TEXT NOT NULL, `status` TEXT NOT NULL, " +
//                    "`payorType` TEXT NOT NULL, `gpsPunch` TEXT NOT NULL, `created_by` TEXT NOT NULL, `modified_by` TEXT NOT NULL, " +
//                    "`user_id` TEXT NOT NULL, `suggestedFrequency` TEXT NOT NULL, `biggestchallengeswithagenciesyoureutilizing` TEXT NOT NULL, " +
//                    "`yourpatienthavefrequentvisittoER` TEXT NOT NULL, `ervisitscouldbeavoidedbyanursecall` TEXT NOT NULL, " +
//                    "`appointmentscanceledduetotransportation` TEXT NOT NULL, `patientscalltheofficemorethanonceperweek` TEXT NOT NULL, " +
//                    "`themostimportantthingtoconsiderduringagencyreference` TEXT NOT NULL, `howcanImakeyourjobeasier` TEXT NOT NULL, " +
//                    "`whatthebesttimeformetocomeandvisit` TEXT NOT NULL, `patientsyouseeperdayonanaverage` TEXT NOT NULL, " +
//                    "`howoftendoyouuseHomeHealthpermonthonaverage` TEXT NOT NULL, `doyouwanttogiveusachancetoshowwhatwecando` TEXT NOT NULL, " +
//                    "`howoftendoyoushiftfornursing` TEXT NOT NULL, `howoftenyourefertoprivateduty` TEXT NOT NULL, " +
//                    "`howoftendoyourefertotransportation` TEXT NOT NULL, `createdDate` TEXT NOT NULL, `modifiedDate` TEXT NOT NULL, " +
//                    "`suggestedRating` TEXT NOT NULL, `leadToAccount` TEXT NOT NULL, `contactRole` TEXT NOT NULL, `archiveStatus` TEXT NOT NULL, " +
//                    "`archivedBy` TEXT NOT NULL, `progressNoteLastModifiedDate` TEXT NOT NULL, `officeHours` TEXT NOT NULL, `leadId` TEXT, " +
//                    "`otherSubType` TEXT, `errorCode` INTEGER NOT NULL, `errorMessage` TEXT NOT NULL, `dataType` TEXT NOT NULL, " +
//                    "`isSynchronized` INTEGER NOT NULL)")
//            database.execSQL("INSERT INTO Account2 (`localId`,`serverID`,`name`,`type`,`subType`,`nPI`,`address1`,`address2`,`city`,`state`," +
//                    "`zipCode`,`phone`,`fax`,`email`,`website`,`howcanIearnyourtrustandbusiness`,`leadSource`,`rating`,`status`,`payorType`," +
//                    "`gpsPunch`,`created_by`,`modified_by`,`user_id`,`suggestedFrequency`,`biggestchallengeswithagenciesyoureutilizing`," +
//                    "`yourpatienthavefrequentvisittoER`,`ervisitscouldbeavoidedbyanursecall`,`appointmentscanceledduetotransportation`," +
//                    "`patientscalltheofficemorethanonceperweek`,`themostimportantthingtoconsiderduringagencyreference`,`howcanImakeyourjobeasier`," +
//                    "`whatthebesttimeformetocomeandvisit`,`patientsyouseeperdayonanaverage`,`howoftendoyouuseHomeHealthpermonthonaverage`," +
//                    "`doyouwanttogiveusachancetoshowwhatwecando`,`howoftendoyoushiftfornursing`,`howoftenyourefertoprivateduty`," +
//                    "`howoftendoyourefertotransportation`,`createdDate`,`modifiedDate`,`suggestedRating`,`leadToAccount`,`contactRole`," +
//                    "`archiveStatus`,`archivedBy`,`progressNoteLastModifiedDate`,`officeHours`,`leadId`,`otherSubType`,`errorCode`,`errorMessage`," +
//                    "`dataType`,`isSynchronized`) " +
//                    "Select `localId`,`serverID`,`name`,`type`,`subType`,`nPI`,`address1`,`address2`,`city`,`state`,`zipCode`,`phone`,`fax`," +
//                    "`email`,`website`,`howcanIearnyourtrustandbusiness`,`leadSource`,`rating`,`status`,`payorType`,`gpsPunch`,`created_by`," +
//                    "`modified_by`,`user_id`,`suggestedFrequency`,`biggestchallengeswithagenciesyoureutilizing`,`yourpatienthavefrequentvisittoER`," +
//                    "`ervisitscouldbeavoidedbyanursecall`,`appointmentscanceledduetotransportation`,`patientscalltheofficemorethanonceperweek`," +
//                    "`themostimportantthingtoconsiderduringagencyreference`,`howcanImakeyourjobeasier`,`whatthebesttimeformetocomeandvisit`," +
//                    "`patientsyouseeperdayonanaverage`,`howoftendoyouuseHomeHealthpermonthonaverage`,`doyouwanttogiveusachancetoshowwhatwecando`," +
//                    "`howoftendoyoushiftfornursing`,`howoftenyourefertoprivateduty`,`howoftendoyourefertotransportation`,`createdDate`," +
//                    "`modifiedDate`,`suggestedRating`,`leadToAccount`,`contactRole`,`archiveStatus`,`archivedBy`,`progressNoteLastModifiedDate`," +
//                    "`officeHours`,`leadId`,`otherSubType`,`errorCode`,`errorMessage`,`dataType`,`isSynchronized` From Account ")
//            database.execSQL("DROP TABLE Account")
//            database.execSQL("Alter table Account2 RENAME TO Account")
//            database.execSQL("CREATE INDEX IF NOT EXISTS `index_Account_name_phone` ON `Account` (`name`, `phone`)")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `AccountPaginationModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO AccountPaginationModel2(`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From AccountPaginationModel")
//            database.execSQL("DROP TABLE AccountPaginationModel")
//            database.execSQL("Alter table AccountPaginationModel2 RENAME TO AccountPaginationModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `ContactLinkedItems2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`serverId` TEXT NOT NULL, `moduleName` TEXT NOT NULL, `moduleId` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`parentContactLocalId` INTEGER NOT NULL, `parentContactServerId` INTEGER NOT NULL, `errorCode` INTEGER NOT NULL, " +
//                    "`errorMessage` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO ContactLinkedItems2(`localId`,`serverId`,`moduleName`,`moduleId`,`userId`,`parentContactLocalId`," +
//                    "`parentContactServerId`,`errorCode`,`errorMessage`)" +
//                    "Select `localId`,`serverId`,`moduleName`,`moduleId`,`userId`,`parentContactLocalId`," +
//                    "`parentContactServerId`,`errorCode`,`errorMessage` From ContactLinkedItems")
//            database.execSQL("DROP TABLE ContactLinkedItems")
//            database.execSQL("Alter table ContactLinkedItems2 RENAME TO ContactLinkedItems")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `LeadNotes2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`serverId` INTEGER NOT NULL, `moduleId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `subject` TEXT NOT NULL, " +
//                    "`visitNote` TEXT NOT NULL, `personalVisit` TEXT NOT NULL, `noteDate` TEXT NOT NULL, `modifiedby` TEXT NOT NULL, " +
//                    "`created_by` TEXT NOT NULL, `archive_status` TEXT NOT NULL, `gps` TEXT NOT NULL, `faciltyName` TEXT NOT NULL, " +
//                    "`dateTimep` TEXT NOT NULL, `noteTime` TEXT NOT NULL, `moduleName` TEXT NOT NULL, `created_date_count` TEXT NOT NULL, " +
//                    "`modified_date_count` TEXT NOT NULL, `modified_date` TEXT NOT NULL, `created_date` TEXT, `errorCode` INTEGER, " +
//                    "`errorMessage` TEXT, `isSynchronized` INTEGER)")
//            database.execSQL("INSERT INTO LeadNotes2(`localId`,`serverId`,`moduleId`,`userId`,`subject`,`visitNote`," +
//                    "`personalVisit`,`noteDate`,`modifiedby`,`created_by`,`archive_status`,`gps`,`faciltyName`,`dateTimep`,`noteTime`," +
//                    "`moduleName`,`created_date_count`,`modified_date_count`,`modified_date`,`created_date`,`errorCode`,`errorMessage`,`isSynchronized`)" +
//                    "Select `localId`,`serverId`,`moduleId`,`userId`,`subject`,`visitNote`," +
//                    "`personalVisit`,`noteDate`,`modifiedby`,`created_by`,`archive_status`,`gps`,`faciltyName`,`dateTimep`,`noteTime`," +
//                    "`moduleName`,`created_date_count`,`modified_date_count`,`modified_date`,`created_date`,`errorCode`,`errorMessage`," +
//                    "`isSynchronized` From LeadNotes")
//            database.execSQL("DROP TABLE LeadNotes")
//            database.execSQL("Alter table LeadNotes2 RENAME TO LeadNotes")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `LeadNotesPaginationDataModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO LeadNotesPaginationDataModel2 (`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From LeadNotesPaginationDataModel")
//            database.execSQL("DROP TABLE LeadNotesPaginationDataModel")
//            database.execSQL("Alter table LeadNotesPaginationDataModel2 RENAME TO LeadNotesPaginationDataModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `Users2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`userId` INTEGER NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `fullName` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO Users2 (`localId`,`userId`,`firstName`,`lastName`,`fullName`)" +
//                    "Select `localId`,`userId`,`firstName`,`lastName`,`fullName` From Users")
//            database.execSQL("DROP TABLE Users")
//            database.execSQL("Alter table Users2 RENAME TO Users")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `AccountNotes2` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`serverId` INTEGER NOT NULL, `moduleId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `subject` TEXT NOT NULL, " +
//                    "`visitNote` TEXT NOT NULL, `personalVisit` TEXT NOT NULL, `noteDate` TEXT NOT NULL, `modifiedby` TEXT NOT NULL, " +
//                    "`created_by` TEXT NOT NULL, `archive_status` TEXT NOT NULL, `gps` TEXT NOT NULL, `faciltyName` TEXT NOT NULL, " +
//                    "`dateTimep` TEXT NOT NULL, `noteTime` TEXT NOT NULL, `moduleName` TEXT NOT NULL, `created_date_count` TEXT NOT NULL, " +
//                    "`modified_date_count` TEXT NOT NULL, `modified_date` TEXT NOT NULL, `created_date` TEXT, `errorCode` INTEGER, " +
//                    "`errorMessage` TEXT, `isSynchronized` INTEGER)")
//            database.execSQL("INSERT INTO AccountNotes2(`localId`,`serverId`,`moduleId`,`userId`,`subject`,`visitNote`," +
//                    "`personalVisit`,`noteDate`,`modifiedby`,`created_by`,`archive_status`,`gps`,`faciltyName`,`dateTimep`,`noteTime`," +
//                    "`moduleName`,`created_date_count`,`modified_date_count`,`modified_date`,`created_date`,`errorCode`,`errorMessage`,`isSynchronized`)" +
//                    "Select `localId`,`serverId`,`moduleId`,`userId`,`subject`,`visitNote`," +
//                    "`personalVisit`,`noteDate`,`modifiedby`,`created_by`,`archive_status`,`gps`,`faciltyName`,`dateTimep`,`noteTime`," +
//                    "`moduleName`,`created_date_count`,`modified_date_count`,`modified_date`,`created_date`,`errorCode`,`errorMessage`," +
//                    "`isSynchronized` From AccountNotes")
//            database.execSQL("DROP TABLE AccountNotes")
//            database.execSQL("Alter table AccountNotes2 RENAME TO AccountNotes")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `AccountNotesPaginationDataModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO AccountNotesPaginationDataModel2 (`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From AccountNotesPaginationDataModel")
//            database.execSQL("DROP TABLE AccountNotesPaginationDataModel")
//            database.execSQL("Alter table AccountNotesPaginationDataModel2 RENAME TO AccountNotesPaginationDataModel")
//
//
//            database.execSQL("CREATE TABLE IF NOT EXISTS `AppointmentPaginationModel2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                    "`totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `hitDate` TEXT NOT NULL, `userId` INTEGER NOT NULL, " +
//                    "`startDate` TEXT NOT NULL)")
//            database.execSQL("INSERT INTO AppointmentPaginationModel2 (`id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate`)" +
//                    "Select `id`,`totalPages`,`currentPage`,`hitDate`,`userId`,`startDate` From AppointmentPaginationModel")
//            database.execSQL("DROP TABLE AppointmentPaginationModel")
//            database.execSQL("Alter table AppointmentPaginationModel2 RENAME TO AppointmentPaginationModel")
//
//
//        }
//    }
//
//}
//
