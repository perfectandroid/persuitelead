package com.perfect.persuitelead.View.Activity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.perfect.persuitelead.Helper.Config
import com.perfect.persuitelead.Helper.DBHelper
import com.perfect.persuitelead.Helper.NetworkChangeReceiver
import com.perfect.persuitelead.R
import com.perfect.persuitelead.ViewModel.OTPActivityViewModel

import org.json.JSONObject

class OTPActivity : AppCompatActivity(), View.OnClickListener {

    var TAG : String? = null
    lateinit var context: Context
    lateinit var otpActivityViewModel: OTPActivityViewModel
    var show: Boolean = false
    private var progressDialog: ProgressDialog? = null
    private var one: TextView? = null
    private var two: TextView? = null
    private var three: TextView? = null
    private var four: TextView? = null
    private var five: TextView? = null
    private var six: TextView? = null
    private var seven: TextView? = null
    private var eight: TextView? = null
    private var nine: TextView? = null
    private var zero: TextView? = null
    private var et_1: EditText? = null
    private var et_2: EditText? = null
    private var et_3: EditText? = null
    private var et_4: EditText? = null
    private var et_5: EditText? = null
    private var et_6: EditText? = null
    private var imgShowPin: ImageView? = null
    private var showPin: LinearLayout? = null
    private var clear: LinearLayout? = null
    var countOtp = 0

    var db : DBHelper? = null
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_otp)
        Log.e(TAG,"6000001   ")
        setRegViews()
        Log.e(TAG,"6000002   ")
        context = this@OTPActivity
        Log.e(TAG,"6000003   ")
        db = DBHelper(this, null)
        Log.e(TAG,"6000004   ")
        otpActivityViewModel = ViewModelProvider(this).get(OTPActivityViewModel::class.java)

        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        Log.e(TAG,"6000005   ")
    }

    private fun setRegViews() {
        var tvdata = findViewById<TextView>(R.id.tvdata)
        one = findViewById<TextView>(R.id.one)
        two = findViewById<TextView>(R.id.two)
        three = findViewById<TextView>(R.id.three)
        four = findViewById<TextView>(R.id.four)
        five = findViewById<TextView>(R.id.five)
        six = findViewById<TextView>(R.id.six)
        seven = findViewById<TextView>(R.id.seven)
        eight = findViewById<TextView>(R.id.eight)
        nine = findViewById<TextView>(R.id.nine)
        zero = findViewById<TextView>(R.id.zero)
        et_1 = findViewById<EditText>(R.id.et_1)
        et_2 = findViewById<EditText>(R.id.et_2)
        et_3 = findViewById<EditText>(R.id.et_3)
        et_4 = findViewById<EditText>(R.id.et_4)
        et_5 = findViewById<EditText>(R.id.et_5)
        et_6 = findViewById<EditText>(R.id.et_6)
        showPin = findViewById<LinearLayout>(R.id.showPin)
        clear = findViewById<LinearLayout>(R.id.clear)
        imgShowPin = findViewById<ImageView>(R.id.imgShowPin)
        one!!.setOnClickListener(this)
        two!!.setOnClickListener(this)
        three!!.setOnClickListener(this)
        four!!.setOnClickListener(this)
        five!!.setOnClickListener(this)
        six!!.setOnClickListener(this)
        seven!!.setOnClickListener(this)
        eight!!.setOnClickListener(this)
        nine!!.setOnClickListener(this)
        zero!!.setOnClickListener(this)
        clear!!.setOnClickListener(this)
        showPin!!.setOnClickListener(this)
        et_1!!.isEnabled = false
        et_2!!.isEnabled = false
        et_3!!.isEnabled = false
        et_4!!.isEnabled = false
        et_5!!.isEnabled = false
        et_6!!.isEnabled = false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.one -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("1")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("1")
                                }
                            } else {
                                et_4!!.setText("1")
                            }
                        } else {
                            et_3!!.setText("1")
                        }
                    } else {
                        et_2!!.setText("1")
                    }
                } else {
                    et_1!!.setText("1")
                }
            }
            R.id.two -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("2")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("2")
                                }
                            } else {
                                et_4!!.setText("2")
                            }
                        } else {
                            et_3!!.setText("2")
                        }
                    } else {
                        et_2!!.setText("2")
                    }
                } else {
                    et_1!!.setText("2");
                }
            }
            R.id.three -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("3")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("3")
                                }
                            } else {
                                et_4!!.setText("3")
                            }
                        } else {
                            et_3!!.setText("3")
                        }
                    } else {
                        et_2!!.setText("3")
                    }
                } else {
                    et_1!!.setText("3")
                }
            }
            R.id.four -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("4")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("4")
                                }
                            } else {
                                et_4!!.setText("4")
                            }
                        } else {
                            et_3!!.setText("4")
                        }
                    } else {
                        et_2!!.setText("4")
                    }
                } else {
                    et_1!!.setText("4")
                }
            }
            R.id.five -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("5")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("5")
                                }
                            } else {
                                et_4!!.setText("5")
                            }
                        } else {
                            et_3!!.setText("5")
                        }
                    } else {
                        et_2!!.setText("5")
                    }
                } else {
                    et_1!!.setText("5")
                }
            }
            R.id.six -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("6")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("6")
                                }
                            } else {
                                et_4!!.setText("6")
                            }
                        } else {
                            et_3!!.setText("6")
                        }
                    } else {
                        et_2!!.setText("6")
                    }
                } else {
                    et_1!!.setText("6")
                }
            }
            R.id.seven -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("7")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("7")
                                }
                            } else {
                                et_4!!.setText("7")
                            }
                        } else {
                            et_3!!.setText("7")
                        }
                    } else {
                        et_2!!.setText("7")
                    }
                } else {
                    et_1!!.setText("7")
                }
            }
            R.id.eight -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("8")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("8")
                                }
                            } else {
                                et_4!!.setText("8")
                            }
                        } else {
                            et_3!!.setText("8")
                        }
                    } else {
                        et_2!!.setText("8")
                    }
                } else {
                    et_1!!.setText("8")
                }
            }
            R.id.nine -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("9")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("9")
                                }
                            } else {
                                et_4!!.setText("9")
                            }
                        } else {
                            et_3!!.setText("9")
                        }
                    } else {
                        et_2!!.setText("9")
                    }
                } else {
                    et_1!!.setText("9")
                }
            }
            R.id.zero -> {
                if (et_1!!.length() == 1) {
                    if (et_2!!.length() == 1) {
                        if (et_3!!.length() == 1) {
                            if (et_4!!.length() == 1) {
                                if (et_5!!.length() == 1) {
                                    et_6!!.setText("0")
                                    OTPVerification(
                                        et_1!!.getText().toString() + et_2!!.getText()
                                            .toString() + et_3!!.getText().toString() +
                                                et_4!!.getText().toString() + et_5!!.getText()
                                            .toString() + et_6!!.getText().toString()
                                    )
                                    clearAll()
                                } else {
                                    et_5!!.setText("0")
                                }
                            } else {
                                et_4!!.setText("0")
                            }
                        } else {
                            et_3!!.setText("0")
                        }
                    } else {
                        et_2!!.setText("0")
                    }
                } else {
                    et_1!!.setText("0")
                }
            }
            R.id.showPin -> {
                if (show == false) {
                    et_1!!.transformationMethod = null
                    et_2!!.transformationMethod = null
                    et_3!!.transformationMethod = null
                    et_4!!.transformationMethod = null
                    et_5!!.transformationMethod = null
                    et_6!!.transformationMethod = null
                    show = true
                    imgShowPin!!.setBackgroundResource(R.drawable.ic_visibility)
                } else {
                    et_1!!.transformationMethod = PasswordTransformationMethod()
                    et_2!!.transformationMethod = PasswordTransformationMethod()
                    et_3!!.transformationMethod = PasswordTransformationMethod()
                    et_4!!.transformationMethod = PasswordTransformationMethod()
                    et_5!!.transformationMethod = PasswordTransformationMethod()
                    et_6!!.transformationMethod = PasswordTransformationMethod()
                    show = false
                    imgShowPin!!.setBackgroundResource(R.drawable.ic_visibility_off)
                }
            }
            R.id.clear -> {
                if (et_6!!.length() == 1) {
                    et_6!!.setText("")
                } else {
                    if (et_5!!.length() == 1) {
                        et_5!!.setText("")
                    } else {
                        if (et_4!!.length() == 1) {
                            et_4!!.setText("")
                        } else {
                            if (et_3!!.length() == 1) {
                                et_3!!.setText("")
                            } else {
                                if (et_2!!.length() == 1) {
                                    et_2!!.setText("")
                                } else {
                                    if (et_1!!.length() == 1) {
                                        et_1!!.setText("")
                                    } else {
                                        et_1!!.text.clear()
                                        et_2!!.text.clear()
                                        et_3!!.text.clear()
                                        et_4!!.text.clear()
                                        et_5!!.text.clear()
                                        et_6!!.text.clear()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        var strMOTP = ""
    }

    private fun OTPVerification(Mpin: String) {
        countOtp=0
        strMOTP =Mpin
            //et_1!!.text.toString() + et_2!!.text.toString() + et_3!!.text.toString() + et_4!!.text.toString() + et_5!!.text.toString() + et_6!!.text.toString()
        when (Config.ConnectivityUtils.isConnected(this)) {
            true -> {
                progressDialog = ProgressDialog(context, R.style.Progress)
                progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar)
                progressDialog!!.setCancelable(false)
                progressDialog!!.setIndeterminate(true)
                progressDialog!!.setIndeterminateDrawable(context.resources.getDrawable(R.drawable.progress))
                progressDialog!!.show()
                otpActivityViewModel.getOTP(this, strMOTP)!!.observe(
                    this,
                    Observer { serviceSetterGetter ->
                        val msg = serviceSetterGetter.message

                        if (msg!!.length > 0) {
                            if (countOtp == 0) {
                                countOtp++
                                val jObject = JSONObject(msg)
                                if (jObject.getString("StatusCode") == "0") {

                                    try {
                                        Log.e("TAG","5066661       "+jObject)
                                        var jobj = jObject.getJSONObject("UserLoginDetails")

                                        val FK_EmployeeSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF1,
                                            0
                                        )
                                        val FK_EmployeeEditer = FK_EmployeeSP.edit()
                                        FK_EmployeeEditer.putString(
                                            "FK_Employee",
                                            jobj.getString("FK_Employee")
                                        )
                                        FK_EmployeeEditer.commit()
                                        val UserNameSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF2,
                                            0
                                        )
                                        val UserNameEditer = UserNameSP.edit()
                                        UserNameEditer.putString("UserName", jobj.getString("UserName"))
                                        UserNameEditer.commit()
                                        val AddressSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF3,
                                            0
                                        )
                                        val AddressEditer = AddressSP.edit()
                                        AddressEditer.putString("Address", jobj.getString("Address"))
                                        AddressEditer.commit()
                                        val MobileNumberSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF4,
                                            0
                                        )
                                        val MobileNumberEditer = MobileNumberSP.edit()
                                        MobileNumberEditer.putString(
                                            "MobileNumber",
                                            jobj.getString("MobileNumber")
                                        )
                                        MobileNumberEditer.commit()
                                        val TokenSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF5,
                                            0
                                        )
                                        val TokenEditer = TokenSP.edit()
                                        TokenEditer.putString("Token", jobj.getString("Token"))
                                        TokenEditer.commit()
                                        val EmailSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF6,
                                            0
                                        )
                                        val EmailEditer = EmailSP.edit()
                                        EmailEditer.putString("Email", jobj.getString("Email"))
                                        EmailEditer.commit()

                                        val UserCodeSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF36,
                                            0
                                        )
                                        val UserCodeEditer = UserCodeSP.edit()
                                        UserCodeEditer.putString("UserCode", jobj.getString("UserCode"))
                                        UserCodeEditer.commit()

                                        val FK_BranchSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF37,
                                            0
                                        )
                                        val FK_BranchEditer = FK_BranchSP.edit()
                                        FK_BranchEditer.putString(
                                            "FK_Branch",
                                            jobj.getString("FK_Branch")
                                        )
                                        FK_BranchEditer.commit()

                                        val FK_BranchTypeSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF38,
                                            0
                                        )
                                        val FK_BranchTypeEditer = FK_BranchTypeSP.edit()
                                        FK_BranchTypeEditer.putString(
                                            "FK_BranchType",
                                            jobj.getString("FK_BranchType")
                                        )
                                        FK_BranchTypeEditer.commit()

                                        val FK_CompanySP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF39,
                                            0
                                        )
                                        val FK_CompanyEditer = FK_CompanySP.edit()
                                        FK_CompanyEditer.putString(
                                            "FK_Company",
                                            jobj.getString("FK_Company")
                                        )
                                        FK_CompanyEditer.commit()

                                        val FK_BranchCodeUserSP =
                                            applicationContext.getSharedPreferences(
                                                Config.SHARED_PREF40,
                                                0
                                            )
                                        val FK_BranchCodeUserEditer = FK_BranchCodeUserSP.edit()
                                        FK_BranchCodeUserEditer.putString(
                                            "FK_BranchCodeUser",
                                            jobj.getString("FK_BranchCodeUser")
                                        )
                                        FK_BranchCodeUserEditer.commit()

                                        val FK_UserRoleSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF41,
                                            0
                                        )
                                        val FK_UserRoleEditer = FK_UserRoleSP.edit()
                                        FK_UserRoleEditer.putString(
                                            "FK_UserRole",
                                            jobj.getString("FK_UserRole")
                                        )
                                        FK_UserRoleEditer.commit()

                                        val UserRoleSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF42,
                                            0
                                        )
                                        val UserRoleEditer = UserRoleSP.edit()
                                        UserRoleEditer.putString("UserRole", jobj.getString("UserRole"))
                                        UserRoleEditer.commit()

                                        val IsAdminSP = applicationContext.getSharedPreferences(
                                            Config.SHARED_PREF43,
                                            0
                                        )
                                        val IsAdminEditer = IsAdminSP.edit()
                                        IsAdminEditer.putString("IsAdmin", jobj.getString("IsAdmin"))
                                        IsAdminEditer.commit()

                                        val IsManagerSP = applicationContext.getSharedPreferences(Config.SHARED_PREF75, 0)
                                        val IsManagerEditer = IsManagerSP.edit()
                                        IsManagerEditer.putString("IsManager",jobj.getString("IsManager"))
                                        IsManagerEditer.commit()

                                        val ID_UserSP = applicationContext.getSharedPreferences(Config.SHARED_PREF44, 0)
                                        val ID_UserEditer = ID_UserSP.edit()
                                        ID_UserEditer.putString("ID_User", jobj.getString("ID_User"))
                                        ID_UserEditer.commit()

                                        val BranchNameSP = applicationContext.getSharedPreferences(Config.SHARED_PREF45, 0)
                                        val BranchNameEditer = BranchNameSP.edit()
                                        BranchNameEditer.putString("BranchName", jobj.getString("BranchName"))
                                        BranchNameEditer.commit()

                                        val CompanyCategorySP = applicationContext.getSharedPreferences(Config.SHARED_PREF46, 0)
                                        val CompanyCategoryEditer = CompanyCategorySP.edit()
                                        CompanyCategoryEditer.putString("CompanyCategory", jobj.getString("CompanyCategory"))
                                        CompanyCategoryEditer.commit()

                                        val ModuleListSP = applicationContext.getSharedPreferences(Config.SHARED_PREF54, 0)
                                        val ModuleListEditer = ModuleListSP.edit()
                                        ModuleListEditer.putString("ModuleList", jobj.getString("ModuleList"))
                                        ModuleListEditer.commit()

                                        val FK_DepartmentSP = applicationContext.getSharedPreferences(Config.SHARED_PREF55, 0)
                                        val FK_DepartmentEditer = FK_DepartmentSP.edit()
                                        FK_DepartmentEditer.putString("FK_Department", jobj.getString("FK_Department"))
                                        FK_DepartmentEditer.commit()

                                        val DepartmentSP = applicationContext.getSharedPreferences(Config.SHARED_PREF56, 0)
                                        val DepartmentEditer = DepartmentSP.edit()
                                        DepartmentEditer.putString("Department", jobj.getString("Department"))
                                        DepartmentEditer.commit()

                                        val UtilityListSP = applicationContext.getSharedPreferences(Config.SHARED_PREF57, 0)
                                        val UtilityListEditer = UtilityListSP.edit()
                                        UtilityListEditer.putString("UtilityList", jobj.getString("UtilityList"))
                                        UtilityListEditer.commit()

                                        val LocLongitudeSP = applicationContext.getSharedPreferences(Config.SHARED_PREF58, 0)
                                        val LocLongitudeEditer = LocLongitudeSP.edit()
                                        LocLongitudeEditer.putString("LocLongitude", jobj.getString("LocLongitude"))
                                        LocLongitudeEditer.commit()

                                        val LocLattitudeSP = applicationContext.getSharedPreferences(Config.SHARED_PREF59, 0)
                                        val LocLattitudeEditer = LocLattitudeSP.edit()
                                        LocLattitudeEditer.putString("LocLattitude", jobj.getString("LocLattitude"))
                                        LocLattitudeEditer.commit()


                                        val LocLocationNameSP = applicationContext.getSharedPreferences(Config.SHARED_PREF60, 0)
                                        val LocLocationNameEditer = LocLocationNameSP.edit()
                                        LocLocationNameEditer.putString("LocLocationName", jobj.getString("LocLocationName"))
                                        LocLocationNameEditer.commit()

                                        val EnteredDateSP = applicationContext.getSharedPreferences(Config.SHARED_PREF61, 0)
                                        val EnteredDateEditer = EnteredDateSP.edit()
                                        EnteredDateEditer.putString("EnteredDate", jobj.getString("EnteredDate"))
                                        EnteredDateEditer.commit()

                                        val EnteredTimeSP = applicationContext.getSharedPreferences(Config.SHARED_PREF62, 0)
                                        val EnteredTimeEditer = EnteredTimeSP.edit()
                                        EnteredTimeEditer.putString("EnteredTime", jobj.getString("EnteredTime"))
                                        EnteredTimeEditer.commit()

                                        val StatusSP = applicationContext.getSharedPreferences(Config.SHARED_PREF63, 0)
                                        val StatusEditer = StatusSP.edit()
                                        StatusEditer.putString("Status", jobj.getString("Status"))
                                        StatusEditer.commit()

                                        val CRMDetailsSP = applicationContext.getSharedPreferences(Config.SHARED_PREF79, 0)
                                        val CRMDetailsEditer = CRMDetailsSP.edit()
                                        CRMDetailsEditer.putString("CRMDetails", jobj.getString("CRMDetails"))
                                        CRMDetailsEditer.commit()

                                        val FollowUpDetailsSP = applicationContext.getSharedPreferences(Config.SHARED_PREF80, 0)
                                        val FollowUpDetailsEditer = FollowUpDetailsSP.edit()
                                        FollowUpDetailsEditer.putString("FollowUpDetails", jobj.getString("FollowUpDetails"))
                                        FollowUpDetailsEditer.commit()

                                        val PSValueSP = applicationContext.getSharedPreferences(Config.SHARED_PREF81, 0)
                                        val PSValueEditer = PSValueSP.edit()
                                        PSValueEditer.putString("PSValue", jobj.getString("PSValue"))
                                        PSValueEditer.commit()

                                        val PnotificationIDSP = applicationContext.getSharedPreferences(Config.SHARED_PREF82, 0)
                                        val PnotificationIDEditer = PnotificationIDSP.edit()
                                        PnotificationIDEditer.putString("PnotificationID", "0")
                                        PnotificationIDEditer.commit()

                                        val ID_TokenUserSP = applicationContext.getSharedPreferences(Config.SHARED_PREF85, 0)
                                        val ID_TokenUserEditer = ID_TokenUserSP.edit()
                                        ID_TokenUserEditer.putString("ID_TokenUser", jobj.getString("ID_TokenUser"))
                                        ID_TokenUserEditer.commit()

                                        var ID_Company = db!!.getLastInsertCompanyID()

                                        var FK_Employee = jobj.getString("FK_Employee")
                                        var UserName = jobj.getString("UserName")
                                        var Address = jobj.getString("Address")
                                        var MobileNumber = jobj.getString("MobileNumber")
                                        var Token = jobj.getString("Token")
                                        var Email = jobj.getString("Email")
                                        var UserCode = jobj.getString("UserCode")
                                        var FK_Branch = jobj.getString("FK_Branch")
                                        var FK_BranchType = jobj.getString("FK_BranchType")
                                        var FK_Company = jobj.getString("FK_Company")
                                        var FK_BranchCodeUser = jobj.getString("FK_BranchCodeUser")
                                        var FK_UserRole = jobj.getString("FK_UserRole")
                                        var UserRole = jobj.getString("UserRole")
                                        var IsAdmin = jobj.getString("IsAdmin")
                                        var IsManager = jobj.getString("IsManager")
                                        var ID_User = jobj.getString("ID_User")
                                        var BranchName = jobj.getString("BranchName")
                                        var FK_Department = jobj.getString("FK_Department")
                                        var Department = jobj.getString("Department")
                                        var CompanyCategory = jobj.getString("CompanyCategory")
                                        var ID_TokenUser = jobj.getString("ID_TokenUser")

                                        db!!.insertUpdateLoginUser(ID_Company!!,FK_Employee,UserName,Address,MobileNumber,Token,Email, UserCode,FK_Branch,
                                            FK_BranchType,FK_Company,FK_BranchCodeUser,FK_UserRole,UserRole,IsAdmin, IsManager,ID_User,
                                            BranchName,FK_Department,Department,CompanyCategory,ID_TokenUser)



                                        val i = Intent(this@OTPActivity, SetMpinActivity::class.java)
                                        startActivity(i)
                                        finish()
                                    }catch (e: Exception){
                                        Log.e("TAG","5066662   "+e.toString())
                                    }



                                } else {
                                    val builder = AlertDialog.Builder(
                                        this@OTPActivity,
                                        R.style.MyDialogTheme
                                    )
                                    builder.setMessage(jObject.getString("EXMessage"))
                                    builder.setPositiveButton("Ok") { dialogInterface, which ->
                                    }
                                    val alertDialog: AlertDialog = builder.create()
                                    alertDialog.setCancelable(false)
                                    alertDialog.show()
                                    clearAll()
                                    progressDialog!!.dismiss()
                                }
                            }
                        } else {
//                            Toast.makeText(
//                                applicationContext,
//                                "Some Technical Issues.",
//                                Toast.LENGTH_LONG
//                            ).show()
                        }
                    })
                progressDialog!!.dismiss()
            }
            false -> {
//                Toast.makeText(applicationContext, "No Internet Connection.", Toast.LENGTH_LONG)
//                    .show()
            }
        }
    }

    private fun clearAll() {
        et_1!!.text.clear()
        et_2!!.text.clear()
        et_3!!.text.clear()
        et_4!!.text.clear()
        et_5!!.text.clear()
        et_6!!.text.clear()
    }

    override fun onRestart() {
        super.onRestart()
        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

}


