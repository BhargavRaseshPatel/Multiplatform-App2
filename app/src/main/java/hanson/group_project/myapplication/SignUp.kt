package hanson.group_project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hanson.group_project.myapplication.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivitySignUpBinding.inflate(layoutInflater)
            setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            val emailID = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if(emailID.isEmpty()){
                binding.editTextTextEmailAddress.setError("Email ID cannot be empty.")
            }
            if(password.isEmpty()){
                binding.editTextTextPassword.setError("Password cannot be empty.")
            }
            else{
                auth.createUserWithEmailAndPassword(emailID,password).addOnCompleteListener{
                    Toast.makeText(this,"Sign Up is created successfully.",Toast.LENGTH_SHORT)
                    startActivity(Intent(this,LogInActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this,"Sign Up is not created.",Toast.LENGTH_SHORT)
                }
            }
        }

        binding.buttonLogIn.setOnClickListener {
            startActivity(Intent(this,LogInActivity::class.java))
        }
    }
}