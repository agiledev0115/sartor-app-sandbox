package com.sartor.data.api

import com.sartor.data.api.request.*
import com.sartor.data.api.response.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

/*Authentication*/


    //Signup
    @POST("/api/users/signup")
    suspend fun createUser(@Body signUpRequest: SignUpRequest) : Response<SignUpResponse>

    //Login
    @POST("/api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    //logout
    @GET("/api/users/logout")
     suspend fun logoutUser()

     //Forgot password
    @PUT("/api/users/password/forgot_password")
     suspend fun forgotPassword(@Query("email") emailAddress : String )

    //update password
    @PUT("/api/users/password/update")
     suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest)

    //TODO -> Attach request and response parameters
     /*Customer*/


    //List of bought Items
    @GET("/api/customers/bought_items")
    suspend fun getBoughtItems()

    //List of cart Items
    @GET("/api/customers/carts/{id}")
    suspend fun getCartItemList(@Path("id") id : String)


    //List of favourite items
    @GET("/api/customers/favorite")
    suspend fun getFavouriteItems()

    //Update profile picture
    @PUT("/api/customers/profileUpdate")
    suspend fun updateProfilePicture()

    //upload profile Picture
    @PUT("/api/customers/profilePicture")
    suspend fun uploadProfilePicture()

    //Delete profile Picture
    @DELETE("/api/customers/profilePic")
    suspend fun deleteProfilePicture()


/*Blog API*/

    //View all blogs
    @GET("/api/blog")
    suspend fun getAllBlogs() : Response<List<BlogResponse>>

    //get specific blogs
    @GET("/api/blog/{id}")
    suspend fun getBlog(@Path("id") id : String) : Response<BlogResponse>

    //Search blogs
    @POST("/api/blog/search")
    suspend fun searchBlog(@Body blogRequest: BlogRequest)



    /*Brands API*/

    //get All Brands
    @GET("/api/brands")
    suspend fun getAllBrands() : Response<List<BrandResponse>>

    //get All Top Brands
    @GET("/api/brands/top")
    suspend fun getAllTopBrands(@Query("is_top") isTop : Boolean)

    //View Specific product under brand
    @GET("/api/brands/products/{id}")
    suspend fun getProductUnderBrand(@Path("id") id: String): Response<List<ProductResponse>>

    //Product sold under brand
    @GET("/api/brands/sales/{id}")
    suspend fun getProductSoldUnderBrand(@Path("id") id: String)

    //Brand follower
    @GET("/api/brands/follow/{id}")
    suspend fun getBrandFollowers(@Path("id") id: String)

    //Brand likes
    @GET("/api/brands/likes/{id}")
    suspend fun getBrandLikes(@Path("id") id: String)

    //View specific brand
    @GET("/api/brands/{id}")
    suspend fun getSpecificBrand(@Path("id") id: String): Response<BrandResponse>

    //Brand messages to buyer
    @GET("/api/messages/{id}")
    suspend fun getBrandMessages(@Path("id") id: String)

    //search brand
    @POST("/api/brands/search")
    suspend fun searchBrands(@Query("title") title: String)


    /*Cart/Bags API*/

    //List cart
    @GET("/api/carts/lists")
    suspend fun getCartList() : Response<CartResponse>

    //specific cart Item
    @GET("/api/carts/{id}")
    suspend fun getCart(@Path("id") id : String )

    //Adding item to bag
    @POST("/api/carts")
    suspend fun addToCart(@Body addToCartRequest: AddToCartRequest ) : Response<CartResponse>

    //Removing item to bag
    @DELETE("/api/carts/{id}")
    suspend fun removeFromCart(@Query("id") id : String )


/*Category API*/

    //specific cart Item
    @GET("/api/categories")
    suspend fun getCategories( )


/*Check out*/

    //get all checkout items
    @GET("/api/checkout/lists")
    suspend fun getCheckoutList( )


    @GET("/api/checkout/{id}")
    suspend fun getCheckout(@Path("id") id : String)

    @POST("/api/checkout")
    suspend fun startCheckout(@Body checkOutRequest : CheckoutRequest) : Response<CheckoutResponse>


/*Favourite*/

    @GET("/api/favorite/list")
    suspend fun getFavouriteList()

    @GET("/api/favorite/{id}")
    suspend fun getFavouriteList(@Path("id") id : String )

    @POST("/api/favorite")
    suspend fun CRProduct(@Query("product") product : String )


/*Message*/

    @GET("/api/message/lists")
    suspend fun getMessageList()

    @GET("/api/message/lists")
    suspend fun addMessage(@Query("message") message : String)


/*Notification*/

    @GET("/api/notification/lists")
    suspend fun getNotificationList()

    @GET("/api/notification/customer/unread")
    suspend fun getUnReadNotificationList()

    @GET("/api/notification/customer/read")
    suspend fun getReadNotificationList()


    /*Product API*/

    @GET("/api/product")
    suspend fun getProductList() : Response<List<ProductResponse>>

    @GET("/api/product/{id}")
    suspend fun getProduct(@Path("id") id : String ) : Response<ProductResponse>

    @POST("/api/product/search")
    suspend fun searchProduct(@Query("name") searchQuery : String )


    /* Review API */

    @GET("/api/reviews")
    suspend fun getReviews()

    @GET("/api/reviews/lists")
    suspend fun getReviewList()

    @GET("/api/reviews/{id}")
    suspend fun getReview(@Path("id") id : String )

    @POST("/api/reviews")
    suspend fun addReview(@Body reviewRequest: ReviewRequest)

    @PUT("/api/reviews/{id}")
    suspend fun updateReview(@Path("id ") id : String, @Body reviewRequest: ReviewRequest)

    @DELETE("/api/reviews/{id}")
    suspend fun removeReview(@Path("id") id : String )


    /*Wish list API*/

    @GET("/api/wishlists/lists")
    suspend fun getWishList()

    @POST("/api/wishlists")
    suspend fun addWishList(@Query("product") product : String)
//TODO -> check
    @PUT("/api/wishlists/{id}")
    suspend fun updateWishList(@Path("id") id : String, )

    @DELETE("/api/wishlists/{id}")
    suspend fun removeWishList(@Path("id") id : String, )
}