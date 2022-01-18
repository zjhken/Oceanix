package vip.bluewater.oceanix.server.components

import com.sshtools.client.SshClient
import java.io.File

var sshClient: SshClient? = null

fun initSshClient() {
  sshClient = SshClient("10.0.0.16", 22, "kenneth",File("C:\\Users\\Kenne\\Apps\\SSH_config\\common_ssh.key"))
  val s = sshClient!!.executeCommand("lscpu")
  println(s)
}
