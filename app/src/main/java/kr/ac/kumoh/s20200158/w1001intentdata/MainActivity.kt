package kr.ac.kumoh.s20200158.w1001intentdata

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ImageActivity
import kr.ac.kumoh.s20200158.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    companion object {
        const val keyName = "image" // KEY_NAME 형식으로도 많이 사용함
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.btnGundam.setOnClickListener(this)
        binding.btnZaku.setOnClickListener(this)

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {

            if(it.resultCode != RESULT_OK)
                return@registerForActivityResult

            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NONE)
            // data가 없을 수도 있기 때문에 nullable로 써줌
            // 아무것도 반환하지 않았을 경우를 대비해서 ImageActivity.NONE 사용

            val str = when(result)
            {
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }

            val image = it.data?.getStringExtra(ImageActivity.imageName)
            when(image) {
                "gundam" -> binding.btnGundam.text = "건담 ($str)" //str은 좋아요, 싫어요 여부
                "zaku" -> binding.btnZaku.text = "자쿠 ($str)"
            }

        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when (v?.id) {
            binding.btnGundam.id -> "gundam"
            binding.btnZaku.id -> "zaku"
            else -> return
        }
        intent.putExtra(keyName, value)
        //startActivity(intent)
        launcher.launch(intent)
    }
}