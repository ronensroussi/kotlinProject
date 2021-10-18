
open class IP(address : String) {

    var address : String = address


    public open fun isValidIp() : Boolean{
        val octets : List<String> = this.address.split(".")
        if (octets.size != 4){
            return false
        }

        for (octet in octets){
            val isNumber = octet.all { ch -> ch.isDigit()}
            if(!isNumber){
                return false
            }
            val intOctet = octet.toInt()
            if(intOctet < 0 || intOctet > 255){
                return false
            }
        }

        return true
    }

    protected fun isValidIp(address: String) : Boolean{
        val octets : List<String> = address.split(".")
        if (octets.size != 4){
            return false
        }

        for (octet in octets){
            val isNumber = octet.all { ch -> ch.isDigit()}
            if(!isNumber){
                return false
            }
            val intOctet = octet.toInt()
            if(intOctet < 0 || intOctet > 255){
                return false
            }
        }

        return true
    }

    public fun getIpDecimal(): Long{

        return if(isValidIp()){
            var res :Long = 0L;
            val ipAddressInArray : List<String> = address.split(".");

            for (i in 3 downTo 0) {
                val ip : Long = ipAddressInArray[3 - i].toLong();
                res = res or (ip shl (i * 8))
            }
            res
        } else{
            -1;
        }
    }
}