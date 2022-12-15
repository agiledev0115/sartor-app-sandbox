package com.sartor.util.constants

import com.sartor.R
import com.sartor.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val brandsStatuses: List<BrandStatus> = listOf(
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
    BrandStatus("Adidas", R.drawable.ic_adidas),
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

val products: List<Product> = listOf(
    Product("Nasty Gal", "$300", "Adidas", "",""),
    Product("Nasty Gal", "$300", "Adidas", "R.drawable.two",""),
    Product("Nasty Gal", "$300", "Adidas", "R.drawable.three",""),
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
/*
val reviews: List<Review> = listOf(
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.one
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.two
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.three
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.four
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.five
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.six
    ),
    Review(
        "Heather Marron",
        R.drawable.fire,
        "03/07/2021",
        "I got it for my daughter for her 16th birthday. She loved it. It came earlier than expected. I recommend buying from this seller",
        R.drawable.seven
    ),


    )*/

val blogList: List<Blog> = listOf(
    Blog(
        "What exactly is fashion?",
        "So do we even ask ourselves what fashion is exactly??",
        R.drawable.magazine_img
    ), Blog(
        "What exactly is fashion?",
        "So do we even ask ourselves what fashion is exactly??",
        R.drawable.magazine_img
    ),
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