package com.udemy.firestorerealtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var textView: TextView
    private lateinit var dbRef: DatabaseReference
    private lateinit var empList: ArrayList<EmployeeModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        empList = arrayListOf<EmployeeModel>()
        getAllData()
    }

    private fun getAllData() {

        dbRef = FirebaseDatabase.getInstance().getReference("Employee")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val empName = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empName!!)
                    }
                    val myAdapter = EmployeeAdapter(empList)
                    recyclerView.adapter = myAdapter

                    myAdapter.setOnItemClickListener(object :EmployeeAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                           val intent  = Intent(this@FetchingActivity,EmployeeDetailsActivity::class.java)

                            intent.putExtra("empId",empList[position].id)
                            intent.putExtra("empName",empList[position].name)
                            intent.putExtra("empAge",empList[position].age)
                            intent.putExtra("empSalary",empList[position].salary)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}