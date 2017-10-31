package com.mgcp.endpoints;

/**
 * Dijital kanallar 64 Kbps'lik bir hizmet sunmaktadýr. Bu tür kanallar
 * bulunmuþtur     gövde ve ISDN arayüzlerinde. Genellikle dijitalin bir
 * parçasýdýrlar.     T1, E1, T3 veya E3 arayüzleri gibi çoklayýcýlar. Medya að
 * geçitleri     Bu tür kanallarýn desteklenmesi dijital     Kanal üzerinde
 * alýnan sinyaller, ki bu sinyaller     A-kanunu veya mu-kanunu, örnek baþýna 8
 * bitten oluþan komple set     veya sadece bu bitlerin 7'sini ses paketlerine
 * dönüþtürür. Medya að geçidi     ayrýca bir Að Eriþim Sunucusu (NAS)
 * hizmetini, að geçidi     sesli olarak kodlanmýþ verileri (modem     baðlantý)
 * veya ikili veri (ISDN baðlantýsý)     veri paketleri.
 * 
 * Medya að geçitleri birkaç baðlantý kurabilmelidir    uç nokta ve paket aðlarý
 * arasýnda veya uç nokta    ve diðer uç noktalarý ayný að geçidinde.
 * Kaynaklanan sinyaller    bu baðlantýlardan baðlantýya göre karýþtýrýlacaktýr
 *    "mod", daha sonra bu belgede belirtildiði gibi. Kesin sayý    Bir uç
 * noktanýn desteklediði baðlantýlar,    að geçidi ve aslýnda tahsise göre
 * deðiþebilir    Að geçidi içindeki kaynaklarý.
 * 
 *    Bazý durumlarda, dijital kanallar sinyal göndermek için kullanýlýr. Bu
 *    örneðin SS7 "F" baðlantýlarý veya ISDN "D" kanallarý için durum. medya
 *    bu sinyalizasyon iþlevlerini destekleyen að geçitleri,    ve çaðrý
 * aracýsýna gelen ve gönderen sinyal paketlerini    SIGTRAN çalýþma grubu
 * tarafýndan tanýmlanan "backhaul" prosedürleri.    IETF. Dijital kanallar
 * bazen    kanal iliþkili sinyalizasyon, örneðin "MF R2". Medya að geçitleri
 *    bu sinyalizasyon fonksiyonlarýný destekleyerek    örneðin "kýrpma" veya
 * "A" gibi ilgili sinyaller,    içinde tanýmlanan olay iþaretleme ve raporlama
 * usullerine göre    MGCP.
 * 
 * =DS0
 * 
 * @author orhan
 *
 */
public class DigitalChannel implements BaseEndpoint {

	public static void main(String[] args) {
		StringBuilder ön = new StringBuilder();
		StringBuilder arka = new StringBuilder();

		int sayfaSayisi = 0;
		for (int i = 1; i < 211; i = i + 4) {
			ön.append("," + i + "-" + (i + 1));
			arka.append("," + (i + 2) + "-" + (i + 3));
			sayfaSayisi++;
		}

		System.out.println("ÖN: " + ön.toString());
		System.out.println("\nARKA: " + arka.toString());
		System.out.println("\nsayfa: " + sayfaSayisi);
	}

}
