//package com.perfect.persuitelead.View.Activity
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.*
//import android.content.*
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.location.Address
//import android.location.Geocoder
//import android.location.Location
//import android.location.LocationManager
//import android.net.ConnectivityManager
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.provider.CalendarContract
//import android.provider.Settings
//import android.util.Base64
//import android.util.Log
//import android.util.TypedValue
//import android.view.*
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.GravityCompat
//import androidx.core.view.get
//import androidx.drawerlayout.widget.DrawerLayout
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//import androidx.viewpager.widget.ViewPager
//import com.bumptech.glide.Glide
//import com.google.android.gms.location.*
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.navigation.NavigationView
//import com.google.android.material.textfield.TextInputEditText
//import com.google.android.material.textfield.TextInputLayout
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.remoteconfig.FirebaseRemoteConfig
//import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
//import com.ismaeldivita.chipnavigation.ChipNavigationBar
//import com.perfect.nbfcmscore.Helper.PicassoTrustAll
//import com.perfect.persuitelead.interfaces.MyCallback
//import com.perfect.persuitelead.Helper.Config
//import com.perfect.persuitelead.Helper.*
//
//import com.perfect.persuitelead.R
//import com.perfect.prodsuit.Helper.ItemClickListener
//
//
//import org.json.JSONArray
//import org.json.JSONObject
//import java.io.ByteArrayInputStream
//import java.io.ByteArrayOutputStream
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class HomeActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
//    ItemClickListener, MyCallback {
//
//    var TAG = "HomeActivity"
//    lateinit var context: Context
//    lateinit var changempinViewModel: ChangeMpinViewModel
//    lateinit var bannerListViewModel: BannerListViewModel
//    lateinit var companyLogoViewModel: CompanyLogoViewModel
//    lateinit var company: BannerListViewModel
//    private var progressDialog: ProgressDialog? = null
//    private var drawer_layout: DrawerLayout? = null
//    private var nav_view: NavigationView? = null
//    private var btn_menu: ImageView? = null
//    private var btn_chat: ImageView? = null
//    private var imgAttendance: ImageView? = null
//    private var imgv_logo: ImageView? = null
//    private var llservice: LinearLayout? = null
//    private var rlnotification: RelativeLayout? = null
//    private var ll_dashboard: LinearLayout? = null
//    private var ll_agenda: LinearLayout? = null
//    private var lllead: LinearLayout? = null
//    private var chipNavigationBar: ChipNavigationBar? = null
//    private var mPager: ViewPager? = null
//    private var indicator: CircleIndicator? = null
//    private var currentPage = 0
//    val XMENArray = ArrayList<String>()
//    var XMEN = intArrayOf(0)
//    internal var ll_reminder: LinearLayout? = null
//    internal var ll_report: LinearLayout? = null
//    internal var etdate: EditText? = null
//    internal var ettime: EditText? = null
//    internal var etdis: EditText? = null
//    internal var tv_navName: TextView? = null
//    internal var name_employee: TextView? = null
//    internal var tv_navDateTime: TextView? = null
//    internal var tv_navStatus: TextView? = null
//    internal var tv_Name: TextView? = null
//    internal var tv_DateTime: TextView? = null
//    internal var txtv_notfcount: TextView? = null
//    internal var tv_Status: TextView? = null
//    internal var yr: Int =0
//    internal var month:Int = 0
//    internal var day:Int = 0
//    internal var hr:Int = 0
//    internal var min:Int = 0
//    private var mYear: Int =0
//    private var mMonth:Int = 0
//    private var mDay:Int = 0
//    private var mHour:Int = 0
//    private var mMinute:Int = 0
//    val PERMISSION_REQUEST_WRITE_CALENDAR=2
//    private val REQUEST_ID_MULTIPLE_PERMISSIONS = 2
//    val callbackId = 42
//    val CALENDAR_PROJECTION=3
//    private val CALENDER_REQUEST_CODE=123;
//    val PERMISSION_ID = 42
//    var logo: String?=""
//    lateinit var notificationViewModel: NotificationViewModel
//    lateinit var mFusedLocationClient: FusedLocationProviderClient
//    var addresses: List<Address>? = null
//    var geocoder: Geocoder? = null
//    var address : String = ""
//    var city : String = ""
//    var state : String = ""
//    var country : String = ""
//    var postalCode : String = ""
//    var knownName : String = ""
//    var strLongitue : String = ""
//    var strLatitude : String = ""
//    var IsOnline : String = "2"
//    var SubMode : String = ""
//    private var imgv_fav: ImageView? = null
//    lateinit var attendanceAddViewModel: AttendanceAddViewModel
//    lateinit var dashboardcountViewModel: DashBoardCountViewModel
//    val UPDATE_INTERVAL = 1500L
//    private val updateWidgetHandler = Handler()
//    var swipe: SwipeRefreshLayout?=null
//    var adapter: NotificationAdapter? = null
//
//    // Licencing
//
//    private var ll_leads: LinearLayout? = null
//    private var ll_service: LinearLayout? = null
//    private var ll_collection: LinearLayout? = null
////    private var llcompany_name1: LinearLayout? = null
//
//    private var im_leads: ImageView? = null
//    private var im_service: ImageView? = null
//    private var im_collection: ImageView? = null
//
//    private var tv_leads: TextView? = null
//    private var tv_service: TextView? = null
//    private var tv_collection: TextView? = null
//    private var tvv_count_home_1: TextView? = null
//    private var tvv_name_home_2: TextView? = null
//    private var tvv_count_home_3: TextView? = null
//    private var tvv_name_home_4: TextView? = null
//    private var tvv_count_home_5: TextView? = null
//    private var tvv_name_home_6: TextView? = null
//    private var tvv_count_home_7: TextView? = null
//    private var tvv_name_home_8: TextView? = null
//
//    private var recyHomegrid: RecyclerView? = null
//    private var recy_count_home: RecyclerView? = null
//    lateinit var homeArrayList: JSONArray
//    lateinit var homeArrayCountlabel: JSONArray
//    lateinit var homeArrayCountsort: JSONArray
//    lateinit var homeArraySort: JSONArray
//    var notificationCount : String = "0"
//    lateinit var adapterHome : HomeGridAdapter
//
//    private var img_techpartner: ImageView? = null
//    private var img_logo: ImageView? = null
//    var activity = "1"
//
////    var mJobScheduler: JobScheduler? = null
//
//    lateinit var dashCountArrayList: JSONArray
////    lateinit var recy_count_home: RecyclerView
//
//
//    private lateinit var alarmManager: AlarmManager
//    private lateinit var pendingIntent: PendingIntent
//
//    private val CALENDAR_PERMISSION_REQUEST_CODE = 1
//    var dashboardcount = 0
//
//    private lateinit var dataChatRef : DatabaseReference
//    var tableCount = 0
//    var logoutMode = 1
//    private lateinit var networkChangeReceiver: NetworkChangeReceiver
//    @SuppressLint("SuspiciousIndentation")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        setContentView(R.layout.activity_homemain)
//        context = this@HomeActivity
//        attendanceAddViewModel = ViewModelProvider(this).get(AttendanceAddViewModel::class.java)
//        dashboardcountViewModel = ViewModelProvider(this).get(DashBoardCountViewModel::class.java)
//        setRegViews()
//        Config.setRedirection(context,"")
//        bottombarnav()
////        getBannerlist()
//        getCompanyLogo()
//        dataChatRef = FirebaseDatabase.getInstance().getReference("Chat")
//       /* val pm = context.packageManager
//        val hasPerm = pm.checkPermission(
//            Manifest.permission.READ_CALENDAR,
//            context.packageName
//        )*/
//
//            // do stuff
//            getCalendarId(context)
//            // Toast.makeText(applicationContext,"granted",Toast.LENGTH_LONG).show()
//
//
//
//     //   Log.i("HASPERM",hasPerm.toString())
//       // getCalenderPermission()
//        checkAndRequestPermissions()
//       // getLocationTracker()
//        dashboardcount = 0
//        getDashBoardCount()
////        getServiceNotification()
//        getNotfCount()
//        SubMode = "2"
//
////        if (hasCalendarPermission()) {
////            // Permission is already granted, perform your calendar-related operations here
////            Log.e(TAG,"269991    Granted")
////        } else {
////            // Permission is not granted, request it
////            requestCalendarPermission()
////        }
//
//        setMenuItemHidind()
//
//        AddAttendanceApi(strLatitude,strLongitue,address)
//        checkAttendance()
//        setTechnologyPartner()
//
////        startService(Intent(this, MyFirebaseMessagingService::class.java))
////        FirebaseApp.initializeApp(this)
//        // Log.e(TAG,"Token  99991    "+  FirebaseMessaging.getInstance().token)
//
//        FireBaseConfig.ServiceStart(context)
//        FireBaseConfig.getToken(context)
//
//
////        FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String?> ->
////            Log.e("spalsh", task.result!!)
////            if (task.isSuccessful){
////                Log.e(TAG,"Token  99991    "+ task.result!!)
////                fetchFcmServerKey()
////
////            }
////        }
//
//        swipe?.setOnRefreshListener {
//            dashboardcount = 0
//            getDashBoardCount()
//            getNotfCount()
////            adapter?.notifyDataSetChanged()
//            swipe?.isRefreshing=false
//        }
//
//        firebaseNotificationCount()
//
//        networkChangeReceiver = NetworkChangeReceiver()
//        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//
//    }
//
//    private fun firebaseNotificationCount() {
//        dataChatRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//
//
//            }
//
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//        })
//    }
//
//
//    private fun getCalenderRequest() {
//        TODO("Not yet implemented")
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALENDAR),CALENDER_REQUEST_CODE)
//    }
//
//
//    /////
//
//    private fun hasCalendarPermission(): Boolean {
//        val calendarPermission = Manifest.permission.READ_CALENDAR
//        val permissionStatus = ContextCompat.checkSelfPermission(this, calendarPermission)
//        return permissionStatus == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun requestCalendarPermission() {
//        val calendarPermission = Manifest.permission.READ_CALENDAR
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(calendarPermission),
//            CALENDAR_PERMISSION_REQUEST_CODE
//        )
//
//    }
//
//  //  override fun onRequestPermissionsResult(
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//       super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        Log.e(TAG,"REQUEST CODE "+ requestCode)
//       /* if (requestCode == CALENDAR_PERMISSION_REQUEST_CODE) {
//         //   when (requestCode) {
//               // CALENDAR_PERMISSION_REQUEST_CODE -> {
//                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                        // Permission granted, you can now access the calendar
//                        // Perform your calendar-related operations here
//                        Log.e(TAG, "269992    Granted" + requestCode)
//                        //   Toast.makeText(applicationContext,"Granted",Toast.LENGTH_LONG).show()
//                    }
//                    else  if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//
//                Log.e(TAG,"GRANT CODE "+ grantResults[0] )
//                        if(grantResults[0] == -1)
//                        {
//                            getCalendarId(context)
//                        }
//
//            }
//               // }
//        }*/
//      when (requestCode) {
//          CALENDAR_PERMISSION_REQUEST_CODE -> {
//              // If request is cancelled, the result arrays are empty.
//              if ((grantResults.isNotEmpty() &&
//                          grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                  Log.e(TAG, "269992    Granted" + requestCode)
//
//              }
//              else {
//
//                  //Now further we check if used denied permanently or not
//                  if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                          Manifest.permission.READ_CALENDAR)) {
//                      // case 4 User has denied permission but not permanently
//                      ActivityCompat.requestPermissions(
//                          this,
//                          arrayOf(Manifest.permission.READ_CALENDAR),
//                          1
//                      )
//                  } else {
//                      startActivity(
//                          Intent(
//                              Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                              Uri.parse("package:" + BuildConfig.APPLICATION_ID)
//                          )
//                      )
//                  }
//
//
//
//
//                  // Ignore all other requests.
//              }
//
//          }
//
//          // Add other 'when' lines to check for other
//          // permissions this app might request.
//          else -> {
//              ActivityCompat.requestPermissions(
//                  this,
//                  arrayOf(Manifest.permission.READ_CALENDAR),
//                  1
//              )
//
//              // Ignore all other requests.
//          }
//      }
//
//      /*  when (requestCode) {
//            CALENDER_REQUEST_CODE -> {
//
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
//
//                } else {
//                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
//
//
//            }
//        }
//    }*/
//
//
//    }
//
//
//    ////
//
//    private fun setMenuItemHidind() {
//        try {
//            val menu: Menu = nav_view!!.getMenu()
//            val menuproduct = menu.findItem(R.id.nav_productenquiry)
//
//            val ModuleListSP = context.getSharedPreferences(Config.SHARED_PREF54, 0)
//            val jsonObj = JSONObject(ModuleListSP.getString("ModuleList",""))
//            var iInventory = jsonObj!!.getString("INVENTORY")
//
//            if (!iInventory.equals("true")){
//                menuproduct.setVisible(false);
//            }
//
//        }catch (e : Exception){
//
//        }
//
//
//    }
//
//    private fun fetchFcmServerKey() {
//        val remoteConfig = FirebaseRemoteConfig.getInstance()
//        val configSettings = FirebaseRemoteConfigSettings.Builder()
//            .setMinimumFetchIntervalInSeconds(30) // Set the minimum fetch interval (optional)
//            .build()
//        remoteConfig.setConfigSettingsAsync(configSettings)
//        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val fcmServerKey = remoteConfig.getString("fcm_server_key")
//                Log.e(TAG,"fcmServerKey  9999100    "+ fcmServerKey!!)
//                // Use the fcmServerKey as needed
//            } else {
//                // Fetching failed, handle the error
//            }
//        }
//    }
//
//
//    private fun getLocationTracker() {
//        try {
//
//            val lat1 = 11.238127908836669 // Latitude of location 1
//
//            val lon1 = 75.83772864713805 // Longitude of location 1
//
//            val lat2 = 11.25938203131191 // Latitude of location 2
//
//            val lon2 = 75.78624313709264 // Longitude of location 2
//
//            val distance = calculateDistance(lat1, lon1, lat2, lon2)
//            Log.e(TAG,"246666  Distance "+distance)
//
//            val db = DBHelper(this, null)
//            db.getLocations()
//          //  db.delete()
//
//
//
//         //   setAlarm()
////            val gpsStatusReceiver = LocationReceiver()
////            val filter1 = IntentFilter("com.example.helloworld.custombroadcasts.CUSTOM_BROADCAST")
////            registerReceiver(gpsStatusReceiver, filter1)
//
////            val alarmReceiver = AlarmReceiver()
////            val filter1 = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
////            registerReceiver(alarmReceiver, filter1)
//
////            BatteryOptimizationHelper(context).requestBatteryOptimizationExemption()
////            startService(Intent(this, LocationServiceCheck::class.java))
//           // startService(Intent(this, GPSService::class.java))
//        //    startService(Intent(this, LocationService::class.java))
//
//
//
//            //  Working New
//            val UtilityListSP = context.getSharedPreferences(Config.SHARED_PREF57, 0)
//            val jsonObj = JSONObject(UtilityListSP.getString("UtilityList", ""))
//            var bTracker = jsonObj!!.getString("LOCATION_TRACKING").toBoolean()
//            Log.e(TAG,"191110   "+bTracker)
//            if (bTracker){
//                Log.e(TAG,"191111   "+bTracker)
//
//                val isMyServiceRunning = Config.isServiceRunning(context, NotificationLocationService::class.java)
//                if (!isMyServiceRunning){
//                    Log.e(TAG,"191111001   "+bTracker)
//                    startService(Intent(this, NotificationLocationService::class.java))
//
//                }else{
//                    Log.e(TAG,"191111002   "+bTracker)
//                }
//
//
//            }else{
//                Log.e(TAG,"191112   "+bTracker)
//
//                val isMyServiceRunning = Config.isServiceRunning(context, NotificationLocationService::class.java)
//                if (isMyServiceRunning){
//
//                    stopService(Intent(this, NotificationLocationService::class.java))
//
//                }
//
//
//
//            }
//
////
////            // Working Code
////            val UtilityListSP = context.getSharedPreferences(Config.SHARED_PREF57, 0)
////            val jsonObj = JSONObject(UtilityListSP.getString("UtilityList", ""))
////            var bTracker = jsonObj!!.getString("LOCATION_TRACKING").toBoolean()
////            Log.e(TAG,"191110   "+bTracker)
////            if (bTracker){
////                Log.e(TAG,"191111   "+bTracker)
//////                val isMyServiceRunning = Config.isServiceRunning(context, LocationUpdateService::class.java)
//////                if (!isMyServiceRunning){
//////                    val serviceIntent = Intent(this, LocationUpdateService::class.java)
//////                    startService(serviceIntent)
//////                }
////
////                val isMyServiceRunning = Config.isServiceRunning(context, LocationService::class.java)
////                if (!isMyServiceRunning){
////                    Log.e(TAG,"191111001   "+bTracker)
////                    val gpsStatusReceiver = GpsStatusReceiver()
////                    val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
////                    registerReceiver(gpsStatusReceiver, filter)
////
////                    val serviceIntent = Intent(this, LocationService::class.java)
////                    startService(serviceIntent)
////
////                }else{
////                    Log.e(TAG,"2   "+bTracker)
////                }
////
////
////            }else{
////                Log.e(TAG,"191112   "+bTracker)
//////                val isMyServiceRunning = Config.isServiceRunning(context, LocationUpdateService::class.java)
//////                if (isMyServiceRunning){
//////                    Log.e(TAG,"1911123   "+bTracker)
//////                    val serviceIntent = Intent(this, LocationUpdateService::class.java)
//////                    stopService(serviceIntent)
//////                }
////
////                val isMyServiceRunning = Config.isServiceRunning(context, LocationService::class.java)
////                if (isMyServiceRunning){
////
////                    val gpsStatusReceiver = GpsStatusReceiver()
////                    unregisterReceiver(gpsStatusReceiver)
////
////                    Log.e(TAG,"1911123   "+bTracker)
////                    val serviceIntent = Intent(this, LocationService::class.java)
////                    stopService(serviceIntent)
////                }
////
////
////
////            }
//        }catch (e : Exception){
//
//            Log.e(TAG,"32222    "+e.toString())
//        }
//    }
//
//
//    private fun getServiceNotification() {
//        try {
//            val Loginpref = applicationContext.getSharedPreferences(Config.SHARED_PREF, 0)
//            if (Loginpref.getString("loginsession", null) != null && !Loginpref.getString("loginsession", null
//                )!!.isEmpty() && Loginpref.getString("loginsession", null) == "Yes"){
//                Log.e(TAG,"55555555666669999   ")
////
////                    startService(Intent(this, NotificationService::class.java))
////
//
//            }
////            else{
////                    Log.e(TAG,"6666666666666   ")
////                    stopService(Intent(this, NotificationService::class.java))
//////
////            }
//
//        }catch (e : Exception){
//
//        }
//
//    }
//
//    private fun setTechnologyPartner() {
//
//        try {
//            val IMAGE_URLSP = applicationContext.getSharedPreferences(Config.SHARED_PREF29, 0)
//            val TechnologyPartnerImageSP = applicationContext.getSharedPreferences(Config.SHARED_PREF20, 0)
//            val AppIconImageCodeSP = applicationContext.getSharedPreferences(Config.SHARED_PREF19, 0)
//            var IMAGEURL = IMAGE_URLSP.getString("IMAGE_URL","")
//
//            val TechnologyPartnerImage  = IMAGEURL + TechnologyPartnerImageSP.getString("TechnologyPartnerImage", "")
//            PicassoTrustAll.getInstance(this@HomeActivity)!!.load(TechnologyPartnerImage).error(R.drawable.svg_trans).into(img_techpartner)
//
//            val AppIconImageCode  = IMAGEURL + AppIconImageCodeSP.getString("AppIconImageCode", "")
//            Log.e(TAG,"AppIconImageCode   191  "+AppIconImageCode)
//            PicassoTrustAll.getInstance(this@HomeActivity)!!.load(AppIconImageCode).error(R.drawable.svg_trans).into(img_logo)
//        }catch (e : Exception){
//
//        }
//
//
//
//    }
//
//    private var updateWidgetRunnable: Runnable = Runnable {
//        run {
//            //Update UI
//            // Re-run it after the update interval
//
////            getNotfCount()
//            updateWidgetHandler.postDelayed(updateWidgetRunnable, UPDATE_INTERVAL)
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        //getCalendarId(context)
//        Config.setRedirection(context,"")
//       /* val pm = context.packageManager
//        val hasPerm = pm.checkPermission(
//            Manifest.permission.READ_CALENDAR,
//            context.packageName
//        )*/
//       /* if (hasPerm != PackageManager.PERMISSION_GRANTED) {
//            // do stuff
//            getCalendarId(context,callbackId)
//            // Toast.makeText(applicationContext,"granted",Toast.LENGTH_LONG).show()
//        }*/
//
//
////        getNotfCount()
//        updateWidgetHandler.postDelayed(updateWidgetRunnable, UPDATE_INTERVAL)
//    }
//
//
//    // REMOVE callback if app in background
//    override fun onPause() {
//        super.onPause()
//      //  getCalendarId(context)
//       /* val pm = context.packageManager
//        val hasPerm = pm.checkPermission(
//            Manifest.permission.READ_CALENDAR,
//            context.packageName
//        )
//        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
//            // do stuff
//            getCalendarId(context,callbackId)
//            // Toast.makeText(applicationContext,"granted",Toast.LENGTH_LONG).show()
//        }*/
//
//
//        updateWidgetHandler.removeCallbacks(updateWidgetRunnable);
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        dashboardcount = 0
//        getDashBoardCount()
//        Config.setRedirection(context,"")
//
//        networkChangeReceiver = NetworkChangeReceiver()
//        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//
//           // getCalendarId(context)
//            // Toast.makeText(applicationContext,"granted",Toast.LENGTH_LONG).show()
//
//
//    }
//
//    private fun checkAttendance() {
//        val UtilityListSP = applicationContext.getSharedPreferences(Config.SHARED_PREF57, 0)
//        val jsonObj = JSONObject(UtilityListSP.getString("UtilityList", ""))
//        var boolAttendance = jsonObj!!.getString("ATTANCE_MARKING").toBoolean()
//        if (boolAttendance){
//            val StatusSP = applicationContext.getSharedPreferences(Config.SHARED_PREF63, 0)
//            var status = StatusSP.getString("Status","")
//            if (status.equals("0") || status.equals("")){
//                Common.punchingRedirectionConfirm(this,"","")
//            }
//
//        }
//    }
//
//
//    private fun getNotfCount() {
//
//        context = this@HomeActivity
//        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
//        when (Config.ConnectivityUtils.isConnected(this)) {
//            true -> {
////                progressDialog = ProgressDialog(this, R.style.Progress)
////                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
////                progressDialog!!.setCancelable(false)
////                progressDialog!!.setIndeterminate(true)
////                progressDialog!!.setIndeterminateDrawable(this.resources.getDrawable(R.drawable.progress))
////                progressDialog!!.show()
//                notificationViewModel.getNotificaationlist(this)!!.observe(
//                    this,
//                    Observer { notificationSetterGetter ->
//                        val msg = notificationSetterGetter.message
//                        Log.e(TAG,"5766660  ")
//                        if (msg!!.length > 0) {
//                            Log.e(TAG,"5766661  ")
//                            val jObject = JSONObject(msg)
//                            if (jObject.getString("StatusCode") == "0") {
//                                val jobjt = jObject.getJSONObject("NotificationDetails")
//                                var count =jobjt.getString("count")
//                                Log.i("Array size", count)
//                                txtv_notfcount!!.text=count
////                                if (adapterHome != null){
////                                    Log.e(TAG,"adapterHome  2211  ")
////                                    notificationCount = count
////                                    val jsonObject = homeArraySort.getJSONObject(2)
////                                    jsonObject.put("count",notificationCount)
////                             //       recyHomegrid!!.adapter!!.notifyItemChanged(2)
////
//////                                    adapterHome = HomeGridAdapter(this@HomeActivity, homeArraySort,notificationCount!!)
//////                                    recyHomegrid!!.adapter!!.notifyDataSetChanged()
////
////
////                                }
//
//
//                            } else {
//                                Log.e(TAG,"187777   "+jObject.getString("StatusCode"))
//                                txtv_notfcount!!.text="0"
////                                if (adapterHome != null){
////                                    Log.e(TAG,"adapterHome  2212  ")
////                                    notificationCount = "0"
////                                    val jsonObject = homeArraySort.getJSONObject(2)
////                                    jsonObject.put("count",notificationCount)
////                                    recyHomegrid!!.adapter!!.notifyItemChanged(2)
////                                }
////                                val builder = AlertDialog.Builder(
////                                    this@HomeActivity,
////                                    R.style.MyDialogTheme
////                                )
////                                builder.setMessage(jObject.getString("EXMessage"))
////                                builder.setPositiveButton("Ok") { dialogInterface, which ->
////                                }
////                                val alertDialog: AlertDialog = builder.create()
////                                alertDialog.setCancelable(false)
////                                alertDialog.show()
//
//                            }
//                        } else {
////                            Toast.makeText(
////                                applicationContext,
////                                "Some Technical Issues.",
////                                Toast.LENGTH_LONG
////                            ).show()
//                        }
//                    })
//           //     progressDialog!!.dismiss()
//            }
//            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                    .show()
//            }
//
//
//        }
//    }
//
//    private fun getDashBoardCount() {
//
//        when (Config.ConnectivityUtils.isConnected(this)) {
//            true -> {
////                progressDialog = ProgressDialog(context, R.style.Progress)
////                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
////                progressDialog!!.setCancelable(false)
////                progressDialog!!.setIndeterminate(true)
////                progressDialog!!.setIndeterminateDrawable(context.resources.getDrawable(R.drawable.progress))
////                progressDialog!!.show()
//                dashboardcountViewModel.getDashBoardCount(this)!!.observe(
//                    this,
//                    Observer { serviceSetterGetter ->
//                        val msg = serviceSetterGetter.message
//                        if (msg!!.length > 0) {
//                            val jObject = JSONObject(msg)
//                            Log.e(TAG,"msg   353   "+msg)
//                            if (jObject.getString("StatusCode") == "0") {
//
//                                val jobjt = jObject.getJSONObject("UserTaskList")
//                                dashCountArrayList = jobjt.getJSONArray("UserTaskListData")
//                                if (dashCountArrayList.length()>0){
//                                    if (dashboardcount == 0){
//                                        dashboardcount++
//
//                                        Log.e(TAG,"dashCountArrayList   "+dashCountArrayList)
//
//                                        val HorizontalLayout = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
//                                        recy_count_home!!.setLayoutManager(HorizontalLayout)
//                                        val adapterDash = HomeGrideCountAdapter(this@HomeActivity,dashCountArrayList)
//                                        recy_count_home!!.adapter = adapterDash
//                                        adapterDash.setClickListener(this@HomeActivity)
//
//
//                                        val handler = Handler()
//                                        val runnable = object : Runnable {
//                                            override fun run() {
//                                                // Scroll to the next card
//                                                recy_count_home!!.smoothScrollToPosition((recy_count_home!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() + 1)
//                                                handler.postDelayed(this, 3000) // Delay in milliseconds
//                                            }
//                                        }
//
//                                        handler.postDelayed(runnable, 3000) // Start auto-sliding after 3 seconds
//                                    }
//
//                                }
//
//                            } else {
////                                val builder = AlertDialog.Builder(
////                                    this@HomeActivity,
////                                    R.style.MyDialogTheme
////                                )
////                                builder.setMessage(jObject.getString("EXMessage"))
////                                builder.setPositiveButton("Ok") { dialogInterface, which ->
////                                }
////                                val alertDialog: AlertDialog = builder.create()
////                                alertDialog.setCancelable(false)
////                                alertDialog.show()
//                            }
//                        } else {
////                            Toast.makeText(
////                                applicationContext,
////                                "Some Technical Issues.",
////                                Toast.LENGTH_LONG
////                            ).show()
//                        }
//                    })
////                progressDialog!!.dismiss()
//            }
//            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                    .show()
//            }
//        }
//    }
//
//
//    private fun bottombarnav() {
//        chipNavigationBar = findViewById(R.id.chipNavigation)
//        val UtilityListSP = applicationContext.getSharedPreferences(Config.SHARED_PREF57, 0)
//        val jsonObj = JSONObject(UtilityListSP.getString("UtilityList", ""))
//        var boolAttendance = jsonObj!!.getString("ATTANCE_MARKING")
//        if (boolAttendance.equals("true")){
//            chipNavigationBar!!.get(2).visibility = View.VISIBLE
//            chipNavigationBar!!.get(1).visibility = View.GONE
//        }else{
//            chipNavigationBar!!.get(2).visibility = View.GONE
//            chipNavigationBar!!.get(1).visibility = View.VISIBLE
//        }
//
//        chipNavigationBar!!.setItemSelected(R.id.home, true)
//        chipNavigationBar!!.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
//            override fun onItemSelected(i: Int) {
//                when (i) {
////                    R.id.home -> {
//////                        val i = Intent(this@HomeActivity, HomeActivity::class.java)
//////                        startActivity(i)
////                        chipNavigationBar!!.setItemSelected(R.id.home, true)
////                    }
//                    R.id.reminder -> {
//
//                        if (ActivityCompat.checkSelfPermission(
//                                applicationContext,
//                                Manifest.permission.READ_CALENDAR
//                            ) == PackageManager.PERMISSION_GRANTED
//                        ) {
//
//                            ActivityCompat.requestPermissions(
//                                this@HomeActivity,
//                                arrayOf(Manifest.permission.READ_CALENDAR),
//                                1
//                            )
//
//                            setReminder()
//                        }
//                        else
//                        {
//                            setPermission()
//                        }
//
//
//
//
//
//
//                    }
//                    R.id.logout -> {
//                       // doLogout()
//                        LogoutBottomSheet()
//                    }
//                    R.id.pucnhing -> {
//                        val myView: View = findViewById(R.id.pucnhing)
//                        Config.disableClick(myView)
//                        chipNavigationBar!!.setItemSelected(R.id.home, true)
//                        val i = Intent(this@HomeActivity, AttendanceMarkingActivity::class.java)
//                        startActivity(i)
//                    }
//                    R.id.quit -> {
//                       // quit()
//                        QuitBottomSheet()
//                    }
//                }
//            }
//        })
//    }
//
//    private fun setPermission() {
//        try
//        {
//
//
//
//            val builder = android.app.AlertDialog.Builder(this)
//            val inflater1 = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val layout = inflater1.inflate(R.layout.calender_popup, null)
//            val btncancel = layout.findViewById(R.id.btncancel) as Button
//            val btnsubmit = layout.findViewById(R.id.btnsubmit) as Button
//            builder.setView(layout)
//            val alertDialog = builder.create()
//
//
//            btncancel.setOnClickListener {
//                Config.Utils.hideSoftKeyBoard(this, it)
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//                alertDialog.dismiss() }
//            btnsubmit.setOnClickListener {
//
//                Config.Utils.hideSoftKeyBoard(this, it)
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//
//                startActivity(Intent().apply {
//                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                    data = Uri.fromParts("package", packageName, null)
//                })
//                alertDialog.dismiss()
//
//            }
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//        }
//        catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//
//    private fun setRegViews() {
//        swipe=findViewById(R.id.swipeRefreshLayout)
//        imgv_fav=findViewById(R.id.imgv_fav)
//        drawer_layout = findViewById(R.id.drawer_layout)
//        nav_view = findViewById(R.id.nav_view)
//        btn_menu = findViewById(R.id.btn_menu)
//        btn_chat = findViewById(R.id.btn_chat)
//        lllead = findViewById(R.id.lllead)
//        llservice = findViewById(R.id.llservice)
//        rlnotification= findViewById(R.id.rlnotification)
//        ll_dashboard = findViewById(R.id.ll_dashboard)
//        ll_agenda = findViewById(R.id.ll_agenda)
//        imgAttendance = findViewById(R.id.imgAttendance)
//      //  imgv_logo = findViewById(R.id.imgv_logo)
//     //   tv_Name = findViewById(R.id.tv_Name)
//        tv_DateTime = findViewById(R.id.tv_DateTime)
//        tv_Status = findViewById(R.id.tv_Status)
//        txtv_notfcount= findViewById(R.id.txtv_notfcount)
//        recy_count_home= findViewById(R.id.recy_count_home)
//
//
//
////        tvv_count_home_1= findViewById(R.id.tvv_count_home_1)
////        tvv_name_home_2= findViewById(R.id.tvv_name_home_2)
////        tvv_count_home_3= findViewById(R.id.tvv_count_home_3)
////        tvv_name_home_4= findViewById(R.id.tvv_name_home_4)
////        tvv_count_home_5= findViewById(R.id.tvv_count_home_5)
////        tvv_name_home_6= findViewById(R.id.tvv_name_home_6)
////        tvv_count_home_7= findViewById(R.id.tvv_count_home_7)
////        tvv_name_home_8= findViewById(R.id.tvv_name_home_8)
//
//        img_techpartner= findViewById(R.id.img_techpartner)
//        img_logo= findViewById(R.id.img_logo)
////        llcompany_name1= findViewById(R.id.llcompany_name1)
//
//
//        val headerView: View = nav_view!!.getHeaderView(0)
//        tv_navName = headerView!!.findViewById(R.id.tv_navName)
//        name_employee = findViewById(R.id.name_employee)
//        tv_navDateTime = headerView!!.findViewById(R.id.tv_navDateTime)
//        tv_navStatus = headerView!!.findViewById(R.id.tv_navStatus)
//
//        btn_menu!!.setOnClickListener(this)
//        btn_chat!!.setOnClickListener(this)
//        lllead!!.setOnClickListener(this)
//        llservice!!.setOnClickListener(this)
//        ll_dashboard!!.setOnClickListener(this)
//        ll_agenda!!.setOnClickListener(this)
//        nav_view!!.setNavigationItemSelectedListener(this)
//        nav_view!!.setItemIconTintList(null);
//        mPager = findViewById(R.id.pager)
//        indicator =findViewById(R.id.indicator)
//        ll_reminder =findViewById(R.id.ll_reminder)
//        ll_reminder!!.setOnClickListener(this)
//        ll_report =findViewById(R.id.ll_report)
//        ll_report!!.setOnClickListener(this)
//        rlnotification!!.setOnClickListener(this)
//        imgAttendance!!.setOnClickListener(this)
//        imgv_fav!!.setOnClickListener(this)
////        llcompany_name1!!.setOnClickListener(this)
//      //  txtv_notfcount!!.setOnClickListener(this)
//
//        ll_leads = findViewById(R.id.ll_leads)
//        ll_service = findViewById(R.id.ll_service)
//        ll_collection = findViewById(R.id.ll_collection)
//
//        im_leads = findViewById(R.id.im_leads)
//        im_service = findViewById(R.id.im_service)
//        im_collection = findViewById(R.id.im_collection)
//
//        tv_leads = findViewById(R.id.tv_leads)
//        tv_service = findViewById(R.id.tv_service)
//        tv_collection = findViewById(R.id.tv_collection)
//
//        recyHomegrid = findViewById(R.id.recyHomegrid)
//
//        ll_leads!!.setOnClickListener(this)
//        ll_service!!.setOnClickListener(this)
//        ll_collection!!.setOnClickListener(this)
//
//
//        setLicensing()
//
//    }
//
//    private fun setLicensing() {
//        var iLead = 1
//        var iService = 1
//        var iCollection = 1
//
//
//
//        if (iLead == 1 && iService == 1 && iCollection == 1){
//            ll_leads!!.visibility = View.VISIBLE
//            ll_service!!.visibility = View.VISIBLE
//            ll_collection!!.visibility = View.VISIBLE
//
//            ll_leads!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_left)
//            ll_service!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_no)
//            ll_collection!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_right)
//        }
//        else if(iLead == 1 && iService == 1){
//            ll_leads!!.visibility = View.VISIBLE
//            ll_service!!.visibility = View.VISIBLE
//            ll_collection!!.visibility = View.GONE
//
//            ll_leads!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_left)
//            ll_service!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_right)
//
//        }
//        else if(iService == 1 && iCollection == 1){
//            ll_leads!!.visibility = View.GONE
//            ll_service!!.visibility = View.VISIBLE
//            ll_collection!!.visibility = View.VISIBLE
//
//            ll_service!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_left)
//            ll_collection!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_right)
//        }
//        else if(iLead == 1 && iCollection == 1){
//            ll_leads!!.visibility = View.VISIBLE
//            ll_service!!.visibility = View.GONE
//            ll_collection!!.visibility = View.VISIBLE
//
//            ll_leads!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_left)
//            ll_collection!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_right)
//        }
//        else if(iLead == 1 ){
//            ll_leads!!.visibility = View.VISIBLE
//            ll_service!!.visibility = View.GONE
//            ll_collection!!.visibility = View.GONE
//            ll_leads!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_lnr)
//            ll_leads!!.orientation = LinearLayout.HORIZONTAL
//            tv_leads!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.licence_font))
//            tv_leads!!.setPadding(25, 0, 0, 0);
//            im_leads!!.getLayoutParams().height = 80
//            im_leads!!.getLayoutParams().width = 80
//        }
//        else if(iService == 1 ){
//            ll_leads!!.visibility = View.GONE
//            ll_service!!.visibility = View.VISIBLE
//            ll_collection!!.visibility = View.GONE
//            ll_service!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_lnr)
//            ll_service!!.orientation = LinearLayout.HORIZONTAL
//            tv_service!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.licence_font))
//            tv_service!!.setPadding(25, 0, 0, 0)
//            im_service!!.getLayoutParams().height = 80
//            im_service!!.getLayoutParams().width = 80
//        }
//        else if(iCollection == 1 ){
//            ll_leads!!.visibility = View.GONE
//            ll_service!!.visibility = View.GONE
//            ll_collection!!.visibility = View.VISIBLE
//            ll_collection!!.background = applicationContext.resources.getDrawable(R.drawable.border_corner_lnr)
//            ll_collection!!.orientation = LinearLayout.HORIZONTAL
//            tv_collection!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.licence_font))
//            tv_collection!!.setPadding(25, 0, 0, 0)
//            im_collection!!.getLayoutParams().height = 80
//            im_collection!!.getLayoutParams().width = 80
//        }
//
//        gridList()
////        gridcount()
//
//
//    }
//
//    private fun gridList() {
//        var gridList = Config.getHomeGrid(this@HomeActivity)
//        Log.e(TAG,"ActionType   44  "+gridList)
//        val jObject = JSONObject(gridList)
//        val jobjt = jObject.getJSONObject("homeGridType")
//        homeArrayList = jobjt.getJSONArray("homeGridDetails")
//
//        Log.e(TAG,"426  :  "+homeArrayList)
//        homeArraySort = JSONArray()
//        for (k in 0 until homeArrayList.length()) {
//            val jsonObject = homeArrayList.getJSONObject(k)
//            if (homeArraySort.length()!=9){
//                homeArraySort.put(jsonObject)
//            }
//        }
//
//        if (homeArraySort.length()>0){
//            val lLayout = GridLayoutManager(this@HomeActivity, 3)
////           val lLayout = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
//            recyHomegrid!!.layoutManager = lLayout as RecyclerView.LayoutManager?
//            adapterHome = HomeGridAdapter(this@HomeActivity, homeArraySort,notificationCount!!)
//            recyHomegrid!!.adapter = adapterHome
//            adapterHome.setClickListener(this@HomeActivity)
//        }
//
//
//
//    }
//
//
////    private fun gridcount() {
////        var gridList = Config.getHomeGrid(this@HomeActivity)
////        Log.e(TAG,"ActionType   44  "+gridList)
////        val jObject = JSONObject(gridList)
////        val jobjt = jObject.getJSONObject("homegridCount")
////        homeArrayCountlabel = jobjt.getJSONArray("homegridCountDetail")
////
////        Log.e(TAG,"426  :  "+homeArrayCountlabel)
////        homeArraySort = JSONArray()
////        for (k in 0 until homeArrayCountlabel.length()) {
////            val jsonObject = homeArrayCountlabel.getJSONObject(k)
////            if (homeArrayCountsort.length()!=4){
////                homeArrayCountsort.put(jsonObject)
////            }
////        }
////
////        if (homeArraySort.length()>0){
//////            val lLayout = GridLayoutManager(this@HomeActivity, 3)
//////           val lLayout = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
//////            recyHomegrid!!.layoutManager = lLayout as RecyclerView.LayoutManager?
//////            adapterHome = HomeGrideCountAdapter(this@HomeActivity, homeArrayCountlabel)
//////            recyHomegrid!!.adapter = adapterHome
//////            adapterHome.setClickListener(this@HomeActivity)
////
////            val HorizontalLayout = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
////            recy_count_home!!.setLayoutManager(HorizontalLayout)
////            val adapterHome = HomeGrideCountAdapter(this@HomeActivity,homeArrayCountlabel!!)
////            recy_count_home!!.adapter = adapterHome
////        }
////
////
////
////    }
//
//    override fun onClick(v: View?) {
//        when(v?.id){
//
//            R.id.imgv_fav -> {
//                val i = Intent(this@HomeActivity, FavActivity::class.java)
//                startActivity(i)
//            }
//
//
//            R.id.btn_menu -> {
//                if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
//                    drawer_layout!!.closeDrawer(GravityCompat.START)
//                } else {
//                    drawer_layout!!.openDrawer(GravityCompat.START)
//                }
//            }
//            R.id.btn_chat -> {
//
//                val UserNameSP = applicationContext.getSharedPreferences(Config.SHARED_PREF2, 0)
//                var MobileNumberSP = applicationContext.getSharedPreferences(Config.SHARED_PREF4, 0)
//                var name = UserNameSP.getString("UserName", "")
//                var mobile = MobileNumberSP.getString("MobileNumber", "")
//
//                val Fbase_NameSP = applicationContext.getSharedPreferences(Config.SHARED_PREF68, 0)
//                val Fbase_NameEditer = Fbase_NameSP.edit()
//                Fbase_NameEditer.putString("Fbase_Name",name)
//                Fbase_NameEditer.commit()
//
//                val Fbase_MobileSP = applicationContext.getSharedPreferences(Config.SHARED_PREF69, 0)
//                val Fbase_MobileEditer = Fbase_MobileSP.edit()
//                Fbase_MobileEditer.putString("Fbase_Mobile",mobile)
//                Fbase_MobileEditer.commit()
//
//
//                val i = Intent(this@HomeActivity, ChatUserListActivity::class.java)
//                i.putExtra("name",name)
//                i.putExtra("mobile",mobile)
//                startActivity(i)
//
////                val i = Intent(this@HomeActivity, ChatRegisterActivity::class.java)
////                startActivity(i)
//
//
//            }
//            R.id.imgAttendance -> {
//                Log.e("HomeActivity","imgAttendance    161  ")
////               if (checkAndRequestPermissions()){
////                   Log.e("HomeActivity","imgAttendance   Granted  161  ")
////                   mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
////                 //  getLocation()
////
////                   attendancConfirmPopup(v)
////               }
//
////                val i = Intent(this@HomeActivity, AttendanceMarkingActivity::class.java)
////                startActivity(i)
//
////                val intent = Intent(this, BarcodeScannerActivity::class.java)
////                startActivity(intent)
//
////                val intent = Intent(this, ProductSearchActivity::class.java)
////                startActivity(intent)
//
////                val i = Intent(this@HomeActivity, ProjectActivity::class.java)
////                startActivity(i)
//
//
////                val i = Intent(this@HomeActivity, StockTransferActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, LeadCorrectionActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, CorrectionSplitupActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, LeadCorrectionActivity::class.java)
////                val i = Intent(this@HomeActivity, CorrectionSplitupActivity::class.java)
////                startActivity(i)
//
//
////                val i = Intent(this@HomeActivity, CorrectionModuleListActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, DashboradCommonActivity::class.java)
////                startActivity(i)
//
//
//
////                val i = Intent(this@HomeActivity, InventoryActivity::class.java)
////                startActivity(i)
//
//
//
////                val i = Intent(this@HomeActivity, StockRequestActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, ChatRegisterActivity::class.java)
////                startActivity(i)
//
//            }
////            R.id.llcompany_name1 -> {
////                val i = Intent(this@HomeActivity, ProjectActivity::class.java)
////                startActivity(i)
////            }
//
//            R.id.ll_leads -> {
//                val i = Intent(this@HomeActivity, LeadActivity::class.java)
//                startActivity(i)
//            }
//
//            R.id.ll_service -> {
//                val i = Intent(this@HomeActivity, ServiceActivity::class.java)
//                startActivity(i)
//            }
//            R.id.ll_collection -> {
//                val i = Intent(this@HomeActivity, EmiActivity::class.java)
//                startActivity(i)
//            }
//
//            R.id.llservice -> {
//                val i = Intent(this@HomeActivity, ServiceActivity::class.java)
//                startActivity(i)
//
////                val i = Intent(this@HomeActivity, SiteVisitActivity::class.java)
////                startActivity(i)
////
////                val i = Intent(this@HomeActivity, CallRemarkActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, AccountDetailsActivity::class.java)
////                startActivity(i)
//            }
//            R.id.lllead -> {
//                val i = Intent(this@HomeActivity, LeadActivity::class.java)
//                startActivity(i)
//            }
//            R.id.ll_agenda -> {
////                https://github.com/PhilJay/MPAndroidChart
//                val i = Intent(this@HomeActivity, AgendaActivity::class.java)
//                startActivity(i)
//            }
//            R.id.ll_dashboard -> {
////                https://github.com/PhilJay/MPAndroidChart
//                val i = Intent(this@HomeActivity, DashboardNewActivity::class.java)
//                startActivity(i)
//            }
//            R.id.ll_reminder -> {
//                val i = Intent(this@HomeActivity, ExpenseActivity::class.java)
//                startActivity(i)
//            }
//            R.id.ll_report -> {
////                val i = Intent(this@HomeActivity, ReportActivity::class.java)
////                startActivity(i)
//                val i = Intent(this@HomeActivity, TicketReportActivity::class.java)
//                startActivity(i)
//            }
//            R.id.rlnotification -> {
//                val i = Intent(this@HomeActivity, NotificationActivity::class.java)
//                startActivity(i)
//
////                val i = Intent(this@HomeActivity, InventoryActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, ApproveActivity::class.java)
////                startActivity(i)
//
////                val i = Intent(this@HomeActivity, ApprovalListDetailActivity::class.java)
////                startActivity(i)
//
//
//
//
//
//            }
//        }
//    }
//
//
//
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//
//            R.id.nav_profile -> {
//                val i = Intent(this@HomeActivity, ProfileActivity::class.java)
//                startActivity(i)
//            }
//            R.id.nav_changempin -> {
//               // changeMpin()
//                changeMpin1()
//            }
//            R.id.nav_productenquiry -> {
//
//                val intent = Intent(this, ProductSearchActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.nav_about -> {
//                val i = Intent(this@HomeActivity, AboutUsActivity::class.java)
//                startActivity(i)
//            }
//            R.id.nav_contact -> {
//                val i = Intent(this@HomeActivity, ContactUsActivity::class.java)
//                startActivity(i)
//            }
//            R.id.nav_intimation -> {
//                val i = Intent(this@HomeActivity, Intimation::class.java)
//                startActivity(i)
//            }
//            R.id.nav_share -> {
//                val shareIntent = Intent()
//                shareIntent.action = Intent.ACTION_SEND
//                shareIntent.type = "text/plain"
//                shareIntent.putExtra(
//                        Intent.EXTRA_TEXT,
//                        getString(R.string.app_name) + "\t" + "Invite You  \n\n For Android Users \n\n http://play.google.com/store/apps/details?id=" + getPackageName() + "\n"
//                )
//                startActivity(Intent.createChooser(shareIntent, "Invite this App to your friends"))
//            }
//            R.id.nav_logout -> {
////                doLogout()
//                LogoutBottomSheet()
//            }
//            R.id.nav_quit -> {
////                quit()
//                QuitBottomSheet()
//            }
//        }
//        drawer_layout!!.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    private fun LogoutRedirection() {
//
//        val db = DBHelper(this, null)
//        var userList = JSONArray()
//        userList = db!!.getRegisteredUserList()
//
//        tableCount = userList.length()
//        logoutMode = 1
//
//
//    }
//
//    private fun LogoutBottomSheet() {
//        // BottomSheet
//
//        val db = DBHelper(this, null)
//        var userList = JSONArray()
//        userList = db!!.getRegisteredUserList()
//
//        tableCount = userList.length()
//        logoutMode = 1
//
//        val dialog = BottomSheetDialog(this)
//        val view = layoutInflater.inflate(R.layout.logout_popup, null)
//
//        val btnNo = view.findViewById<Button>(R.id.btnNo)
//        val btnYes = view.findViewById<Button>(R.id.btnYes)
//        val btnSingleNo = view.findViewById<Button>(R.id.btnSingleNo)
//        val tv_message = view.findViewById<TextView>(R.id.tv_message)
//
//        if (tableCount > 1){
//            tv_message.text = "Do you want to logout Multiple Account?"
//            btnSingleNo!!.visibility = View.VISIBLE
//            btnNo!!.visibility = View.GONE
//        }else{
//            tv_message.text = "Do you want to logout?"
//        }
//
//        btnNo.setOnClickListener {
//            dialog .dismiss()
//            chipNavigationBar!!.setItemSelected(R.id.home, true)
//        }
//        btnSingleNo.setOnClickListener {
//            btnSingleNo!!.visibility = View.GONE
//            btnNo!!.visibility = View.VISIBLE
//            val ResellerNameSP = context.getSharedPreferences(Config.SHARED_PREF32, 0)
//            var resellerName = ResellerNameSP.getString("ResellerName", "")
//            tv_message.text = "Do you want to logout "+resellerName+" Account?"
//
//            logoutMode = 0
//
//        }
//        btnYes.setOnClickListener {
//            dialog.dismiss()
//           // dologoutchanges()
//            Config.logOut(context,logoutMode)
//            startActivity(Intent(this@HomeActivity, SplashActivity::class.java))
//        }
//        dialog.setCancelable(false)
//        dialog!!.setContentView(view)
//
//        dialog.show()
//    }
//
//    private fun QuitBottomSheet() {
//        // BottomSheet
//
//        val dialog = BottomSheetDialog(this)
//        val view = layoutInflater.inflate(R.layout.quit_popup, null)
//
//        val btnNo = view.findViewById<Button>(R.id.btn_No)
//        val btnYes = view.findViewById<Button>(R.id.btn_Yes)
//
//        btnNo.setOnClickListener {
//            dialog .dismiss()
//            chipNavigationBar!!.setItemSelected(R.id.home, true)
//        }
//        btnYes.setOnClickListener {
//            dialog.dismiss()
//            finish()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                finishAffinity()
//            }
//        }
//        dialog.setCancelable(false)
//        dialog!!.setContentView(view)
//
//        dialog.show()
//    }
//
//    private fun doLogout() {
//        try {
//            val dialog1 = Dialog(this)
//            dialog1 .requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog1 .setCancelable(false)
//            dialog1 .setContentView(R.layout.logout_popup)
//            dialog1.window!!.attributes.gravity = Gravity.BOTTOM;
//            val btn_Yes = dialog1 .findViewById(R.id.btnYes) as Button
//            val btn_No = dialog1 .findViewById(R.id.btnNo) as Button
//            btn_No.setOnClickListener {
//                dialog1 .dismiss()
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//            }
//            btn_Yes.setOnClickListener {
//                dialog1.dismiss()
////                dologoutchanges()
////                Config.logOut(context)
////                startActivity(Intent(this@HomeActivity, SplashActivity::class.java))
//            }
//            dialog1.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun dologoutchanges() {
//
//        val loginSP = applicationContext.getSharedPreferences(Config.SHARED_PREF, 0)
//        val loginEditer = loginSP.edit()
//        loginEditer.putString("loginsession", "No")
//        loginEditer.commit()
//
//
//        val loginmobileSP = applicationContext.getSharedPreferences(Config.SHARED_PREF14, 0)
//        val loginmobileEditer = loginmobileSP.edit()
//        loginmobileEditer.putString("Loginmobilenumber", "")
//        loginmobileEditer.commit()
//
//        val companyCodeSP = applicationContext.getSharedPreferences(Config.SHARED_PREF17, 0)
//        val companyCodeEditer = companyCodeSP.edit()
//        companyCodeEditer.putString("companyCode", "")
//        companyCodeEditer.commit()
//
//        val commonAppSP = applicationContext.getSharedPreferences(Config.SHARED_PREF18, 0)
//        val commonAppEditer = commonAppSP.edit()
//        commonAppEditer.putString("commonApp", "")
//        commonAppEditer.commit()
//    }
//
//    private fun quit() {
//        try {
//            val dialog1 = Dialog(this)
//            dialog1 .requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog1 .setCancelable(false)
//            dialog1 .setContentView(R.layout.quit_popup)
//            dialog1.window!!.attributes.gravity = Gravity.BOTTOM;
//            val btn_Yes = dialog1 .findViewById(R.id.btn_Yes) as Button
//            val btn_No = dialog1 .findViewById(R.id.btn_No) as Button
//            btn_No.setOnClickListener {
//                dialog1 .dismiss()
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//            }
//            btn_Yes.setOnClickListener {
//                dialog1.dismiss()
//                finish()
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    finishAffinity()
//                }
//            }
//            dialog1.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun changeMpin1() {
//
//        val dialogMpinSheet = Dialog(this)
//        dialogMpinSheet!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialogMpinSheet!! .setContentView(R.layout.changepin_dialog)
//        dialogMpinSheet!!.window!!.attributes.gravity = Gravity.CENTER_VERTICAL
//        dialogMpinSheet!!.setCancelable(false)
//
//        val window: Window? = dialogMpinSheet!!.getWindow()
//        window!!.setBackgroundDrawableResource(android.R.color.transparent);
//        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//
//        val til_old_mpin = dialogMpinSheet .findViewById(R.id.til_old_mpin) as TextInputLayout
//        val tie_old_mpin = dialogMpinSheet .findViewById(R.id.tie_old_mpin) as TextInputEditText
//        val tie_new_mpin = dialogMpinSheet .findViewById(R.id.tie_new_mpin) as TextInputEditText
//        val tie_confirm_mpin = dialogMpinSheet .findViewById(R.id.tie_confirm_mpin) as TextInputEditText
//
//        val btn_mpin_cancel = dialogMpinSheet .findViewById(R.id.btn_mpin_cancel) as Button
//        val btn_mpin_submit = dialogMpinSheet .findViewById(R.id.btn_mpin_submit) as Button
//
//        btn_mpin_cancel.setOnClickListener {
//            dialogMpinSheet!!.dismiss()
//        }
//
//        btn_mpin_submit.setOnClickListener {
//
//            if (tie_old_mpin!!.text.toString() == null || tie_old_mpin!!.text.toString().isEmpty()) {
////                tie_old_mpin!!.setError("Please Enter Your Old mPin.")
//                Config.snackBarWarning(context,it,"Please Enter Your Old mPin.")
//            }
//            else if (tie_old_mpin!!.text.toString().isNotEmpty() && tie_old_mpin!!.text.toString().length!=6) {
////                tie_old_mpin.setError("Please Enter 6 digit mPin")
//                Config.snackBarWarning(context,it,"Please Enter 6 digit Old mPin")
//            }
//            else if (tie_new_mpin!!.text.toString() == null || tie_new_mpin!!.text.toString().isEmpty()) {
//               // tie_new_mpin!!.setError("Please Enter Your New mPin.")
//                Config.snackBarWarning(context,it,"Please Enter Your New mPin.")
//            }
//            else if (tie_new_mpin!!.text.toString().isNotEmpty() && tie_new_mpin!!.text.toString().length!=6) {
////                tie_new_mpin.setError("Please Enter 6 digit mPin")
//                Config.snackBarWarning(context,it,"Please Enter 6 digit New mPin")
//            }
//            else if (tie_confirm_mpin!!.text.toString() == null || tie_confirm_mpin!!.text.toString().isEmpty()) {
//               // tie_confirm_mpin!!.setError("Please Confirm Your New mPin.")
//                Config.snackBarWarning(context,it,"Please Confirm Your New mPin.")
//            }
//            else if (tie_confirm_mpin!!.text.toString().isNotEmpty() && tie_confirm_mpin!!.text.toString().length!=6) {
//                //  tie_confirm_mpin.setError("Please Enter 6 digit mPin")
//                Config.snackBarWarning(context,it,"Please Enter 6 digit Confirm mPin")
//            }
//            else if (tie_new_mpin!!.text.toString() != tie_confirm_mpin!!.text.toString()) {
//                Config.snackBarWarning(context,it,"New & Confirm mPin doesn't match")
////                val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
////                dialog.setMessage("New & Confirm mPin doesn't match")
////                dialog.setPositiveButton("Ok",
////                    DialogInterface.OnClickListener { dialog, which ->
////                    })
////                val alertDialog: AlertDialog = dialog.create()
////                alertDialog.show()
//            }
//
//            else{
//                dialogMpinSheet!!.dismiss()
//                changempinverficationcode(tie_old_mpin!!.text.toString(), tie_new_mpin!!.text.toString())
//            }
//        }
//
//        dialogMpinSheet!!.show()
//
//    }
//
//    private fun changeMpin() {
//        try {
//            val dialog1 = Dialog(this)
//            dialog1 .requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog1 .setCancelable(true)
//            dialog1 .setContentView(R.layout.changepin_popup)
//            val btnreset = dialog1 .findViewById(R.id.btnreset) as Button
//            val btnSubmit = dialog1 .findViewById(R.id.btnSubmit) as Button
//            val etxt_oldpin = dialog1 .findViewById(R.id.etxt_oldpin) as EditText
//            val etxt_newpin = dialog1 .findViewById(R.id.etxt_newpin) as EditText
//            val etxt_confirmnewpin = dialog1 .findViewById(R.id.etxt_confirmnewpin) as EditText
//            btnSubmit.setOnClickListener {
//                if (etxt_oldpin!!.text.toString() == null || etxt_oldpin!!.text.toString().isEmpty()) {
//                    etxt_oldpin!!.setError("Please Enter Your Old mPin.")
//                }
//                else if (etxt_newpin!!.text.toString() == null || etxt_newpin!!.text.toString().isEmpty()) {
//                    etxt_newpin!!.setError("Please Enter Your New mPin.")
//                }
//                else if (etxt_confirmnewpin!!.text.toString() == null || etxt_confirmnewpin!!.text.toString().isEmpty()) {
//                    etxt_confirmnewpin!!.setError("Please Confirm Your New mPin.")
//                }
//                else if (etxt_newpin!!.text.toString() != etxt_confirmnewpin!!.text.toString()) {
//                    val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
//                    dialog.setMessage("New & Confirm mPin doesn't match")
//                    dialog.setPositiveButton("Ok",
//                            DialogInterface.OnClickListener { dialog, which ->
//                            })
//                    val alertDialog: AlertDialog = dialog.create()
//                    alertDialog.show()
//                }
//                else if (etxt_oldpin!!.text.toString().isNotEmpty() && etxt_oldpin!!.text.toString().length!=6) {
//                    etxt_oldpin.setError("Please Enter 6 digit mPin")
//                }
//                else if (etxt_newpin!!.text.toString().isNotEmpty() && etxt_newpin!!.text.toString().length!=6) {
//                    etxt_newpin.setError("Please Enter 6 digit mPin")
//                }
//                else if (etxt_confirmnewpin!!.text.toString().isNotEmpty() && etxt_confirmnewpin!!.text.toString().length!=6) {
//                    etxt_confirmnewpin.setError("Please Enter 6 digit mPin")
//                }else{
//                    dialog1 .dismiss()
//                    changempinverficationcode(etxt_oldpin!!.text.toString(), etxt_newpin!!.text.toString())
//                }
//            }
//            btnreset.setOnClickListener {
//                etxt_oldpin.setText("")
//                etxt_newpin.setText("")
//                etxt_confirmnewpin.setText("")
//            }
//            dialog1.show()
//        }
//        catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    companion object {
//        var strOldMPIN= ""
//        var strNewMPIN= ""
//    }
//
//    private fun changempinverficationcode(oldPin: String, newPin: String) {
//        context = this@HomeActivity
//        changempinViewModel = ViewModelProvider(this).get(ChangeMpinViewModel::class.java)
//        strOldMPIN = oldPin
//        strNewMPIN = newPin
//        when (Config.ConnectivityUtils.isConnected(this)) {
//            true -> {
//                progressDialog = ProgressDialog(this, R.style.Progress)
//                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
//                progressDialog!!.setCancelable(false)
//                progressDialog!!.setIndeterminate(true)
//                progressDialog!!.setIndeterminateDrawable(this.resources.getDrawable(R.drawable.progress))
//                progressDialog!!.show()
//                changempinViewModel.changeMpin(this,strOldMPIN, strNewMPIN)!!.observe(
//                        this,
//                        Observer { serviceSetterGetter ->
//                            val msg = serviceSetterGetter.message
//                            if (msg!!.length > 0) {
//                                val jObject = JSONObject(msg)
//                                if (jObject.getString("StatusCode") == "0") {
//                                    val mpinSP = applicationContext.getSharedPreferences(
//                                        Config.SHARED_PREF74,
//                                        0
//                                    )
//                                    val mpinEditer = mpinSP.edit()
//                                    mpinEditer.putString("mpin", newPin)
//                                    mpinEditer.commit()
//                                    val db = DBHelper(this, null)
//                                    db!!.updateUserMpin("",newPin,"1")
//
//                                    var jobj = jObject.getJSONObject("MPINDetails")
//
//                                    val builder = AlertDialog.Builder(
//                                            this@HomeActivity,
//                                            R.style.MyDialogTheme
//                                    )
//                                    builder.setMessage(jobj.getString("ResponseMessage"))
//                                    builder.setPositiveButton("Ok") { dialogInterface, which ->
//
//                                        val i = Intent(this@HomeActivity, MpinActivity::class.java)
//                                        startActivity(i)
//                                        finish()
//                                    }
//                                    val alertDialog: AlertDialog = builder.create()
//                                    alertDialog.setCancelable(false)
//                                    alertDialog.show()
//                                } else {
//                                    val builder = AlertDialog.Builder(
//                                            this@HomeActivity,
//                                            R.style.MyDialogTheme
//                                    )
//                                    builder.setMessage(jObject.getString("EXMessage"))
//                                    builder.setPositiveButton("Ok") { dialogInterface, which ->
//                                    }
//                                    val alertDialog: AlertDialog = builder.create()
//                                    alertDialog.setCancelable(false)
//                                    alertDialog.show()
//                                }
//                            } else {
////                                Toast.makeText(
////                                        applicationContext,
////                                        "Some Technical Issues.",
////                                        Toast.LENGTH_LONG
////                                ).show()
//                            }
//                        })
//                progressDialog!!.dismiss()
//            }
//            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                        .show()
//            }
//        }
//    }
//
//    private fun getBannerlist() {
//        var bannerDetail = 0
//        context = this@HomeActivity
//        bannerListViewModel = ViewModelProvider(this).get(BannerListViewModel::class.java)
//        when (Config.ConnectivityUtils.isConnected(this)) {
//            true -> {
//                progressDialog = ProgressDialog(this, R.style.Progress)
//                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
//                progressDialog!!.setCancelable(false)
//                progressDialog!!.setIndeterminate(true)
//                progressDialog!!.setIndeterminateDrawable(this.resources.getDrawable(R.drawable.progress))
//                progressDialog!!.show()
//                bannerListViewModel.getBannerlist(this)!!.observe(
//                    this,
//                    Observer { serviceSetterGetter ->
//                        val msg = serviceSetterGetter.message
//                        if (msg!!.length > 0) {
//                            val jObject = JSONObject(msg)
//                            if (jObject.getString("StatusCode") == "0") {
//                                val jsonObj: JSONObject = jObject.getJSONObject("BannerDetails")
//
//                                val jresult = jsonObj.getJSONArray("BannerDetailsList")
//                                if (jresult.length() > 0) {
//                                    if (bannerDetail == 0) {
//                                        bannerDetail++
//
//                                        for (i in 0 until jresult!!.length()) {
//                                            try {
//                                                val json = jresult!!.getJSONObject(i)
//                                                var s = "" + json.getString("ImagePath")
//
//                                                XMENArray!!.add(s)
//                                                mPager!!.adapter = BannerAdapter(
//                                                    this@HomeActivity,
//                                                    XMENArray
//                                                )
//                                                indicator!!.setViewPager(mPager)
//
//
//                                            } catch (e: Exception) {
//
//                                            }
//                                        }
//                                        //  mPager!!.setPageTransformer(true, CubeInScalingAnimation())
//                                        val handler = Handler()
//                                        val Update = Runnable {
//                                            //Log.e("TAG","currentPage  438   "+currentPage+"   "+jresult!!.length())
//                                            if (currentPage == jresult!!.length()) {
//                                                currentPage = 0
//                                            }
////                                            mPager!!.setCurrentItem(currentPage++, true)
//
//                                        }
//                                        val swipeTimer = Timer()
//                                        swipeTimer.schedule(object : TimerTask() {
//                                            override fun run() {
//                                                handler.post(Update)
//                                            }
//                                        }, 3000, 3000)
//
//
////                                        for (i in 0 until jresult!!.length()) {
////                                            try {
////                                                val json = jresult!!.getJSONObject(i)
////
////                                                var s = ""+ json.getString("ImagePath")
////
////                                                XMENArray!!.add(s)
////                                                mPager!!.adapter = BannerAdapter(
////                                                    this@HomeActivity,
////                                                    XMENArray
////                                                )
////                                                indicator!!.setViewPager(mPager)
////                                                val handler = Handler()
////                                                val Update = Runnable {
////                                                    Log.e("TAG","currentPage  438   "+currentPage+"   "+jresult!!.length())
////                                                    if (currentPage == jresult!!.length()) {
////                                                        currentPage = 0
////                                                    }
////                                                    mPager!!.setCurrentItem(currentPage++, true)
////                                                }
////                                                val swipeTimer = Timer()
////                                                swipeTimer.schedule(object : TimerTask() {
////                                                    override fun run() {
////                                                        handler.post(Update)
////                                                    }
////                                                }, 3000, 3000)
////                                            } catch (e: JSONException) {
////                                                e.printStackTrace()
////                                            }
////
////                                        }
//                                    }
//                                }
//
//                            } else {
////                                    val builder = AlertDialog.Builder(
////                                            this@HomeActivity,
////                                            R.style.MyDialogTheme
////                                    )
////                                    builder.setMessage(jObject.getString("EXMessage"))
////                                    builder.setPositiveButton("Ok") { dialogInterface, which ->
////                                    }
////                                    val alertDialog: AlertDialog = builder.create()
////                                    alertDialog.setCancelable(false)
////                                    alertDialog.show()
//                            }
//                        } else {
////                                Toast.makeText(
////                                        applicationContext,
////                                        "Some Technical Issues.",
////                                        Toast.LENGTH_LONG
////                                ).show()
//                        }
//                    })
//                progressDialog!!.dismiss()
//            }
//            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                    .show()
//            }
//        }
//    }
//
//
//    private fun getCompanyLogo() {
//        var bannerDetail = 0
//        context = this@HomeActivity
//        companyLogoViewModel = ViewModelProvider(this).get(CompanyLogoViewModel::class.java)
//        when (Config.ConnectivityUtils.isConnected(this)) {
//            true -> {
//                progressDialog = ProgressDialog(this, R.style.Progress)
//                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
//                progressDialog!!.setCancelable(false)
//                progressDialog!!.setIndeterminate(true)
//                progressDialog!!.setIndeterminateDrawable(this.resources.getDrawable(R.drawable.progress))
//                progressDialog!!.show()
//                companyLogoViewModel.getCompanylogoType(this)!!.observe(
//                        this,
//                        Observer { serviceSetterGetter ->
//                            val msg = serviceSetterGetter.message
//                            if (msg!!.length > 0) {
//                                val jObject = JSONObject(msg)
//                                Log.e(TAG, "msg   bytearray   " + msg)
//                                if (jObject.getString("StatusCode") == "0") {
//                                    val jobjt = jObject.getJSONObject("CompanyLogDetails")
//
//
//                                    var count =jobjt.getString("CompanyLogo")
//                                    var type =jobjt.getString("DisplayType")
//                                    var companyname =jobjt.getString("CompanyName")
//
//
//                                    logo = jobjt!!.getString("CompanyLogo")
//                                    Log.i("DIL", count)
//                                    Log.i("logo1212","logo======"+logo);
//
//                                    Log.e(TAG,"1961 type   "+type)
//                                    if(type.equals("0"))
//                                    {
//                                        tv_Status!!.visibility=View.GONE;
//                                        if(!logo.equals(""))
//                                        {
//                                            Log.e(TAG,"19612 type   "+logo)
//                                            val decodedString = Base64.decode(logo, Base64.DEFAULT)
//                                            ByteArrayToBitmap(decodedString)
//                                            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                                            val stream = ByteArrayOutputStream()
//                                            decodedByte.compress(Bitmap.CompressFormat.PNG, 100, stream)
//                                            Glide.with(this) .load(stream.toByteArray()).into(imgAttendance!!)
//
//                                        }
//                                    }
//                                    else
//                                    {
//                                        if(!logo.equals(""))
//                                        {
//                                            Log.e(TAG,"19613 type   "+logo)
//                                            val decodedString = Base64.decode(logo, Base64.DEFAULT)
//                                            ByteArrayToBitmap(decodedString)
//                                            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                                            val stream = ByteArrayOutputStream()
//                                            decodedByte.compress(Bitmap.CompressFormat.PNG, 100, stream)
//                                            Glide.with(this) .load(stream.toByteArray()).into(imgAttendance!!)
//
//
//                                        }
//                                        tv_Status!!.visibility=View.VISIBLE;
//                                        tv_Status!!.text=companyname
//                                    }
//
//
//
//
//
//                                } else {
//
//                                }
//                            } else {
//
//                            }
//                        })
//                progressDialog!!.dismiss()
//            }
//            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                        .show()
//            }
//        }
//    }
//
//    override fun onBackPressed() {
////        quit()
//        QuitBottomSheet()
//        getDashBoardCount()
//    }
//
//
//    private fun setReminder() {
//       // getCalendarId(this,callbackId)
//       try
//        {
//
//
//
//            val builder = android.app.AlertDialog.Builder(this)
//            val inflater1 = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val layout = inflater1.inflate(R.layout.reminder_setter_popup, null)
//            val btncancel = layout.findViewById(R.id.btncancel) as Button
//            val btnsubmit = layout.findViewById(R.id.btnsubmit) as Button
//             etdate = layout.findViewById(R.id.etdate) as EditText
//             ettime = layout.findViewById(R.id.ettime) as EditText
//            etdis = layout.findViewById(R.id.etdis) as EditText
//           /* val ll_ok = layout.findViewById(R.id.ll_ok) as LinearLayout
//            val ll_cancel = layout.findViewById(R.id.ll_cancel) as LinearLayout
//            etdate = layout.findViewById(R.id.etdate) as TextView
//            ettime = layout.findViewById(R.id.ettime) as TextView
//            val etdis = layout.findViewById(R.id.etdis) as EditText*/
//            etdate!!.setKeyListener(null)
//            ettime!!.setKeyListener(null)
//            builder.setView(layout)
//            val alertDialog = builder.create()
//            val c = Calendar.getInstance()
//            val sdf = SimpleDateFormat("dd-MM-yyyy")
//            val sdf1 = SimpleDateFormat("hh:mm a",Locale.getDefault())
//            val sdf2 = SimpleDateFormat("hh:mm")
//            yr = c.get(Calendar.YEAR)
//            month = c.get(Calendar.MONTH)
//            day = c.get(Calendar.DAY_OF_MONTH)
//            etdate!!.setText(sdf.format(c.time))
//            ettime!!.setText(sdf1.format(c.time))
//            val s = sdf2.format(c.time)
//            val split = s.split((":").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            val strhr = split[0]
//            val strmin = split[1]
//
//
//            var dateShow = etdate!!.text.toString()
//            var timeShow = ettime!!.text.toString()
//
//            hr = Integer.parseInt(strhr)
//            min = Integer.parseInt(strmin)
//
//            ettime!!.setOnClickListener(View.OnClickListener { timeSelector() })
//            etdate!!.setOnClickListener(View.OnClickListener { dateSelector() })
//            btncancel.setOnClickListener {
//                Config.Utils.hideSoftKeyBoard(this, it)
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//                alertDialog.dismiss() }
//            btnsubmit.setOnClickListener {
//                Config.Utils.hideSoftKeyBoard(this, it)
//                addEvent(yr, month, day, hr, min, etdis!!.text.toString(), " Reminder",dateShow,timeShow)
//                alertDialog.dismiss()
//                chipNavigationBar!!.setItemSelected(R.id.home, true)
//            }
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//        }
//        catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//
//    fun addEvent(iyr: Int, imnth: Int, iday: Int, ihour: Int, imin: Int, descriptn: String, Title: String,dateShow: String ,timeShow: String ) {
//
//
////        if (ActivityCompat.checkSelfPermission(
////                        applicationContext,
////                        Manifest.permission.WRITE_CALENDAR
////                ) != PackageManager.PERMISSION_GRANTED
////        ) {
////            ActivityCompat.requestPermissions(
////                    this,
////                    arrayOf(Manifest.permission.WRITE_CALENDAR),
////                    1
////            )
////        }
////        val cr = contentResolver
////        val beginTime = Calendar.getInstance()
////        beginTime.set(2022, 11 - 1, 28, 9, 30)
////        val endTime = Calendar.getInstance()
////        endTime.set(iyr, imnth, iday, ihour, imin)
////        val values = ContentValues()
////        values.put(CalendarContract.Events.DTSTART, endTime.timeInMillis)
////        values.put(CalendarContract.Events.DTEND, endTime.timeInMillis)
////        values.put(CalendarContract.Events.TITLE, Title)
////        values.put(CalendarContract.Events.DESCRIPTION, descriptn)
////
////        Log.e(TAG,"154444 : "+iyr+" : "+ imnth+" : "+iday+" : "+ ihour+" : "+imin)
////
////        val calendarId = getCalendarId(context)
////        Log.i("Calender", calendarId.toString())
////        if(calendarId != null) {
////            values.put(CalendarContract.Events.CALENDAR_ID, calendarId)
////        }
////
////
////        val tz = TimeZone.getDefault()
////        values.put(CalendarContract.Events.EVENT_TIMEZONE, tz.id)
////        values.put(CalendarContract.Events.EVENT_LOCATION, "India")
////
////
////
////
////
////        try {
////
////        //    AlarmHelper.setReminder(context,hr,min)
////
////            val uri = cr.insert(CalendarContract.Events.CONTENT_URI, values)
////            val reminders = ContentValues()
////            reminders.put(CalendarContract.Reminders.EVENT_ID, uri!!.lastPathSegment)
////            reminders.put(
////                    CalendarContract.Reminders.METHOD,
////                    CalendarContract.Reminders.METHOD_ALERT
////            )
////            reminders.put(CalendarContract.Reminders.MINUTES, 10)
////            cr.insert(CalendarContract.Reminders.CONTENT_URI, reminders)
////
////
////
////        }catch (e: Exception){
////            e.printStackTrace()
////        }
//
//        try {
//
//            var random1 = (0..1000000).shuffled().last()
//            val requestCode = random1++
//            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val intent = Intent(this, MyAlarmReceiver::class.java)
//            intent.putExtra("REQUEST_CODE", requestCode)
//            intent.putExtra("title", Title)
//            intent.putExtra("message", descriptn)
//            intent.putExtra("dateShow", dateShow)
//            intent.putExtra("timeShow", timeShow)
//            intent.putExtra("date", ""+iday+"-"+imnth+"-"+iyr)
//            intent.putExtra("time", ""+ihour+":"+imin)
//            pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_MUTABLE)
//            val calendar: Calendar = Calendar.getInstance()
////            calendar.set(
////                calendar.get(iyr),
////                calendar.get(imnth),
////                calendar.get(iday),
////                ihour,
////                imin,
////                0
////            )
//
//            Log.e("TAG","888888   "+calendar.get(Calendar.YEAR)+":"+calendar.get(Calendar.MONTH)+":"+calendar.get(Calendar.DAY_OF_MONTH))
//            Log.e("TAG","8888882   "+ihour+"  :  "+imin)
//
//            if (Build.VERSION.SDK_INT >= 23) {
//                calendar.set(
//                    iyr,
//                    imnth,
//                    iday,
//                    ihour,
//                    imin,
//                    0
//                )
//               // Log.e("TAG","8888881   "+timePicker.hour+":"+timePicker!!.minute)
//            } else {
//                calendar.set(
//                    iyr,
//                    imnth,
//                    iday,
//                    ihour,
//                    imin, 0
//                )
//               // Log.e("TAG","8888881   "+timePicker.currentHour+":"+timePicker!!.currentMinute)
//            }
//
//
//            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis, pendingIntent);
//            Log.e("TAG","1999      Alarm Set  "+descriptn+"  :   "+requestCode)
//
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("Reminder set successfully.")
//                .setCancelable(false)
//                .setPositiveButton(
//                    "OK"
//                ) { dialog, id -> dialog.dismiss()
//                }
//            val alert = builder.create()
//            alert.show()
//
//        }
//        catch (e : Exception){
//
//            Log.e(TAG,"1620     "+e.toString())
//        }
//
//
//    }
//
//   private fun getCalendarId(context: Context): Long? {
//       try
//       {
//           val permissions = true
//           if (ActivityCompat.checkSelfPermission(
//                   applicationContext,
//                   Manifest.permission.READ_CALENDAR
//               ) != PackageManager.PERMISSION_GRANTED
//           ) {
//               ActivityCompat.requestPermissions(
//                   this,
//                   arrayOf(Manifest.permission.READ_CALENDAR),
//                   1
//               )
//           }
//
//
//           val projection = arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME)
//
//           var calCursor = context.contentResolver.query(
//               CalendarContract.Calendars.CONTENT_URI,
//               projection,
//               CalendarContract.Calendars.VISIBLE + " = 1 AND " + CalendarContract.Calendars.IS_PRIMARY + "=1",
//               null,
//               CalendarContract.Calendars._ID + " ASC"
//           )
//            Log.e("calcursor", calCursor.toString())
//           if (calCursor != null && calCursor.count <= 0) {
//               calCursor = context.contentResolver.query(
//                   CalendarContract.Calendars.CONTENT_URI,
//                   projection,
//                   CalendarContract.Calendars.VISIBLE + " = 1",
//                   null,
//                   CalendarContract.Calendars._ID + " ASC"
//               )
//           }
//           if (calCursor != null) {
//               if (calCursor.moveToFirst()) {
//                   val calName: String
//                   val calID: String
//                   val nameCol = calCursor.getColumnIndex(projection[1])
//                   val idCol = calCursor.getColumnIndex(projection[0])
//
//                   calName = calCursor.getString(nameCol)
//                   calID = calCursor.getString(idCol)
//
//                   //    Log.d("Calendar name = $calName Calendar ID = $calID")
//
//                   calCursor.close()
//                   Log.e(TAG,"CALID : "+calID.toLong())
//                   return calID.toLong()
//               }
//           }
//
//
//       }
//       catch(e:SecurityException)
//       {
//             Log.e(TAG,"Error"+e.toString())
//       }
//       return null
//
//
//
//
//   }
//
//    fun timeSelector() {
//        val c = Calendar.getInstance()
//        mHour = c.get(Calendar.HOUR_OF_DAY)
//        mMinute = c.get(Calendar.MINUTE)
//        // Launch Time Picker Dialog
//        val timePickerDialog = TimePickerDialog(this,
//                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                    val strDate = String.format(
//                            "%02d:%02d %s", if (hourOfDay == 0) 12 else hourOfDay,
//                            minute, if (hourOfDay < 12) "am" else "pm"
//                    )
//                    ettime!!.setText(strDate)
//                    hr = hourOfDay
//                    min = minute
//                }, mHour, mMinute, false
//        )
//        timePickerDialog.show()
//    }
//
//    fun dateSelector() {
//        try {
//            val sdf = SimpleDateFormat("dd-MM-yyyy")
//            val c = Calendar.getInstance()
//            mYear = c.get(Calendar.YEAR)
//            mMonth = c.get(Calendar.MONTH)
//            mDay = c.get(Calendar.DAY_OF_MONTH)
//            val datePickerDialog = DatePickerDialog(this,
//                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                        yr = year
//                        month = monthOfYear
//                        day = dayOfMonth
//                        etdate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
//                    }, mYear, mMonth, mDay
//            )
//            datePickerDialog.datePicker.minDate = c.timeInMillis
//            datePickerDialog.show()
//
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun attendancConfirmPopup(v : View ){
//        try {
//
//            val dialogCountry = Dialog(this)
//            dialogCountry!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialogCountry!! .setContentView(R.layout.attendance_popup)
//            dialogCountry!!.window!!.attributes.gravity = Gravity.CENTER_VERTICAL;
//
//            val btn_online_Yes = dialogCountry .findViewById(R.id.btn_online_Yes) as Button
//            val btn_online_No = dialogCountry .findViewById(R.id.btn_online_No) as Button
//            btn_online_No.setOnClickListener {
//                dialogCountry .dismiss()
//            }
//            btn_online_Yes.setOnClickListener {
//                dialogCountry.dismiss()
//                SubMode = "1"
//                getLocation(v)
//            }
//
//            dialogCountry!!.show()
//            dialogCountry!!.getWindow()!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialogCountry!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//
//    private fun checkAndRequestPermissions(): Boolean {
//
//        var result = false
//        try {
//            val locationPermission =
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            val coarsePermision =
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            val listPermissionsNeeded: MutableList<String> = ArrayList()
//            if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//            if (coarsePermision != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
//            }
//            if (!listPermissionsNeeded.isEmpty()) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    listPermissionsNeeded.toTypedArray(),
//                    REQUEST_ID_MULTIPLE_PERMISSIONS
//                )
//                result =  false
//            }
//            result =  true
//        }catch (e : Exception){
//
//        }
//        return result
//
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun getLocation(v : View) {
//
////        address      = ""
////        city         = ""
////        state        = ""
////        country      = ""
////        postalCode   = ""
////        knownName    = ""
////        strLongitue  = ""
////        strLatitude  = ""
//
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
//                    var location: Location? = task.result
//                    if (location == null) {
//                        requestNewLocationData()
//                    } else {
////                        txtLongitude!!.text = location.longitude.toString()
////                        txtLatitude!!.text = location.latitude.toString()
//
//                        Log.e("HOEMACTIVITY","longitude  819    "+location.longitude)
//                        Log.e("HOEMACTIVITY","latitude   819    "+location.latitude)
//
//
//                        geocoder = Geocoder(this, Locale.getDefault())
//                        addresses = geocoder!!.getFromLocation(location.latitude, location.longitude, 1);
//                        address = addresses!!.get(0).getAddressLine(0)
//                        city = addresses!!.get(0).locality
//                        state = addresses!!.get(0).adminArea
//                        country = addresses!!.get(0).countryName
//                        postalCode = addresses!!.get(0).postalCode
//                        knownName = addresses!!.get(0).featureName
//                        strLongitue = location.longitude.toString()
//                        strLatitude = location.latitude.toString()
//
//                        validLocation(v)
//
//
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//            }
//        } else {
//            requestPermissions()
//        }
//    }
//
//    private fun validLocation(v : View) {
//
//        if (strLatitude.equals("") || strLongitue.equals("")){
//            Config.snackBars(context,v,"Location Not Found")
//        }else{
//
//            AddAttendanceApi(strLatitude,strLongitue,address)
//        }
//    }
//
//
//
//    private fun checkPermissions(): Boolean {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            return true
//        }
//        return false
//    }
//
//    private fun requestPermissions() {
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
//            PERMISSION_ID
//        )
//    }
//
//    private fun isLocationEnabled(): Boolean {
//        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER
//        )
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun requestNewLocationData() {
//        var mLocationRequest = LocationRequest()
//        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        mLocationRequest.interval = 0
//        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        mFusedLocationClient!!.requestLocationUpdates(
//            mLocationRequest, mLocationCallback,
//            Looper.myLooper()
//        )
//    }
//
//    private val mLocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//
//
//            try {
//                var location: Location = locationResult.lastLocation
//                Log.e("HOEMACTIVITY","longitude  8191    "+location.longitude)
//                Log.e("HOEMACTIVITY","latitude   8191    "+location.latitude)
//                geocoder = Geocoder(this@HomeActivity, Locale.getDefault())
//                addresses = geocoder!!.getFromLocation(location.latitude, location.longitude, 1);
//                address = addresses!!.get(0).getAddressLine(0)
//                city = addresses!!.get(0).locality
//                state = addresses!!.get(0).adminArea
//                country = addresses!!.get(0).countryName
//                postalCode = addresses!!.get(0).postalCode
//                knownName = addresses!!.get(0).featureName
//                strLongitue = location.longitude.toString()
//                strLatitude = location.latitude.toString()
//            }catch (e : Exception){
//                Log.e("HOEMACTIVITY","Exception  8191    "+e.toString())
//            }
//
//
//
//        }
//    }
//
//
//    private fun AddAttendanceApi(strLatitude: String, strLongitue: String, address: String) {
//        val UserNameSP = context.getSharedPreferences(Config.SHARED_PREF2, 0)
//        val LOGIN_DATETIMESP = applicationContext.getSharedPreferences(Config.SHARED_PREF30, 0)
////        tv_Name!!.text = UserNameSP.getString("UserName", "")
//        tv_navName!!.text = UserNameSP.getString("UserName", "")
//        name_employee!!.text = UserNameSP.getString("UserName", "")
//        Log.e(TAG,"weewec   "+UserNameSP.getString("UserName", ""))
//      //  tv_DateTime!!.text = LOGIN_DATETIMESP.getString("LOGIN_DATETIME", "")
//        tv_navDateTime!!.text = LOGIN_DATETIMESP.getString("LOGIN_DATETIME", "")
//
////        var addAttendan = 0
////        when (Config.ConnectivityUtils.isConnected(this)) {
////            true -> {
////                progressDialog = ProgressDialog(context, R.style.Progress)
////                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
////                progressDialog!!.setCancelable(false)
////                progressDialog!!.setIndeterminate(true)
////                progressDialog!!.setIndeterminateDrawable(context.resources.getDrawable(R.drawable.progress))
////                progressDialog!!.show()
////
////                attendanceAddViewModel.AddAttendanceAddAttendance(this,IsOnline!!,strLatitude!!,strLongitue!!,address!!,SubMode)!!.observe(
////                    this,
////                    Observer { serviceSetterGetter ->
////                        val msg = serviceSetterGetter.message
////                        if (msg!!.length > 0) {
////
////
////                            val jObject = JSONObject(msg)
////                            Log.e("HOMEACTIVITY","msg   1026   "+msg.length)
////                            Log.e("HOMEACTIVITY","msg   1026   "+msg)
////                            if (jObject.getString("StatusCode") == "0") {
////                                val jobjt = jObject.getJSONObject("UpdateUserLoginStatus")
////
////                                tv_Name!!.text = jobjt.getString("Name")
////                                tv_navName!!.text = jobjt.getString("Name")
//////                                tv_DateTime!!.text = "On Duty from "+jobjt.getString("LoginDate")+" "+jobjt.getString("LoginTime")
////                                tv_Status!!.text = jobjt.getString("LoginStatus")
////
////                                if (jobjt.getString("LoginMode").equals("1")){
////                                    imgAttendance!!.setImageResource(R.drawable.finger_online)
////                                    tv_DateTime!!.text = jobjt.getString("DutyStatus")+" \n "+jobjt.getString("LoginDate")+" "+jobjt.getString("LoginTime")
////                                    tv_navStatus!!.text = jobjt.getString("DutyStatus")
////                                    tv_navDateTime!!.text = jobjt.getString("LoginDate")+" "+jobjt.getString("LoginTime")
////                                    tv_navStatus!!.getCompoundDrawables()[0].setTint(resources.getColor(R.color.green))
////                                }else{
////                                    imgAttendance!!.setImageResource(R.drawable.finger_offline)
////                                    tv_DateTime!!.text = jobjt.getString("DutyStatus")+" "+jobjt.getString("LoginDate")+" "+jobjt.getString("LoginTime")
////                                    tv_navStatus!!.text = jobjt.getString("DutyStatus")
////                                    tv_navDateTime!!.text = jobjt.getString("LoginDate")+" "+jobjt.getString("LoginTime")
////                                    tv_navStatus!!.getCompoundDrawables()[0].setTint(resources.getColor(R.color.greydark))
////                                }
//////                                leadFromArrayList = jobjt.getJSONArray("LeadFromDetails")
//////                                if (leadFromArrayList.length()>0){
//////                                    if (countLeadFrom == 0){
//////                                        countLeadFrom++
//////                                        leadFromPopup(leadFromArrayList)
//////                                    }
//////
//////                                }
////
////                                val LS_LocLatitudeSP = context.getSharedPreferences(Config.SHARED_PREF17, 0)
////                                val LS_LocLatitudeEditer = LS_LocLatitudeSP.edit()
////                                LS_LocLatitudeEditer.putString("LocLatitude", jobjt.getString("LocLatitude"))
////                                LS_LocLatitudeEditer.commit()
////
////                                val LS_LocLongitudeSP = context.getSharedPreferences(Config.SHARED_PREF18, 0)
////                                val LS_LocLongitudeEditer = LS_LocLongitudeSP.edit()
////                                LS_LocLongitudeEditer.putString("LocLongitude", jobjt.getString("LocLongitude"))
////                                LS_LocLongitudeEditer.commit()
////
////                                val LS_LocationNameSP = context.getSharedPreferences(Config.SHARED_PREF19, 0)
////                                val LS_LocationNameEditer = LS_LocationNameSP.edit()
////                                LS_LocationNameEditer.putString("LocationName",jobjt.getString("LocationName"))
////                                LS_LocationNameEditer.commit()
////
////                                val LS_FK_EmployeeSP = context.getSharedPreferences(Config.SHARED_PREF20, 0)
////                                val LS_FK_EmployeeEditer = LS_FK_EmployeeSP.edit()
////                                LS_FK_EmployeeEditer.putString("FK_Employee", jobjt.getString("FK_Employee"))
////                                LS_FK_EmployeeEditer.commit()
////
////                                val LS_NameSP = context.getSharedPreferences(Config.SHARED_PREF21, 0)
////                                val LS_NameEditer = LS_NameSP.edit()
////                                LS_NameEditer.putString("Name", jobjt.getString("Name"))
////                                LS_NameEditer.commit()
////
////                                val LS_AddressSP = context.getSharedPreferences(Config.SHARED_PREF22, 0)
////                                val LS_AddressEditer = LS_AddressSP.edit()
////                                LS_AddressEditer.putString("Address", jobjt.getString("Address"))
////                                LS_AddressEditer.commit()
////
////                                val LS_LoginDateSP = context.getSharedPreferences(Config.SHARED_PREF23, 0)
////                                val LS_LoginDateEditer = LS_LoginDateSP.edit()
////                                LS_LoginDateEditer.putString("LoginDate", jobjt.getString("LoginDate"))
////                                LS_LoginDateEditer.commit()
////
////                                val LS_LoginTimeSP = context.getSharedPreferences(Config.SHARED_PREF24, 0)
////                                val LS_LoginTimeEditer = LS_LoginTimeSP.edit()
////                                LS_LoginTimeEditer.putString("LoginTime", jobjt.getString("LoginTime"))
////                                LS_LoginTimeEditer.commit()
////
////                                val LS_LoginModeSP = context.getSharedPreferences(Config.SHARED_PREF25, 0)
////                                val LS_LoginModeEditer = LS_LoginModeSP.edit()
////                                LS_LoginModeEditer.putString("LoginMode", jobjt.getString("LoginMode"))
////                                LS_LoginModeEditer.commit()
////
////                                val LS_LoginStauatsSP = context.getSharedPreferences(Config.SHARED_PREF26, 0)
////                                val LS_LoginStauatsEditer = LS_LoginStauatsSP.edit()
////                                LS_LoginStauatsEditer.putString("LoginStatus", jobjt.getString("LoginStatus"))
////                                LS_LoginStauatsEditer.commit()
////
////                                val LS_DutyStatusSP = context.getSharedPreferences(Config.SHARED_PREF27, 0)
////                                val LS_DutyStatusEditer = LS_DutyStatusSP.edit()
////                                LS_DutyStatusEditer.putString("DutyStatus", jobjt.getString("DutyStatus"))
////                                LS_DutyStatusEditer.commit()
////
////                            } else {
////                                val builder = AlertDialog.Builder(
////                                    this@HomeActivity,
////                                    R.style.MyDialogTheme
////                                )
////                                builder.setMessage(jObject.getString("EXMessage"))
////                                builder.setPositiveButton("Ok") { dialogInterface, which ->
////                                }
////                                val alertDialog: AlertDialog = builder.create()
////                                alertDialog.setCancelable(false)
////                                alertDialog.show()
////                            }
////                        } else {
////                            Toast.makeText(
////                                applicationContext,
////                                "Some Technical Issues.",
////                                Toast.LENGTH_LONG
////                            ).show()
////                        }
////                    })
////                progressDialog!!.dismiss()
////            }
////            false -> {
////                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
////                    .show()
////            }
////
////        }
//
//    }
//    fun ByteArrayToBitmap(byteArray: ByteArray): Bitmap {
//        val arrayInputStream = ByteArrayInputStream(byteArray)
//        return BitmapFactory.decodeStream(arrayInputStream)
//    }
//
//    override fun onClick(position: Int, data: String) {
//        if (data.equals("homeGrid")) {
//            val jsonObject = homeArraySort.getJSONObject(position)
//            var grid_id = jsonObject.getString("grid_id")
//
//
//            if (grid_id.equals("1")){
//                val i = Intent(this@HomeActivity, AgendaActivity::class.java)
//                startActivity(i)
//
////                val i = Intent(this@HomeActivity, MyActivity::class.java)
////                startActivity(i)
//            }
//            if (grid_id.equals("2")){
//                val i = Intent(this@HomeActivity, DashboardNewActivity::class.java)
//                startActivity(i)
//
////                                val i = Intent(this@HomeActivity, InventoryActivity::class.java)
////                startActivity(i)
//            }
//            if (grid_id.equals("3")){
////                val i = Intent(this@HomeActivity, NotificationActivity::class.java)
////                startActivity(i)
//
//                val i = Intent(this@HomeActivity, ApproveActivity::class.java)
//                startActivity(i)
//
//            }
//            if (grid_id.equals("4")){
//                val i = Intent(this@HomeActivity, LeadActivity::class.java)
//                startActivity(i)
//            }
//            if (grid_id.equals("5")){
//                val i = Intent(this@HomeActivity, ServiceActivity::class.java)
//                startActivity(i)
//               // Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//
//            }
//            if (grid_id.equals("6")){
////                val i = Intent(this@HomeActivity, EmiActivity::class.java)
////                startActivity(i)
//               // Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//
//                val i = Intent(this@HomeActivity, ProjectActivity::class.java)
//                startActivity(i)
//            }
//            if (grid_id.equals("7")){
//                val i = Intent(this@HomeActivity, PickUpAndDeliveryActivity::class.java)
//                startActivity(i)
//               // Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//            }
//            if (grid_id.equals("8")){
//              //  setReminder()
//              //  Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//                val i = Intent(this@HomeActivity, LocationMarkingNewActivity::class.java)
//                startActivity(i)
//            }
//            if (grid_id.equals("9")){
////                val i = Intent(this@HomeActivity, TicketReportActivity::class.java)
////                startActivity(i)
////
//                val i = Intent(this@HomeActivity, ReportMainActivity::class.java)
//                startActivity(i)
//              //  Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//
//
//            }
//            if (grid_id.equals("10")){
//                if (ActivityCompat.checkSelfPermission(
//                        applicationContext,
//                        Manifest.permission.READ_CALENDAR
//                    ) == PackageManager.PERMISSION_GRANTED
//                ) {
//
//                    ActivityCompat.requestPermissions(
//                            this,
//                            arrayOf(Manifest.permission.READ_CALENDAR),
//                            1
//                        )
//
//                    setReminder()
//                }
//                else
//                {
//                    setPermission()
//                }
//
//            }
//            if (grid_id.equals("11")){
////                val i = Intent(this@HomeActivity, ExpenseActivity::class.java)
////                startActivity(i)
//
//                val i = Intent(this@HomeActivity, ProfileActivity::class.java)
//                startActivity(i)
//
//              //  Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//            }
//
//            if (grid_id.equals("12")){
//                val i = Intent(this@HomeActivity, ContactUsActivity::class.java)
//                startActivity(i)
//
//                //  Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//            }
//            if (grid_id.equals("13")){
//                val i = Intent(this@HomeActivity, AboutUsActivity::class.java)
//                startActivity(i)
//                //  Toast.makeText(context, "Work in progess", Toast.LENGTH_SHORT).show()
//            }
//
//
//        }
//
//
//        /*if (data.equals("homeDashClicks")) {
//            Log.e(TAG,"2385    "+position)
//            val jsonObject = dashCountArrayList.getJSONObject(position)
//            // Task is Unique
//            if (jsonObject.getString("Task").equals("Correction Pending")){
////                val i = Intent(this@HomeActivity, CorrectionSplitupActivity::class.java)
////                startActivity(i)
////                if value=1 go to individual page,
////                if value>1 and ModuleCount>1 go to Module List (2nd page)
////                if value>1 and ModuleCount=1 go to splitup (3rd page
//
//                if (jsonObject.getInt("Value") == 1){
//                    val i = Intent(this@HomeActivity, LeadCorrectionActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//
////                    val i = Intent(this@HomeActivity, CorrectionSplitupActivity::class.java)
////                    i.putExtra("jsonObject",jsonObject.toString())
////                    startActivity(i)
//
////                    val i = Intent(this@HomeActivity, CorrectionModuleListActivity::class.java)
////                    i.putExtra("jsonObject",jsonObject.toString())
////                    startActivity(i)
//                }
//                else if (jsonObject.getInt("Value") > 1 && jsonObject.getInt("ModuleCount") > 1){
//                    val i = Intent(this@HomeActivity, CorrectionModuleListActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//                }
//                else if (jsonObject.getInt("Value") > 1 && jsonObject.getInt("ModuleCount") == 1){
//                    val i = Intent(this@HomeActivity, CorrectionSplitupActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//                }
//
//            }
//            if (jsonObject.getString("Criteria").equals("1")){
//
//                if (jsonObject.getString("Value").equals("0")){
//                    Toast.makeText(applicationContext, "No Data", Toast.LENGTH_LONG).show()
//                }else{
//                    val i = Intent(this@HomeActivity, HomeDashBoardDetailPageActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//                }
//
//            }
//            if (jsonObject.getString("Criteria").equals("2")){
//
//                if (jsonObject.getString("Value").equals("0")){
//                    Toast.makeText(applicationContext, "No Data", Toast.LENGTH_LONG).show()
//                }else{
//                    val i = Intent(this@HomeActivity, HomeDashBoardDetailPageActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//                }
//
//            }
//            if (jsonObject.getString("Criteria").equals("4")){
//
//                if (jsonObject.getString("Value").equals("0")){
//                    Toast.makeText(applicationContext, "No Data", Toast.LENGTH_LONG).show()
//                }else{
//                    val i = Intent(this@HomeActivity, HomeDashBoardDetailPageActivity::class.java)
//                    i.putExtra("jsonObject",jsonObject.toString())
//                    startActivity(i)
//                }
//
//            }
//
//        }*/
//    }
//
//    override fun onServiceCallback(data: String) {
//
//        activity = ""
//        Log.e(TAG,"1838     "+data)
//    }
//
////    override fun onStart() {
////        super.onStart()
////        Log.e(TAG,"3666  11   ")
////        val serviceIntent = Intent(this, LocationService::class.java)
////        startService(serviceIntent)
////    }
////
////    override fun onStop() {
////        super.onStop()
////        Log.e(TAG,"3666  22   ")
////        val serviceIntent = Intent(this, LocationService::class.java)
////        stopService(serviceIntent)
////    }
//
//
////    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
////        getMenuInflater().inflate(R.menu.navbottom_item, menu)
////        return true
////    }
////
////    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
////        val reminder = menu.findItem(R.id.reminder)
////        val logout1 = menu.findItem(R.id.logout1)
////        val logout = menu.findItem(R.id.logout)
////        val quit = menu.findItem(R.id.quit)
////        reminder.setIcon(R.drawable.calendar_icon)
////        logout1.isVisible = false
////        logout.isVisible = false
////        quit.isVisible = false
////        return true
////    }
//
//
//}
//
//
//
//
//
