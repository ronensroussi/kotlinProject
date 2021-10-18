
val suspiciousList = listOf<String>("1.1.1.100/24", "2.2.2.0/20", "169.128.0.1/26", "6.6.63.20/30", "192.168.0.0/16")


fun main(args: Array<String>) {

    val fw = FireWall()

    fw.addCiderIpListToBlackList(suspiciousList)

    fw.isAllowed("1.1.2.25") //true
    fw.isAllowed("1.1.1.1") // false
    fw.isAllowed("1.1.1.255") //false
    fw.isAllowed("169.128.0.20") // false
    fw.isAllowed("169.128.0.62") // false
    fw.isAllowed("169.128.0.64") // true
    fw.isAllowed("6.6.62.25") // true
    fw.isAllowed("6.6.63.24") // true
    fw.isAllowed("192.168.20.0") // false
    fw.isAllowed("192.168.20.255")// false
    fw.isAllowed("192.168.255.255") // false
    fw.isAllowed("192.169.255.255") // true

    //illegal ips
    fw.isAllowed("192.169.255.255/23") // false
    fw.isAllowed("192.764.255.255") // false
    fw.isAllowed("-422.169.255.255") // false
    fw.isAllowed("111.111.111.111") // true



}





