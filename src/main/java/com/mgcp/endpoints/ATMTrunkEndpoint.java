package com.mgcp.endpoints;

/**
 * ATM "bagaj tarafý" uç noktalarý tipik olarak bir veya birkaç     ATM sürekli
 * sanal devreleri,     Anahtarlarý birbirine baðlayan klasik "TDM" gövdeleri.
 * ATM / AAL2 kullanýldýðýnda,     Birkaç gövde veya kanal, tek bir sanal    
 * devre; Bu gövdelerin her biri tek bir son noktaya karþýlýk gelir.
 * 
 * Media gateways should be able to establish several connections between the
 * endpoint and the packet networks, or between the endpoint and other endpoints
 * in the same gateway. The signals originating from these connections shall be
 * mixed according to the connection "mode", as specified later in this
 * document. The precise number of connections that an endpoint supports is a
 * characteristic of the gateway, and may in fact vary according to the
 * allocation of resources within the gateway.
 * 
 * @author orhan
 *
 */
public class ATMTrunkEndpoint implements BaseEndpoint {

}
