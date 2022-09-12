package com.task.artivatic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExampleData {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var rows: List<Row>? = null

    class Row {
        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("imageHref")
        @Expose
        var imageHref: String? = null
    }
}