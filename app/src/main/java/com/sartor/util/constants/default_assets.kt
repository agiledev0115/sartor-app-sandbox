package com.sartor.util.assets

import com.sartor.R
import com.sartor.data.api.response.BlogResponse
import com.sartor.data.api.response.BrandResponse
import com.sartor.data.api.response.ProductResponse
import com.sartor.data.model.*

val brandsStatuses: List<BrandStatus> = listOf(
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
)
val brandAdidas: BrandResponse = BrandResponse(
    "2022-12-20",listOf("followers"),"1",
    "https://www.freepnglogos.com/uploads/adidas-logo-png-black-0.png",
    true, listOf("likes"),"Adidas")
val brandNike: BrandResponse = BrandResponse(
    "2022-12-20",listOf("followers"),"2",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxIi6gMPvrnlwYIn6XofUraBrJFRiGYEV_oe0CMqqHFOoCmX8&s",
    true, listOf("likes"),"Nike")
val brandHugo: BrandResponse = BrandResponse(
    "2022-12-20",listOf("followers"),"2",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    true, listOf("likes"),"Hugo")

val brandData: List<Brands> = listOf(
    Brands( listOf("followers"),listOf("likes"),true,
        "https://www.freepnglogos.com/uploads/adidas-logo-png-black-0.png",
        "Adidas","1"),
    Brands( listOf("followers"),listOf("likes"),true,
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxIi6gMPvrnlwYIn6XofUraBrJFRiGYEV_oe0CMqqHFOoCmX8&s",

        "Nike","2"),
    Brands( listOf("followers"),listOf("likes"),true,
        "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
        "Hugo","3"),
)
val brandList: List<BrandResponse> = listOf(
    brandAdidas,
    brandNike,
    brandHugo,
)
val trends: List<Trend> = listOf(
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
    Trend("Latest designs", R.drawable.red),
)

//val createdBy:ProductResponse.CreatedBy = ProductResponse.CreatedBy()
val imgAdidas: ProductResponse.Img = ProductResponse.Img(
    "2022-12-12","Admin","1",
    "https://www.freepnglogos.com/uploads/adidas-logo-png-black-0.png",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg"
)
val imgNike: ProductResponse.Img = ProductResponse.Img(
    "2022-12-12","Admin","1",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxIi6gMPvrnlwYIn6XofUraBrJFRiGYEV_oe0CMqqHFOoCmX8&s",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg"
)
val imgHugo: ProductResponse.Img = ProductResponse.Img(
    "2022-12-12", "Admin","1",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
    "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg"
)


val products: List<ProductResponse> = listOf(
    ProductResponse(
        ProductResponse.Brands(
            "2022-12-12",
            listOf("followers"),"1",
            "https://www.freepnglogos.com/uploads/adidas-logo-png-black-0.png",
            true,listOf("likes"),
            "Adidas"), "Sport", "2022-12-12",
        ProductResponse.CreatedBy(
            true,
            "FALSE",
            true,
            "2022-12-12",
            "1",
            ProductResponse.CreatedBy.Internal(
                "2022-12-12",
                "Adidas",
                "2022-12-12",
                "admin",
                "admin",
                "admin",
                "https://www.freepnglogos.com/uploads/adidas-logo-png-black-0.png",
                "2022-12-12",
                "admin",
            ),
            true,
            "admin",
            "Active",
            "Admin"
        ),"Adidas Desciption","1",
        imgAdidas,"Shoes Adidas",100,5,2,"Adidas"
    )
)
val productsData: List<Product> = listOf(
    Product(
       "Shoes", "100", "Adidas",
        "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
        "1"),
    Product(
        "Shoes", "100", "Adidas",
        "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
        "1"),
    Product(
        "Shoes", "100", "Adidas",
        "https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg",
        "1")
)
val sellers: List<Seller> = listOf(
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
    Seller("Hugo boss", R.drawable.rect, 23, 832),
)

val bagItems: List<ShoppingBagItem> = listOf(
    ShoppingBagItem("Cool", "$372", "Cool guy", 1, R.drawable.one),
    ShoppingBagItem("Nice", "$372", "Nice", 1, R.drawable.two),
    ShoppingBagItem("Nice", "$372", "Nice", 1, R.drawable.three),
    ShoppingBagItem("Nice", "$372", "Nice", 1, R.drawable.four),
    ShoppingBagItem("Nice", "$372", "Nice", 1, R.drawable.five),
    ShoppingBagItem("Nice", "$372", "Nice", 1, R.drawable.six),
    ShoppingBagItem("Suits", "$372", "Suits", 1, R.drawable.seven),
    ShoppingBagItem("Nasty girl", "$372", "Nasty girl", 1, R.drawable.eight),
    ShoppingBagItem("Nasty girl", "$372", "Nasty girl", 1, R.drawable.nine),
)

val photoGallery: List<ProductImage> = listOf(
    ProductImage("https://img.freepik.com/free-photo/woman-black-trousers-purple-blouse-laughs-leaning-stand-with-elegant-clothes-pink-background_197531-17614.jpg?size=626&ext=jpg"),
    ProductImage("https://image.shutterstock.com/image-photo/young-beautiful-woman-multicolored-packages-260nw-1013872861.jpg"),
    ProductImage("https://i.pinimg.com/originals/66/82/f5/6682f56b7e64e100c798239c6b13fa25.jpg"),
    ProductImage("https://i.pinimg.com/564x/68/5d/45/685d45f1867b27d6a2b0e13802488f43--casual-dressing-pakistani-dresses.jpg"),
    ProductImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOZhcvG0tqgLTelEEe2iOG6B5cbPkIik2HfA&usqp=CAU"),
    ProductImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6LqjMF065mO0lMJTV3bzHTaX_N0nlNjBNew&usqp=CAU"),
    ProductImage("https://i.pinimg.com/236x/98/c7/98/98c798d1959e6aff3c3e4cdd638f1343.jpg"),
    ProductImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY4pYr1S10YrZx8-_EO9HHHCcpXkfcf8Rn0Q&usqp=CAU"),
    ProductImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"),
)

val reviews: List<Review> = listOf(
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU"
    ),


    )

val blogList: List<BlogResponse> = listOf(
    BlogResponse("2022-12-11",
        BlogResponse.CreatedBy(
            true,
            "FALSE",
            true,
            "1",
            "1",
            true,
            "2022-12-12",
            "Admin",
            "Active",
            "Admin"
        ),
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgEcdRp5cMYDNXXYSGPBcJiH1aRcx7QN9U9g&usqp=CAU",
        "So do we even ask ourselves what fashion is exactly??",
        "story 01",
        "Title 01",
    )
)

val coupons: List<Coupon> = listOf(
    Coupon("Adve Stores", "24% Off"),
    Coupon("Adve Stores", "14% Off"),
    Coupon("Adve Stores", "30% Off"),
    Coupon("Adve Stores", "20% Off"),
    Coupon("Adve Stores", "10% Off"),
    Coupon("Adve Stores", "42% Off"),
    Coupon("Adve Stores", "5% Off"),
)