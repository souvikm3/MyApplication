package com.example.myapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityShowRealtimeBinding
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.databinding.ActivitySigninBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ShowRealtimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowRealtimeBinding
    private lateinit var studentList: ArrayList<Student>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowRealtimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentList= ArrayList()
        databaseReference= FirebaseDatabase.getInstance().getReference("students")
        adapter= RecyclerViewAdapter(studentList)
        binding.fbAdd.setOnClickListener(){
            val intent= Intent(this,AddRealtimeActivity::class.java)
            startActivity(intent)

        }
        binding.rvMain.adapter=adapter
        binding.rvMain.layoutManager=LinearLayoutManager(this)
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                studentList.clear()
                for(datasnapshot in snapshot.children){
                    val student=datasnapshot.getValue(Student::class.java)
                    student ?.let {
                        studentList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}