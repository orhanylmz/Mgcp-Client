package com.mgcp.endpoints;

/**
 * Duyuru sunucusu bir duyuru sonuna, bir duyuruya eriþim saðlar hizmet. Çaðrý
 * Aracýsý'nýn isteði üzerine, duyuru sunucusu Belli bir duyuru "oynayacak".
 * Çaðrý talepleri Temsilci olay iþaretleme ve raporlama prosedürlerini takip
 * edecektir. MGCP'de tanýmlanmýþtýr.
 * 
 * Belli bir duyuru bitiþ noktasýndan daha fazlasýný desteklemesi
 * beklenmemektedir.     bir seferde bir baðlantý. Birkaç baðlantý kurulmuþsa
 *     Ayný son nokta, daha sonra ayný duyurular oynanýr     tüm baðlantýlarý
 * ayný anda.
 * 
 *     Duyuru sunucusuna baðlantýlar genellikle bir yönden veya "yarý    
 * dubleks "- duyuru sunucusunun     ses sinyallerini baðlayýn.
 * 
 * @author orhan
 *
 */
public class AnnouncementServerAccessPoint implements BaseEndpoint {

}
