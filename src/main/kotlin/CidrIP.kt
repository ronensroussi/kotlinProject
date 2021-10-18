
class CidrIP(address: String) : IP(address) {

    private var subnet : String = ""


    public override fun isValidIp(): Boolean{
        val ipParts : List<String> = super.address.split("/")
        if (ipParts.size != 2 ){
            return false
        }

        var prefix : String = ipParts[0]
        if(!this.isValidIp(prefix)){
            return false
        }

        var subnet :String = ipParts[1]
        if(!isValidSubnet(subnet)){
            return false
        }
        this.address = prefix
        this.subnet = subnet
        return true

    }


        public fun getSubnetMaskDecimal() : Long {
        return if(subnet.isNotEmpty()){
            val subnetAsInt = subnet.toInt()
            val allOnes = 0xffffffffL
            allOnes.shl(32 - subnetAsInt)
        } else {
            -1;
        }
    }


    private fun isValidSubnet(subnet: String) : Boolean{

        val isNumber = subnet.all { ch -> ch.isDigit()}
        if(!isNumber){
            return false
        }
        val intSubnet = subnet.toInt()
        if( intSubnet < 0 || intSubnet > 32){
            return false
        }

        return true

    }


}