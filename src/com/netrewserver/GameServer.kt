package com.netrewserver

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.esotericsoftware.kryonet.Server
import com.netrewserver.requests.SomeRequest

class GameServer : Listener() {
    val server = Server()

    init {
        server.kryo.register(SomeRequest::class.java)
        server.start()
        server.bind(13370, 13371)
    }

    override fun connected(connection: Connection) {
        val clientIP = connection.remoteAddressTCP.hostString
        println("${clientIP} connected")
    }

    override fun received(connection: Connection, obj: Any) {
        if (obj is SomeRequest) {
            println(obj.text)
        }
    }

    override fun disconnected(connection: Connection) {
        val clientIP = connection.remoteAddressTCP.hostString
        println("${clientIP} disconnected")
    }
}