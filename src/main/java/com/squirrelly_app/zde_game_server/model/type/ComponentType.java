package com.squirrelly_app.zde_game_server.model.type;

public enum ComponentType {

    SYS, // System / Generic: Used for Upgrade System and Exploit Action Cards
    CPU, // Central Processing Unit: Used for Code Cards
    GPU, // Graphics Processing Unit: Used for Hash Cards
    NIC, // Network Interface Card: Used for Packet Cards
    RAM, // Random Access Memory: Limits the number of Action Cards that can be played
    HDD, // Hard Disk Drive: Limits the number of Exploit Cards that can be stored in hand

}
