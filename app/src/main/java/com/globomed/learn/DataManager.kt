package com.globomed.learn

import android.content.ContentValues
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.COLUMN_DESIGNATION
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.COLUMN_DOB
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.COLUMN_ID
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.COLUMN_NAME
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.COLUMN_SURGEON
import com.globomed.learn.GloboMedDBContract.EmployeeEntry.TABLE_NAME

object DataManager {

    fun fetchAllEmployees(databaseHelper: DatabaseHelper): ArrayList<Employee> {

        val employees = ArrayList<Employee>()

        val db = databaseHelper.readableDatabase

        val columns = arrayOf(
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_DOB,
            COLUMN_DESIGNATION,
            COLUMN_SURGEON
        )

        val cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)

        val idPos = cursor.getColumnIndex(COLUMN_ID)
        val namePos = cursor.getColumnIndex(COLUMN_NAME)
        val dobPos = cursor.getColumnIndex(COLUMN_DOB)
        val designationPos = cursor.getColumnIndex(COLUMN_DESIGNATION)
        val surgeonPos = cursor.getColumnIndex(COLUMN_SURGEON)

        while (cursor.moveToNext()) {

            val id = cursor.getString(idPos)
            val name = cursor.getString(namePos)
            val dob = cursor.getLong(dobPos)
            val designation = cursor.getString(designationPos)
            val surgeon = cursor.getInt(surgeonPos)

            employees.add(Employee(id, name, dob, designation, surgeon))
        }

        cursor.close()

        return employees
    }

    fun fetchEmployee(databaseHelper: DatabaseHelper, empId: String): Employee? {
        val db = databaseHelper.readableDatabase
        var employee: Employee? = null

        val columns = arrayOf(
            COLUMN_NAME,
            COLUMN_DOB,
            COLUMN_DESIGNATION,
            COLUMN_SURGEON
        )

        val selection: String = COLUMN_ID + " LIKE ? "
        val selectionArgs = arrayOf(empId)

        val cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null)

        val namePos = cursor.getColumnIndex(COLUMN_NAME)
        val dobPos = cursor.getColumnIndex(COLUMN_DOB)
        val designationPos = cursor.getColumnIndex(COLUMN_DESIGNATION)
        val surgeonPos = cursor.getColumnIndex(COLUMN_SURGEON)

        while (cursor.moveToNext()) {

            val name = cursor.getString(namePos)
            val dob = cursor.getLong(dobPos)
            val designation = cursor.getString(designationPos)
            val surgeon = cursor.getInt(surgeonPos)

            employee = Employee(empId, name, dob, designation, surgeon)
        }

        cursor.close()

        return employee
    }

    fun updateEmployee(databaseHelper: DatabaseHelper, employee: Employee) {
        val db = databaseHelper.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_NAME, employee.name)
        values.put(COLUMN_DESIGNATION, employee.designation)
        values.put(COLUMN_DOB, employee.dob)
        values.put(COLUMN_SURGEON, employee.isSurgeon)

        val selection: String = COLUMN_ID + " LIKE ? "
        val selectionArgs = arrayOf(employee.id)

        db.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun deleteEmployee(databaseHelper: DatabaseHelper, empId: String) : Int {
        val db = databaseHelper.writableDatabase

        val selection: String = COLUMN_ID + " LIKE ? "
        val selectionArgs = arrayOf(empId)

        return db.delete(TABLE_NAME, selection, selectionArgs)
    }

    fun deleteAllEmployees(databaseHelper: DatabaseHelper): Int {
        val db = databaseHelper.writableDatabase

        return db.delete(TABLE_NAME, "1", null)
    }
}