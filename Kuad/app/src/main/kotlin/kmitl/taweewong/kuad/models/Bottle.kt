package kmitl.taweewong.kuad.models

data class Bottle(
       val bottleId: String = "",
       val bottleTitle: String = "",
       val owner: String = "",
       val createdAt: String = "",
       val isHolding: Boolean = false,
       val messages: HashMap<String, Message> = HashMap()
)
