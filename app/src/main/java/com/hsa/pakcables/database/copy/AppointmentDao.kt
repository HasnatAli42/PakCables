package com.hsa.pakcables.database.copy
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.ssasoft.korlezz.data.*
//import com.ssasoft.korlezz.models.*
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import java.util.*
//
//@Dao
//interface AppointmentDao {
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertAll(appointment: Appointment):Long
//
//    @Insert
//    fun insertMultipleAppointments(appointment: List<Appointment>)
//
//    @Query("UPDATE Appointment set serverId=:serverId, parentId=:parentId WHERE localId=:id")
//    fun updateServerAndParentID(serverId:Long, parentId:Long, id:Long)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    abstract fun insertSync(appointment: SyncStatus)
//
//    @Query("Select * from Appointment WHERE localId = :id")
//    fun getAppointmentUsingLocalId(id : Long): Appointment
//
//    @Query("Select * from Appointment WHERE serverId = :id and moduleName=:appointmentType and (userId IN (:userIds))")
//    fun getAppointmentUsingServerId(id : Long, appointmentType:String,userIds:List<String>): Appointment
//
//    @Query("Delete from Appointment Where localId=:id")
//    fun deleteAppointmentData(id:Int)
//
//    @Query("UPDATE Appointment SET moduleId=:moduleId WHERE localId=:id")
//    fun updateModuleID(moduleId:String, id:Long)
//
//    @Query("Select count(*) from Appointment where serverId=:id and appointmentType=:appointmentType and (userId IN (:userIds))")
//    fun checkIfAppointmentExists(id: Long, appointmentType:String, userIds:List<String>): Int
//
//    @Query("Update Appointment Set eventId=:eventId where localId=:id")
//    fun addEventId(eventId:Long, id:Long)
//
//    @Query("Select serverID from Appointment Where localId=:id")
//    fun getServerID(id:Long):Long
//
//    @Query("Select appointmentType from Appointment Where localId=:id")
//    fun getAppointmentType(id:Long):String
//
//    @Query("Select isSynchronized from Appointment Where localId=:id")
//    fun checkIfDataIsSynced(id:Long):Boolean
//
//    @Query("Select localId from Appointment Where serverId=:serverId and appointmentType=:appointmentType and (userId IN (:userIds))")
//    fun getLocalIdUsingServerId(serverId:String,appointmentType:String,userIds:List<String>):Long
//
//    @Query("Update Appointment set errorCode=:errorCode,errorMessage=:errorDetail, isSynchronized=:isSynchronized" +
//            " where localId=:id")
//    fun updateErrorDetailAndSyncStatusUsingLocalId(id:Long, errorCode: Int, errorDetail: String, isSynchronized:Boolean)
//
//    @Query("Update Appointment Set archivedStatus=:status, archived_by=:archiveby," +
//            "  modefiedDate=:modifiedDate, modifiedby=:modifiedBy where localId=:localId")
//    fun updateDeleteAppointment(status:String, archiveby:String, modifiedDate:String
//                                , modifiedBy:String, localId:Long)
//
//    @Query("Update Appointment Set recurrenceMeetingDates=:recurrenceMeetingDates where localId=:localId")
//    fun updateParentAppointmentRecurrenceDatesUsingLocalId(recurrenceMeetingDates:String, localId:String)
//
//    @Query("Update Appointment Set recurrenceMeetingDates=:recurrenceMeetingDates where parentId!='0' and (parentId=:parentServerId and isSynchronized='1')" +
//            " or (parentId=:parentLocalId and isSynchronized='0') and appointmentType=:appointmentType")
//    fun updateChildAppointmentRecurrenceDates(recurrenceMeetingDates:String, parentServerId:String, parentLocalId:String, appointmentType:String)
//
//    @Query("UPDATE Appointment SET serverID=:serverId, isSynchronized=:isSynchronized, errorCode=:errorCode," +
//            "errorMessage=:errorMessage WHERE localId=:id")
//    fun updateServerIdAndErrorDetails(serverId:String, isSynchronized:Boolean, errorCode:Int,
//                                      errorMessage:String, id:Long)
//
//    @Query("UPDATE Appointment SET serverID=:serverId, moduleId=:moduleId, taskDate=:taskDate, taskEnd=:taskEnd," +
//            "isSynchronized=:isSynchronized, errorCode=:errorCode," +
//            "errorMessage=:errorMessage WHERE localId=:localId")
//    fun updateDetailsUsingLocalId(serverId:String, moduleId:String, taskDate:String, taskEnd:String,
//                                  isSynchronized:Boolean, errorCode:Int, errorMessage:String, localId:Long)
//
//    @Query("UPDATE Appointment SET serverID=:serverId, moduleId=:moduleId, taskDate=:taskDate, taskEnd=:taskEnd, parentId=:parentId," +
//            "isSynchronized=:isSynchronized, isUpdate=:isUpdate, recurrenceId=:recurrenceId, errorCode=:errorCode," +
//            "errorMessage=:errorMessage WHERE localId=:localId")
//    fun updateDetailAndUpdateFlagUsingLocalId(serverId:String, moduleId: String, taskDate:String, taskEnd:String, parentId:String, recurrenceId:String,
//                                  isSynchronized:Boolean, isUpdate:Boolean, errorCode:Int, errorMessage:String, localId:Long)
//
//    @Query("UPDATE Appointment SET moduleId=:moduleId, taskDate=:taskDate, taskEnd=:taskEnd, parentId=:parentId," +
//            "isSynchronized=:isSynchronized, isUpdate=:isUpdate, recurrenceId=:recurrenceId, errorCode=:errorCode," +
//            "errorMessage=:errorMessage WHERE serverId=:serverId and (userId IN (:userIds))")
//    fun updateDetailAndUpdateFlagUsingServerId(moduleId: String, taskDate:String, taskEnd:String, parentId:String, recurrenceId:String,
//                                              isSynchronized:Boolean, isUpdate:Boolean, errorCode:Int, errorMessage:String, serverId:Long,
//                                               userIds:List<String>)
//
//    @Query("UPDATE Appointment SET moduleId=:moduleId, title=:title, subject=:subject, address=:address, attendeesEmail=:attendeesEmail, archivedStatus=:archivedStatus, " +
//            "archived_by=:archived_by, timeZone=:timeZone, taskDate=:taskDate, taskEnd=:taskEnd, orgTaskDate=:orgTaskDate, orgTaskEnd=:orgTaskEnd, parentId=:parentId, " +
//            "taskNote=:taskNote, recurrenceMeetingDates=:recurrenceMeetingDates, recurrenceStartDate=:recurrenceStartDate, recurrenceEndDate=:recurrenceEndDate, " +
//            "recurrenceStartTime=:recurrenceStartTime, recurrenceEndTime=:recurrenceEndTime, modefiedDate=:modefiedDate, modifiedBy=:modifiedBy," +
//            "isSynchronized=:isSynchronized, isUpdate=:isUpdate, updateAll=:updateAll, errorCode=:errorCode, errorMessage=:errorMessage WHERE localId=:localId")
//    fun updateAppointment(moduleId:String, title:String, subject:String, address:String, attendeesEmail:String, timeZone:String, parentId:String,archivedStatus:String,
//                          archived_by:String, taskDate:String, taskEnd:String, orgTaskDate:String, orgTaskEnd:String, taskNote:String,recurrenceMeetingDates:String,
//                          recurrenceStartDate:String, recurrenceEndDate:String, recurrenceStartTime:String, recurrenceEndTime:String, errorCode:Int, errorMessage: String,
//                          modefiedDate:String, modifiedBy:String, isSynchronized: Boolean, isUpdate:Boolean, updateAll:Boolean, localId:Long)
//
//    @Query("UPDATE Appointment SET rating=:rating, facilityName=:facilityName, archived_by=:archived_by, archivedStatus=:archivedStatus, " +
//            "title=:title, subject=:subject, address=:address, attendeesEmail=:attendeesEmail, recurrenceId=:recurrenceId," +
//            "timeZone=:timeZone, taskDate=:taskDate, taskEnd=:taskEnd, address=:address, recurrenceFlag=:recurrenceFlag," +
//            "taskNote=:taskNote, modefiedDate=:modefiedDate, modifiedBy=:modifiedBy, recurrenceStartDate=:recurrenceStartDate," +
//            "recurrenceStartTime=:recurrenceStartTime, recurrenceEndTime=:recurrenceEndTime, recurrenceDuration=:recurrenceDuration," +
//            "recurrencePattern=:recurrencePattern, recurrencePatternSub=:recurrencePatternSub, recurrenceDaysCount=:recurrenceDaysCount," +
//            "recurrenceEndDate=:recurrenceEndDate, recurrenceWeekDays=:recurrenceWeekDays," +
//            "recurrenceWeeksCount=:recurrenceWeeksCount, recurrenceMonthType=:recurrenceMonthType, recurrenceMonthCount=:recurrenceMonthCount," +
//            "recurrenceMonthDate=:recurrenceMonthDate, "+
//            "isSynchronized=:isSynchronized, isUpdate=:isUpdate, updateAll=:updateAll WHERE serverId=:serverId and (userId IN (:userIds))")
//    fun updateAppointmentFromServer(rating:String, facilityName:String, archived_by: String,
//                                    title:String, subject:String, address:String, attendeesEmail:String, timeZone:String,
//                                    archivedStatus:String, recurrenceId: String, recurrenceFlag:String, recurrenceStartDate:String,
//                                    recurrencePatternSub:String, recurrenceDaysCount:String,
//                                    recurrenceEndDate:String, recurrenceWeekDays:String, recurrenceWeeksCount:String,
//                                    recurrenceMonthType:String, recurrenceMonthCount:String, recurrenceMonthDate:String,
//                                    recurrenceStartTime:String, recurrenceEndTime:String, recurrenceDuration:String, recurrencePattern:String,
//                                    taskDate:String, taskEnd:String, taskNote:String, modefiedDate:String,
//                          modifiedBy:String, isSynchronized: Boolean, isUpdate:Boolean, updateAll:Boolean, serverId:Long, userIds:List<String>)
//
//    @Query("UPDATE Appointment SET rating=:rating, facilityName=:facilityName, archived_by=:archived_by, archivedStatus=:archivedStatus, " +
//            "title=:title, subject=:subject, address=:address, attendeesEmail=:attendeesEmail, moduleId=:moduleId, recurrenceId=:recurrenceId," +
//            "timeZone=:timeZone, orgTaskDate=:orgTaskDate, orgTaskEnd=:orgTaskEnd, taskDate=:taskDate, taskEnd=:taskEnd, address=:address, parentId=:parentId, recurrenceFlag=:recurrenceFlag," +
//            "taskNote=:taskNote, modefiedDate=:modefiedDate, modifiedBy=:modifiedBy, recurrenceStartDate=:recurrenceStartDate," +
//            "recurrenceStartTime=:recurrenceStartTime, recurrenceEndTime=:recurrenceEndTime, recurrenceDuration=:recurrenceDuration," +
//            "recurrencePattern=:recurrencePattern, recurrencePatternSub=:recurrencePatternSub, recurrenceDaysCount=:recurrenceDaysCount," +
//            "recurrenceMeetingDates=:recurrenceMeetingDates, recurrenceEndDate=:recurrenceEndDate, recurrenceWeekDays=:recurrenceWeekDays," +
//            "recurrenceWeeksCount=:recurrenceWeeksCount, recurrenceMonthType=:recurrenceMonthType, recurrenceMonthCount=:recurrenceMonthCount," +
//            "recurrenceMonthDate=:recurrenceMonthDate, errorCode=:errorCode, errorMessage=:errorMessage, "+
//            "isSynchronized=:isSynchronized, isUpdate=:isUpdate, updateAll=:updateAll WHERE localId=:localId")
//    fun updateAppointmentUsingLocalId(rating:String, facilityName:String, archived_by: String, moduleId: String,
//                                    title:String, subject:String, address:String, attendeesEmail:String, timeZone:String, parentId:String,
//                                    archivedStatus:String, recurrenceId: String, recurrenceFlag:String, recurrenceStartDate:String,
//                                    recurrencePatternSub:String, recurrenceDaysCount:String, recurrenceMeetingDates:String,
//                                    recurrenceEndDate:String, recurrenceWeekDays:String, recurrenceWeeksCount:String,
//                                    recurrenceMonthType:String, recurrenceMonthCount:String, recurrenceMonthDate:String,
//                                    recurrenceStartTime:String, recurrenceEndTime:String, recurrenceDuration:String, recurrencePattern:String,
//                                    orgTaskDate: String, orgTaskEnd: String, taskDate:String, taskEnd:String, taskNote:String, modefiedDate:String,
//                                    modifiedBy:String, isSynchronized: Boolean, isUpdate:Boolean, updateAll:Boolean, localId:Long, errorCode:Int, errorMessage: String)
//
//    @Query("Select * from Appointment where ((moduleId=:serverId and isSynchronized='1') or" +
//            " (moduleId=:localId and isSynchronized='0')) and appointmentType=:type and archivedStatus='new'" +
//            " and userId IN (:userIds)")
//    fun getAppointmentList(serverId: String, localId: String, type:String, userIds:List<String>): LiveData<List<Appointment>>
//
//    @Query("Select * from Appointment where parentId!='0' and ((parentId=:localId and isSynchronized='0') or" +
//            " (parentId=:serverId and isSynchronized='1')) and appointmentType=:type and archivedStatus='new'")
//    fun getChildAppointmentUsingParent(serverId: String, localId: String, type:String):List<Appointment>
//
//    @Query("Select * from Appointment where (parentId=:parentId and isSynchronized='0') or (parentId=:parentIdServer and isSynchronized='1')")
//    fun getAppointmentListbyParentId(parentId:String, parentIdServer:String):List<Appointment>
//
//    @Query("Select appointment.localId, appointment.serverId, appointment.taskDate, appointment.periority," +
//            "appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            "appointment.taskEnd, appointment.appointmentType as moduleName, appointment.subject, appointment.taskNote," +
//            "appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, appointment.isSynchronized," +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, appointment.isUpdate, appointment.recurrenceId," +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate, appointment.updateAll, appointment.parentId, appointment.moduleId " +
//            "from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0' and appointment.appointmentType = 'personal' and " +
//            "syncStatus.status = 'Not Sync' and syncStatus.requestType = '11.4' ")
//    fun getUnexecutedPersonalAppointmentsForUpdate():List<Appointment>
//
//    @Query("Select appointment.localId, appointment.serverId, appointment.taskDate, appointment.periority," +
//            "appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            "appointment.taskEnd, appointment.appointmentType as moduleName, appointment.subject, appointment.taskNote," +
//            "appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, appointment.isSynchronized," +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, appointment.isUpdate, appointment.recurrenceId," +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate, appointment.updateAll, appointment.parentId, lead.serverID as moduleId " +
//            "from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0' and appointment.appointmentType = 'lead' and " +
//            "syncStatus.status = 'Not Sync' and syncStatus.requestType = '11.4' " +
//            "inner join Lead lead on lead.localId = appointment.moduleId and lead.isSynchronized = '1'")
//    fun getUnexecutedLeadAppointmentsForUpdate():List<Appointment>
//
//    @Query("Select appointment.localId, appointment.serverId, appointment.taskDate, appointment.periority," +
//            "appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            "appointment.taskEnd, appointment.appointmentType as moduleName, appointment.subject, appointment.taskNote," +
//            "appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, appointment.isSynchronized," +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, appointment.isUpdate, appointment.recurrenceId," +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate, appointment.updateAll, appointment.parentId, account.serverID as moduleId " +
//            "from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0'and appointment.appointmentType = 'account'  and " +
//            "syncStatus.status = 'Not Sync' and syncStatus.requestType = '11.4' " +
//            "inner join Account account on account.localId = appointment.moduleId and account.isSynchronized = '1'")
//    fun getUnexecutedAccountAppointmentsForUpdate():List<Appointment>
//
//
//    @Query("Select appointment.localId, appointment.taskDate, appointment.periority, appointment.moduleId," +
//            " appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            " appointment.taskEnd, appointment.moduleName, appointment.subject, appointment.taskNote," +
//            " appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, " +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, " +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0' and " +
//            "syncStatus.status = 'Not Sync' and requestType = '2.1'")
//    fun getUnexecutedPersonalAppointments():List<Appointment>
//
//    @Query("Select appointment.localId, appointment.taskDate, appointment.periority," +
//            "appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            "appointment.taskEnd, appointment.moduleName, appointment.subject, appointment.taskNote," +
//            "appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, " +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, " +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate, lead.serverID as moduleId from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0' and " +
//            "syncStatus.status = 'Not Sync' and syncStatus.requestType = '2.2' " +
//            "inner join Lead lead on lead.localId = appointment.moduleId and lead.isSynchronized = '1'")
//    fun getUnexecutedLeadAppointments():List<Appointment>
//
//    @Query("Select appointment.localId, appointment.taskDate, appointment.periority," +
//            "appointment.userName, appointment.timeZone, appointment.profileZone, appointment.userId," +
//            "appointment.taskEnd, appointment.moduleName, appointment.subject, appointment.taskNote," +
//            "appointment.address, appointment.gps, appointment.status, appointment.title, appointment.parentId, " +
//            "appointment.orgTaskDate, appointment.orgTaskEnd, appointment.archivedStatus, appointment.attendeesEmail, " +
//            "appointment.createdDate, appointment.createdBy, appointment.facilityName, " +
//            "appointment.modifiedBy, appointment.modefiedDate, appointment.appointmentType, " +
//            "appointment.recurrenceFlag, appointment.recurrenceStartDate, appointment.recurrenceStartTime," +
//            "appointment.recurrenceEndTime, appointment.recurrenceDuration, appointment.recurrencePattern," +
//            "appointment.recurrencePatternSub, appointment.recurrenceDaysCount, appointment.recurrenceMeetingDates," +
//            "appointment.recurrenceEndDateOption, appointment.recurrenceEndDate, appointment.recurrenceWeekDays," +
//            "appointment.recurrenceWeeksCount, appointment.recurrenceMonthType, appointment.recurrenceMonthCount," +
//            "appointment.recurrenceMonthDate, account.serverID as moduleId from Appointment appointment inner join SyncStatus syncstatus on " +
//            "appointment.localId = syncStatus.parentTableId and appointment.isSynchronized = '0' and " +
//            "syncStatus.status = 'Not Sync' and syncStatus.requestType = '2.3' " +
//            "inner join Account account on account.localId = appointment.moduleId and account.isSynchronized = '1'")
//    fun getUnexecutedAccountAppointments():List<Appointment>
//
//
//    @Query("Update Appointment set errorCode=:errorCode,errorMessage=:errorDetail" +
//            " where localId=:id")
//    fun updateErrorCodeAndDetailUsingLocalId(id:Long, errorCode: Int, errorDetail: String)
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Transaction
//    fun createSyncItemAddAppointment(appointment: Appointment, type:String, isParent:Boolean, profile: ProfileModel):Long
//    {
//        val currentId = insertAll(appointment = appointment)
//        if(isParent)
//        {
//            val syncFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
//            val syncFormatted = LocalDateTime.now().format(syncFormatter)
//
//            val c = Calendar.getInstance().time
//            val syncStatus = SyncStatus()
//            syncStatus.parentTableId = currentId
//            syncStatus.effectedDate = syncFormatted
//            syncStatus.requestType = type
//            syncStatus.noOfHits = 0
//            syncStatus.status = SyncStatusType.TypeNotExecuted.type
//            syncStatus.priority = DataPriority.PriorityAppointment.priority
//            syncStatus.requestTypeString = "Appointment"
//            syncStatus.name = appointment.title
//            syncStatus.operation = "Add"
//            syncStatus.userId = profile.userId
//
//            insertSync(syncStatus)
//        }
//        return currentId
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Transaction
//    fun createSyncItemAddAppointmentWithRecurrence(appointment: Appointment, appointmentList : List<Appointment>, type:String):Long
//    {
//        val currentId = insertAll(appointment = appointment)
//
//        appointmentList.forEach {
//            it.parentId = currentId.toString()
//        }
//        insertMultipleAppointments(appointmentList)
//
//        val syncFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
//        val syncFormatted = LocalDateTime.now().format(syncFormatter)
//
//        val c = Calendar.getInstance().time
////        val df = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        val syncStatus = SyncStatus()
//        syncStatus.parentTableId = currentId
//        syncStatus.effectedDate = syncFormatted
//        syncStatus.requestType = type
//        syncStatus.noOfHits = 0
//        syncStatus.status = SyncStatusType.TypeNotExecuted.type
//        syncStatus.priority = DataPriority.PriorityAppointment.priority
//        syncStatus.requestTypeString = "Appointment"
//        syncStatus.name = appointment.title
//        syncStatus.operation = "Add"
//
//        insertSync(syncStatus)
//        return currentId
//    }
//
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Transaction
//    fun createSyncItemUpdateSingleAppointment(appointment:Appointment, profile:ProfileModel, updateAll:Boolean, isParent:Boolean)
//    {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
//        val formatted = current.format(formatter)
//
//        appointment.let {
//            updateAppointment(it.moduleId.toString(),it.title,it.subject,it.address,it.attendeesEmail!!,it.timeZone,it.parentId,
//                it.archivedStatus.toString(),it.archived_by.toString(), it.taskDate, it.taskEnd, it.orgTaskDate!!,it.orgTaskEnd!!,
//                it.taskNote, it.recurr.recurrenceMeetingDates.toString(), it.recurr.recurrenceStartDate.toString(),
//                it.recurr.recurrenceEndDate.toString(), it.recurr.recurrenceStartTime.toString(), it.recurr.recurrenceEndTime.toString(),
//                it.errorCode!!, it.errorMessage.toString(), it.modefiedDate!!, it.modifiedby!!, it.isSynchronized!!, true, updateAll, it.localId)
//        }
//
//        if(isParent)
//        {
//            val syncFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
//            val syncFormatted = LocalDateTime.now().format(syncFormatter)
//
//            val c = Calendar.getInstance().time
//            val syncStatus = SyncStatus()
//            syncStatus.parentTableId = appointment.localId
//            syncStatus.effectedDate = syncFormatted
//            syncStatus.requestType = SyncType.TypeAppointmentUpdate.type
//            syncStatus.noOfHits = 0
//            syncStatus.status = SyncStatusType.TypeNotExecuted.type
//            syncStatus.priority = DataPriority.PriorityAppointmentUpdate.priority
//            syncStatus.requestTypeString = "Appointment"
//            syncStatus.name = appointment.title
//            syncStatus.operation = "Update"
//            syncStatus.userId = profile.userId
//
//            insertSync(syncStatus)
//        }
//    }
//
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Transaction
//    fun createSyncItemDeleteAppointment(appointment: Appointment, profile: ProfileModel)
//    {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
//        val formatted = current.format(formatter)
//
//        updateDeleteAppointment("trash",profile.firstName+" "+profile.lastName,formatted,
//            profile.firstName+" "+profile.lastName,appointment.localId)
//
//        val syncFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
//        val syncFormatted = LocalDateTime.now().format(syncFormatter)
//
//        val c = Calendar.getInstance().time
//        val syncStatus = SyncStatus()
//        syncStatus.parentTableId = appointment.localId
//        syncStatus.effectedDate = syncFormatted
//        syncStatus.requestType = SyncType.TypeAppointmentDelete.type
//        syncStatus.noOfHits = 0
//        syncStatus.status = SyncStatusType.TypeNotExecuted.type
//        syncStatus.priority = DataPriority.PriorityAppointmentArchiveOrDelete.priority
//        syncStatus.requestTypeString = "Appointment"
//        syncStatus.name = appointment.title
//        syncStatus.operation = "Delete"
//        syncStatus.userId = profile.userId
//
//        insertSync(syncStatus)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Transaction
//    fun createSyncItemArchiveAppointment(appointment: Appointment, profile: ProfileModel)
//    {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
//        val formatted = current.format(formatter)
//
//        updateDeleteAppointment("archived",profile.firstName+" "+profile.lastName,formatted,
//            profile.firstName+" "+profile.lastName,appointment.localId)
//
//        val syncFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
//        val syncFormatted = LocalDateTime.now().format(syncFormatter)
//
//        val c = Calendar.getInstance().time
//        val syncStatus = SyncStatus()
//        syncStatus.parentTableId = appointment.localId
//        syncStatus.effectedDate = syncFormatted
//        syncStatus.requestType = SyncType.TypeAppointmentArchive.type
//        syncStatus.noOfHits = 0
//        syncStatus.status = SyncStatusType.TypeNotExecuted.type
//        syncStatus.priority = DataPriority.PriorityAppointmentArchiveOrDelete.priority
//        syncStatus.requestTypeString = "Appointment"
//        syncStatus.name = appointment.title
//        syncStatus.operation = "Archive"
//        syncStatus.userId = profile.userId
//
//        insertSync(syncStatus)
//    }
//
//
//    fun updateAppointmentTransaction(appointment: Appointment, profile: ProfileModel){
//        appointment.let {
//            updateAppointmentFromServer(it.rating.toString(), it.facilityName.toString(), it.archived_by.toString(), it.title, it.subject,
//                it.address, it.attendeesEmail.toString(), it.timeZone, it.archivedStatus.toString(), it.recurrenceId.toString(),
//                it.recurr.recurrenceFlag.toString(),it.recurr.recurrenceStartDate.toString(),it.recurr.recurrencePatternSub.toString(),it.recurr.recurrenceDaysCount.toString(),
//                it.recurr.recurrenceEndDate.toString(),it.recurr.recurrenceWeekDays.toString(),
//                it.recurr.recurrenceWeeksCount.toString(),it.recurr.recurrenceMonthType.toString(),it.recurr.recurrenceMonthCount.toString(),
//                it.recurr.recurrenceMonthDate.toString(),it.recurr.recurrenceStartTime.toString(),it.recurr.recurrenceEndTime.toString(),
//                it.recurr.recurrenceDuration.toString(),it.recurr.recurrencePattern.toString(),
//                it.taskDate, it.taskEnd, it.taskNote, it.modefiedDate.toString(),
//                it.modifiedby.toString(), it.isSynchronized!!, it.isUpdate!!, it.updateAll!!, it.serverId!!, profile.subUserIds.split(",") )
//        }
//
//    }
//
//    fun updateAppointmentUsingLocalIdTransaction(appointment: Appointment){
//        appointment.let {
//            updateAppointmentUsingLocalId(it.rating.toString(), it.facilityName.toString(), it.archived_by.toString(), it.moduleId.toString(), it.title, it.subject,
//                it.address, it.attendeesEmail.toString(), it.timeZone, it.parentId, it.archivedStatus.toString(), it.recurrenceId.toString(),
//                it.recurr.recurrenceFlag.toString(),it.recurr.recurrenceStartDate.toString(),it.recurr.recurrencePatternSub.toString(),it.recurr.recurrenceDaysCount.toString(),
//                it.recurr.recurrenceMeetingDates.toString(),it.recurr.recurrenceEndDate.toString(),it.recurr.recurrenceWeekDays.toString(),
//                it.recurr.recurrenceWeeksCount.toString(),it.recurr.recurrenceMonthType.toString(),it.recurr.recurrenceMonthCount.toString(),
//                it.recurr.recurrenceMonthDate.toString(),it.recurr.recurrenceStartTime.toString(),it.recurr.recurrenceEndTime.toString(),
//                it.recurr.recurrenceDuration.toString(),it.recurr.recurrencePattern.toString(),
//                it.orgTaskDate.toString(), it.orgTaskEnd.toString(),
//                it.taskDate, it.taskEnd, it.taskNote, it.modefiedDate.toString(),
//                it.modifiedby.toString(), it.isSynchronized!!, it.isUpdate!!, it.updateAll!!, it.localId, it.errorCode!!, it.errorMessage.toString())
//        }
//
//    }
//
//}