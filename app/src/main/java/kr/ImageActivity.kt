package kr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kr.ac.kumoh.s20200158.w1001intentdata.MainActivity
import kr.ac.kumoh.s20200158.w1001intentdata.R
import kr.ac.kumoh.s20200158.w1001intentdata.databinding.ActivityImageBinding
import kr.ac.kumoh.s20200158.w1001intentdata.databinding.ActivityMainBinding

class ImageActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityImageBinding
    private var image: String? = null // nullable을 쓰기 위해 ?를 붙여줘야 한다.

    companion object {
        const val imageName = "image"
        const val resultName = "result"

        const val LIKE = 10
        const val DISLIKE = 20
        const val NONE = 0 // 쓰지는 않지만 혹시 모르니까 만들기
    } // 이를 통해 putExtra를 해서 caller에게 전달하겠다는 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
//        Toast.makeText(this,
//            intent.getStringExtra("image"),
//            Toast.LENGTH_SHORT).show()

        image = intent.getStringExtra(MainActivity.keyName)

        val res = when (image) {
            "gundam" -> R.drawable.night
            "zaku" -> R.drawable.night
            else -> R.drawable.ic_launcher_foreground
        }
        binding.imgGundam.setImageResource(res)
        binding.btnLike.setOnClickListener(this)
        binding.btnDislike.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // 어떤 그림이 넘어왔는지 저장
        val intent = Intent()
        val value = when(v?.id) {
            binding.btnLike.id -> LIKE
            binding.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(imageName, image)
        intent.putExtra(resultName, value)

        setResult(RESULT_OK, intent)
        finish()
    }
}