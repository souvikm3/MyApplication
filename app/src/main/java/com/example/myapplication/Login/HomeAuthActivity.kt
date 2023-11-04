package com.example.myapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeAuthBinding
import com.example.myapplication.databinding.ActivitySigninBinding
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class HomeAuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAuthBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser !=null){
            binding.tvUserName.text=firebaseAuth.currentUser!!.email
        }
        binding.Logout.setOnClickListener{
            if(firebaseAuth.currentUser !=null){
                firebaseAuth.signOut()
                val intent=Intent(this,SigninActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}