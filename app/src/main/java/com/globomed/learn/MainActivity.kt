package com.globomed.learn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val employeeListAdapter = EmployeeListAdapter(this)
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        recyclerView.adapter = employeeListAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)
        employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))

        fab.setOnClickListener {
            val addEmployee = Intent(this, AddEmployeeActivity::class.java)
            startActivityForResult(addEmployee, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))
        }
    }
}
