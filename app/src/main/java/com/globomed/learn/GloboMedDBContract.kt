package com.globomed.learn

import android.provider.BaseColumns

object GloboMedDBContract {

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${EmployeeEntry.TABLE_NAME} (" +
                "${EmployeeEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTO INCREMENT, " +
                "${EmployeeEntry.COLUMN_NAME} TEXT NOT NULL, " +
                "${EmployeeEntry.COLUMN_DOB} INTEGER, NOT NULL" +
                "${EmployeeEntry.COLUMN_DESIGNATION} TEXT NOT NULL)"

    const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS ${EmployeeEntry.TABLE_NAME}"

    object EmployeeEntry : BaseColumns {
        const val TABLE_NAME = "employee"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DOB = "dob"
        const val COLUMN_DESIGNATION = "designation"
    }
}