package android.rezkyauliapratama.com.qlue_project.data.model

data class Complaint(
        val id: Int,
        val user_id: Int,
        val username: String,
        val lat: Double,
        val lng: Double,
        val progress: String,
        val timestamp: String,
        val image_url: String,
        val format: String,
        val tag1: String,
        val tag2: String,
        val title: String,
        val description: String,
        val kelurahan: String,
        val kode_kelurahan: String,
        val kecamatan: String,
        val kode_kecamatan: String,
        val kabupaten: String,
        val kode_kota: String,
        val provinsi: String,
        val back_count: Int,
        val rt_rw: String
)