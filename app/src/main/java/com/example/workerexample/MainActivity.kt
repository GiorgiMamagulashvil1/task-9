package com.example.workerexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.workerexample.data.model.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm: UserVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val dataFetcherRequest = OneTimeWorkRequest.Builder(DataFetcherWorker::class.java).build()
        val dbWorkerRequest = OneTimeWorkRequest.Builder(DatabaseWorker::class.java).build()

        WorkManager.getInstance(this).beginWith(dataFetcherRequest)
            .then(dbWorkerRequest)
            .enqueue()
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(dbWorkerRequest.id)
            .observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    vm.getData()
                }
            }
        lifecycleScope.launch {
            vm.data.collect {
                it?.let {
                    setUpRecycler(it)
                }
            }
        }
    }

    private fun setUpRecycler(data: List<User>) {
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserAdapter(data)
        }
    }
}