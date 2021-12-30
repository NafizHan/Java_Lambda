package JavaLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Lambda02 {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>(Arrays.asList(12, -3, 65, 3, 7, 34, 22, -60, 42, 15));

		ciftKareMax(list);
		System.out.println("");
		System.out.println("    ******     ");
		ToplaEl1(list);
		System.out.println("");
		System.out.println("    ******     ");
		ToplaEl2(list);
		System.out.println("");
		System.out.println("    ******     ");
		carpCiftEl1(list);
		System.out.println("");
		System.out.println("    ******     ");
		carpCiftEl2(list);
		System.out.println("");
		System.out.println("    ******     ");
		min1(list);
		min2(list);
		min3(list);
		min4(list);
		System.out.println("");
		System.out.println("    ******     ");
		onbestenBykKckTekSayi(list);
		System.out.println("");
		System.out.println("    ******     ");
		ciftKareKckByk(list);
		System.out.println("");
		System.out.println("    ******     ");
		tekKareBykKck(list);
	
	}
	
//List'in cift olan  elemanların karelerini aliniz ve en büğünü yazdiriniz

	public static void ciftKareMax(List<Integer> list) {
//	Optional <Integer> maxEl=list.stream().filter(Lambda01::ciftbul).map(t->t*t).reduce(Integer::max);
		Optional<Integer> maxEl = list.stream().filter(Lambda01::ciftbul).map(t -> t * t).reduce(Math::max);
		// bu task için math ve Integer classlarından max deger alınır. fakat math class
		// dada spesifik oldogo için tercih edilmelidir.
		// ve daha hızlı çalışır
		// reduce return edilen eleman null yada int den büyük olma ihitmali için java
		// güvenlik olarak handle eder ve optinal
		System.out.println(maxEl);

	}

//listeki tüm elemanların toplamını yazdiriniz//Lambda expression olsun

	public static void ToplaEl1(List<Integer> list) {

		int toplam = list.stream().reduce(0, (x, y) -> x + y);
		// toplama oldugu için baslangıçta bi 0 degeri atamak zorundayız.x her zaman ilk
		// degerini atanan degerden(0'dan) alır.
		// y her zaman degerini Stream 'den (akıştan) alır
		// bu yüzden de optional a gerek duyulmaz.
		System.out.println(toplam);
//System.out.println(list.stream().reduce(0,(x,y)->x+y));
	}

	// listeki tüm elemanların toplamını yazdiriniz//Method references olsun

	public static void ToplaEl2(List<Integer> list) {
		// Optional<Integer> toplam = list.stream().reduce(Integer::sum);
		Optional<Integer> toplam = list.stream().reduce(Math::addExact);

		System.out.println(toplam);
	}

//listteki çift elemanların çarpımını yazdiriniz//MethodPrefences
	public static void carpCiftEl1(List<Integer> list) {

		Optional<Integer> carp = list.stream().filter(Lambda01::ciftbul).reduce(Math::multiplyExact);
		System.out.println(carp);
	}

	// listteki çift elemanların çarpımını yazdiriniz//Lambda expressions

	public static void carpCiftEl2(List<Integer> list) {

		Integer carp = list.stream().filter(Lambda01::ciftbul).reduce(1, (x, y) -> (x * y));
//pozitif değer alınız
		Integer carpPoz = list.stream().filter(Lambda01::ciftbul).reduce(-1, (x, y) -> (x * y));

		System.out.println(carp);
		System.out.println(carpPoz);
	}
//listeki elemanlardan en kücüğünü 4 farklı yontem  ile yazdırınız

	public static void min1(List<Integer> list) {// INTEGER method references//1.yöntem

		Optional<Integer> min = list.stream().reduce(Integer::min);
		System.out.println(min);
	}

	public static void min2(List<Integer> list) {// MATH method references//2.yöntem

		Optional<Integer> min = list.stream().reduce(Math::min);
		System.out.println(min);

	}

	public static int minBul(int x, int y) {// kendimiz min bulan bi method yazdık//3.yöntem

		return x < y ? x : y;// ternary
	}

	public static void min3(List<Integer> list) {// HALUK class method references

		Optional<Integer> min = list.stream().reduce(Lambda02::minBul);
		System.out.println(min);

	}

	public static void min4(List<Integer> list) {// 4. yöntem Lambda expression

		Integer min = list.stream().reduce(Integer.MAX_VALUE, (x, y) -> x < y ? x : y);
		System.out.println(min);

	}
	// 15 den büyük en kücük tek sati yazdirinizz.

	public static void onbestenBykKckTekSayi(List<Integer> list) {

//list.stream().filter(t-> t%2 ==1 & t > 15).reduce(Integer::min)

		System.out.println(list.stream().filter(t -> t % 2 == 1 & t > 15).reduce(Integer::min));

	}

//listin çift  elemanlarinin kareleri  kücükten büyüğe yazdiriniz 
public static void ciftKareKckByk(List<Integer> list) {
	
	list.stream().filter(Lambda01::ciftbul).map(t->t*t).sorted().forEach(Lambda01::printEl);
}

//listin tek elemanlarının karelerini büyükten kücüğe sıralayınız
public static void tekKareBykKck(List<Integer> list) {
	
	list.stream().filter(t-> t%2==1).map(t->t*t).sorted(Comparator.reverseOrder()).forEach(Lambda01::printEl);

}

}
