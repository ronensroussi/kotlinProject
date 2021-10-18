class FireWall() {

    private val networksBlackList: MutableList<Pair<Long,Long>> = mutableListOf()



    fun isAllowed(incomingIp: String): Boolean{
        val ip: IP = IP(incomingIp)
        if(!ip.isValidIp()){
            println("IP $incomingIp IS INVALID......DROPPING PACKET!")
            return false
        }

        val ipDecimal : Long = ip.getIpDecimal()
        for (entry in networksBlackList){
            val incomingIpNetwork : Long = (entry.first and ipDecimal)
            if(incomingIpNetwork == entry.second){
                println("IP $incomingIp IS ON ONE OF THE SUSPICIOUS NETWORKS, DROPPING PACKET!")
                return false
            }
        }
        println("IP $incomingIp IS ALLOWED TO PASS!")
        return true
    }

    private fun addCidrIpToBlackList(cidrIP : CidrIP){
        val prefix = cidrIP.getIpDecimal()
        val subnet = cidrIP.getSubnetMaskDecimal()

        if(prefix > 0 && subnet > 0){
            val network = prefix and subnet
            this.networksBlackList.add(Pair(subnet, network))
        }
        else{
            println("The Given cider IP is not valid ${cidrIP.address}")
        }
    }

    fun addCiderIpListToBlackList(listOfIps: List<String>){
        for (ip in listOfIps){
            val cidrIP: CidrIP = CidrIP(ip)
            addCidrIpToBlackList(cidrIP)
        }
    }
}