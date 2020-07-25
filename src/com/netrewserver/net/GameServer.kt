package com.netrewserver.net

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.esotericsoftware.kryonet.Server
import com.netrewserver.net.requests.ChatMessage

class GameServer : Listener() {
    val server = Server()

    init {
        server.kryo.register(ChatMessage::class.java)
        server.start()
        server.bind(13370, 13371)
        server.addListener(this)
    }

    override fun connected(connection: Connection) {
        val clientIP = connection.remoteAddressTCP.hostString
        println("${clientIP} connected")
    }

    override fun received(connection: Connection, obj: Any) {
        if (obj is ChatMessage) {
            connection.sendTCP(obj)
        }
    }

    override fun disconnected(connection: Connection) {
    }
}