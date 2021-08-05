package com.sedat.kotlinapp.fragment.workmanager

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.work.*
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.databinding.FragmentUsageWorkManagerBinding
import java.util.concurrent.TimeUnit

class UsageWorkManagerFragment : Fragment() {

    private var fragmentbinding: FragmentUsageWorkManagerBinding ?= null
    private val _binding get() = fragmentbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentbinding = FragmentUsageWorkManagerBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Data.Builder().putBoolean("isSend", true).build()
        val constraints = Constraints.Builder()  //Work manager için detaylı ayarlamaların, çalışma koşullarının ayarlandığı kısım.
            .setRequiresCharging(false)  //pil durumu önemli olmasın.
            .build()

        //----------------------------------------------------------------------------------------

        //Arkaplanda yapılacak işi bir defa çalıştırmak için:
        /*val myWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
            .setConstraints(constraints)
            .setInputData(data)
            .setInitialDelay(2, TimeUnit.SECONDS) //işlemi 2sn sonra başlat.
            .build()

        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest) //Workmanager i çalıştır.*/

        //----------------------------------------------------------------------------------------

        //Arkaplanda yapılacak işi periyodik olarak çalıştırmak için:
        //Min 15dk süre verilebilir
        /*val myWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<MyWorkManager>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)*/

        //----------------------------------------------------------------------------------------

        //Workmanager i iptal etmek için:
        //WorkManager.getInstance(requireContext()).cancelWorkById(myWorkRequest.id)

        //----------------------------------------------------------------------------------------

        //OneTimeRequest leri zincirleme(biri bittiğinde diğerine geç) şekilde çalıştırmak için:
        /*val oneTimeRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
                .setConstraints(constraints)
                .setInputData(data)
                .build()

        WorkManager.getInstance(requireContext()).beginWith(oneTimeRequest)  //işi bitir
                .then(oneTimeRequest)  //sonra buradaki işe başla
                .then(oneTimeRequest)  //sonra buradaki işe başla.
                .enqueue()*/

        //----------------------------------------------------------------------------------------

        /*val myWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
            .setConstraints(constraints)
            .setInputData(data)
            .setInitialDelay(2, TimeUnit.SECONDS) //işlemi 2sn sonra başlat.
            .build()

        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(myWorkRequest.id).observe(viewLifecycleOwner, Observer {
            if(it.state == WorkInfo.State.CANCELLED)
                println("iş iptla edildi.")
            else if(it.state == WorkInfo.State.RUNNING)
                println("çalışıyor.")
        })*/

        //----------------------------------------------------------------------------------------

        if(isMyWorkerRunning()){
            _binding.btnSend.visibility = View.GONE
            _binding.btnCancel.visibility = View.VISIBLE
            _binding.time.text = "Bekleniyor..."
        }else{
            _binding.btnSend.visibility = View.VISIBLE
            _binding.btnCancel.visibility = View.GONE
            _binding.time.text = "Bildirimi 30sn sonra gönder"
        }

        _binding.btnSend.setOnClickListener {

            //Aynı tag ismine sahip bir workmanager zaten çalışıyorsa hata, çakışma ya da ikinci workmanager in çalışmasını engeller.
            //(Tek bir workmanager ve tag kullanıldığı için)
            if(isMyWorkerRunning()){
                return@setOnClickListener
            }

            val workRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
                    .addTag("MY_WORK_MANAGER")
                    .setConstraints(constraints)
                    .setInputData(data)
                    .setInitialDelay(30, TimeUnit.SECONDS)
                    .build()

            WorkManager.getInstance(requireContext()).enqueue(workRequest)
            _binding.time.text = "Bekleniyor..."
            it.visibility = View.GONE
            _binding.btnCancel.visibility = View.VISIBLE
        }

        _binding.btnCancel.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWorkByTag("MY_WORK_MANAGER")
            _binding.time.text = "Bildirimi 30sn sonra gönder"
            _binding.btnSend.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
    }


    //Aynı tag a sahip ikinci bir workmanager çalışmasını engellemek için bu fonksiyon kullanıldı.
    private fun isMyWorkerRunning(): Boolean{
        val status: List<WorkInfo>
        try {

            status = WorkManager.getInstance(requireContext()).getWorkInfosByTag("MY_WORK_MANAGER").get()
            for (i in status){
                if(i.state == WorkInfo.State.RUNNING || i.state == WorkInfo.State.ENQUEUED){
                    return true
                }
            }
            return false

        }catch (e: Exception){
            e.printStackTrace()
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentbinding = null
    }


}