package com.mgcp.endpoints;

/**
 * Ýnteraktif Sesli Yanýt (IVR) uç nokta,     IVR hizmeti. Çaðrý Aracýsý'ndan
 * gelen istekler doðrultusunda, IVR sunucusu     Duyurularý ve sesleri "oynat"
 * ve yanýtlarýný "dinle"     kullanýcýdan DTMF giriþi veya sesli mesaj olarak.
 * Gelen talepler     Çaðrý Temsilcisi olay sinyalizasyonunu ve raporlamayý
 * izleyecektir.     MGCP'de tanýmlanan prosedürler.
 * 
 * Belirli bir IVR son noktasýnýn birden fazla desteklemesi beklenmiyor     bir
 * seferde baðlantý. Birkaç baðlantý kurulmuþsa     Ayný bitiþ noktasýnda, ayný
 * tonlar ve duyurular oynanýr     tüm baðlantýlarý ayný anda.
 * 
 * @author orhan
 *
 */
public class IVRAccessPoint implements BaseEndpoint {

}
