# WeatherPatterns

**Turin Polytechnic University in Tashkent (TTPU)** — *Design of Software Systems* fanidan amaliy mashg'ulot (Lecture 10: Architectural Design Patterns).

Loyihaning maqsadi — bitta oddiy ob-havo (weather) funksiyasini **to'rtta turli arxitektura patterni** (MVC, MVP, MVVM, MVI) orqali alohida-alohida amalga oshirish va ularni bir-biri bilan solishtirish.

## Texnologiyalar

- **Til:** Kotlin
- **UI:** Jetpack Compose, Material 3
- **Package nomi:** `uz.ttpu.weatherpatterns`
- **Minimum SDK:** API 24
- **Target SDK / Compile SDK:** 37

## Loyiha tuzilishi

```
uz.ttpu.weatherpatterns
├── MainActivity.kt          — launcher ekrani (4 ta tugma: MVC / MVP / MVVM / MVI)
├── data/                    — barcha patternlar uchun umumiy data qatlami
│   ├── api                  — WeatherApi, FakeWeatherApi (soxta/simulyatsiya qilingan tarmoq)
│   ├── model                — Weather (data class)
│   └── repository           — WeatherRepository
├── di/                      — WeatherModule (repository va ViewModel factory'lar)
├── ui/                      — WeatherScreen (umumiy Composable ekran)
├── mvc/                     — Model-View-Controller implementatsiyasi
├── mvp/                     — Model-View-Presenter implementatsiyasi
├── mvvm/                    — Model-View-ViewModel implementatsiyasi
└── mvi/                     — Model-View-Intent implementatsiyasi
```

Barcha to'rtta pattern **bitta umumiy data qatlamidan** (`WeatherRepository`) foydalanadi — shunday qilib ular adolatli taqqoslanadi, chunki ularning farqi faqat **presentation (taqdimot) qatlamida**.

## Nima qilinadi

Har bir ekranda bitta status matni va 4 ta shahar tugmasi bor: **Tashkent, Samarkand, Bukhara, Atlantis**.

- Tashkent, Samarkand, Bukhara — mavjud shaharlar, ob-havo ma'lumotini qaytaradi.
- Atlantis — mavjud bo'lmagan shahar, ataylab **xato (error)** holatini sinash uchun qo'shilgan.

Tarmoq chaqiruvi `FakeWeatherApi` orqali simulyatsiya qilinadi (haqiqiy internet kerak emas, sun'iy kechikish — 3 soniya).

## Patternlar haqida qisqacha

| Pattern | Mantiq qayerda | Testlanishi | Burilishdan (rotation) nima omon qoladi |
|---|---|---|---|
| **MVC** | `WeatherController` | Oddiy JUnit, qo'shimcha kutubxonasiz | Hech narsa |
| **MVP** | `WeatherPresenter` | Oddiy JUnit, soxta (fake) View bilan | Hech narsa |
| **MVVM** | `WeatherViewModel` (StateFlow) | `kotlinx-coroutines-test` kerak | Holat va ishlayotgan so'rov |
| **MVI** | `WeatherMviViewModel` (bitta immutable state) | `kotlinx-coroutines-test` kerak | Holat va ishlayotgan so'rov |

## Testlarni ishga tushirish

Har bir modulning `src/test` papkasida unit testlar bor (`WeatherRepositoryTest`, `WeatherPresenterTest`, `WeatherMviViewModelTest` va h.k.). Ularni Android Studio'da gutter'dagi yashil ▶ strelka orqali yoki quyidagi buyruq bilan ishga tushirish mumkin:

```
./gradlew test
```

## Ishga tushirish

1. Loyihani Android Studio'da oching.
2. Gradle sinxronlashuvi tugashini kuting.
3. Emulator yoki haqiqiy qurilmani tanlang (Min SDK 24+).
4. `app` konfiguratsiyasini ▶ (Run) tugmasi orqali ishga tushiring.
5. Launcher ekranidan kerakli patternni tanlang va shaharlardan birini bosing.

## Muallif

TTPU, *Design of Software Systems* fani, amaliy mashg'ulot (Lecture 10) doirasida bajarilgan.
