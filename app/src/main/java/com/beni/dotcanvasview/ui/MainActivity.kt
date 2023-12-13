package com.beni.dotcanvasview.ui

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.beni.core.data.local.preferences.CanvasSize
import com.beni.core.data.local.room.PointEntitiy
import com.beni.core.ui.CanvasView
import com.beni.core.util.ConstantFunction
import com.beni.core.util.ConstantFunction.setInputError
import com.beni.core.util.ConstantVariable.TAG
import com.beni.dotcanvasview.R
import com.beni.dotcanvasview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var isCardVisible = false
    private var canvasSize: CanvasSize? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertPointData()
        checkCanvasExist()

        binding.apply {
            btnExpand.setOnClickListener {
                showLayoutInput(hideableLayout.isVisible)
            }
            inputCard.setOnClickListener {
                showLayoutInput(hideableLayout.isVisible)
            }
            root.setOnClickListener {
                showLayoutInput()
            }
            btnSave.setOnClickListener {
                getUserInput {
                    showLayoutInput()
                    ConstantFunction.hideKeyboard(this@MainActivity, root)
                }
            }
            btnReset.setOnClickListener {
                resetAllData()
            }
        }
    }

    private fun resetAllData() {
        viewModel.deleteCanvasSize()
        showLayoutInput()
        checkCanvasExist()
    }

    private fun insertPointData() {
        viewModel.getAllPont().observe(this@MainActivity) { points ->
            if(points.isEmpty()) {
                val listPoints = mutableListOf<PointEntitiy>()
                for (i in 0 until 3000) {
                    listPoints.add(
                        PointEntitiy(
                            x = Random.nextInt(1, 3000).toFloat(),
                            y = Random.nextInt(1, 3000).toFloat(),
                        )
                    )
                }
                viewModel.insertPoints(listPoints)
            }
        }
    }

    private fun getUserInput(isSuccessInput: () -> Unit) {
        binding.apply {
            val width = etWidth.text.toString()
            val height = etHeight.text.toString()

            if (validateInput(width, height)) {
                val widthInt = width.toInt()
                val heightInt = height.toInt()
                viewModel.saveCanvasSize(CanvasSize(widthInt, heightInt))
                checkCanvasExist()
                isSuccessInput()
            }
        }
    }

    private fun validateInput(width: String, height: String): Boolean {
        binding.apply {
            var isInputEmpty = true
            if (width.isEmpty()) {
                isInputEmpty = ilWidth.setInputError(getString(R.string.must_not_empty))
            }
            if (height.isEmpty()) {
                isInputEmpty = ilHeight.setInputError(getString(R.string.must_not_empty))
            }
            return isInputEmpty
        }
    }

    private fun checkCanvasExist() {
        viewModel.getCanvasSize().observe(this) { canvas ->
            canvasSize = canvas
            if (canvasSize != null) {
                viewModel.getPointByPosition(canvas.width.toFloat(), canvas.height.toFloat()).observe(this@MainActivity) { points ->
                    Log.d(TAG, "point size: ${points.size}")
                    val dotView = CanvasView(this@MainActivity, points)
                    val layoutParams = LinearLayout.LayoutParams(
                        canvas.width,
                        canvas.height
                    )
                    dotView.layoutParams = layoutParams
                    binding.canvasContainer.removeAllViews()
                    binding.canvasContainer.addView(dotView)
                }
            }
        }
    }

    private fun showLayoutInput(isShown: Boolean = true) {
        binding.apply {
            isCardVisible = !isShown
            val iconIndicator = if (isCardVisible) {
                R.drawable.ic_up
            } else {
                R.drawable.ic_down
            }
            TransitionManager.beginDelayedTransition(root, AutoTransition())
            hideableLayout.isVisible = isCardVisible
            btnExpand.setImageResource(iconIndicator)

            if(canvasSize?.width != 0 || canvasSize?.height != 0) {
                etWidth.setText(canvasSize?.width.toString())
                etHeight.setText(canvasSize?.height.toString())
            } else {
                etWidth.text?.clear()
                etHeight.text?.clear()
            }
        }
    }
}