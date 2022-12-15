package com.sartor.data.db

import android.content.Context
import android.content.SharedPreferences
import com.sartor.data.api.request.ChangePasswordRequest
import com.sartor.data.api.request.SignUpRequest
import com.sartor.data.api.response.LoginResponse
import com.sartor.data.model.CheckoutBillingDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreference @Inject constructor(@ApplicationContext context: Context) {

    private  val BASE_URL = "http://52.159.111.122:8082/"

        private val PREFS_NAME = "com.sartor.app"
        private val LOGIN_TOKEN = "login_token"
        private val USER_EMAIL = "user_email"
        private val USER_ROLE = "user_role"
        private val USER_FULL_NAME = "full_name"
        private val USER_PICTURE = "user_profile_picture"
        private val USER_PASSWORD = "user_password"
        private val IS_LOGIN = "is_login"

        private val CHANGE_PASSWORD_EMAIL = "change_password_email"
        private val CHANGE_PASSWORD = "change_password"
        private val CHANGE_PASSWORD_PREVIOUS = "change_password_confirm"


       private val CHECKOUT_AMOUNT = "checkout_amount"
       private val CHECKOUT_BILLING_ADDRESS = "checkout_billing_ADDRESS"
       private val CHECKOUT_MOBILE = "checkout_mobile"
       private val CHECKOUT_COUNTRY = "checkout_country"
       private val CHECKOUT_STATE = "checkout_state"
       private val CHECKOUT_ZIPCODE = "checkout_zipcode"
       private val CHECKOUT_CARD_NAME = "checkout_card_name"
       private val CHECKOUT_CARD_NUMBER = "checkout_card_number"
       private val CHECKOUT_CARD_EXPIRE_MONTH = "checkout_expire_month"
       private val CHECKOUT_CARD_EXPIRE_YEAR = "checkout_expire_year"




        val sharedPref: SharedPreferences = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /*Functions to persist data*/




            fun saveUserData( loginResponse: LoginResponse, isUserLogin : Boolean = false) {

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString(LOGIN_TOKEN, loginResponse.token)
            editor.putString(USER_EMAIL, loginResponse.user.username)
            editor.putString(USER_ROLE, loginResponse.user.role)


           if(loginResponse.user.customers!==null)
           {
               editor.putString(USER_FULL_NAME, loginResponse.user.customers.fullName)

               editor.putString(USER_PICTURE, BASE_URL + loginResponse.user.customers.picture)
           }
            editor.putBoolean(IS_LOGIN, isUserLogin)
            editor.apply()

            }


    fun saveEmail(email: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER_EMAIL,email)
        editor.apply()
    }


    fun savePassword(password: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER_PASSWORD,password)
        editor.apply()
    }

    fun saveFullName(fullName: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER_FULL_NAME,fullName)
        editor.apply()
    }

    fun save(key:String,value: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key,value)
        editor.apply()
    }


    fun saveRole(role: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER_ROLE,role)
        editor.apply()
    }

    fun saveCheckoutAmount(amount : Int ){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(CHECKOUT_AMOUNT,amount)
        editor.apply()
    }

    fun saveShippingDetails(
        billingAddress: String,
        mobileNumber: String,
        country: String,
        state: String,
        zipCode: Int

    ){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(CHECKOUT_BILLING_ADDRESS,billingAddress)
        editor.putString(CHECKOUT_MOBILE,mobileNumber)
        editor.putString(CHECKOUT_COUNTRY,country)
        editor.putString(CHECKOUT_STATE,state)
        editor.putInt(CHECKOUT_ZIPCODE,zipCode)
        editor.apply()
    }


    fun getCheckoutDetail():CheckoutBillingDetails = CheckoutBillingDetails(
        sharedPref.getInt(CHECKOUT_AMOUNT,0),
        sharedPref.getString(CHECKOUT_BILLING_ADDRESS,"")!!,
        sharedPref.getString(CHECKOUT_MOBILE,"")!!,
        sharedPref.getString(CHECKOUT_COUNTRY,"")!!,
        sharedPref.getString(CHECKOUT_STATE,"")!!,
        sharedPref.getInt(CHECKOUT_ZIPCODE, 0),
        "",
        "",
        "",
        "",
        "",
    )




    fun getRegisteredUserData(): SignUpRequest = SignUpRequest(
        sharedPref.getString(USER_EMAIL,""),
        sharedPref.getString(USER_PASSWORD,""),
        sharedPref.getString(USER_FULL_NAME,""),
        sharedPref.getString(USER_ROLE,"customers"),

    )


    fun get(key: String): String? {
        return sharedPref.getString(key,"")
    }

    fun getProfileImage(): String? {
        return sharedPref.getString(USER_PICTURE,"")
    }


    fun getFullName(): String? {
        return sharedPref.getString(USER_FULL_NAME,"")
    }






//        fun save(KEY_NAME: String, status: Boolean) {
//
//            val editor: SharedPreferences.Editor = sharedPref.edit()
//
//            editor.putBoolean(KEY_NAME, status!!)
//
//            editor.apply()
//        }

        fun getToken(): String? {
            return sharedPref.getString(LOGIN_TOKEN, null)
        }

        fun isLogin(): Boolean {
        return sharedPref.getBoolean(IS_LOGIN, false)
        }


        fun logoutUser() {

            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.clear()
            editor.apply()
        }



//    Change password


    fun forgotPasswordEmail(email : String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(CHANGE_PASSWORD_EMAIL,email)
        editor.apply()
    }


    fun forgotPasswordPrevious(password : String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(CHANGE_PASSWORD,password)
        editor.apply()
    }



    fun forgotPasswordNew(password: String ){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(CHANGE_PASSWORD_PREVIOUS,password)
        editor.apply()
    }




    fun getForgotEmail(): String? {
        return sharedPref.getString(CHANGE_PASSWORD_EMAIL,null)
    }

    fun getForgotPassword(): ChangePasswordRequest {
        return ChangePasswordRequest(
            sharedPref.getString(CHANGE_PASSWORD,null),
             sharedPref.getString(CHANGE_PASSWORD_PREVIOUS,null)
        )
    }









    fun removeValue(KEY_NAME: String) {

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.remove(KEY_NAME)
            editor.commit()
        }


}