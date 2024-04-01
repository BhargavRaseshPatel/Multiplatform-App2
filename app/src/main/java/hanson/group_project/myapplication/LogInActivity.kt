package hanson.group_project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hanson.group_project.myapplication.databinding.ActivityLogInBinding
import hanson.group_project.myapplication.databinding.ActivitySignUpBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonLogIn.setOnClickListener {
            val emailID = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if(emailID.isEmpty()){
                binding.editTextTextEmailAddress.setError("Email ID cannot be empty.")
            }
            if(password.isEmpty()){
                binding.editTextTextPassword.setError("Password cannot be empty.")
            }
            else{
                auth.signInWithEmailAndPassword(emailID,password).addOnSuccessListener {
                    Toast.makeText(this,"LogIn Successful",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this,"LogIn Unsuccessful",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }
    }
}