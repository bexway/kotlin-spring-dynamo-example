package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter

class HeightTypeConverter : DynamoDBTypeConverter<String, Height> {

    override fun convert(height: Height): String? {
        var heightString: String? = null
        try {
            heightString = String.format("%s' %s\"", height.feet, height.inches)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return heightString
    }

    override fun unconvert(s: String?): Height {
        var height = Height()
        try {
            println(s)
            if (s != null && s.isNotEmpty()) {
                val data = s.split("[\"\']".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                println(data)
                height = Height(
                    data[0].trim { it <= ' ' }.toInt(),
                    data[1].trim { it <= ' ' }.toInt()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return height
    }
}
