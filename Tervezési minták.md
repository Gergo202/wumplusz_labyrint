MVC, mint modell-nézet-vezérlő minta és néhány másik tervezési minták:

Modell:
    Az alkalmazás által kezelt információk tartomány-specifikus ábrázolása. A tartománylogika jelentést ad a puszta adatnak.
    Sok alkalmazás használ állandó tároló eljárásokat adatok tárolásához. Az MNV nem említi külön az adatelérési réteget, mert ezt beleérti a modellbe.

Nézet:
    Megjeleníti a modellt egy megfelelő alakban, mely alkalmas a felhasználói interakcióra, jellmezően egy felhasználói felületi elem képében.
    Különböző célokra különböző nézetel létezhetnek ugyanahhoz a modellhez.

Vezérlő:
    Az eseményeket, jellemzően felhasználói műveleteket dolgozza fel és válaszol rájuk, illetve a modellben történő változásokat is kiválthat.

Az MNV gyakran látható webalkalmazásokban, ahol a nézet az aktuális HTML oldal, a vezérlő pedig a kód, ami összagyűjti a dinamikus adatokat és létrehozza a HTML-ben a tartalmat. 
Végül a modellt a tartalom képvisali, ami általában adatbázisban vagy XML állományokban van tárolva.

Habár az MNV-nek sok értelmezése létezik, a vezérlés menete általánosságban a következőképp müködik:
	
	1. A felhasználó valamilyen hatást gyakorol a felhasználói felületre.
	2. A vezértlő átveszi a bejövő eseményt a felhasználói felülettől, gyakran egy bejegyzett eseménykezelő vagy visszahívás útján.
	3. A vezérlő kapcsolatot teremt a modelel, esetleg frissíti azt a felhasználó tevékenységének megfelelő módon.
	   Az összetett vezérlőket gyakran alakítják ki az utasítás mintának megfelelően, a műveletek egységbezárásáért és a bővítés egyszerűsítéséért.
	4. A nézet a modell alapján megfelelő felhasználói felületet hoz létre.
	   A nézet a modellből nyeri az adatait. A modellnek nincs közvetlen tudomása a nézetről.
	5. A felhasználói felület újabb eseményre vár, mely az elejéről kezdi a kört.

A modell és a nézet kettéválasztásával az MNV csökkenti a szerkezeti bonyolultságot, és megnöveli a rugalmasságot és a felhasználhatóságot.

Szolgáltatás:
	A vezérlő és a modell közötti réteg. A modelltől kér le adatokat és a vezértlőnek adja azt. Ennek a rétegnek a segítségével az adattárolás,
	adatlekérés és adatkezelés elkülöníthetőek egymástől. Mivel eza  réteg nem része az eredeti MNV mintának, ezért használata nem kötelező.

Előnyök:
	- Egyidejű fejlesztés
	- Magas szintű összetartás
	- Függetlenség
	- Könnyen változtatható
	- Több nézet egy modellhez
	- Tesztelheetőség
Hátrányok:
	- Kód olvashatósága
	- Sok boilerplate kód
	- Nehezebben tanulható

Tervezési minták egy OO programozási nyelvben:

Fogalma Christopher Alexander építész ötlete nyomán született meg. Az építészetben újra és újra felbukkanó mintákat keresett, amelyek a jól megépített házakat jellemzik.
A minták a létrehozó építészek sok éves tapasztalata miatt szebb, jobb vagy használhatóbb házakat eredményeztek, mintha a tervezőnek csupán saját erejére támaszkodva kellett volna megterveznie azokat.

A programtervezési minták a GoF definíciója szerint: egymással együttműködő objektumok és osztályok leírásai, amelyek testre szabott formában valamilyen általános tervezési problémát oldanak meg egy
bizonyos összefüggésben. A szoftvertervezésben egy-egy problémára végtelen sok különböző megoldás adható, azonban ezek között kevés optimális van.

Elönyei:
	- lerövidítik a tapasztalatszerzési időt
	- lerövidítik a trervezési időt
	- közös szótárat ad a fejlesztők kezébe
	- magasabb szintű programozást tesz lehetővé

A tábla-adat kapu elkülöníti az adatok elhozását és felhasználását egy kapu objektum segítségével. A kapu felhasználói függetlenek az adatok tárolásának módjától.

A programtervezási minták nem az egyetlen, a programozáshoz kapcsolható minták. Általában háromféle csoportosítást szoktak alkalmazni:
	- Menedzsmenti minták: folyamatok és emberek kezelésének szintje
	- Architekturális: rétegek, erőforrások szintje
	- Tervezési: kódolási gyakorlatok, osztályok, objektumok szintje

Ezen kívül szokták használni a programozási minták kifejezést azokra a programtervezési mintáknál kisebb mintákra, amelyek egy objektum belsejének részleteivel vagy egy elemeinek használatával foglalkoznak.
A dor-adat kapőu egy tervminta, amiben egy objektum egy adatbázis sor számára szolgál kapuként.

A tervezési minták leírása több szakaszból áll. Ezek közül a legfontosabbak a szerkezet, a résztvevők és az együttműködés. Ezek írják le magát a mikroszerkezetet, amit alkalmazni lehet bizonyos problémák megoldására.
A mikroszerkezet a program egyes egységeiből, mint osztályok és metódusok, és kapcsolataiból áll.

Vannak törekvések arra, hogy konkrétabb, bizonyos célokra szolgáló mintákat találjanak. Ide tartoznak a felhasználói interfészek, az információmegjelenítés, a biztonságos tervezés,
a biztonságos használat, Web design és az üzleti modell terb.

A tervezési minták a modulokat és kapcsolataikat szervezik. Alacsonyabb szintűek, mint az architekturális minták, amelyek a teljes rendszer általános felépítését jellemzik.

Több különböző tervminta létezik:
	- Algoritmus stratégia minták
	- Számítástervezési minták
	- Végrehajtési minták
	- Implementációs stratégia minták
	- Szerkezeti tervezési minták

A tervminták Gammáék eredetileg három kategóriába csopportosították: létrehozási minták, szerkezeti minták, és viselkedési minták, amihez használták a delegálás, az aggregálás, és a konzultáció fogalmát.
Objektumorientált környezetben az öröklődés, polimorfizmus és az absztrakt ősosztály fogalmait is felhasználják, habár az absztrakt osztályok többnyire interfészek is lehetnek.
Egyes szerzők elkülönítik az architekturális tervezési mintákat is, mint a modell-nézet-vezérlő.

Gammáék leírása ezek között van:
	- Név, osztályozás: Azonosításra alkalmas, leíró név
	- Cél: miért használjuk a mintát, mi célt szolgál
	- Más nevek: alternatív elnevezések
	- Motiváció: egy kontextusba ágyazott probléma, amit a tervmintával lehet megoldani
	- Alkalmazhatóság: milyen körülmények között használahtó a minta
	- Szerkezet: a minta szerkezetének grafikus megjelenítése osztály- és interakciós diagramokkal
	- Résztvevők: a mintában szereplő osztályok és objektumok leírása, szerepük meghatározása
	- Együttműködés: az interakciók leírása
	- Következmények: az eredmények, mellékhatások, előnyök és hátrányok kifejtése
	- Implementáció: az implementáció bemutatása
	- Példa kód: a tervminta megvalósításának példája egy programozási nyelven
	- Ismert használatok: a tervminta használata valós környezetben
	- Rokon minták: más, hasonló, kapcsolódó minták, és a tárgyalt minta összehasonlítása velük